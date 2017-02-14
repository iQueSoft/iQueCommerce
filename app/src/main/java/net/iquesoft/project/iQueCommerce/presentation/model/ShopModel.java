package net.iquesoft.project.iQueCommerce.presentation.model;

import java.util.List;

public class ShopModel {

    private String name;
    private String city;
    private String province;
    private String country;
    private String contactEmail;
    private String currency;
    private String domain;
    private String url;
    private String myShopifyDomain;
    private String description;
    private List<String> shipsToCountries;
    private String moneyFormat;
    private long publishedProductsCount;
    private Long id;

    public ShopModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMyShopifyDomain() {
        return myShopifyDomain;
    }

    public void setMyShopifyDomain(String myShopifyDomain) {
        this.myShopifyDomain = myShopifyDomain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getShipsToCountries() {
        return shipsToCountries;
    }

    public void setShipsToCountries(List<String> shipsToCountries) {
        this.shipsToCountries = shipsToCountries;
    }

    public String getMoneyFormat() {
        return moneyFormat.substring(0, 1);
    }

    public void setMoneyFormat(String moneyFormat) {
        this.moneyFormat = moneyFormat;
    }

    public long getPublishedProductsCount() {
        return publishedProductsCount;
    }

    public void setPublishedProductsCount(long publishedProductsCount) {
        this.publishedProductsCount = publishedProductsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Shop Id:" + id + "\n" +
                " name: " + name + "\n" +
                " city: " + city + "\n" +
                " province: " + province + "\n" +
                " country: " + country + "\n" +
                " domain: " + domain + "\n" +
                " currency: " + currency + "\n" +
                " url: " + url + "\n";
    }
}
