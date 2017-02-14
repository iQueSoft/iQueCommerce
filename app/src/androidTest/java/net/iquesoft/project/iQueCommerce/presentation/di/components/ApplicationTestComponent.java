package net.iquesoft.project.iQueCommerce.presentation.di.components;


import android.content.Context;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ApplicationModuleTest;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.navigation.Navigator;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.SplashActivityTest;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModuleTest.class)
public interface ApplicationTestComponent extends ApplicationComponent {

    void inject(SplashActivityTest splashActivityTest);


    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    Navigator navigator();

    UserModel userModel();

    SelectedProductModel selectedProductModel();

    CartModel cartModel();

    ShopifyManager shopifyManager();

    ShopModel shopModel();

    JSONCountriesReader jsonCountriesReader();

    RealmDataBase realmDataBase();

}