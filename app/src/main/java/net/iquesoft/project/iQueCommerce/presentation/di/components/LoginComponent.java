package net.iquesoft.project.iQueCommerce.presentation.di.components;


import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.LoginModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.MainActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AuthChoiceFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.LoginFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SignUpFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LoginModule.class})
public interface LoginComponent extends ActivityComponent {

    void inject(LoginFragment loginFragment);

    void inject(SignUpFragment signUpFragment);

    void inject(MainActivity mainActivity);

    void inject(AuthChoiceFragment authChoiceFragment);

}
