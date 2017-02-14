package net.iquesoft.project.iQueCommerce.domain.provider;

import com.shopify.buy.model.Address;
import com.shopify.buy.model.CreditCard;
import com.shopify.buy.model.Customer;
import com.shopify.buy.model.CustomerToken;
import com.shopify.buy.model.GiftCard;
import com.shopify.buy.model.ShippingRate;

import rx.Observable;


public interface CustomerProvider {

    Observable<Customer> logIn(String email, String password);

    Observable<Customer> signUp(String email, String password, String firstName, String lastName);

    Observable<Void> logOut();

    Observable updateCheckoutWithData(String email, Address billingAddress, Address shippingAddress, ShippingRate shippingRate, String discountCode, GiftCard giftCard);

    Observable storeCreditCard(CreditCard creditCard);

    Observable completeCheckout();

    Observable<CustomerToken> getCustomerToken();

    Observable renewCustomerToken();
}
