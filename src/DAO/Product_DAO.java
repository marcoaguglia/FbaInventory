package DAO;

import Db_connection.Db_connection;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
            //      product.setCondition(d[3]);
            product.setGiacenza(d[5]);
            product.setCountry(d[4]);
            return product;
        }

    }


    public Product findTempProduct_for_Sku(String sku, String country) {

        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM FBA_temp WHERE sku='" + sku + "' and country='" + country + "';");
        if (result.size() == 0) return null;
        else {
            Product product = new Product();
            String[] d = result.get(0);
            product.setSku(d[0]);
            product.setFullfillmentChannelSku(d[1]);
            product.setAsin(d[2]);
            //      product.setCondition(d[3]);
            product.setGiacenza(d[5]);
            product.setCountry(d[4]);
            return product;
        }

    }

    public void insertSku(String sku, String asin) {
        Db_connection.getInstance().eseguiAggiornamento("INSERT IGNORE INTO `Skus`(`sku`,`asin`) " + "VALUES ('" + sku + "','" + asin + "')");

    }

    public void insertProductInventory(Product p) {
        Db_connection.getInstance().eseguiAggiornamento("INSERT INTO `FBA_inventory`(`Sku`,`fulfillmenChannelsku`,`asin`,`country`,`quantityLocalFulfillment`,`data`) " +
                "VALUES ('" + p.getSku() + "','" + p.getFullfillmentChannelSku() + "','" + p.getAsin() + "', '" + p.getCountry() + "','" + p.getGiacenza() + "',NOW())");
    }

    public void insertPrice(Product p) {
        Db_connection.getInstance().eseguiAggiornamento("INSERT INTO `Prezzi`(`sku`,`country`,`price`,`buybox`,`lowest`,`priority`,`total_fee`,`fba_fee`) " +
                "VALUES ('" + p.getSku() + "','" + p.getCountry() + "','" + p.getPrice() + "','" + p.getBuyBox_Price() + "','" + p.getLowest_price() + "',NOW(),'" + p.getTotal_Fee() + "','" + p.getFba_Fee() + "') " +
                "ON DUPLICATE KEY UPDATE " +
                "priority=NOW()" +
                ",price='" + p.getPrice() + "'" +
                ",buybox='" + p.getBuyBox_Price() + "'" +
                ",total_fee='" + p.getTotal_Fee() + "'" +
                ",fba_fee='" + p.getFba_Fee() + "'" +
                ",lowest='" + p.getLowest_price() + "'");

    }


    public void insertAttribute(Product p) {
        String url = "<img src =\"" + p.getImg_url() + "\" width=\"150px\" height=\"150px\"></img> ";

        Db_connection.getInstance().eseguiAggiornamento("INSERT INTO `Attributi`(`sku`,`country`,`is_winner`,`img_url`) " +
                "VALUES ('" + p.getSku() + "','" + p.getCountry() + "','" + p.isBuybox_winner() + "','" + url + "')" +
                "ON DUPLICATE KEY UPDATE " +
                "is_winner='" + p.isBuybox_winner() + "'," +
                "img_url='" + url + "'");
    }

    public Product findInventoryProduct(String sku, String country, String giacenza) {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM FBA_inventory WHERE sku='" + sku + "'and country='" + country + "'and " +
                "quantityLocalFulfillment='" + giacenza + "' order by data desc;");
        if (result.size() == 0) return null;
        else {
            Product product = new Product();
            String[] d = result.get(0);
            product.setSku(d[0]);
            product.setFullfillmentChannelSku(d[1]);
            product.setAsin(d[2]);
            //   product.setCondition(d[3]);
            product.setGiacenza(d[4]);
            product.setCountry(d[3]);
            return product;
        }
    }

    public void updateBuyBoxPrice(String asin, BigDecimal buybox_price, String marketplace) {

        Db_connection.getInstance().eseguiAggiornamento("UPDATE FBA_inventory SET buybox_landed = '" + buybox_price + "' WHERE asin='" + asin + "' and country='" + marketplace + "';");

    }

    public void _Is_Winner_Status(Product product) {
        Db_connection.getInstance().eseguiAggiornamento("UPDATE FBA_inventory SET is_winner = '" + product.isBuybox_winner() + "' WHERE asin='" + product.getAsin() + "' and country='" + product.getCountry() + "';");
    }

    public List<Product> findSku_forPricing() {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT sku,asin, priority FROM " +
                "(SELECT Skus.sku,Skus.asin, Prezzi.priority FROM fba2019.Skus left join fba2019.Prezzi ON Skus.sku=Prezzi.sku) s " +
                "group by sku order by priority asc;");


        if (result.size() == 0) return null;
        else {
            List<Product> products = new ArrayList<>();
            for (String[] d : result) {
                Product p = new Product();
                p.setSku(d[0]);
                p.setAsin(d[1]);
                products.add(p);
            }


            return products;
        }

    }

    public Product findPrice(String sku, String country) {

        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM Prezzi WHERE sku='" + sku + "'and country='" + country + "';");
        if (result.size() == 0) return null;
        else {
            Product product = new Product();
            String[] d = result.get(0);
            product.setSku(d[0]);
            product.setCountry(d[4]);
            return product;
        }
    }

    public Product findSku(int i) {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM Skus order by priority asc");

        if (result.size() == 0) return null;
        else {
            String[] d = result.get(i);
            Product product = new Product();
            product.setSku(d[0]);
            product.setAsin(d[1]);
            return product;
        }
    }


    public ArrayList<String[]> findInventoryProduct() {
        ArrayList<String[]> result = Db_connection.getInstance().eseguiQuery("SELECT * FROM FBA_inventory group by sku, country ;");

        if (result.size() == 0) return null;
        else {
            return result;
        }
    }



}
