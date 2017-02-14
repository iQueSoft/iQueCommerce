package net.iquesoft.project.iQueCommerce.data.providers;

import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class RepositoryDataProvider implements RepositoryProvider {

    private final JSONCountriesReader jsonCountriesReader;
    private final RealmDataBase realmDataBase;

    @Inject
    public RepositoryDataProvider(JSONCountriesReader jsonCountriesReader, RealmDataBase realmDataBase) {
        this.jsonCountriesReader = jsonCountriesReader;
        this.realmDataBase = realmDataBase;
    }

    @Override
    public Observable<List<String>> getCountries() {
        return this.jsonCountriesReader.getCountries();
    }

    @Override
    public Observable<List<String>> getProvinces(String country) {
        return this.jsonCountriesReader.getProvinces(country);
    }

    @Override
    public Observable<UserModel> getCustomerDetails(Long customerId) {
        return this.realmDataBase.getCustomerDetails(customerId);
    }

    @Override
    public UserModel getCustomerUserModel(Long userId) {
        return this.realmDataBase.getCustomerUserModel(userId);
    }

    @Override
    public Observable saveCustomerDetails(Long userId, String first_name, String last_name, String email,
                                          String phoneNumber, String billingAddress, String billingCity,
                                          String billingPostalCode, String shippingAddress, String shippingCity,
                                          String shippingPostalCode, String billingCountry,
                                          String billingProvince, String shippingCountry, String shippingProvince) {
        return this.realmDataBase.saveCustomerDetails(userId, first_name, last_name, email,
                phoneNumber, billingAddress, billingCity,
                billingPostalCode, shippingAddress, shippingCity,
                shippingPostalCode, billingCountry, billingProvince,
                shippingCountry, shippingProvince);
    }

    @Override
    public Observable saveProductToWishList(Long productId) {
        return this.realmDataBase.saveProductToWishList(productId);
    }

    @Override
    public Observable removeProductFromWishList(Long productId) {
        return this.realmDataBase.removeProductFromWishList(productId);
    }

    @Override
    public Observable<Boolean> isProductInWishList(Long productId) {
        return this.realmDataBase.isProductInWishList(productId);
    }

    @Override
    public Observable<List<Long>> getProductsIdFromWishList() {
        return this.realmDataBase.getProductsIdFromWishList();
    }

}
