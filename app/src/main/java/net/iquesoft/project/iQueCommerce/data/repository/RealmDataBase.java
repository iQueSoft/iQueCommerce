package net.iquesoft.project.iQueCommerce.data.repository;

import net.iquesoft.project.iQueCommerce.presentation.AndroidApplication;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.model.WishProduct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;


public class RealmDataBase {

    private static final long SCHEMA_VERSION = 0;
    private final Realm realm;
    private UserModel userModel;

    @Inject
    public RealmDataBase(AndroidApplication application) {
        RealmConfiguration config = new RealmConfiguration.Builder(application)
                .schemaVersion(SCHEMA_VERSION) // Must be bumped when the schema changes
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        this.realm = Realm.getDefaultInstance();
        this.realm.executeTransaction(realm1 -> userModel = new UserModel());
    }

    public Observable<UserModel> getCustomerDetails(Long customerId) {
        return Observable.create(subscriber -> {
            userModel = this.realm.where(UserModel.class).equalTo("userId", customerId).findFirst();
            subscriber.onNext(userModel);
            subscriber.onCompleted();
        });
    }


    public UserModel getCustomerUserModel(Long customerId) {
        return this.realm.where(UserModel.class).equalTo("userId", customerId).findFirst();
    }

    public Observable saveCustomerDetails(Long userId, String first_name, String last_name, String email,
                                          String phoneNumber, String billingAddress, String billingCity,
                                          String billingPostalCode, String shippingAddress, String shippingCity,
                                          String shippingPostalCode, String billingCountry, String billingProvince, String shippingCountry, String shippingProvince) {
        return Observable.create(subscriber -> {
            this.realm.beginTransaction();
            userModel.setUserId(userId);
            userModel.setFirstName(first_name);
            userModel.setLastName(last_name);
            userModel.setUserEmail(email);
            userModel.setPhoneNumber(phoneNumber);
            userModel.setBillingAddress(billingAddress);
            userModel.setBillingCity(billingCity);
            userModel.setBillingPostal(billingPostalCode);
            userModel.setShippingAddress(shippingAddress);
            userModel.setShippingCity(shippingCity);
            userModel.setShippingPostal(shippingPostalCode);
            userModel.setBillingCountry(billingCountry);
            userModel.setShippingCountry(shippingCountry);
            userModel.setBillingProvince(billingProvince);
            userModel.setShippingProvince(shippingProvince);
            this.realm.copyToRealmOrUpdate(userModel);
            subscriber.onCompleted();
            this.realm.commitTransaction();
        });
    }

    public Observable saveProductToWishList(Long productId) {
        return Observable.create(subscriber -> {
            this.realm.executeTransaction(realm1 -> {
                WishProduct wishProduct = realm1.createObject(WishProduct.class);
                wishProduct.setProductId(productId);
            });
        });
    }

    public Observable<Boolean> isProductInWishList(Long productId) {
        return Observable.create(subscriber -> {
            WishProduct wishProduct = this.realm.where(WishProduct.class).equalTo("productId", productId).findFirst();
            subscriber.onNext(wishProduct != null);
        });
    }

    public Observable removeProductFromWishList(Long productId) {
        return Observable.create(subscriber -> {
            this.realm.executeTransaction(realm1 -> {
                WishProduct wishProduct = this.realm.where(WishProduct.class).equalTo("productId", productId).findFirst();
                wishProduct.deleteFromRealm();
            });
        });
    }

    public Observable<List<Long>> getProductsIdFromWishList() {
        return Observable.create(subscriber -> {
            RealmResults<WishProduct> realmResults = this.realm.where(WishProduct.class).findAll();
            List<Long> productIds = new ArrayList<>(realmResults.size());
            for (WishProduct wishProduct : realmResults) {
                productIds.add(wishProduct.getProductId());
            }
            subscriber.onNext(productIds);
            subscriber.onCompleted();
        });
    }

}
