package net.iquesoft.project.iQueCommerce.domain.provider;

import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;

import java.util.List;

import rx.Observable;

public interface RepositoryProvider {

    Observable<List<String>> getCountries();

    Observable<List<String>> getProvinces(String country);

    Observable<UserModel> getCustomerDetails(Long customerId);

    Observable saveCustomerDetails(Long userId, String first_name, String last_name, String email,
                                   String phoneNumber, String billingAddress, String billingCity,
                                   String billingPostalCode, String shippingAddress,
                                   String shippingCity, String shippingPostalCode,
                                   String billingCountry, String billingProvince,
                                   String shippingCountry, String shippingProvince);

    Observable saveProductToWishList(Long productId);

    Observable removeProductFromWishList(Long productId);

    Observable<Boolean> isProductInWishList(Long productId);

    Observable<List<Long>> getProductsIdFromWishList();

    UserModel getCustomerUserModel(Long userId);
}
