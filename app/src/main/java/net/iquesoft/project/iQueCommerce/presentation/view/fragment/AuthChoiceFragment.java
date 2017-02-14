package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.LoginComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthChoiceFragment extends BaseFragment {

    @BindView(R.id.btn_log_in_auth_choice)
    AppCompatButton goToLogIn;
    @BindView(R.id.btn_sign_up_auth_choice)
    AppCompatButton goToSignUp;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_choice, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    void initializeInjection() {
        getComponent(LoginComponent.class).inject(this);
    }

    @OnClick(R.id.btn_log_in_auth_choice)
    void goToLogIn() {
        this.navigator.navigateToLogInFragment(this.fragmentManager);
    }

    @OnClick(R.id.btn_sign_up_auth_choice)
    void goToSignUp() {
        this.navigator.navigateToSignUpFragment(this.fragmentManager);
    }
}
