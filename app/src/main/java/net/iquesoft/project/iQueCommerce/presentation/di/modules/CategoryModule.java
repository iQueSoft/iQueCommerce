package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.RepositoryDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCategoriesListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.IsProductInWishListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.RemoveProductFromWishListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveProductToWishListUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class CategoryModule {
    private long categoryId = -1;

    public CategoryModule() {
    }

    public CategoryModule(long categoryId) {
        this.categoryId = categoryId;
    }


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
    GetCategoriesListUseCase provideGetAllCategories(ShopProvider shopProvider, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetCategoriesListUseCase(shopProvider, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    GetProductsListUseCase provideGetAllProducts(ShopProvider shopProvider, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetProductsListUseCase(this.categoryId, shopProvider, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    SaveProductToWishListUseCase provideSaveProductToWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new SaveProductToWishListUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    RemoveProductFromWishListUseCase provideRemoveProductFromWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new RemoveProductFromWishListUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    IsProductInWishListUseCase provideIsProductInWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new IsProductInWishListUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }
}
