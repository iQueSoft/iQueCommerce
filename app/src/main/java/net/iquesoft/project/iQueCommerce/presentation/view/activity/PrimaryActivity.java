package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import net.iquesoft.project.iQueCommerce.presentation.navigation.Navigator;
import net.iquesoft.project.iQueCommerce.presentation.presenter.PrimaryActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.MainFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import javax.inject.Inject;

public class PrimaryActivity extends BaseActivity
        implements HasComponent<CategoryComponent>, MainFragment.CategoriesListListener {


    @Inject
    Navigator navigator;

    @Inject
    PrimaryActivityPresenter primaryActivityPresenter;
    private CategoryComponent categoryComponent;
    private MenuItem cart_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.content_primary, null, false);
        this.drawer.addView(contentView, 0);

        this.inject();

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer_primary, new MainFragment());
        }
        this.showUserInfoInUI(baseActivityPresenter.getCurrentUser());

    }

    private void inject() {
        this.getComponent().inject(this);
    }

    @Override
    protected void setupActivityComponent() {
        this.categoryComponent = DaggerCategoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .categoryModule(new CategoryModule())
                .build();
    }

    @Override
    public CategoryComponent getComponent() {
        return this.categoryComponent;
    }

    @Override
    public void onCategoryClicked(CategoryModel categoryModel) {
        this.navigator.navigateToCategoryActivity(this, categoryModel);
    }

    public void changeBadgeCount(int badgeCount) {
        ActionItemBadge.update(cart_menu, badgeCount);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary, menu);
        this.cart_menu = menu.findItem(R.id.btn_cart_menu);
        menu.findItem(R.id.btn_view_menu).setVisible(false);
        ActionItemBadge.update(this, menu.findItem(R.id.btn_cart_menu),
                UIUtil.getCompatDrawable(this, R.drawable.shopping_cart), Constants.badgeStyle, NumberUtils.formatNumber(this.primaryActivityPresenter.getCartItemCount()));
        return true;
    }

    public void setToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }

}
