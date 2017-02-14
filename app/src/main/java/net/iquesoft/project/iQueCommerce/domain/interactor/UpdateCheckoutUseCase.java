package net.iquesoft.project.iQueCommerce.domain.interactor;

import com.shopify.buy.model.Address;
import com.shopify.buy.model.GiftCard;
import com.shopify.buy.model.ShippingRate;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class UpdateCheckoutUseCase extends UseCase {
    private final CustomerProvider customerProvider;
    private String email;
    private Address billingAddress;
    private Address shippingAddress;
    private ShippingRate shippingRate;
    private String discountCode;
    private GiftCard giftCard;

    @Inject
    public UpdateCheckoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    public void setArguments(String email, Address billingAddress, Address shippingAddress, ShippingRate shippingRate, String discountCode, GiftCard giftCard) {
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.shippingRate = shippingRate;
        this.discountCode = discountCode;
        this.giftCard = giftCard;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.updateCheckoutWithData(email, billingAddress, shippingAddress, shippingRate, discountCode, giftCard);
    }

}
