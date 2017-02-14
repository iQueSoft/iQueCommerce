package net.iquesoft.project.iQueCommerce.data.entity.mapper;


import net.iquesoft.project.iQueCommerce.data.entity.CategoriesEntity;
import net.iquesoft.project.iQueCommerce.data.entity.ProductsEntity;
import net.iquesoft.project.iQueCommerce.domain.model.Category;
import net.iquesoft.project.iQueCommerce.domain.model.Product;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link ProductsEntity} (in the data layer)
 * to {@link net.iquesoft.project.iQueCommerce.domain.model.Product} in the
 * domain layer.
 */
@PerActivity
public class ProductEntityMapper {

    @Inject
    public ProductEntityMapper() {
    }

    public Product transform(ProductsEntity productsEntity) {
        Product product = null;
        if (productsEntity != null) {
            product = new Product();
            product.setItemId(productsEntity.getItemId());
            product.setCurrencyID(productsEntity.getCurrencyID());
            product.setCategoryID(productsEntity.getCategoryID());
            product.setPrice(productsEntity.getPrice());
            product.setTitle(productsEntity.getTitle());
            product.setAvailable(productsEntity.isAvailable());
            product.setShopUrl(productsEntity.getShopUrl());
            product.setPictureUrl(productsEntity.getPictureUrl());
            product.setDescription(productsEntity.getDescription());
        }
        return product;
    }

    public List<Product> transform(Collection<ProductsEntity> productsEntityCollection) {
        List<Product> productList = new ArrayList<>(productsEntityCollection.size());
        Product product;
        for (ProductsEntity productsEntity : productsEntityCollection) {
            product = transform(productsEntity);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }


    private Category transform(CategoriesEntity categoriesEntity) {
        Category category = null;
        if (categoriesEntity != null) {
            category = new Category();
            category.setCategoryTitle(categoriesEntity.getCategoryTitle());
            category.setCategoryID(categoriesEntity.getCategoryId());
        }
        return category;
    }


    public List<Category> transformListOfCategories(List<CategoriesEntity> listOfCategories) {
        List<Category> categoryList = new ArrayList<>(listOfCategories.size());
        Category category;
        for (CategoriesEntity categoriesEntity : listOfCategories) {
            category = transform(categoriesEntity);
            if (category != null) {
                categoryList.add(category);
            }
        }
        return categoryList;
    }

}
