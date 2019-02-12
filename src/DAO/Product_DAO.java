package DAO;

import Db_connection.Db_connection;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Product_DAO {

    public static Product_DAO instance;

    public static synchronized Product_DAO getInstance() {
        if (instance == null)
            instance = new Product_DAO();
        return instance;
    }

    public Product findTempProduct(int i) {


        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM FBA_temp");
        if (result.size() == 0) return null;
        else {
            Product product = new Product();
            String[] d = result.get(i);
            product.setSku(d[0]);
            product.setFullfillmentChannelSku(d[1]);
            product.setAsin(d[2]);
            product.setCondition(d[3]);
            product.setGiacenza(d[5]);
            product.setCountry(d[4]);
            return product;
        }

    }

    public void insertProduct(Product p) {
        String url = "<img src =\"" + p.getImg_url() + "\" width=\"150px\" height=\"150px\"></img> ";
        Db_connection.getInstance().eseguiAggiornamento("INSERT INTO `FBA_status`(`Sku`,`fulfillmenChannelsku`,`asin`,`conditionType`,`country`,`quantityLocalFulfillment`,`data`,`price`,`buybox_landed`,`is_winner`,`lowest_price`,`img_url`) " +
                "VALUES ('" + p.getSku() + "','" + p.getFullfillmentChannelSku() + "','" + p.getAsin() + "','" + p.getCondition() + "','" + p.getCountry() + "','" + p.getGiacenza() + "',NOW(),'" + p.getPrice() + "','" + p.getBuyBox_Price() + "','" + p.isBuybox_winner() + "','" + p.getLowest_price() + "','" + url + "')");
    }

    public Product findStatusProduct(String sku, String country, String giacenza) {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM FBA_status WHERE sku='" + sku + "'and country='" + country + "'and " +
                "quantityLocalFulfillment='" + giacenza + "' order by data desc;");
        if (result.size() == 0) return null;
        else {
            Product product = new Product();
            String[] d = result.get(0);
            product.setSku(d[0]);
            product.setFullfillmentChannelSku(d[1]);
            product.setAsin(d[2]);
            product.setCondition(d[3]);
            product.setGiacenza(d[5]);
            product.setCountry(d[4]);
            return product;
        }
    }

    public void updateBuyBoxPrice(String asin, BigDecimal buybox_price, String marketplace) {

        Db_connection.getInstance().eseguiAggiornamento("UPDATE FBA_status SET buybox_landed = '" + buybox_price + "' WHERE asin='" + asin + "' and country='" + marketplace + "';");

    }

    public void _Is_Winner_Status(Product product) {
        Db_connection.getInstance().eseguiAggiornamento("UPDATE FBA_status SET is_winner = '" + product.isBuybox_winner() + "' WHERE asin='" + product.getAsin() + "' and country='" + product.getCountry() + "';");
    }
}
