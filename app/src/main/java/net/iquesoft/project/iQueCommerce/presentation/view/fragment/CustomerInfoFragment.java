package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.PaymentComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CustomerInfoPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PaymentActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.PaymentView;
import net.iquesoft.project.iQueCommerce.utils.Constants;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CustomerInfoFragment extends BaseFragment implements PaymentView, AdapterView.OnItemSelectedListener {

    @Inject
    CustomerInfoPresenter presenter;

    @BindView(R.id.tilCustomerFirstName)
    TextInputLayout tilCustomerFirstName;
    @BindView(R.id.etCustomerFirstName)
    TextInputEditText etCustomerFirstName;
    @BindView(R.id.tilCustomerLastName)
    TextInputLayout tilCustomerLastName;
    @BindView(R.id.etCustomerLastName)
    TextInputEditText etCustomerLastName;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.tilPhoneNumber)
    TextInputLayout tilPhoneNumber;
    @BindView(R.id.etPhoneNumber)
    TextInputEditText etPhoneNumber;
    @BindView(R.id.tilBillingAddress)
    TextInputLayout tilBillingAddress;
    @BindView(R.id.etBillingAddress)
    TextInputEditText etBillingAddress;
    @BindView(R.id.til_city_billing)
    TextInputLayout til_city_billing;
    @BindView(R.id.et_city_billing)
    TextInputEditText et_city_billing;
    @BindView(R.id.til_postal_code_billing)
    TextInputLayout til_postal_code_billing;
    @BindView(R.id.et_postal_code_billing)
    TextInputEditText et_postal_code_billing;
    @BindView(R.id.spinner_country_billing)
    Spinner spinner_country_billing;
    @BindView(R.id.spinner_province_billing)
    Spinner spinner_province_billing;
    @BindView(R.id.tilShippingAddress)
    TextInputLayout tilShippingAddress;
    @BindView(R.id.etShippingAddress)
    TextInputEditText etShippingAddress;
    @BindView(R.id.til_city_shipping)
    TextInputLayout til_city_shipping;
    @BindView(R.id.et_city_shipping)
    TextInputEditText et_city_shipping;
    @BindView(R.id.til_postal_code_shipping)
    TextInputLayout til_postal_code_shipping;
    @BindView(R.id.et_postal_code_shipping)
    TextInputEditText et_postal_code_shipping;
    @BindView(R.id.spinner_country_shipping)
    Spinner spinner_country_shipping;
    @BindView(R.id.spinner_province_shipping)
    Spinner spinner_province_shipping;
    @BindView(R.id.btn_proceed)
    Button btn_proceed;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;


    @Override
    void initializeInjection() {
        getComponent(PaymentComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getFragmentManager();
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.spinner_country_billing.setOnItemSelectedListener(this);
        this.spinner_country_shipping.setOnItemSelectedListener(this);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getCurrentActivity().hideFAB();
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        ToastMaker.showMessage(getActivity(), message, ToastMaker.duration.LONG);
    }

    @Override
    public void showCountries(List<String> countries) {
        Collections.sort(countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getCurrentActivity(), android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner_country_billing.setAdapter(adapter);
        this.spinner_country_shipping.setAdapter(adapter);
    }

    @Override
    public void showBillingProvinces(List<String> provinces) {
        this.spinner_province_billing.setAdapter(null);
        Collections.sort(provinces);
        ArrayAdapter<String> adapter_province_billing = new ArrayAdapter<>(this.getCurrentActivity(), android.R.layout.simple_spinner_item, provinces);
        this.spinner_province_billing.setAdapter(adapter_province_billing);
    }


    @Override
    public void showShippingProvinces(List<String> provinces) {
        this.spinner_province_shipping.setAdapter(null);
        Collections.sort(provinces);
        ArrayAdapter<String> adapter_province_shipping = new ArrayAdapter<>(this.getCurrentActivity(), android.R.layout.simple_spinner_item, provinces);
        this.spinner_province_shipping.setAdapter(adapter_province_shipping);
    }

    @OnClick(R.id.btn_proceed)
    void proceed() {
        if (isAllFieldsFilled()) {
            this.presenter.proceed(
                    this.etCustomerFirstName.getText().toString(),
                    this.etCustomerLastName.getText().toString(),
                    this.etEmail.getText().toString(),
                    this.etPhoneNumber.getText().toString(),
                    this.etBillingAddress.getText().toString(),
                    this.et_city_billing.getText().toString(),
                    this.et_postal_code_billing.getText().toString(),
                    this.spinner_country_billing.getSelectedItem().toString(),
                    this.spinner_province_billing,
                    this.etShippingAddress.getText().toString(),
                    this.et_city_shipping.getText().toString(),
                    this.et_postal_code_shipping.getText().toString(),
                    this.spinner_country_shipping.getSelectedItem().toString(),
                    this.spinner_province_shipping);
        }
    }

    private boolean isAllFieldsFilled() {
        this.clearErrorMessages();
        if (this.etCustomerFirstName.getText().toString().isEmpty()) {
            this.tilCustomerFirstName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etCustomerLastName.getText().toString().isEmpty()) {
            this.tilCustomerLastName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etEmail.getText().toString().isEmpty()) {
            this.tilEmail.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (!this.etEmail.getText().toString().contains("@")) {
            this.tilEmail.setError(Constants.ERROR_MESSAGE_INVALID_EMAIL);
            return false;
        }
        if (this.etBillingAddress.getText().toString().isEmpty()) {
            this.tilBillingAddress.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etShippingAddress.getText().toString().isEmpty()) {
            this.tilShippingAddress.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etPhoneNumber.getText().toString().isEmpty()) {
            this.tilPhoneNumber.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        return true;
    }

    private void clearErrorMessages() {
        this.tilCustomerFirstName.setError(null);
        this.tilCustomerLastName.setError(null);
        this.tilEmail.setError(null);
        this.tilBillingAddress.setError(null);
        this.tilShippingAddress.setError(null);
        this.tilPhoneNumber.setError(null);
    }

    private PaymentActivity getCurrentActivity() {
        return (PaymentActivity) getActivity();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_country_billing:
                this.presenter.getBillingProvinces(adapterView.getSelectedItem().toString());
                break;
            case R.id.spinner_country_shipping:
                this.presenter.getShippingProvinces(adapterView.getSelectedItem().toString());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void goToCreditCardInfoFragment() {
        this.navigator.navigateToCreditCardInfoFragment(this.fragmentManager);
    }

    public void showCustomerDetails(UserModel userModel, int shippingCountryPosition, int billingCountryPosition) {
        this.etCustomerFirstName.setText(userModel.getFirstName());
        this.etCustomerLastName.setText(userModel.getLastName());
        this.etEmail.setText(userModel.getUserEmail());
        this.etPhoneNumber.setText(userModel.getPhoneNumber());
        this.etBillingAddress.setText(userModel.getBillingAddress());
        this.et_city_billing.setText(userModel.getBillingCity());
        this.et_postal_code_billing.setText(userModel.getBillingPostal());
        this.etShippingAddress.setText(userModel.getShippingAddress());
        this.et_city_shipping.setText(userModel.getShippingCity());
        this.et_postal_code_shipping.setText(userModel.getShippingPostal());
        this.spinner_country_shipping.setSelection(shippingCountryPosition, true);
        this.spinner_country_billing.setSelection(billingCountryPosition, true);
    }
}
