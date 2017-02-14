package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.LoginComponent;
import net.iquesoft.project.iQueCommerce.presentation.presenter.UserSignUpPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.LoginView;
import net.iquesoft.project.iQueCommerce.utils.Constants;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignUpFragment extends BaseFragment implements LoginView, View.OnFocusChangeListener {

    @Inject
    UserSignUpPresenter presenter;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.btnSignUp)
    Button btnLogin;
    @BindView(R.id.tvGoToLogIn)
    TextView tvGoToSignUp;
    @BindView(R.id.tilFirstName)
    TextInputLayout tilFirstName;
    @BindView(R.id.tilLastName)
    TextInputLayout tilLastName;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.tilRegistrationPassword)
    TextInputLayout tilRegistrationPassword;
    @BindView(R.id.etFirstName)
    TextInputEditText etFirstName;
    @BindView(R.id.etLastName)
    TextInputEditText etLastName;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etRegistrationPassword)
    TextInputEditText etRegistrationPassword;

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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
    }

    @OnClick(R.id.btnSignUp)
    protected void signUp() {
        if (TextUtils.isEmpty(etFirstName.getText())) {
            tilFirstName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return;
        }
        if (TextUtils.isEmpty(etLastName.getText())) {
            tilLastName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return;
        }
        this.presenter.signUpWithEmailAndPassword(etEmail.getText().toString(),
                etRegistrationPassword.getText().toString(),
                etFirstName.getText().toString(),
                etLastName.getText().toString());
    }

    @OnClick(R.id.tvGoToLogIn)
    protected void goToLogIn() {
        this.navigator.navigateToLogInFragment(this.fragmentManager);
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
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.etEmail:
                    this.tilEmail.setError(null);
                    break;
                case R.id.etRegistrationPassword:
                    this.tilRegistrationPassword.setError(null);
                    break;
                case R.id.etFirstName:
                    this.etFirstName.setError(null);
                    break;
                case R.id.etLastName:
                    this.etLastName.setError(null);
                    break;
            }
        }
    }


    @Override
    public void clearTextField() {
        this.tilEmail.setErrorEnabled(false);
        this.tilRegistrationPassword.setErrorEnabled(false);
        this.tilFirstName.setErrorEnabled(false);
        this.tilLastName.setErrorEnabled(false);
    }

    @Override
    public void showEmailError(String errorMessage) {
        this.tilEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError(String errorMessage) {
        this.tilRegistrationPassword.setError(errorMessage);

    }

    public void onLoggedIn() {
        this.navigator.navigateToPrimaryActivity(getActivity());
    }

    private void hideKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
