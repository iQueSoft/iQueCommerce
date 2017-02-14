//package net.iquesoft.project.seedEcommerce.presentation.presenter;
//
//import android.content.Context;
//import android.util.Pair;
//
//import com.shopify.buy.model.Customer;
//
//import net.iquesoft.project.seedEcommerce.domain.PreferencesManager;
//import net.iquesoft.project.seedEcommerce.domain.RegularExpressionValidation;
//import net.iquesoft.project.seedEcommerce.domain.exception.AuthenticationException;
//import net.iquesoft.project.seedEcommerce.domain.interactor.DefaultSubscriber;
//import net.iquesoft.project.seedEcommerce.domain.interactor.GetCustomerTokenUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.LogInUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.LogOutUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.RenewCustomerTokenUseCase;
//import net.iquesoft.project.seedEcommerce.domain.interactor.SaveCustomerDetailsUseCase;
//import net.iquesoft.project.seedEcommerce.presentation.mapper.ModelDataMapper;
//import net.iquesoft.project.seedEcommerce.presentation.view.fragment.LoginFragment;
//import net.iquesoft.project.seedEcommerce.utils.Constants;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class UserLoginPresenterTest {
//
//    private static final String USER_EMAIL = "user@email.com";
//    private static final String USER_PASSWORD = "USER_PASSWORD";
//    private static final java.lang.Long USER_ID = 1234564L;
//    private static final java.lang.String USER_FIRST_NAME = "USER_FIRST_NAME";
//    private static final java.lang.String USER_LAST_NAME = "USER_LAST_NAME";
//    @Mock
//    Context context;
//    @Mock
//    SaveCustomerDetailsUseCase saveCustomerDetailsUseCase;
//    @Mock
//    LogInUseCase logInUseCase;
//    @Mock
//    LogOutUseCase logOutUseCase;
//    @Mock
//    RegularExpressionValidation regularExpressionValidation;
//    @Mock
//    ModelDataMapper modelDataMapper;
//    @Mock
//    PreferencesManager preferencesManager;
//    @Mock
//    LoginFragment loginFragment;
//    @Mock
//    Customer customer;
//    @Mock
//    GetCustomerTokenUseCase getCustomerTokenUseCase;
//    @Mock
//    RenewCustomerTokenUseCase renewCustomerTokenUseCase;
//
//    @Captor
//    ArgumentCaptor<DefaultSubscriber<Customer>> subscriberCapture;
//
//    private UserLoginPresenter presenter;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        this.presenter = new UserLoginPresenter(context, getCustomerTokenUseCase,
//                renewCustomerTokenUseCase, saveCustomerDetailsUseCase, logInUseCase,
//                logOutUseCase, regularExpressionValidation, modelDataMapper, preferencesManager);
//        this.presenter.setView(loginFragment);
//
//        when(this.preferencesManager.loadUserCredentials(context)).thenReturn(new Pair<>(USER_EMAIL, USER_PASSWORD));
//        when(this.preferencesManager.isAutoLoginEnabled(context)).thenReturn(true);
//        when(this.preferencesManager.isRelogging(context)).thenReturn(false);
//        when(customer.getId()).thenReturn(USER_ID);
//        when(customer.getFirstName()).thenReturn(USER_FIRST_NAME);
//        when(customer.getLastName()).thenReturn(USER_LAST_NAME);
//        when(customer.getEmail()).thenReturn(USER_EMAIL);
//    }
//
//
//    @Test
//    public void initialize() {
//        this.presenter.initialize();
//        this.loginFragment.displayUserCredentials(USER_EMAIL, USER_PASSWORD);
//        verify(this.loginFragment).displayUserCredentials(USER_EMAIL, USER_PASSWORD);
//        verify(this.loginFragment).showLoading();
//        verify(this.preferencesManager).setRelogging(context, false);
//    }
//
//    @Test
//    public void logInWithEmailAndPassword() throws Exception {
//
//        this.presenter.logInWithEmailAndPassword(USER_EMAIL, USER_PASSWORD, true);
//
//        verify(this.loginFragment).showLoading();
//        verify(this.loginFragment).clearTextField();
//        verify(this.regularExpressionValidation).isEmailValid(USER_EMAIL);
//        verify(this.regularExpressionValidation).isPasswordValid(USER_PASSWORD);
//        verify(this.preferencesManager).saveCredentials(context, USER_EMAIL, USER_PASSWORD);
//        verify(this.preferencesManager).setAutoLogin(context, true);
//        verify(this.logInUseCase).setCredentials(USER_EMAIL, USER_PASSWORD);
//        verify(this.logInUseCase).execute(subscriberCapture.capture());
//
//        this.subscriberCapture.getValue().onNext(customer);
//        this.subscriberCapture.getValue().onCompleted();
//
//        verify(this.loginFragment).hideLoading();
//        verify(this.saveCustomerDetailsUseCase).setArguments(USER_ID, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL);
//
//        this.subscriberCapture.getValue().onError(new AuthenticationException());
//
//        verify(this.loginFragment).showToast(Constants.ERROR_MESSAGE_AUTH_FAILED);
//
//        verify(this.saveCustomerDetailsUseCase).executeRealm(subscriberCapture.capture());
//        verify(this.modelDataMapper).transformCustomer(customer);
//        verify(this.loginFragment).onLoggedIn();
//    }
//
//}