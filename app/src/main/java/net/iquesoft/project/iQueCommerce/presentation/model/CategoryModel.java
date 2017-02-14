package net.iquesoft.project.iQueCommerce.presentation.model;

import java.util.Date;


public class CategoryModel {


    private String title;
    private String htmlDescription;
    private String handle;
    private boolean published;
    private Long categoryId;
    private Date createdAtDate;
    private Date updatedAtDate;
    private Date publishedAtDate;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Date getPublishedAtDate() {
        return publishedAtDate;
    }

    public void setPublishedAtDate(Date publishedAtDate) {
        this.publishedAtDate = publishedAtDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "***** CategoryModel Details *****\n" +
                "id=" + categoryId + "\n" +
                "title=" + title + "\n" +
                "htmlDescription=" + htmlDescription + "\n" +
                "handle=" + handle + "\n" +
                "published=" + published + "\n" +
                "createdAtDate=" + createdAtDate + "\n" +
                "updatedAtDate=" + updatedAtDate + "\n" +
                "publishedAtDate=" + publishedAtDate + "\n";
    }
}
