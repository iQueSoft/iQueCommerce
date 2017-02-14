package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.RepositoryDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsIdFromWishListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListByIdUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class WishListModule {


    @Provides
    @PerActivity
    ShopProvider provideShopProvider(ShopifyManager shopifyManager) {
        return new ShopDataProvider(shopifyManager);
    }

    @Provides
    @PerActivity
    RepositoryProvider provideRepositoryProvider(JSONCountriesReader jsonCountriesReader, RealmDataBase realmDataBase) {
        return new RepositoryDataProvider(jsonCountriesReader, realmDataBase);
    }

    @Provides
    @PerActivity
    GetProductsListByIdUseCase provideGetProductsListByIdUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        return new GetProductsListByIdUseCase(threadExecutor, postExecutionThread, shopProvider);
    }

    @Provides
    @PerActivity
    GetProductsIdFromWishListUseCase provideGetProductsIdFromWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new GetProductsIdFromWishListUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }
}
