package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CartModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CartActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CartFragment;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CartModule.class})
public interface CartComponent extends ActivityComponent {
    void inject(CartActivity cartActivity);

    void inject(CartFragment cartFragment);

}
