package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.WishListModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.WishListActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.WishListFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.WishListProductFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, WishListModule.class})
public interface WishListComponent {

    void inject(WishListActivity wishListActivity);

    void inject(WishListFragment wishListFragment);

    void inject(WishListProductFragment wishListProductFragment);
}
