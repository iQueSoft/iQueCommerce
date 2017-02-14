package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerLoginComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.LoginComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.LoginModule;
import net.iquesoft.project.iQueCommerce.presentation.navigation.Navigator;
import net.iquesoft.project.iQueCommerce.presentation.presenter.UserLoginPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AuthChoiceFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasComponent<LoginComponent> {

    @Inject
    UserLoginPresenter presenter;

    @Inject
    Navigator navigator;
    private LoginComponent loginComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new AuthChoiceFragment());
        }

        Intent intent = getIntent();
        if (intent.getStringExtra("EXTRA") != null) {
            if (intent.getStringExtra("EXTRA").equals("SignOut")) {
                signOut();
            }
        }
    }

    @Override
    protected void setupActivityComponent() {
        this.loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .loginModule(new LoginModule(this))
                .build();
        this.getComponent().inject(this);
    }

    private void signOut() {
        this.presenter.logOut();
        this.navigator.navigateToLogInFragment(getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public LoginComponent getComponent() {
        return this.loginComponent;
    }
}
