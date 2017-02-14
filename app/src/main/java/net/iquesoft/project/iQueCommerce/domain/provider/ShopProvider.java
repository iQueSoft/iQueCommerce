package net.iquesoft.project.iQueCommerce.domain.provider;

import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.Shop;

import java.util.List;

import rx.Observable;

public interface ShopProvider {

    /**
     * Get an {@link rx.Observable} which will emit a {@link Shop}.
     */
    Observable<Shop> shop();


    /**
     * Get an {@link rx.Observable} which will emit a {@link List<Collection>}.
     *
     * @param pageNumber number of the paginated list
     */
    Observable<List<Collection>> collection(int pageNumber);


    /**
     * Get an {@link rx.Observable} which will emit a {@link Collection}.
     *
     * @param handle name of the handle
     */
    Observable<Collection> collection(String handle);


    /**
     * Get an {@link rx.Observable} which will emit a {@link List<Product>}.
     *
     * @param pageNumber number of the paginated list
     **/
    Observable<List<Product>> products(int pageNumber);


    /**
     * Get an {@link rx.Observable} which will emit a {@link List<Product>}.
     *  @param pageNumber   number of the paginated list
     * @param collectionId collection ID
     **/
    Observable<List<Product>> products(int pageNumber, long collectionId);


    /**
     * Get an {@link rx.Observable} which will emit a {@link List<Product>}.
     *
     * @param ids list of products IDs
     **/
    Observable<List<Product>> products(List<Long> ids);


    /**
     * Get an {@link rx.Observable} which will emit a {@link Product}.
     *
     * @param handle name of the handle
     **/
    Observable<Product> product(String handle);


    Observable createCheckout();

    Observable setProductQuantity(Product productModel, int productVariant, Integer quantity);

}
