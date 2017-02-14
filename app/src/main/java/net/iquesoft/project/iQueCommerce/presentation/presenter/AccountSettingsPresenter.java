package net.iquesoft.project.iQueCommerce.presentation.presenter;

import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AccountSettingsFragment;

import javax.inject.Inject;


public class AccountSettingsPresenter implements Presenter {

    private final GetCustomerDetailsUseCase getCustomerDetailsUseCase;
    private final SaveCustomerDetailsUseCase saveCustomerDetailsUseCase;
    private final UserModel userModel;
    private AccountSettingsFragment fragmentView;

    @Inject
    AccountSettingsPresenter(GetCustomerDetailsUseCase getCustomerDetailsUseCase,
                             SaveCustomerDetailsUseCase saveCustomerDetailsUseCase,
                             UserModel userModel) {
        this.getCustomerDetailsUseCase = getCustomerDetailsUseCase;
        this.saveCustomerDetailsUseCase = saveCustomerDetailsUseCase;
        this.userModel = userModel;
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

    public void setView(AccountSettingsFragment view) {
        this.fragmentView = view;
    }

    public void getCustomerDetails() {
        this.getCustomerDetailsUseCase.setArguments(this.userModel.getUserId());
        this.getCustomerDetailsUseCase.executeRealm(new CustomerDetailsSubscriber());
    }

    private void showCustomerDetails(UserModel userModel) {
        this.addToUserModel(userModel);
        this.fragmentView.showUserInfo(userModel);
    }

    private void addToUserModel(UserModel userModel) {
        this.userModel.setShippingAddress(userModel.getShippingAddress());
        this.userModel.setShippingCity(userModel.getShippingCity());
        this.userModel.setShippingPostal(userModel.getShippingPostal());
        this.userModel.setShippingCountry(userModel.getShippingCountry());
        this.userModel.setShippingProvince(userModel.getShippingProvince());
        this.userModel.setBillingAddress(userModel.getBillingAddress());
        this.userModel.setBillingCity(userModel.getBillingCity());
        this.userModel.setBillingPostal(userModel.getBillingPostal());
        this.userModel.setBillingCountry(userModel.getBillingCountry());
        this.userModel.setBillingProvince(userModel.getBillingProvince());
        this.userModel.setPhoneNumber(userModel.getPhoneNumber());
    }

    public String getCustomerFirstName() {
        return this.userModel.getFirstName();
    }

    public String getCustomerLastName() {
        return this.userModel.getLastName();
    }

    public String getCustomerEmail() {
        return this.userModel.getUserEmail();
    }

    public String getCustomerPhoneNumber() {
        return this.userModel.getPhoneNumber();
    }

    public String getShippingAddress() {
        return this.userModel.getShippingAddress();
    }

    public String getShippingCity() {
        return this.userModel.getShippingCity();
    }

    public String getShippingPostalCode() {
        return this.userModel.getShippingPostal();
    }

    public String getBillingAddress() {
        return this.userModel.getBillingAddress();
    }

    public String getBillingCity() {
        return this.userModel.getBillingCity();
    }

    public String getBillingPostalCode() {
        return this.userModel.getBillingPostal();
    }

    public void updateCustomerDetails(String phoneNumber) {
        this.saveCustomerDetailsUseCase.setArguments(this.userModel.getUserId(),
                this.userModel.getFirstName(), this.userModel.getLastName(), this.userModel.getUserEmail(),
                phoneNumber, this.userModel.getBillingAddress(), this.userModel.getBillingCity(),
                this.userModel.getBillingPostal(), this.userModel.getShippingAddress(), this.userModel.getShippingCity(),
                this.userModel.getShippingPostal(), this.userModel.getBillingCountry(), this.userModel.getBillingProvince(),
                this.userModel.getShippingCountry(), this.userModel.getShippingProvince());
        this.saveCustomerDetailsUseCase.executeRealm(new SaveCustomerDetailsSubscriber());
    }

    public void updateCustomerDetails(String tag, String address, String city, String postalCode) {
        if (tag.equals(AccountSettingsFragment.TAG_SHIPPING)) {
            this.saveCustomerDetailsUseCase.setArguments(this.userModel.getUserId(),
                    this.userModel.getFirstName(), this.userModel.getLastName(), this.userModel.getUserEmail(),
                    this.userModel.getPhoneNumber(), this.userModel.getBillingAddress(), this.userModel.getBillingCity(),
                    this.userModel.getBillingPostal(), address, city, postalCode, this.userModel.getBillingCountry(),
                    this.userModel.getBillingProvince(), this.userModel.getShippingCountry(), this.userModel.getShippingProvince());
        } else if (tag.equals(AccountSettingsFragment.TAG_BILLING)) {
            this.saveCustomerDetailsUseCase.setArguments(this.userModel.getUserId(),
                    this.userModel.getFirstName(), this.userModel.getLastName(), this.userModel.getUserEmail(),
                    this.userModel.getPhoneNumber(), address, city, postalCode, this.userModel.getShippingAddress(),
                    this.userModel.getShippingCity(), this.userModel.getShippingPostal(), this.userModel.getBillingCountry(),
                    this.userModel.getBillingProvince(), this.userModel.getShippingCountry(), this.userModel.getShippingProvince());
        }
        this.saveCustomerDetailsUseCase.executeRealm(new SaveCustomerDetailsSubscriber());
    }

    private class CustomerDetailsSubscriber extends DefaultSubscriber<UserModel> {

        @Override
        public void onNext(UserModel userModel) {
            super.onNext(userModel);
            AccountSettingsPresenter.this.showCustomerDetails(userModel);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
        }
    }

    private class SaveCustomerDetailsSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            AccountSettingsPresenter.this.getCustomerDetails();
        }
    }
}
