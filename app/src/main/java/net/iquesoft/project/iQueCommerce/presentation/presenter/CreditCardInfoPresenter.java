package net.iquesoft.project.iQueCommerce.presentation.presenter;

import com.shopify.buy.model.CreditCard;

import net.iquesoft.project.iQueCommerce.domain.interactor.CompleteCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.StoreCreditCardUseCase;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CreditCardInfoFragment;

import javax.inject.Inject;


public class CreditCardInfoPresenter implements Presenter {


    private final StoreCreditCardUseCase storeCreditCardUseCase;
    private final CompleteCheckoutUseCase completeCheckoutUseCase;
    private CreditCardInfoFragment fragmentView;

    @Inject
    public CreditCardInfoPresenter(StoreCreditCardUseCase storeCreditCardUseCase,
                                   CompleteCheckoutUseCase completeCheckoutUseCase) {
        this.storeCreditCardUseCase = storeCreditCardUseCase;
        this.completeCheckoutUseCase = completeCheckoutUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setView(CreditCardInfoFragment view) {
        this.fragmentView = view;

    }

    public void initialize() {

    }

    public void proceed(String credit_card_number,
                        String first_name,
                        String last_name,
                        String expiration_month,
                        String expiration_year,
                        String verification_number) {
        this.storeCreditCardUseCase.setCreditCard(this.getCreditCard(credit_card_number, first_name, last_name, expiration_month, expiration_year, verification_number));
        this.storeCreditCardUseCase.execute(new StoreCreditCardSubscriber());
    }

    private CreditCard getCreditCard(String credit_card_number, String first_name, String last_name, String expiration_month, String expiration_year, String verification_number) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(credit_card_number);
        creditCard.setFirstName(first_name);
        creditCard.setLastName(last_name);
        creditCard.setMonth(expiration_month);
        creditCard.setYear(expiration_year);
        creditCard.setVerificationValue(verification_number);
        return creditCard;
    }

    private void completeCheckout() {
        this.completeCheckoutUseCase.execute(new CompleteCheckoutSubscriber());
    }

    private class StoreCreditCardSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CreditCardInfoPresenter.this.fragmentView.hideLoading();
            CreditCardInfoPresenter.this.completeCheckout();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CreditCardInfoPresenter.this.fragmentView.hideLoading();
            CreditCardInfoPresenter.this.fragmentView.showError(e.getMessage());
        }
    }

    private class CompleteCheckoutSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CreditCardInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CreditCardInfoPresenter.this.fragmentView.hideLoading();
            CreditCardInfoPresenter.this.fragmentView.showError(e.getMessage());
        }
    }
}
