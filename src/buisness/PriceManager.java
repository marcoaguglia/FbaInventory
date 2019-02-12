package buisness;

import Db_connection.Db_connection;
import amazon.product.*;
import model.Inventory;
import model.Product;
import utility.Is_winner;

import java.io.IOException;
import java.math.BigDecimal;

/*******************************************
 * PriceManagerClass
 * -Scan FBA_temp, for every item find:
 * -Price, BuyBoxPrice, LowestPrice.
 ******************************************/

public class PriceManager {
    // Get name Operative System for right console command
    private final static String os = System.getProperty("os.name");

    /*******************************************
     * SetPrice method
     * -Scan price, for every item find:
     ******************************************/
    public static void setPrice() {
        int fba_Temp_Size = Inventory.findSizeInventoryTemp();

        //InsertProduct,PriceError counter
        int product_inserted = 0;
        int error_price = 0;

        //Scan every item of Fba_temp
        for (int i = 0; i < fba_Temp_Size; i++) {
            //Print Status of process
            /***************************************************************************/
            System.out.println("\n\tConfronto FBA_temp con FBA_status" +
                    "\n\n\n\tCaricamento:\t" + (i * 100) / fba_Temp_Size + "%" +
                    "\n\n\n\tElemento: " + i + " di " + fba_Temp_Size + "" +
                    "\n\n\n\t" + product_inserted + " Elementi aggiunti in FBA_status" +
                    "\n\n\n\t" + error_price + " Prezzi non trovati");
            /*******************************************************************/
            //Get i-item from FBA_temp
            Product productTemp = Product.findTempProduct(i);
            //Search and get if available a clone productTemp
            Product productStatus = Product.findStatusProduct(productTemp.getSku(), productTemp.getCountry(), productTemp.getGiacenza());

            // if clone exist in product status, jump insert
            if (productStatus == null || !(productStatus.getGiacenza().equals(productTemp.getGiacenza()))) {
                // search my price, buybox price, lowest price
                BigDecimal price = GetMyPriceForSKUSample.getMyPriceForSKUSample(productTemp.getSku(), productTemp.getCountry());
                BigDecimal buybox_price = GetCompetitivePricingForSKUSample.getCompetitivePricing(productTemp);
                BigDecimal lowest_price = GetLowestOfferListingsForSKUSample.getLowestOfferListing(productTemp);

                //if don't found my price set at 0.00 else set price
                if (price == null) {
                    price = BigDecimal.valueOf(0.00);
                    error_price++;
                    productTemp.setPrice(BigDecimal.valueOf(0.00));
                } else {
                    productTemp.setPrice(price);
                }

                // if (productStatus == null) {
                // GetLowestPricedOffersForSKUSample.getLowestPricedOffersForSKUSample(productTemp);

                //if don't found buybox price set at 0.00 else set buybox price
                if (buybox_price == null) {
                    buybox_price = BigDecimal.valueOf(0.00);
                    productTemp.setBuyBox_Price(BigDecimal.valueOf(0.00));
                } else {
                    productTemp.setBuyBox_Price(buybox_price);
                }
                //if don't found lowest price set at 0.00 else set lowest price
                if (lowest_price == null) {
                    lowest_price = BigDecimal.valueOf(0.0);

                } else {
                    productTemp.setLowest_price(lowest_price);
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
                    productTemp.setBuybox_winner(Is_winner.UNKNOWN);
                if (buybox_price.compareTo(lowest_price) < 0)
                    productTemp.setBuybox_winner(Is_winner.TRUE);
                else if (price.compareTo(buybox_price) > 0)
                    productTemp.setBuybox_winner(Is_winner.FALSE);

                //Se lowest_price non trova prezzi, la nostra è l'unica offerta
                if (lowest_price.equals(BigDecimal.valueOf(0.00))) {
                    productTemp.setBuybox_winner(Is_winner.TRUE);
                    productTemp.setLowest_price(productTemp.getPrice());
                }
                if (buybox_price.equals(BigDecimal.valueOf(0.00)))
                    productTemp.setBuybox_winner(Is_winner.FALSE);

                if (productTemp.isBuybox_winner() == Is_winner.UNKNOWN) {

                    GetLowestPricedOffersForSKUSample.getLowestPricedOffersForSKUSample(productTemp);
                }
                GetMatchingProductForIdSample.getMatchingProductForId(productTemp);

                //}

                //Insert in FBA_status
                Product.insertProduct(productTemp);
                //incremet counter
                product_inserted++;
            }

            //Clean console
            /**********************************************************************************/
            if (os.contains("Windows")) {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Runtime.getRuntime().exec("clear");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /**********************************************************************************/
        }
        //Truncate FBA_temp
        Inventory.truncateTempTable();

        /****************************************************************************************************************
         * String[] marketplace = {"APJ6JRA9NG5V4", "A1RKKUPIHCS9HS", "A1PA6795UKMFR9", "A1F83G8C2ARO7P", "A13V1IB3VIYZZH"};
         *
         *        for (int i = 0; i < marketplace.length; i++) {
         *       nextToken = null;
         *      List<SelectionRecommendation> fulfillmentrecomendation;
         *     do {
         *    Sleep.sleepSecond(1);
         *if (nextToken == null || nextToken.equals("")) {
         *ListRecommendationsResponse responseRecommmendatins = ListRecommendationsSample.listRaccomandation(marketplace[i]);
         *ListRecommendationsResult getRecommendationListResult = responseRecommmendatins.getListRecommendationsResult();
         *nextToken = getRecommendationListResult.getNextToken();
         *fulfillmentrecomendation = getRecommendationListResult.getSelectionRecommendations();
         *
         *
         *        } else {
         *       ListRecommendationsByNextTokenResponse responseRecommmendatinsByNextToken = ListRecommendationsByNextTokenSample.listRecommendationNextToken(nextToken);
         *      ListRecommendationsByNextTokenResult recommendationNextTokenResult = responseRecommmendatinsByNextToken.getListRecommendationsByNextTokenResult();
         *     nextToken = recommendationNextTokenResult.getNextToken();
         *    fulfillmentrecomendation = recommendationNextTokenResult.getSelectionRecommendations();
         *   }
         *  int size = fulfillmentrecomendation.size();
         *
         *        for(int x=0; x<size;x++){
         *       try {
         *      BigDecimal buybox_price = fulfillmentrecomendation.get(x).getBuyboxPrice().getAmount();
         *     String asin = fulfillmentrecomendation.get(x).getItemIdentifier().getAsin();
         *    Product.updateBuyBoxPrice(asin, buybox_price,marketplace[i]);
         *   }catch (NullPointerException ignore){}
         *
         *        }
         *
         *        }while(nextToken!=null);
         *
         *
         *
         *        }
         *****************************************************************************************************/
        //close mySql connection
        Db_connection.getInstance().disconnetti();

    }
}


