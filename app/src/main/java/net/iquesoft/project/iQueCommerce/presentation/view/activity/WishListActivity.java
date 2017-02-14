package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.utils.NumberUtils;
import com.mikepenz.actionitembadge.library.utils.UIUtil;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerWishListComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.WishListComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.WishListModule;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.WishListPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.WishListFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import javax.inject.Inject;

public class WishListActivity extends BaseActivity implements HasComponent<WishListComponent>, WishListFragment.ProductsListListener, View.OnClickListener {

    @Inject
    WishListPresenter wishListActivityPresenter;
    @Inject
    SelectedProductModel selectedProductModel;


    private WishListComponent wishListComponent;

    @Override
    protected void setupActivityComponent() {
        this.wishListComponent = DaggerWishListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .wishListModule(new WishListModule())
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.inject();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_wish_list, null, false);
        this.drawer.addView(contentView, 0);

        this.toolbar.setNavigationIcon(R.drawable.back_arrow);
        this.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        this.fab.setImageResource(R.drawable.ic_plus);
        this.fab.setOnClickListener(this);
        this.showUserInfoInUI(this.baseActivityPresenter.getCurrentUser());
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer_activity_wish_list, new WishListFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary, menu);
        this.cart_menu = menu.findItem(R.id.btn_cart_menu);
        ActionItemBadge.update(this, menu.findItem(R.id.btn_cart_menu),
                UIUtil.getCompatDrawable(this, R.drawable.shopping_cart), Constants.badgeStyle, NumberUtils.formatNumber(this.wishListActivityPresenter.getCartItemCount()));
        return true;
    }

    public void showSnackbar(View view) {
        Snackbar.make(view, "Item was added to your cart!", Snackbar.LENGTH_LONG)
                .setAction("View cart", view1 -> this.navigator.navigateToCartActivity(this)).show();
    }

    public void changeBadgeCount(int badgeCount) {
        ActionItemBadge.update(cart_menu, badgeCount);
    }

    public void hideFAB() {
        this.fab.hide();
    }

    public void showFAB() {
        this.fab.show();
    }

    @Override
    public void onProductClicked(ProductModel productModel) {
        this.selectedProductModel.setSelectedProductModel(productModel);
        this.navigator.navigateToWishListProductFragment(getSupportFragmentManager());
    }

    @Override
    public void onAddToCartButtonClicked(ProductModel productModel) {
        this.wishListActivityPresenter.addToCart(productModel);
        this.showSnackbar(this.getCurrentFocus());
        this.changeBadgeCount(this.wishListActivityPresenter.getCartItemCount());
    }

    @Override
    public WishListComponent getComponent() {
        return this.wishListComponent;
    }


    private void inject() {
        this.getComponent().inject(this);
    }

    public void setToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            this.wishListActivityPresenter.addToCart(this.wishListActivityPresenter.getSelectedProduct());
            this.showSnackbar(view);
            this.changeBadgeCount(this.wishListActivityPresenter.getCartItemCount());
        }
    }
}
