package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.utils.NumberUtils;
import com.mikepenz.actionitembadge.library.utils.UIUtil;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerCategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CategoryModule;
import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CategoryActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CategoryFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import javax.inject.Inject;

public class CategoryActivity extends BaseActivity implements HasComponent<CategoryComponent>, CategoryFragment.ProductsListListener, View.OnClickListener {

    private static final String INTENT_EXTRA_CATEGORY_ID = "INTENT_EXTRA_CATEGORY_ID";
    public static String categoryTitle;

    @Inject
    CategoryActivityPresenter categoryActivityPresenter;
    @Inject
    SelectedProductModel selectedProductModel;
    long categoryId;
    private Bundle savedInstanceState;
    private CategoryComponent categoryComponent;

    public static Intent getCallingIntent(Context context, CategoryModel categoryModel) {
        Intent callingIntent = new Intent(context, CategoryActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_CATEGORY_ID, categoryModel.getCategoryId());
        categoryTitle = categoryModel.getTitle();
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        this.inject();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_category, null, false);
        this.drawer.addView(contentView, 0);

        this.toolbar.setNavigationIcon(R.drawable.back_arrow);
        this.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        this.fab.setImageResource(R.drawable.ic_plus);
        this.fab.setOnClickListener(this);

        this.showUserInfoInUI(this.baseActivityPresenter.getCurrentUser());
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            this.categoryActivityPresenter.addToCart(this.categoryActivityPresenter.getSelectedProduct());
            this.showSnackbar(view);
            this.changeBadgeCount(this.categoryActivityPresenter.getCartItemCount());
        }
    }

    public void showSnackbar(View view) {
        Snackbar.make(view, "Item was added to your cart!", Snackbar.LENGTH_LONG)
                .setAction("View cart", view1 -> this.navigator.navigateToCartActivity(this)).show();
    }

    private void inject() {
        this.getComponent().inject(this);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.categoryId = getIntent().getLongExtra(INTENT_EXTRA_CATEGORY_ID, -1);
            this.addFragment(R.id.fragmentContainer_activity_category, new CategoryFragment());
        } else {
            this.categoryId = savedInstanceState.getInt(INTENT_EXTRA_CATEGORY_ID);
        }
    }

    @Override
    protected void setupActivityComponent() {
        this.initializeActivity(savedInstanceState);
        this.categoryComponent = DaggerCategoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .categoryModule(new CategoryModule(this.categoryId))
                .build();
    }

    @Override
    public CategoryComponent getComponent() {
        return this.categoryComponent;
    }


    @Override
    public void onProductClicked(ProductModel productModel) {
        this.selectedProductModel.setSelectedProductModel(productModel);
        this.navigator.navigateToProductFragment(getSupportFragmentManager());
    }

    @Override
    public void onAddToCartButtonClicked(ProductModel productModel) {
        this.categoryActivityPresenter.addToCart(productModel);
        this.showSnackbar(this.getCurrentFocus());
        this.changeBadgeCount(this.categoryActivityPresenter.getCartItemCount());
    }


    public void changeBadgeCount(int badgeCount) {
        ActionItemBadge.update(cart_menu, badgeCount);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary, menu);
        this.cart_menu = menu.findItem(R.id.btn_cart_menu);
        ActionItemBadge.update(this, this.cart_menu,
                UIUtil.getCompatDrawable(this, R.drawable.shopping_cart), Constants.badgeStyle, NumberUtils.formatNumber(this.categoryActivityPresenter.getCartItemCount()));
        return true;
    }


    public void setToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }

    public void hideFAB() {
        this.fab.hide();
    }

    public void showFAB() {
        this.fab.show();
    }

}
