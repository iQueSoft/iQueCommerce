package net.iquesoft.project.iQueCommerce.data.providers;

import com.shopify.buy.model.Address;
import com.shopify.buy.model.CreditCard;
import com.shopify.buy.model.Customer;
import com.shopify.buy.model.CustomerToken;
import com.shopify.buy.model.GiftCard;
import com.shopify.buy.model.ShippingRate;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


@Singleton
public class CustomerDataProvider implements CustomerProvider {

    private final ShopifyManager shopifyManager;

    @Inject
    public CustomerDataProvider(ShopifyManager shopifyManager) {
        this.shopifyManager = shopifyManager;
    }


    @Override
    public Observable<Customer> logIn(String email, String password) {
        return this.shopifyManager.logIn(email, password);
    }

    @Override
    public Observable<Customer> signUp(String email, String password, String firstName, String lastName) {
        return this.shopifyManager.signUp(email, password, firstName, lastName);
    }

    @Override
    public Observable<Void> logOut() {
        return this.shopifyManager.logOut();
    }

    @Override
    public Observable updateCheckoutWithData(String email, Address billingAddress, Address shippingAddress, ShippingRate shippingRate, String discountCode, GiftCard giftCard) {
        return this.shopifyManager.updateCheckoutWithData(email, billingAddress, shippingAddress, shippingRate, discountCode, giftCard);
    }

    @Override
    public Observable storeCreditCard(CreditCard creditCard) {
        return this.shopifyManager.storeCreditCard(creditCard);
    }

    @Override
    public Observable completeCheckout() {
        return this.shopifyManager.completeCheckout();
    }


    @Override
    public Observable<CustomerToken> getCustomerToken() {
        return this.shopifyManager.getCustomerToken();
    }

    @Override
    public Observable renewCustomerToken() {
        return this.shopifyManager.renewCustomer();
    }

}
