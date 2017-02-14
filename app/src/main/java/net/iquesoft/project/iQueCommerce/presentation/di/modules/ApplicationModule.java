/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import android.app.Application;
import android.content.Context;

import net.iquesoft.project.iQueCommerce.data.executor.JobExecutor;
import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
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

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
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
        return new ShopifyManager(jobExecutor);
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
