package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetShopUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.presenter.SplashActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.SplashActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {
    private final SplashActivity splashActivity;

    public SplashActivityModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }


    @Provides
    @PerActivity
    SplashActivity provideActivity() {
        return this.splashActivity;
    }


    @Provides
    @PerActivity
    ShopProvider provideShopProvider(ShopifyManager shopifyManager) {
        return new ShopDataProvider(shopifyManager);
    }


    @Provides
    @PerActivity
    GetShopUseCase provideGetShopUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        return new GetShopUseCase(threadExecutor, postExecutionThread, shopProvider);
    }

    @Provides
    @PerActivity
    SplashActivityPresenter providePresenter(GetShopUseCase getShopUseCase, ModelDataMapper modelDataMapper) {
        return new SplashActivityPresenter(getShopUseCase, modelDataMapper);
    }
}
