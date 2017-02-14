package net.iquesoft.project.iQueCommerce.presentation.presenter;

import android.widget.Spinner;

import com.shopify.buy.model.Address;

import net.iquesoft.project.iQueCommerce.domain.interactor.CreateCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCountriesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProvincesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetShippingRatesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SetShippingRatesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.UpdateCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CustomerInfoFragment;

import java.util.List;

import javax.inject.Inject;


public class CustomerInfoPresenter implements Presenter {

    private static final String DEFAULT_PROVINCE_NAME = "No province name";
    private final CreateCheckoutUseCase createCheckoutUseCase;
    private final GetCountriesUseCase getCountriesUseCase;
    private final GetProvincesUseCase getProvincesUseCase;
    private final UpdateCheckoutUseCase updateCheckoutUseCase;
    private final GetShippingRatesUseCase getShippingRatesUseCase;
    private final SetShippingRatesUseCase setShippingRatesUseCase;
    private final GetCustomerDetailsUseCase getCustomerDetailsUseCase;
    private final SaveCustomerDetailsUseCase saveCustomerDetailsUseCase;
    private final UserModel userModel;
    private CustomerInfoFragment fragmentView;
    private List<String> countries;

    @Inject
    public CustomerInfoPresenter(CreateCheckoutUseCase createCheckoutUseCase,
                                 GetCountriesUseCase getCountriesUseCase,
                                 GetProvincesUseCase getProvincesUseCase,
                                 UpdateCheckoutUseCase updateCheckoutUseCase,
                                 GetShippingRatesUseCase getShippingRatesUseCase,
                                 SetShippingRatesUseCase setShippingRatesUseCase,
                                 GetCustomerDetailsUseCase getCustomerDetailsUseCase,
                                 SaveCustomerDetailsUseCase saveCustomerDetailsUseCase,
                                 UserModel userModel) {
        this.createCheckoutUseCase = createCheckoutUseCase;
        this.getCountriesUseCase = getCountriesUseCase;
        this.getProvincesUseCase = getProvincesUseCase;
        this.updateCheckoutUseCase = updateCheckoutUseCase;
        this.getShippingRatesUseCase = getShippingRatesUseCase;
        this.setShippingRatesUseCase = setShippingRatesUseCase;
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

    public void setView(CustomerInfoFragment customerInfoFragment) {
        this.fragmentView = customerInfoFragment;
    }

    public void initialize() {
        this.createCheckoutUseCase.execute(new CreateCheckoutSubscriber());
        this.getCountriesUseCase.execute(new GetCountriesSubscriber());
        this.getCustomerDetailsUseCase.setArguments(this.userModel.getUserId());
        this.getCustomerDetailsUseCase.executeRealm(new GetCustomerDetailsSubscriber());
    }

    public void proceed(String first_name,
                        String last_name,
                        String email,
                        String phoneNumber,
                        String billingAddress,
                        String billingCity,
                        String billingPostalCode,
                        String billingCountry,
                        Spinner billingProvince,
                        String shippingAddress,
                        String shippingCity,
                        String shippingPostalCode,
                        String shippingCountry,
                        Spinner shippingProvince) {
        this.updateCheckoutUseCase.setArguments(email,
                this.getAddress(billingAddress, billingCity, billingPostalCode, billingCountry, billingProvince, first_name, last_name, phoneNumber),
                this.getAddress(shippingAddress, shippingCity, shippingPostalCode, shippingCountry, shippingProvince, first_name, last_name, phoneNumber),
                null,
                null,
                null);
        this.updateCheckoutUseCase.execute(new UpdateCheckoutSubscriber());
        this.saveDetailsToDB(first_name, last_name, email, phoneNumber, billingAddress, billingCity, billingPostalCode, shippingAddress, shippingCity, shippingPostalCode, billingCountry, getProvince(billingProvince), shippingCountry, getProvince(shippingProvince));
    }

    public void getBillingProvinces(String country) {
        this.getProvincesUseCase.setCountry(country);
        this.getProvincesUseCase.execute(new GetBillingProvincesSubscriber());
    }

    public void getShippingProvinces(String country) {
        this.getProvincesUseCase.setCountry(country);
        this.getProvincesUseCase.execute(new GetShippingProvincesSubscriber());
    }

    private void saveDetailsToDB(String first_name,
                                 String last_name,
                                 String email,
                                 String phoneNumber,
                                 String billingAddress,
                                 String billingCity,
                                 String billingPostalCode,
                                 String shippingAddress,
                                 String shippingCity,
                                 String shippingPostalCode, String billingCountry, String billingProvince, String shippingCountry, String shippingProvince) {
        this.fragmentView.showLoading();

        this.saveCustomerDetailsUseCase.setArguments(this.userModel.getUserId(), first_name, last_name, email, phoneNumber, billingAddress, billingCity,
                billingPostalCode, shippingAddress, shippingCity, shippingPostalCode, billingCountry, billingProvince, shippingCountry, shippingProvince);
        this.saveCustomerDetailsUseCase.executeRealm(new SaveCustomerDetailsSubscriber());
    }

    private Address getAddress(String address, String city, String postalCode, String country, Spinner province, String first_name, String last_name, String phoneNumber) {
        Address se = new Address();
        se.setAddress1(address);
        se.setCity(city);
        se.setZip(postalCode);
        se.setCountry(country);
        se.setProvince(getProvince(province));
        se.setFirstName(first_name);
        se.setLastName(last_name);
        se.setPhone(phoneNumber);
        return se;
    }

    private String getProvince(Spinner province) {
        if (province.getSelectedItem() != null) {
            return province.getSelectedItem().toString();
        } else return DEFAULT_PROVINCE_NAME;
    }

    private int getBillingCountryPosition(UserModel userModel) {
        return this.countries.indexOf(userModel.getBillingCountry()) == -1 ? 0 : this.countries.indexOf(userModel.getBillingCountry());
    }

    private int getShippingCountryPosition(UserModel userModel) {
        return this.countries.indexOf(userModel.getShippingCountry()) == -1 ? 0 : this.countries.indexOf(userModel.getShippingCountry());

    }

    private class GetCountriesSubscriber extends DefaultSubscriber<List<String>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CustomerInfoPresenter.this.fragmentView.hideLoading();
            CustomerInfoPresenter.this.fragmentView.showError(e.getMessage());
        }

        @Override
        public void onNext(List<String> strings) {
            super.onNext(strings);
            CustomerInfoPresenter.this.countries = strings;
            CustomerInfoPresenter.this.fragmentView.showCountries(strings);
        }
    }

    private class GetBillingProvincesSubscriber extends DefaultSubscriber<List<String>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onNext(List<String> strings) {
            super.onNext(strings);
            CustomerInfoPresenter.this.fragmentView.showBillingProvinces(strings);
        }
    }

    private class GetShippingProvincesSubscriber extends DefaultSubscriber<List<String>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onNext(List<String> strings) {
            super.onNext(strings);
            CustomerInfoPresenter.this.fragmentView.showShippingProvinces(strings);
        }
    }

    private class UpdateCheckoutSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
            CustomerInfoPresenter.this.fragmentView.goToCreditCardInfoFragment();
            // TODO: 19-Sep-16 NEEDED TO BE IMPLEMENTED. Get shipping rates,  let the user select from the possible shipping rates, set shipping rates, update checkout
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CustomerInfoPresenter.this.fragmentView.hideLoading();
            CustomerInfoPresenter.this.fragmentView.showError(e.getMessage());
        }
    }

    private class CreateCheckoutSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            CustomerInfoPresenter.this.fragmentView.hideLoading();
            CustomerInfoPresenter.this.fragmentView.showError(e.getMessage());
        }
    }

    private class GetCustomerDetailsSubscriber extends DefaultSubscriber<UserModel> {
        @Override
        public void onCompleted() {
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onNext(UserModel userModel) {
            super.onNext(userModel);
            CustomerInfoPresenter.this.fragmentView.showCustomerDetails(userModel, CustomerInfoPresenter.this.getShippingCountryPosition(userModel), CustomerInfoPresenter.this.getBillingCountryPosition(userModel));
        }
    }

    private class SaveCustomerDetailsSubscriber extends DefaultSubscriber {
        @Override
        public void onCompleted() {
            super.onCompleted();
            CustomerInfoPresenter.this.fragmentView.hideLoading();
        }
    }
}

