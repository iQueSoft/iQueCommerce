package net.iquesoft.project.iQueCommerce.presentation.model;

import com.shopify.buy.model.Image;
import com.shopify.buy.model.Option;
import com.shopify.buy.model.ProductVariant;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProductModel {


    private Long productId;
    private String title;
    private String handle;
    private String bodyHtml;
    private Date publishedAtDate;
    private Date createdAtDate;
    private Date updatedAtDate;
    private String vendor;
    private String productType;
    private List<ProductVariant> variants;
    private List<Image> images;
    private List<Option> options;
    private Set<String> tags;
    private boolean available;
    private boolean published;
    private Set<String> prices;
    private String minimumPrice;

    @Override
    public String toString() {
        return "Product id " + productId + "\n" +
                " price " + prices + "\n"
                ;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public Date getPublishedAtDate() {
        return publishedAtDate;
    }

    public void setPublishedAtDate(Date publishedAtDate) {
        this.publishedAtDate = publishedAtDate;
    }

    public Date getCreatedAtDate() {
        return createdAtDate;
    }

    public void setCreatedAtDate(Date createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public Date getUpdatedAtDate() {
        return updatedAtDate;
    }

    public void setUpdatedAtDate(Date updatedAtDate) {
        this.updatedAtDate = updatedAtDate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Set<String> getPrices() {
        return prices;
    }

    public void setPrices(Set<String> prices) {
        this.prices = prices;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }


}
