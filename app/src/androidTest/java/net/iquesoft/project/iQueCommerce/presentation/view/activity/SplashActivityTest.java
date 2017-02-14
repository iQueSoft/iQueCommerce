package net.iquesoft.project.iQueCommerce.presentation.view.activity;


import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.AndroidApplication;
import net.iquesoft.project.iQueCommerce.presentation.di.components.ApplicationTestComponent;
import net.iquesoft.project.iQueCommerce.tools.ConstantsTest;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Before
    public void setUp() throws Exception {
        ((ApplicationTestComponent) AndroidApplication.getAppComponent()).inject(this);
    }

    @Test
    public void authorizationTest() throws Exception {
        // Fragment authentication choice
        this.checkDisplayed(R.id.btn_log_in_auth_choice);
        this.checkDisplayed(R.id.btn_sign_up_auth_choice);

        this.clickOnView(R.id.btn_sign_up_auth_choice);


        // Fragment sign up
        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilFirstName, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etFirstName, ConstantsTest.FIRST_NAME);

        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilLastName, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etLastName, ConstantsTest.LAST_NAME);


        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilEmail, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etEmail, ConstantsTest.BAD_EMAIL);
        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilEmail, Constants.ERROR_MESSAGE_INVALID_EMAIL);
        this.checkEditText(R.id.etEmail, ConstantsTest.EMAIL);


        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilRegistrationPassword, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etRegistrationPassword, ConstantsTest.BAD_PASSWORD);
        this.clickOnView(R.id.btnSignUp);
        this.checkErrorMessage(R.id.tilRegistrationPassword, Constants.ERROR_MESSAGE_INVALID_PASSWORD);
        this.checkEditText(R.id.etRegistrationPassword, ConstantsTest.PASSWORD);

        this.checkDisplayed(R.id.btnSignUp);
        this.checkDisplayed(R.id.tvGoToLogIn);

        this.clickOnView(R.id.btnSignUp);

        // Fragment main
        this.openNavigationDrawer();
        this.clickOnViewInDrawer(ConstantsTest.NAVIGATION_MENU_LOG_OUT);

        // Fragment Log In
        this.cleanField(R.id.etEmail);
        this.cleanField(R.id.etPassword);

        this.clickOnView(R.id.btnLogin);
        this.checkErrorMessage(R.id.tilEmail, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etEmail, ConstantsTest.BAD_EMAIL);
        this.clickOnView(R.id.btnLogin);
        this.checkErrorMessage(R.id.tilEmail, Constants.ERROR_MESSAGE_INVALID_EMAIL);
        this.checkEditText(R.id.etEmail, ConstantsTest.EMAIL);


        this.clickOnView(R.id.btnLogin);
        this.checkErrorMessage(R.id.tilPassword, Constants.ERROR_MESSAGE_EMPTY_FIELD);
        this.checkEditText(R.id.etPassword, ConstantsTest.BAD_PASSWORD);
        this.clickOnView(R.id.btnLogin);
        this.checkErrorMessage(R.id.tilPassword, Constants.ERROR_MESSAGE_INVALID_PASSWORD);
        this.checkEditText(R.id.etPassword, ConstantsTest.PASSWORD);

//        this.checkDisplayed(R.id.chb_remember_me);
//        this.clickOnView(R.id.chb_remember_me);

        this.clickOnView(R.id.btnLogin);
    }

    @Test
    public void collectionTest() throws Exception {

        this.clickOnView(R.id.btn_log_in_auth_choice);
        this.checkEditText(R.id.etEmail, ConstantsTest.EMAIL);
        this.checkEditText(R.id.etPassword, ConstantsTest.PASSWORD);
//        this.clickOnView(R.id.chb_remember_me);
        this.clickOnView(R.id.btnLogin);


        // Fragment main
        this.checkRecyclerViewDisplayed(R.id.rv_categories, R.id.fragmentContainer_primary);
        int position = 0;
        for (String text : ConstantsTest.COLLECTION_TITLES) {
            this.scrollRecyclerView(R.id.rv_categories, position++, position == ConstantsTest.COLLECTION_TITLES.length);
            this.checkRecyclerViewHasText(R.id.tv_category_title, text);
        }

        this.scrollRecyclerView(R.id.rv_categories, 0, false);
        this.clickOnRecyclerView(R.id.rv_categories, 0);

        // Fragment category

        for (int i = 0; i < ConstantsTest.NOTEBOOK_TITLE.length - 1; i++) {
            this.scrollRecyclerView(R.id.rv_products, i, position == ConstantsTest.NOTEBOOK_TITLE.length);
            this.checkDisplayed(R.id.tv_product_title, ConstantsTest.NOTEBOOK_TITLE[i]);
            this.checkDisplayed(R.id.tv_product_price, "$" + ConstantsTest.NOTEBOOK_PRICES[i]);
        }

        this.clickOnRecyclerView(R.id.rv_products, 0);

        // Fragment product

        this.checkDisplayed(R.id.tv_product_title_fragment_product, ConstantsTest.NOTEBOOK_TITLE[0]);
        this.checkDisplayed(R.id.tv_product_price_fragment_product, "$" + ConstantsTest.NOTEBOOK_PRICES[0]);
        this.checkDisplayed(R.id.tv_product_description_fragment_product, ConstantsTest.NOTEBOOK_DESCRIPTIONS[0]);
        this.checkDisplayed(R.id.iv_favourite);
        this.checkDisplayed(R.id.iv_share);
//        this.checkDisplayed(R.id.iv_more);

        this.clickOnView(R.id.iv_favourite);

        pressBack();
        pressBack();

        this.openNavigationDrawer();
        this.clickOnViewInDrawer(ConstantsTest.NAVIGATION_MENU_WISH_LIST);
        //Fragment WishList

        this.checkDisplayed(R.id.tv_product_title, ConstantsTest.NOTEBOOK_TITLE[0]);
        this.checkDisplayed(R.id.tv_product_price, "$" + ConstantsTest.NOTEBOOK_PRICES[0]);
        this.checkDisplayed(R.id.btn_add_to_cart);


        this.clickOnView(R.id.btn_add_to_cart);
        this.checkDisplayed(R.id.menu_badge, "1");

        pressBack();
    }


    @Test
    public void settingsTest() throws Exception {

        this.clickOnView(R.id.btn_log_in_auth_choice);
        this.checkEditText(R.id.etEmail, ConstantsTest.EMAIL);
        this.checkEditText(R.id.etPassword, ConstantsTest.PASSWORD);
//        this.clickOnView(R.id.chb_remember_me);
        this.clickOnView(R.id.btnLogin);

        this.openNavigationDrawer();
        this.clickOnViewInDrawer(ConstantsTest.NAVIGATION_MENU_ACCOUNT_SETTINGS);


        // Fragment AccountSettings

        this.checkDisplayed(R.id.btn_edit_customer_info);
        this.clickOnView(R.id.btn_edit_customer_info);
        this.editText(R.id.etPhoneNumber, ConstantsTest.PHONE_NUMBER);
        this.clickOnView(android.R.id.button1);
        this.checkDisplayed(R.id.tv_customer_info, ConstantsTest.CUSTOMER_INFO);

        this.checkDisplayed(R.id.btn_edit_shipping_address);
        this.clickOnView(R.id.btn_edit_shipping_address);
        this.editText(R.id.etAddress, ConstantsTest.SHIPPING_ADDRESS);
        this.editText(R.id.et_city, ConstantsTest.SHIPPING_CITY);
        this.editText(R.id.et_postal_code, ConstantsTest.SHIPPING_POSTAL);
        this.clickOnView(android.R.id.button1);
        this.checkDisplayed(R.id.tv_shipping_address, ConstantsTest.SHIPPING_ADDRESS_INFO);

        this.checkDisplayed(R.id.btn_edit_billing_address);
        this.clickOnView(R.id.btn_edit_billing_address);
        this.editText(R.id.etAddress, ConstantsTest.BILLING_ADDRESS);
        this.editText(R.id.et_city, ConstantsTest.BILLING_CITY);
        this.editText(R.id.et_postal_code, ConstantsTest.BILLING_POSTAL);
        this.clickOnView(android.R.id.button1);
        this.checkDisplayed(R.id.tv_billing_address, ConstantsTest.BILLING_ADDRESS_INFO);

    }

    @Test
    public void checkoutTest() throws Exception {

        this.clickOnView(R.id.btn_log_in_auth_choice);
        this.checkEditText(R.id.etEmail, ConstantsTest.EMAIL);
        this.checkEditText(R.id.etPassword, ConstantsTest.PASSWORD);
//        this.clickOnView(R.id.chb_remember_me);
        this.clickOnView(R.id.btnLogin);

        this.checkDisplayed(R.id.rv_categories);
        this.clickOnRecyclerView(R.id.rv_categories, 0);
        this.clickOnRecyclerView(R.id.rv_products, 0);

        this.clickOnView(R.id.fab);
        this.checkDisplayed(R.id.menu_badge, "1");

        this.clickOnView(R.id.btn_cart_menu);

        // Fragment cart

//        this.checkRecyclerViewDisplayed(R.id.rv_products_cart_fragment, R.id.fragmentContainer_cart_activity);
        this.checkDisplayed(R.id.tv_discount);
        this.checkEditText(R.id.et_discount, ConstantsTest.DISCOUNT_CODE);
        this.checkDisplayed(R.id.btn_apply_coupon);


        this.checkDisplayed(R.id.tv_product_title_cart, ConstantsTest.NOTEBOOK_TITLE[0]);
        this.checkDisplayed(R.id.tv_product_price_cart, "$" + ConstantsTest.NOTEBOOK_PRICES[0]);

        this.checkDisplayed(R.id.btn_continue);
        this.clickOnView(R.id.btn_continue);

        // Fragment payment

        this.checkDisplayed(R.id.tv_customer_info);
        this.checkDisplayed(R.id.tilCustomerFirstName);
        this.checkDisplayed(R.id.etCustomerFirstName);
        this.checkDisplayed(R.id.tilCustomerLastName);
        this.checkDisplayed(R.id.etCustomerLastName);
        this.checkDisplayed(R.id.tilEmail);
        this.checkDisplayed(R.id.etEmail);
        this.checkDisplayed(R.id.tilPhoneNumber);
        this.checkDisplayed(R.id.etPhoneNumber);
    }

    private void editText(int viewId, String text) {
        ViewInteraction textInputEditText8 = onView(
                allOf(withId(viewId), isDisplayed()));
        textInputEditText8.perform(replaceText(text), ViewActions.closeSoftKeyboard());
    }

    private void clickOnRecyclerView(int recyclerViewId, int position) {
        onView(withId(recyclerViewId)).perform(actionOnItemAtPosition(position, click()));
    }

    private void scrollRecyclerView(int recyclerViewId, int position, boolean swipeUp) throws InterruptedException {
        if (swipeUp) {
            onView(withId(recyclerViewId)).perform(swipeUp());
        } else {
            onView(withId(recyclerViewId)).perform(scrollToPosition(position));
        }
    }

    private void checkRecyclerViewHasText(int textViewId, String text) {
        ViewInteraction textView = onView(
                allOf(withId(textViewId), withText(text),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(text)));
    }

    private void checkRecyclerViewDisplayed(int recyclerViewId, int containerId) {
        ViewInteraction recyclerView = onView(
                allOf(withId(recyclerViewId),
                        childAtPosition(
                                childAtPosition(
                                        withId(containerId),
                                        0),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }


    private void cleanField(int editTextId) {
        onView(withId(editTextId)).perform(replaceText(""));
    }

    private void clickOnViewInDrawer(String textInView) {
        if (textInView.equals(ConstantsTest.NAVIGATION_MENU_LOG_OUT)) {
            onView(withId(R.id.nav_view_primary)).perform(swipeUp());
        }
        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText(textInView), isDisplayed()));
        appCompatCheckedTextView.perform(click());
    }

    private void openNavigationDrawer() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar_primary)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    private void checkErrorMessage(int textInputLayoutId, String errorMessage) {
        onView(withId(textInputLayoutId)).check(matches(hasTextInputLayoutErrorText(errorMessage)));
    }

    private void clickOnView(int viewId) {
        onView(withId(viewId)).perform(click());
    }

    private void checkDisplayed(int viewId) {
        onView(withId(viewId)).check(matches(isDisplayed()));
    }

    private void checkDisplayed(int viewId, String viewText) {
        ViewInteraction viewInteraction = onView(
                allOf(withId(viewId), withText(viewText)));
        viewInteraction.check(matches(isDisplayed()));
        viewInteraction.check(matches(withText(viewText)));
    }

    private void checkEditText(int viewId, String inputText) throws InterruptedException {
        ViewInteraction viewInteraction = onView(withId(viewId));
        viewInteraction.check(matches(isDisplayed()));
        viewInteraction.perform(replaceText(inputText));
        closeSoftKeyboard();
        viewInteraction.check(matches(withText(inputText)));
    }

    private Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }


    private Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
