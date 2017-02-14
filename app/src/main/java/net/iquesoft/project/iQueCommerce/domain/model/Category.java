package net.iquesoft.project.iQueCommerce.domain.model;

import android.graphics.Bitmap;

import java.util.List;


public class Category {
    private String categoryID;
    private int numberOfSubcategories;
    private List<Integer> IDsOfSubcategories;
    private String categoryTitle;
    private Bitmap categoryImage;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getNumberOfSubcategories() {
        return numberOfSubcategories;
    }

    public void setNumberOfSubcategories(int numberOfSubcategories) {
        this.numberOfSubcategories = numberOfSubcategories;
    }

    public List<Integer> getIDsOfSubcategories() {
        return IDsOfSubcategories;
    }

    public void setIDsOfSubcategories(List<Integer> IDsOfSubcategories) {
        this.IDsOfSubcategories = IDsOfSubcategories;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Bitmap getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Bitmap categoryImage) {
        this.categoryImage = categoryImage;
    }

    @Override
    public String toString() {
        return "***** Category Details *****\n" +
                "id=" + this.getCategoryID() + "\n" +
                "title=" + this.getCategoryTitle() + "\n" +
                "*******************************";
    }
}
