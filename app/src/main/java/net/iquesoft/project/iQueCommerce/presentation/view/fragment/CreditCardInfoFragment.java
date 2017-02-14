package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.PaymentComponent;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CreditCardInfoPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PaymentActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.LoadDataView;
import net.iquesoft.project.iQueCommerce.utils.Constants;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreditCardInfoFragment extends BaseFragment implements TextWatcher, LoadDataView {

    private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
    private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
    private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
    private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
    private static final char DIVIDER = '-';

    @Inject
    CreditCardInfoPresenter presenter;

    @BindView(R.id.tilCreditCardNumber)
    TextInputLayout tilCreditCardNumber;
    @BindView(R.id.etCreditCardNumber)
    TextInputEditText etCreditCardNumber;
    @BindView(R.id.tilCreditCardHolderFirstName)
    TextInputLayout tilCreditCardHolderFirstName;
    @BindView(R.id.etCreditCardHolderFirstName)
    TextInputEditText etCreditCardHolderFirstName;
    @BindView(R.id.tilCreditCardHolderLastName)
    TextInputLayout tilCreditCardHolderLastName;
    @BindView(R.id.etCreditCardHolderLastName)
    TextInputEditText etCreditCardHolderLastName;
    @BindView(R.id.tilCreditCardExpirationMonth)
    TextInputLayout tilCreditCardExpirationMonth;
    @BindView(R.id.etCreditCardExpirationMonth)
    TextInputEditText etCreditCardExpirationMonth;
    @BindView(R.id.tilCreditCardExpirationYear)
    TextInputLayout tilCreditCardExpirationYear;
    @BindView(R.id.etCreditCardExpirationYear)
    TextInputEditText etCreditCardExpirationYear;
    @BindView(R.id.tilCreditCardVerificationNumber)
    TextInputLayout tilCreditCardVerificationNumber;
    @BindView(R.id.etCreditCardVerificationNumber)
    TextInputEditText etCreditCardVerificationNumber;
    @BindView(R.id.btn_proceed)
    Button btn_proceed;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;


    @Override
    void initializeInjection() {
        getComponent(PaymentComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fragmentManager = getFragmentManager();
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_card_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.etCreditCardNumber.addTextChangedListener(this);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getCurrentActivity().hideFAB();
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
            s.replace(0, s.length(), buildCorrectString(getDigitArray(s, TOTAL_DIGITS), DIVIDER_POSITION, DIVIDER));
        }
    }

    private boolean isInputCorrect(Editable s, int totalSymbols, int dividerModulo, char divider) {
        boolean isCorrect = s.length() <= totalSymbols; // check size of entered string
        for (int i = 0; i < s.length(); i++) { // check that every element is right
            if (i > 0 && (i + 1) % dividerModulo == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }


    private String buildCorrectString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }

    private char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int index = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }

    @OnClick(R.id.btn_proceed)
    void proceed() {
        if (isAllFieldsFilled()) {
            this.presenter.proceed(
                    this.etCreditCardNumber.getText().toString().replace("-", ""),
                    this.etCreditCardHolderFirstName.getText().toString(),
                    this.etCreditCardHolderLastName.getText().toString(),
                    this.etCreditCardExpirationMonth.getText().toString(),
                    this.etCreditCardExpirationYear.getText().toString(),
                    this.etCreditCardVerificationNumber.getText().toString());
        }
    }

    private boolean isAllFieldsFilled() {
        this.clearErrorMessages();

        if (this.etCreditCardNumber.getText().toString().isEmpty()) {
            this.tilCreditCardNumber.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etCreditCardNumber.getText().toString().length() < 19) {
            this.tilCreditCardNumber.setError(Constants.ERROR_MESSAGE_SHORT_CREDIT_CARD_NUMBER);
            return false;
        }
        if (this.etCreditCardHolderFirstName.getText().toString().isEmpty()) {
            this.tilCreditCardHolderFirstName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etCreditCardHolderLastName.getText().toString().isEmpty()) {
            this.tilCreditCardHolderLastName.setError(Constants.ERROR_MESSAGE_EMPTY_FIELD);
            return false;
        }
        if (this.etCreditCardExpirationMonth.getText().toString().isEmpty()) {
            this.tilCreditCardExpirationMonth.setError("!!!");
            return false;
        }
        if (this.etCreditCardExpirationYear.getText().toString().isEmpty()) {
            this.tilCreditCardExpirationYear.setError("!!!");
            return false;
        }
        if (this.etCreditCardVerificationNumber.getText().toString().isEmpty()) {
            this.tilCreditCardVerificationNumber.setError("!!!");
            return false;
        }
        return true;
    }

    private void clearErrorMessages() {
        this.tilCreditCardNumber.setError(null);
        this.tilCreditCardHolderFirstName.setError(null);
        this.tilCreditCardHolderLastName.setError(null);
        this.tilCreditCardExpirationMonth.setError(null);
        this.tilCreditCardExpirationYear.setError(null);
        this.tilCreditCardVerificationNumber.setError(null);
    }

    private PaymentActivity getCurrentActivity() {
        return (PaymentActivity) getActivity();
    }
}
