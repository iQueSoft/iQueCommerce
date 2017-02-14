/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iquesoft.project.iQueCommerce.presentation.mapper;


import com.shopify.buy.model.Customer;
import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ModelDataMapper {

    @Inject
    UserModel userModel;
    @Inject
    ShopModel shopModel;

    @Inject
    public ModelDataMapper() {
    }

    public void transformCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        userModel.setFirstName(customer.getFirstName());
        userModel.setLastName(customer.getLastName());
        userModel.setUserEmail(customer.getEmail());
        userModel.setUserAddresses(customer.getAddresses());
        userModel.setUserDefaultAddress(customer.getDefaultAddress());
        userModel.setUserId(customer.getId());
        userModel.setUserOrdersCount(customer.getOrdersCount());
        userModel.setUserTotalSpent(customer.getTotalSpent());
    }

    public void transformShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        shopModel.setId(shop.getId());
        shopModel.setName(shop.getName());
        shopModel.setCity(shop.getCity());
        shopModel.setProvince(shop.getProvince());
        shopModel.setCountry(shop.getCountry());
        shopModel.setContactEmail(shop.getContactEmail());
        shopModel.setCurrency(shop.getCurrency());
        shopModel.setDomain(shop.getDomain());
        shopModel.setUrl(shop.getUrl());
        shopModel.setMyShopifyDomain(shop.getMyshopifyDomain());
        shopModel.setDescription(shop.getDescription());
        shopModel.setShipsToCountries(shop.getShipsToCountries());
        shopModel.setMoneyFormat(shop.getMoneyFormat());
        shopModel.setPublishedProductsCount(shop.getPublishedProductsCount());
        LogUtil.makeLog(shopModel.toString());
    }

    public ProductModel transformProduct(com.shopify.buy.model.Product product) {
        ProductModel productModel = new ProductModel();
        if (product == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        productModel.setProductId(product.getProductId());
        productModel.setTitle(product.getTitle());
        productModel.setHandle(product.getHandle());
        productModel.setBodyHtml(product.getBodyHtml());
        productModel.setPublishedAtDate(product.getPublishedAtDate());
        productModel.setCreatedAtDate(product.getCreatedAtDate());
        productModel.setUpdatedAtDate(product.getUpdatedAtDate());
        productModel.setVendor(product.getVendor());
        productModel.setProductType(product.getProductType());
        productModel.setVariants(product.getVariants());
        productModel.setImages(product.getImages());
        productModel.setOptions(product.getOptions());
        productModel.setTags(product.getTags());
        productModel.setAvailable(product.isAvailable());
        productModel.setPublished(product.isPublished());
        productModel.setPrices(product.getPrices());
        productModel.setMinimumPrice(product.getMinimumPrice());
        return productModel;
    }


    public Collection<ProductModel> transformProductCollection(List<com.shopify.buy.model.Product> productCollection) {
        Collection<ProductModel> productModelCollection;
        List<String> titles = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<String> images = new ArrayList<>();

        if (productCollection != null && !productCollection.isEmpty()) {
            productModelCollection = new ArrayList<>();
            for (com.shopify.buy.model.Product product : productCollection) {
                productModelCollection.add(transformProduct(product));
                titles.add(product.getTitle());
                prices.add(product.getMinimumPrice());
                description.add(product.getBodyHtml());
                images.add(product.getImages().get(0).getSrc());
            }
        } else {
            productModelCollection = Collections.emptyList();
        }

        LogUtil.makeLog(titles);
        LogUtil.makeLog(prices);
        LogUtil.makeLog(description);
        LogUtil.makeLog(images);

        return productModelCollection;
    }


    public CategoryModel transformCategory(com.shopify.buy.model.Collection category) {
        CategoryModel categoryModel = new CategoryModel();
        if (category == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        categoryModel.setTitle(category.getTitle());
        categoryModel.setHtmlDescription(category.getHtmlDescription());
        categoryModel.setHandle(category.getHandle());
        categoryModel.setPublished(category.isPublished());
        categoryModel.setCategoryId(category.getCollectionId());
        categoryModel.setCreatedAtDate(category.getCreatedAtDate());
        categoryModel.setUpdatedAtDate(category.getUpdatedAtDate());
        categoryModel.setPublishedAtDate(category.getPublishedAtDate());
        categoryModel.setImageUrl(category.getImageUrl());
        return categoryModel;
    }

    public Collection<CategoryModel> transformCategoriesCollection(List<com.shopify.buy.model.Collection> categoriesCollection) {
        ArrayList<CategoryModel> categoryModelsCollection = new ArrayList<>();
        CategoryModel categoryModel = null;
        for (com.shopify.buy.model.Collection collection : categoriesCollection) {
            categoryModel = transformCategory(collection);
            categoryModelsCollection.add(categoryModel);
        }
        return categoryModelsCollection;
    }

}
