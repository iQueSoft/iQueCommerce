package net.iquesoft.project.iQueCommerce.presentation.navigation;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.AccountSettingsActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CartActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CategoryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.ContactUsActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.MainActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PaymentActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PrimaryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.WishListActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AboutUsFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CreditCardInfoFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.LoginFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.MainFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.ProductFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SignUpFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.WishListProductFragment;

import javax.inject.Inject;

/**
 * Class used to navigate through the application.
 */
public class Navigator {
    @Inject
    public Navigator() {
    }

    public void navigateToSignUpFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer, new SignUpFragment())
                .commit();
    }

    public void navigateToMainFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer, new MainFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToLogInFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer, new LoginFragment())
                .commit();
    }

    public void navigateToPrimaryActivity(Context context) {
        context.startActivity(new Intent(context, PrimaryActivity.class));
    }

    public void navigateToMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void navigateToMainActivity(Context context, String extra) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("EXTRA", extra);
        context.startActivity(intent);
    }


    public void navigateToCategoryActivity(Context context, CategoryModel categoryModel) {
        Intent intent = CategoryActivity.getCallingIntent(context, categoryModel);
        context.startActivity(intent);
    }

    public void navigateToProductFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer_activity_category, new ProductFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToCartActivity(Context context) {
        context.startActivity(new Intent(context, CartActivity.class));
    }

    public void navigateToAccountSettingsActivity(Context context) {
        context.startActivity(new Intent(context, AccountSettingsActivity.class));
    }


    public void goToPaymentActivity(Context context) {
        context.startActivity(new Intent(context, PaymentActivity.class));
    }

    public void navigateToCreditCardInfoFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer_payment_activity, new CreditCardInfoFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToWishList(Context context) {
        context.startActivity(new Intent(context, WishListActivity.class));
    }

    public void navigateToWishListProductFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer_activity_wish_list, new WishListProductFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToAboutUsFragment(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction()
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragmentContainer_primary, new AboutUsFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToContactUsActivity(Context context) {
        context.startActivity(new Intent(context, ContactUsActivity.class));
    }
}
