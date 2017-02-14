package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.AccountSettingsModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.AccountSettingsActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AccountSettingsFragment;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AccountSettingsModule.class})
public interface AccountSettingsComponent {
    void inject(AccountSettingsActivity accountSettingsActivity);

    void inject(AccountSettingsFragment accountSettingsFragment);
}
