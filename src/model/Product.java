package model;

import DAO.Product_DAO;
import utility.Is_winner;

import java.math.BigDecimal;

public class Product {
    private String sku;
    private String fullfillmentChannelSku;
    private String condition;
    private BigDecimal price;
    private BigDecimal lowest_price;
    private String country;
    private String asin;
    private String giacenza;
    private BigDecimal buyBox_Price;
    private Is_winner buybox_winner;
    private boolean is_prime;
    private String img_url;

    public static void update_Is_Winner_Status(Product product) {
        Product_DAO.getInstance()._Is_Winner_Status(product);
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    private String date;
    private String posizione;

    public static Product findTempProduct(int i) {
        return Product_DAO.getInstance().findTempProduct(i);
    }

    public static Product findStatusProduct(String sku, String country, String giacenza) {
        return Product_DAO.getInstance().findStatusProduct(sku, country, giacenza);
    }

    public static void updateBuyBoxPrice(String asin, BigDecimal buybox_price, String marketplace) {
        Product_DAO.getInstance().updateBuyBoxPrice(asin, buybox_price, marketplace);
    }

    public static void insertProduct(Product p) {
        Product_DAO.getInstance().insertProduct(p);
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

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
}
