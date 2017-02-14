package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.LoginComponent;
import net.iquesoft.project.iQueCommerce.presentation.presenter.UserLoginPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.LoginView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView, View.OnFocusChangeListener {

    @Inject
    UserLoginPresenter presenter;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    @BindView(R.id.tvGoToSignUp)
    TextView tvGoToSignUp;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.chb_remember_me)
    CheckBox chb_remember_me;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getFragmentManager();
    }

    @Override
    void initializeInjection() {
        getComponent(LoginComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @OnClick(R.id.btnLogin)
    protected void btnLoginClicked() {
        this.hideKeyboard();
        this.presenter.logInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString(), chb_remember_me.isChecked());
    }

    @OnClick(R.id.tvGoToSignUp)
    protected void tvGoToSignUpClicked() {
        this.navigator.navigateToSignUpFragment(fragmentManager);
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
    public void clearTextField() {
        this.tilEmail.setErrorEnabled(false);
        this.tilPassword.setErrorEnabled(false);
    }

    @Override
    public void showEmailError(String errorMessage) {
        this.tilEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        this.tilPassword.setError(errorMessage);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.etEmail:
                    this.tilEmail.setError(null);
                    break;
                case R.id.etPassword:
                    this.tilPassword.setError(null);
                    break;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.hideLoading();
    }

    public void displayUserCredentials(String first, String second) {
        this.etEmail.setText(first);
        this.etPassword.setText(second);
        this.chb_remember_me.setChecked(true);
    }

    public void onLoggedIn() {
        this.navigator.navigateToPrimaryActivity(getContext());
    }

    private void hideKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
