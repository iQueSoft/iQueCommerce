package net.iquesoft.project.iQueCommerce.data.entity;


public class CategoriesEntity {
    private String categoryTitle;
    private String categoryId;

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "***** CategoryEntity Details *****\n" +
                "id=" + this.getCategoryId() + "\n" +
                "title=" + this.getCategoryTitle() + "\n" +
                "*******************************";
    }
}
