package net.iquesoft.project.iQueCommerce.presentation.presenter;

import android.content.Context;

import com.shopify.buy.model.Customer;

import net.iquesoft.project.iQueCommerce.domain.RegularExpressionValidation;
import net.iquesoft.project.iQueCommerce.domain.exception.DefaultErrorBundle;
import net.iquesoft.project.iQueCommerce.domain.exception.EmptyFieldException;
import net.iquesoft.project.iQueCommerce.domain.exception.ErrorMessageFactory;
import net.iquesoft.project.iQueCommerce.domain.exception.InvalidEmailException;
import net.iquesoft.project.iQueCommerce.domain.exception.InvalidPasswordException;
import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.SignUpUseCase;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SignUpFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

public class UserSignUpPresenter implements Presenter {

    private final ModelDataMapper modelDataMapper;
    private final SignUpUseCase signUpUseCase;
    private final Context context;
    private RegularExpressionValidation validation;
    private SignUpFragment fragmentView;

    public UserSignUpPresenter(SignUpUseCase signUp,
                               Context context,
                               RegularExpressionValidation validation,
                               ModelDataMapper modelDataMapper) {
        this.signUpUseCase = signUp;
        this.validation = validation;
        this.context = context;
        this.modelDataMapper = modelDataMapper;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.signUpUseCase.unsubscribe();
    }

    public void setView(SignUpFragment fragmentView) {
        this.fragmentView = fragmentView;
    }

    public void signUpWithEmailAndPassword(String email, String password, String firstName, String lastName) {
        this.fragmentView.showLoading();
        this.fragmentView.clearTextField();

        try {
            this.validation.isEmailValid(email);
        } catch (InvalidEmailException | EmptyFieldException e) {
            this.fragmentView.hideLoading();
            this.showEmailError(new DefaultErrorBundle(e));
            return;
        }

        try {
            this.validation.isPasswordValid(password);
        } catch (EmptyFieldException | InvalidPasswordException e) {
            this.fragmentView.hideLoading();
            this.showPasswordError(new DefaultErrorBundle(e));
            return;
        }

        this.signUpUseCase.setCredentials(email, password, firstName, lastName);
        this.signUpUseCase.execute(new UseCaseSubscriber());
    }

    private void showPasswordError(DefaultErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(context,
                (RuntimeException) errorBundle.getException());
        this.fragmentView.showPasswordError(errorMessage);
    }

    private void showEmailError(DefaultErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(context,
                (RuntimeException) errorBundle.getException());
        this.fragmentView.showEmailError(errorMessage);
    }

    private void transformCustomer(Customer customer) {
        this.modelDataMapper.transformCustomer(customer);
        this.fragmentView.onLoggedIn();
    }

    private class UseCaseSubscriber extends DefaultSubscriber<Customer> {
        @Override
        public void onCompleted() {
            UserSignUpPresenter.this.fragmentView.hideLoading();

        }

        @Override
        public void onError(Throwable e) {
            UserSignUpPresenter.this.fragmentView.hideLoading();
            UserSignUpPresenter.this.fragmentView.showToast(Constants.ERROR_MESSAGE_AUTH_FAILED);

        }

        @Override
        public void onNext(Customer customer) {
            UserSignUpPresenter.this.transformCustomer(customer);
        }
    }
}
