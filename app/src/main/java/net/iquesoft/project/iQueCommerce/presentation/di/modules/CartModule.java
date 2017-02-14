package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListByIdUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SetProductQuantityUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class CartModule {

    public CartModule() {
    }

    @Provides
    @PerActivity
    ShopProvider provideShopProvider(ShopifyManager shopifyManager) {
        return new ShopDataProvider(shopifyManager);
    }

    @Provides
    @PerActivity
    SetProductQuantityUseCase provideSetProductQuantityUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        return new SetProductQuantityUseCase(threadExecutor, postExecutionThread, shopProvider);
    }

    @Provides
    @PerActivity
    GetProductsListByIdUseCase provideGetProductsListByIdUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        return new GetProductsListByIdUseCase(threadExecutor, postExecutionThread, shopProvider);
    }
}
