package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import net.iquesoft.project.iQueCommerce.data.executor.JobExecutor;
import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.net.ShopifyManagerTest;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.AndroidApplication;
import net.iquesoft.project.iQueCommerce.presentation.UIThread;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModuleTest {

    private final AndroidApplication application;

    public ApplicationModuleTest(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    UserModel provideUserModel() {
        return new UserModel();
    }

    @Provides
    @Singleton
    ShopProvider provideUserRepository(ShopDataProvider shopDataProvider) {
        return shopDataProvider;
    }

    @Provides
    @Singleton
    SelectedProductModel provideSelectedProductModel() {
        return new SelectedProductModel();
    }

    @Provides
    @Singleton
    CartModel provideCartModel() {
        return new CartModel();
    }

    @Provides
    @Singleton
    ShopifyManager provideShopifyManager(JobExecutor jobExecutor) {
        return new ShopifyManagerTest(jobExecutor);
    }

    @Provides
    @Singleton
    JSONCountriesReader providesJsonCountriesReader() {
        return new JSONCountriesReader();
    }

    @Provides
    @Singleton
    RealmDataBase provideRealmDataBase() {
        return new RealmDataBase(this.application);
    }

    @Provides
    @Singleton
    ShopModel provideShopModel() {
        return new ShopModel();
    }

}