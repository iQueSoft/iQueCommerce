package net.iquesoft.project.iQueCommerce.presentation.view.interfaces;

import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;

import java.util.HashMap;
import java.util.List;


public interface CartListView extends LoadDataView {

    void showContent(List<ProductModel> productModelList, HashMap<Long, Integer> quantityList, float totalPrice, String currency);
}
