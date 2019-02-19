package buisness;

import Db_connection.Db_connection;
import amazon.product.*;
import model.Inventory;
import model.Product;
import utility.Is_winner;

import java.math.BigDecimal;

/*******************************************
 * PriceManagerClass
 * -Scan FBA_temp, for every item find:
 * -Price, BuyBoxPrice, LowestPrice.
 ******************************************/

public class PriceManager {
    // Get name Operative System for right console command
    private final static String[] marketplace = {"IT", "DE", "ES", "FR", "GB"};
    public static boolean quota_over = true;

    public static boolean isQuota_over() {
        return quota_over;
    }

    public static void setQuota_over(boolean quota_over) {
        PriceManager.quota_over = quota_over;
    }

    /*******************************************
     * SetPrice method
     * -Scan price, for every item find:
     ******************************************/
    public static void setPrice() {

        int fba_Skus_Size = Inventory.findSizeSkus();

        //InsertProduct,PriceError counter
        int product_inserted = 0;
        int error_price = 0;
        int count_max_hour_quota = 0;

        //Scan every item of Fba_temp
        for (int i = 0; i < fba_Skus_Size; i++) {

            if (product_inserted > 1450)
                quota_over = false;
            //Print Status of process
            /***************************************************************************/
            System.out.println("Caricamento: " + (i * 100) / (fba_Skus_Size) + "%" +
                    "\tElemento: " + i * marketplace.length + " : " + fba_Skus_Size * marketplace.length +
                    "\t" + product_inserted + " Aggiunti" +
                    "\t" + error_price + " Prezzi non trovati");
            /*******************************************************************/

            //Get i-item from FBA_temp
            Product product = Product.findSku_forPricing(i);
            //Search and get if available a clone product
            for (String country : marketplace) {

                product.setCountry(country);

                BigDecimal price = GetMyPriceForSKUSample.getMyPriceForSKUSample(product.getSku(), country);
                BigDecimal buybox_price = GetCompetitivePricingForSKUSample.getCompetitivePricing(product);
                BigDecimal lowest_price = GetLowestOfferListingsForSKUSample.getLowestOfferListing(product);

                //if don't found my price set at 0.00 else set price
                if (price == null) {
                    price = BigDecimal.valueOf(0.00);
                    error_price++;
                    product.setPrice(BigDecimal.valueOf(0.00));
                } else {
                    product.setPrice(price);
                }
                // if (productStatus == null) {
                // GetLowestPricedOffersForSKUSample.getLowestPricedOffersForSKUSample(product);

                //if don't found buybox price set at 0.00 else set buybox price
                if (buybox_price == null) {
                    buybox_price = BigDecimal.valueOf(0.00);
                    product.setBuyBox_Price(BigDecimal.valueOf(0.00));
                } else {
                    product.setBuyBox_Price(buybox_price);
                }
                //if don't found lowest price set at 0.00 else set lowest price
                if (lowest_price == null) {
                    lowest_price = BigDecimal.valueOf(0.0);

                } else {
                    product.setLowest_price(lowest_price);
                }

                /*******************************************
                 * LowestPrice restituisce il prezzo più basso
                 * escluso il nostro, quindi se il nostro è
                 * più basso ed è uguale al buyBox price allora
                 * siamo noi in buybox.
                 * Inoltre se il nostro prezzo è maggiore del BuyBox
                 * sicuramente non abbiamo la buyBox.
                 ******************************************/
                if (price.compareTo(buybox_price) == 0)
                    product.setBuybox_winner(Is_winner.UNKNOWN);
                if (buybox_price.compareTo(lowest_price) < 0)
                    product.setBuybox_winner(Is_winner.TRUE);
                else if (price.compareTo(buybox_price) > 0)
                    product.setBuybox_winner(Is_winner.FALSE);

                //Se lowest_price non trova prezzi, la nostra è l'unica offerta
                if (lowest_price.equals(BigDecimal.valueOf(0.00))) {
                    product.setBuybox_winner(Is_winner.TRUE);
                    product.setLowest_price(product.getPrice());
                }
                if (buybox_price.equals(BigDecimal.valueOf(0.00)))
                    product.setBuybox_winner(Is_winner.FALSE);

                if (product.isBuybox_winner() == Is_winner.UNKNOWN && quota_over) {
                    count_max_hour_quota++;
                    GetLowestPricedOffersForSKUSample.getLowestPricedOffersForSKUSample(product);
                }
                if (!product.getAsin().isEmpty())
                    GetMatchingProductForIdSample.getMatchingProductForId(product);

                /*******************************************
                 *FEES
                 ******************************************/
                GetMyFeesEstimateSample.getMyFeesEstimate(product);
                /*******************************************
                 *Insert to mysql
                 ******************************************/
                //Insert in FBA_status
                Product.insertPrice(product);
                Product.insertAttribute(product);
                //incremet counter
                product_inserted++;

                //Clean console

            }


        }
        //close mySql connection
        Db_connection.getInstance().disconnetti();

    }
}


