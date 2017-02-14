//package net.iquesoft.project.seedEcommerce.presentation.presenter;
//
//import android.content.Context;
//
//import com.shopify.buy.model.Customer;
//
//import net.iquesoft.project.seedEcommerce.domain.RegularExpressionValidation;
//import net.iquesoft.project.seedEcommerce.domain.exception.AuthenticationException;
//import net.iquesoft.project.seedEcommerce.domain.interactor.DefaultSubscriber;
//import net.iquesoft.project.seedEcommerce.domain.interactor.SignUpUseCase;
//import net.iquesoft.project.seedEcommerce.presentation.mapper.ModelDataMapper;
//import net.iquesoft.project.seedEcommerce.presentation.view.fragment.SignUpFragment;
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
//
//public class UserSignUpPresenterTest {
//
//    private static final String USER_EMAIL = "user@email.com";
//    private static final String USER_PASSWORD = "USER_PASSWORD";
//    private static final java.lang.Long USER_ID = 1234564L;
//    private static final java.lang.String USER_FIRST_NAME = "USER_FIRST_NAME";
//    private static final java.lang.String USER_LAST_NAME = "USER_LAST_NAME";
//
//
//    @Mock
//    SignUpUseCase signUpUseCase;
//    @Mock
//    Context context;
//    @Mock
//    RegularExpressionValidation regularExpressionValidation;
//    @Mock
//    ModelDataMapper modelDataMapper;
//    @Mock
//    SignUpFragment fragmentView;
//    @Mock
//    Customer customer;
//
//    @Captor
//    ArgumentCaptor<DefaultSubscriber<Customer>> subscriberCapture;
//
//    private UserSignUpPresenter presenter;
//
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        this.presenter = new UserSignUpPresenter(signUpUseCase, context, regularExpressionValidation, modelDataMapper);
//    }
//
//    @Test
//    public void setView() throws Exception {
//        this.presenter.setView(fragmentView);
//        verify(this.presenter).setView(fragmentView);
//    }
//
//    @Test
//    public void signUpWithEmailAndPassword() throws Exception {
//
//        this.presenter.signUpWithEmailAndPassword(USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME);
//
//        verify(this.fragmentView).showLoading();
//        verify(this.fragmentView).clearTextField();
//        verify(this.regularExpressionValidation).isEmailValid(USER_EMAIL);
//        verify(this.regularExpressionValidation).isPasswordValid(USER_PASSWORD);
//        verify(this.signUpUseCase).setCredentials(USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME);
//        verify(this.signUpUseCase).execute(subscriberCapture.capture());
//
//        this.subscriberCapture.getValue().onNext(customer);
//        this.subscriberCapture.getValue().onCompleted();
//
//        verify(this.fragmentView).hideLoading();
//        verify(this.modelDataMapper).transformCustomer(customer);
//        verify(this.fragmentView).onLoggedIn();
//
//        this.subscriberCapture.getValue().onError(new AuthenticationException());
//
//        verify(this.fragmentView).showToast(Constants.ERROR_MESSAGE_AUTH_FAILED);
//
//
//    }
//
//}