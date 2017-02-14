package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.utils.NumberUtils;
import com.mikepenz.actionitembadge.library.utils.UIUtil;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CartComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerCartComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CartModule;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CartActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CartFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import javax.inject.Inject;

public class CartActivity extends BaseActivity implements HasComponent<CartComponent>, CartFragment.ProductsListListener {

    @Inject
    CartActivityPresenter cartActivityPresenter;

    private CartComponent cartComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.inflateLayout();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer_cart_activity, new CartFragment());
        }
        this.inject();
        this.toolbar.setNavigationIcon(R.drawable.back_arrow);
        this.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        this.showUserInfoInUI(baseActivityPresenter.getCurrentUser());
    }


    @Override
    protected void setupActivityComponent() {
        this.cartComponent = DaggerCartComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .cartModule(new CartModule())
                .build();
    }

    private void inflateLayout() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_cart, null, false);
        this.drawer.addView(contentView, 0);
    }

    private void inject() {
        this.getComponent().inject(this);
    }

    @Override
    public CartComponent getComponent() {
        return this.cartComponent;
    }

    public void hideFAB() {
        this.fab.hide();
    }

    @Override
    public void onProductClicked(ProductModel productModel) {
        // TODO: 09-Aug-16  Do something on product click. For example open fullscreen picture
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary, menu);
        this.cart_menu = menu.findItem(R.id.btn_cart_menu);
        menu.findItem(R.id.btn_view_menu).setVisible(false);
        ActionItemBadge.update(this, this.cart_menu,
                UIUtil.getCompatDrawable(this, R.drawable.shopping_cart), Constants.badgeStyle, NumberUtils.formatNumber(this.cartActivityPresenter.getCartItemCount()));
        return true;
    }

    public void changeBadgeCount(int badgeCount) {
        ActionItemBadge.update(cart_menu, badgeCount);
    }
}
