package net.iquesoft.project.iQueCommerce.presentation.presenter;

import android.content.Context;
import android.util.Pair;

import com.shopify.buy.model.Customer;

import net.iquesoft.project.iQueCommerce.domain.PreferencesManager;
import net.iquesoft.project.iQueCommerce.domain.RegularExpressionValidation;
import net.iquesoft.project.iQueCommerce.domain.exception.DefaultErrorBundle;
import net.iquesoft.project.iQueCommerce.domain.exception.EmptyFieldException;
import net.iquesoft.project.iQueCommerce.domain.exception.ErrorMessageFactory;
import net.iquesoft.project.iQueCommerce.domain.exception.InvalidEmailException;
import net.iquesoft.project.iQueCommerce.domain.exception.InvalidPasswordException;
import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerTokenUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.LogInUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.LogOutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.RenewCustomerTokenUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.LoginFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

public class UserLoginPresenter implements Presenter {

    private final SaveCustomerDetailsUseCase saveCustomerDetailsUseCase;
    private final PreferencesManager preferencesManager;
    private final LogOutUseCase logOutUseCase;
    private final LogInUseCase logInUseCase;
    private final Context context;
    private final ModelDataMapper modelDataMapper;
    private final RegularExpressionValidation validation;
    private final GetCustomerTokenUseCase getCustomerTokenUseCase;
    private final RenewCustomerTokenUseCase renewCustomerTokenUseCase;
    private LoginFragment fragmentView;

    public UserLoginPresenter(Context context,
                              GetCustomerTokenUseCase getCustomerTokenUseCase,
                              RenewCustomerTokenUseCase renewCustomerTokenUseCase,
                              SaveCustomerDetailsUseCase saveCustomerDetailsUseCase,
                              LogInUseCase logInUseCase,
                              LogOutUseCase logOutUseCase,
                              RegularExpressionValidation regularExpressionValidation,
                              ModelDataMapper modelDataMapper,
                              PreferencesManager preferencesManager) {
        this.context = context;
        this.getCustomerTokenUseCase = getCustomerTokenUseCase;
        this.renewCustomerTokenUseCase = renewCustomerTokenUseCase;
        this.saveCustomerDetailsUseCase = saveCustomerDetailsUseCase;
        this.logInUseCase = logInUseCase;
        this.logOutUseCase = logOutUseCase;
        this.validation = regularExpressionValidation;
        this.modelDataMapper = modelDataMapper;
        this.preferencesManager = preferencesManager;
    }

    public void setView(LoginFragment view) {
        this.fragmentView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.logInUseCase.unsubscribe();
        this.logOutUseCase.unsubscribe();
    }


    public void initialize() {
        // TODO: 05-Oct-16 NEEDED TO BE CHANGED! SAVE TOKEN, NOT PASSWORD!!!!!!
        Pair<String, String> pair = this.preferencesManager.loadUserCredentials(context);
        this.fragmentView.displayUserCredentials(pair.first, pair.second);
        if (this.preferencesManager.isAutoLoginEnabled(context) && !this.preferencesManager.isRelogging(context)) {
            this.fragmentView.showLoading();
            this.logIn(pair.first, pair.second);
        }
        this.preferencesManager.setRelogging(context, false);
    }


    public void logInWithEmailAndPassword(String email, String password, boolean saveCredentials) {
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

        if (saveCredentials) {
            this.preferencesManager.saveCredentials(context, email, password);
            this.preferencesManager.setAutoLogin(context, true);
        } else {
            this.preferencesManager.saveCredentials(context, "", "");
            this.preferencesManager.setAutoLogin(context, false);
        }
        this.logIn(email, password);
    }


    public void logOut() {
        this.logOutUseCase.execute(new DefaultSubscriber());
    }

    private void logIn(String email, String password) {
        this.logInUseCase.setCredentials(email, password);
        this.logInUseCase.execute(new UseCaseSubscriber());
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
        this.saveCustomerDetailsUseCase.setArguments(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
        this.saveCustomerDetailsUseCase.executeRealm(new DefaultSubscriber());
        this.modelDataMapper.transformCustomer(customer);
        this.fragmentView.onLoggedIn();
    }



    private class UseCaseSubscriber extends DefaultSubscriber<Customer> {
        @Override
        public void onCompleted() {
            UserLoginPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            UserLoginPresenter.this.fragmentView.hideLoading();
            UserLoginPresenter.this.fragmentView.showToast(Constants.ERROR_MESSAGE_AUTH_FAILED);
        }

        @Override
        public void onNext(Customer customer) {
            UserLoginPresenter.this.transformCustomer(customer);
        }
    }

}
