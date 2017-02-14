//package net.iquesoft.project.seedEcommerce.domain.exception;
//
//import android.content.Context;
//
//import net.iquesoft.project.seedEcommerce.R;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ErrorMessageFactoryTest {
//
//    @Mock
//    Context context;
//    DefaultErrorBundle defaultErrorBundle;
//
//    @Before
//    public void setUp() throws Exception {
//        when(context.getString(R.string.exception_message_invalid_email)).thenReturn("Invalid email");
//        when(context.getString(R.string.exception_message_empty_field)).thenReturn("Empty field");
//        when(context.getString(R.string.exception_message_invalid_ip_address)).thenReturn("Invalid ip address");
//        when(context.getString(R.string.exception_message_invalid_name)).thenReturn("Invalid name");
//        when(context.getString(R.string.exception_message_invalid_password)).thenReturn("Invalid password");
//        when(context.getString(R.string.exception_message_invalid_web_url)).thenReturn("Invalid web url");
//    }
//
//    @Test
//    public void testFactory() throws Exception {
//        defaultErrorBundle = new DefaultErrorBundle(new InvalidEmailException());
//        String errorMessage = ErrorMessageFactory.create(context, (RuntimeException) defaultErrorBundle.getException());
//        assertThat(context.getString(R.string.exception_message_invalid_email), is(errorMessage));
//
//
////        defaultErrorBundle = new DefaultErrorBundle(new EmptyFieldException());
////        errorMessage = ErrorMessageFactory.create(context, defaultErrorBundle.getException());
////        assertThat(context.getString(R.string.exception_message_empty_field), is(errorMessage));
////
////        defaultErrorBundle = new DefaultErrorBundle(new InvalidIpAddressException());
////        errorMessage = ErrorMessageFactory.create(context, defaultErrorBundle.getException());
////        assertThat(context.getString(R.string.exception_message_invalid_ip_address), is(errorMessage));
////
////        defaultErrorBundle = new DefaultErrorBundle(new InvalidNameException());
////        errorMessage = ErrorMessageFactory.create(context, defaultErrorBundle.getException());
////        assertThat(context.getString(R.string.exception_message_invalid_name), is(errorMessage));
////
////        defaultErrorBundle = new DefaultErrorBundle(new InvalidPasswordException());
////        errorMessage = ErrorMessageFactory.create(context, defaultErrorBundle.getException());
////        assertThat(context.getString(R.string.exception_message_invalid_password), is(errorMessage));
////
////        defaultErrorBundle = new DefaultErrorBundle(new InvalidWebUrlException());
////        errorMessage = ErrorMessageFactory.create(context, defaultErrorBundle.getException());
//        assertThat(context.getString(R.string.exception_message_invalid_web_url), is(errorMessage));
//    }
//}