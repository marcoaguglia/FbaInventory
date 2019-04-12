package model;

import DAO.Product_DAO;
import utility.Is_winner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String sku;
    private String fullfillmentChannelSku;
    private String condition;
    private BigDecimal price;
    private BigDecimal lowest_price;
    private BigDecimal total_Fee;
    private String country;
    private String asin;
    private String giacenza;
    private BigDecimal buyBox_Price;
    private Is_winner buybox_winner;
    private BigDecimal fba_Fee;
    //  private boolean is_prime;
    private String img_url;
    private int vendite;

    public static List<Product> findSku_forPricing() {
        return Product_DAO.getInstance().findSku_forPricing();
    }

    public static ArrayList<String[]> findProduct() {
        return Product_DAO.getInstance().findInventoryProduct();
    }


    public static void update_Is_Winner_Status(Product product) {
        Product_DAO.getInstance()._Is_Winner_Status(product);
    }

    public static void insertSkus(String sku, String asin) {
        Product_DAO.getInstance().insertSku(sku, asin);
    }

    public static void insertProductInventory(Product p) {
        Product_DAO.getInstance().insertProductInventory(p);
    }


    public static void insertPrice(Product p) {
        Product_DAO.getInstance().insertPrice(p);
    }


    // private String posizione;


    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public BigDecimal getBuyBox_Price() {
        return buyBox_Price;
    }

    public void setBuyBox_Price(BigDecimal buyBox_Price) {
        this.buyBox_Price = buyBox_Price;
    }

    public BigDecimal getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(BigDecimal lowest_price) {
        this.lowest_price = lowest_price;
    }

    public Is_winner isBuybox_winner() {
        return buybox_winner;
    }

    public void setBuybox_winner(Is_winner buybox_winner) {
        this.buybox_winner = buybox_winner;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFullfillmentChannelSku() {
        return fullfillmentChannelSku;
    }

    public void setFullfillmentChannelSku(String fullfillmentChannelSku) {
        this.fullfillmentChannelSku = fullfillmentChannelSku;
    }

    /*public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }*/

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(String giacenza) {
        this.giacenza = giacenza;
    }

    public static void insertAttribute(Product p) {
        Product_DAO.getInstance().insertAttribute(p);
    }

    public static Product findInventoryProduct(String sku, String country, String giacenza) {
        return Product_DAO.getInstance().findInventoryProduct(sku, country, giacenza);
    }

    public static Product findTempProduct(int i) {
        return Product_DAO.getInstance().findTempProduct(i);
    }

    public static void updateBuyBoxPrice(String asin, BigDecimal buybox_price, String marketplace) {
        Product_DAO.getInstance().updateBuyBoxPrice(asin, buybox_price, marketplace);
    }

    public static Product findTempProduct_for_Sku(String sku, String country) {

        return Product_DAO.getInstance().findTempProduct_for_Sku(sku, country);
    }

    public static Product findPrice(String sku, String country) {
        return Product_DAO.getInstance().findPrice(sku, country);
    }

    public static Product findSku(int i) {
        return Product_DAO.getInstance().findSku(i);
    }

    public int getVendite() {
        return vendite;
    }

    public void setVendite(int vendite) {
        this.vendite = vendite;
    }

    public BigDecimal getTotal_Fee() {
        return total_Fee;
    }

    public void setTotal_Fee(BigDecimal total_Fee) {
        this.total_Fee = total_Fee;
    }

    public BigDecimal getFba_Fee() {
        return fba_Fee;
    }

    public void setFba_Fee(BigDecimal fba_Fee) {
        this.fba_Fee = fba_Fee;
    }


}
