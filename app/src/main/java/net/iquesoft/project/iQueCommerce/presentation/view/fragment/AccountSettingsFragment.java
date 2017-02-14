package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.AccountSettingsComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.AccountSettingsPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.dialog.EditAddressDialog;
import net.iquesoft.project.iQueCommerce.presentation.view.dialog.EditCustomerInfoDialog;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.LoadDataView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountSettingsFragment extends BaseFragment implements EditCustomerInfoDialog.OnEditCustomerInfoListener, EditAddressDialog.OnEditAddressListener, LoadDataView {


    public static final String TAG_SHIPPING = "shipping";
    public static final String TAG_BILLING = "billing";

    @Inject
    AccountSettingsPresenter presenter;


    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.tv_customer_info)
    TextView tv_customer_info;
    @BindView(R.id.tv_shipping_address)
    TextView tv_shipping_address;
    @BindView(R.id.tv_billing_address)
    TextView tv_billing_address;
    @BindView(R.id.btn_edit_customer_info)
    TextView btn_edit_customer_info;
    @BindView(R.id.btn_edit_billing_address)
    TextView btn_edit_billing_address;
    @BindView(R.id.btn_edit_shipping_address)
    TextView btn_edit_shipping_address;


    @Override
    void initializeInjection() {
        getComponent(AccountSettingsComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_settings, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.getCustomerDetails();
    }

    public void showUserInfo(UserModel userModel) {
        if (userModel != null) {
            String customerInfo = "Full name: " + userModel.getFirstName() + " " + userModel.getLastName() + "\n"
                    + "Phone: " + userModel.getPhoneNumber() + "\n"
                    + "Email: " + userModel.getUserEmail();
            this.tv_customer_info.setText(customerInfo);
            String shippingAddress = "Address: " + userModel.getShippingAddress() + "\n"
                    + "City and province: " + userModel.getShippingCity() + ", " + userModel.getShippingProvince() + "\n"
                    + "Zip and country: " + userModel.getShippingPostal() + ", " + userModel.getShippingCountry();
            this.tv_shipping_address.setText(shippingAddress);
            String billingAddress = "Address: " + userModel.getBillingAddress() + "\n"
                    + "City and province: " + userModel.getBillingCity() + ", " + userModel.getBillingProvince() + "\n"
                    + "Zip and country: " + userModel.getBillingPostal() + ", " + userModel.getBillingCountry();
            this.tv_billing_address.setText(billingAddress);
        }
    }

    @OnClick(R.id.btn_edit_customer_info)
    void editCustomerInfo() {
        EditCustomerInfoDialog editCustomerInfoDialog = EditCustomerInfoDialog.newInstance(this.presenter.getCustomerFirstName(),
                this.presenter.getCustomerLastName(),
                this.presenter.getCustomerEmail(),
                this.presenter.getCustomerPhoneNumber());
        editCustomerInfoDialog.onAttachFragment(this);
        editCustomerInfoDialog.show(this.fragmentManager, null);
    }

    @OnClick(R.id.btn_edit_shipping_address)
    void editShippingAddress() {
        EditAddressDialog editAddressDialog = EditAddressDialog.newInstance(this.presenter.getShippingAddress(),
                this.presenter.getShippingCity(),
                this.presenter.getShippingPostalCode());
        editAddressDialog.onAttachFragment(this);
        editAddressDialog.show(this.fragmentManager, TAG_SHIPPING);
    }

    @OnClick(R.id.btn_edit_billing_address)
    void editBillingAddress() {
        EditAddressDialog editAddressDialog = EditAddressDialog.newInstance(this.presenter.getBillingAddress(),
                this.presenter.getBillingCity(),
                this.presenter.getBillingPostalCode());
        editAddressDialog.onAttachFragment(this);
        editAddressDialog.show(this.fragmentManager, TAG_BILLING);
    }

    @Override
    public void OnCustomerPhoneNumberChanged(String phoneNumber) {
        this.presenter.updateCustomerDetails(phoneNumber);
    }

    @Override
    public void OnCustomerAddressChanged(String tag, String address, String city, String postalCode) {
        this.presenter.updateCustomerDetails(tag, address, city, postalCode);
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (this.rl_progress != null) {
            this.rl_progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        ToastMaker.showMessage(getActivity(), message, ToastMaker.duration.LONG);
    }

}
