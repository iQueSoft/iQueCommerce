package net.iquesoft.project.iQueCommerce.data.providers;

import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ShopDataProvider implements ShopProvider {

    private final ShopifyManager shopifyManager;

    @Inject
    public ShopDataProvider(ShopifyManager shopifyManager) {
        this.shopifyManager = shopifyManager;
    }


    @Override
    public Observable<Shop> shop() {
        return this.shopifyManager.getShop();
    }

    @Override
    public Observable<List<Collection>> collection(int pageNumber) {
        return this.shopifyManager.getCollections(pageNumber);
    }

    @Override
    public Observable<Collection> collection(String handle) {
        return this.shopifyManager.getCollection(handle);
    }

    @Override
    public Observable<List<com.shopify.buy.model.Product>> products(int pageNumber) {
        return this.shopifyManager.getProducts(pageNumber);
    }

    @Override
    public Observable<List<com.shopify.buy.model.Product>> products(int pageNumber, long collectionId) {
        return this.shopifyManager.getProducts(pageNumber, collectionId);
    }

    @Override
    public Observable<List<com.shopify.buy.model.Product>> products(List<Long> ids) {
        return this.shopifyManager.getProducts(ids);
    }

    @Override
    public Observable<Product> product(String handle) {
        return this.shopifyManager.getProduct(handle);
    }

    @Override
    public Observable createCheckout() {
        return this.shopifyManager.createCheckout();
    }

    @Override
    public Observable setProductQuantity(Product productModel, int productVariant, Integer quantity) {
        return this.shopifyManager.setProductQuantity(productModel, productVariant, quantity);
    }

}
