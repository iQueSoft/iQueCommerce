package net.iquesoft.project.iQueCommerce.presentation.view.interfaces;

import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;

import java.util.Collection;


public interface CategoriesGridView extends LoadDataView {

    void onItemClicked(CategoryModel categoryModel);

    /**
     * Load downloaded content into view.
     *
     * @param modelCollection Downloaded content
     */
    void loadContent(Collection<CategoryModel> modelCollection);
}
