package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.CategoryModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CategoryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PrimaryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AboutUsFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CategoryFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.MainFragment;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.ProductFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CategoryModule.class})
public interface CategoryComponent extends ActivityComponent {

    void inject(PrimaryActivity primaryActivity);

    void inject(MainFragment mainFragment);

    void inject(CategoryFragment categoryFragment);

    void inject(CategoryActivity categoryActivity);

    void inject(ProductFragment productFragment);

    void inject(AboutUsFragment aboutUsFragment);
}
