package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CreditCardInfoModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CustomerInfoModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PaymentActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CreditCardInfoFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CustomerInfoFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CustomerInfoModule.class, CreditCardInfoModule.class})

public interface PaymentComponent {
    void inject(PaymentActivity paymentActivity);

    void inject(CustomerInfoFragment customerInfoFragment);

    void inject(CreditCardInfoFragment creditCardInfoFragment);
}
