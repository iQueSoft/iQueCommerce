package net.iquesoft.project.iQueCommerce.data.net;

import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Customer;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.data.executor.JobExecutor;
import net.iquesoft.project.iQueCommerce.tools.ConstantsTest;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ShopifyManagerTest extends ShopifyManager {

    private ShopTest shopTest;
    private CustomerTest customerTest;

    public ShopifyManagerTest(JobExecutor jobExecutor) {
        super(jobExecutor);
        shopTest = new ShopTest();
        customerTest = new CustomerTest();
    }

    @Override
    public Observable<Shop> getShop() {
        return Observable.create(subscriber -> {
            subscriber.onNext(shopTest);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Customer> signUp(String email, String password, String firstName, String lastName) {
        return Observable.create(subscriber -> {
            subscriber.onNext(customerTest);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<Customer> logIn(String email, String password) {
        return Observable.create(subscriber -> {
            subscriber.onNext(customerTest);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Collection>> getCollections(int pageNumber) {
        return Observable.create(subscriber -> {
            if (pageNumber == 1) {
                subscriber.onNext(this.getListOfCollections());
            }
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Product>> getProducts(int pageNumber, long collectionId) {
        return Observable.create(subscriber -> {
            if (pageNumber > 1) {
                subscriber.onNext(null);
                subscriber.onCompleted();
                return;
            }
            subscriber.onNext(this.getListOfProducts());
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Product>> getProducts(List<Long> ids) {
        return Observable.create(subscriber -> {
            subscriber.onNext(this.getListWithOneProduct());
            subscriber.onCompleted();
        });
    }

    private List<Collection> getListOfCollections() {
        List<Collection> list = new ArrayList<>(ConstantsTest.COLLECTION_TITLES.length);
        for (int i = 0; i < ConstantsTest.COLLECTION_TITLES.length; i++) {
            list.add(new CollectionTest(i, ConstantsTest.COLLECTION_TITLES[i], ConstantsTest.COLLECTION_IMAGES[i]));
        }
        return list;
    }

    public List<Product> getListOfProducts() {
        List<Product> list = new ArrayList<>(ConstantsTest.NOTEBOOK_TITLE.length);
        for (int i = 0; i < ConstantsTest.NOTEBOOK_TITLE.length - 1; i++) {
            list.add(new ProductTest(i, ConstantsTest.NOTEBOOK_TITLE[i], ConstantsTest.NOTEBOOK_PRICES[i], ConstantsTest.NOTEBOOK_DESCRIPTIONS[i], ConstantsTest.NOTEBOOK_IMAGES[i]));
        }
        return list;
    }

    public List<Product> getListWithOneProduct() {
        List<Product> list = new ArrayList<>(ConstantsTest.NOTEBOOK_TITLE.length);
        list.add(new ProductTest(0, ConstantsTest.NOTEBOOK_TITLE[0], ConstantsTest.NOTEBOOK_PRICES[0], ConstantsTest.NOTEBOOK_DESCRIPTIONS[0], ConstantsTest.NOTEBOOK_IMAGES[0]));
        return list;
    }
}