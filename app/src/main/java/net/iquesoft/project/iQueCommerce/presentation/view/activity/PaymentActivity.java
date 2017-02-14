package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerPaymentComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.PaymentComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CreditCardInfoModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CustomerInfoModule;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CustomerInfoFragment;

public class PaymentActivity extends BaseActivity implements HasComponent<PaymentComponent> {

    private PaymentComponent paymentComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.inflateLayout();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer_payment_activity, new CustomerInfoFragment());
        }
        this.inject();
        this.showUserInfoInUI(baseActivityPresenter.getCurrentUser());
    }

    @Override
    protected void setupActivityComponent() {
        this.paymentComponent = DaggerPaymentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .customerInfoModule(new CustomerInfoModule())
                .creditCardInfoModule(new CreditCardInfoModule())
                .build();
    }

    @Override
    public PaymentComponent getComponent() {
        return paymentComponent;
    }

    public void hideFAB() {
        this.fab.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.empty, menu);
        return true;
    }

    private void inflateLayout() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_payment, null, false);
        this.drawer.addView(contentView, 0);
    }

    private void inject() {
        this.getComponent().inject(this);
    }

}
