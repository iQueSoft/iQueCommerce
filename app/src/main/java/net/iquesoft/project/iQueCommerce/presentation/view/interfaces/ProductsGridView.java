package net.iquesoft.project.iQueCommerce.presentation.view.interfaces;

import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;

import java.util.Collection;

public interface ProductsGridView extends LoadDataView {

    void onItemClicked(ProductModel productModel);

    /**
     * Load downloaded content into view.
     *
     * @param modelCollection Downloaded content
     * @param currency Money format
     */
    void loadContent(Collection<ProductModel> modelCollection, String currency);
}
