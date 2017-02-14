package net.iquesoft.project.iQueCommerce.presentation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CartModel {

    private List<ProductModel> offeredProducts = new ArrayList<>();
    private HashMap<Long, Integer> quantityList;
    private float totalPrice;
    private int itemCount;

    public CartModel() {
        this.quantityList = new HashMap<>();
    }

    public List<ProductModel> getOfferedProducts() {
        return offeredProducts;
    }

    public void setOfferedProducts(List<ProductModel> offeredProducts) {
        this.offeredProducts = offeredProducts;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ProductModel productModel :
                offeredProducts
                ) {
            stringBuilder.append(productModel.toString());
        }
        return stringBuilder.toString();
    }

    public HashMap<Long, Integer> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(HashMap<Long, Integer> quantityList) {
        this.quantityList = quantityList;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
