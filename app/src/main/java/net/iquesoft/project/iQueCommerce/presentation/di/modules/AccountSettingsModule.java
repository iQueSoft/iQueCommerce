package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.CustomerDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.RepositoryDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCountriesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProvincesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class AccountSettingsModule {


    @Provides
    @PerActivity
    CustomerProvider provideCustomerProvider(ShopifyManager shopifyManager) {
        return new CustomerDataProvider(shopifyManager);
    }


    @Provides
    @PerActivity
    RepositoryProvider provideRepositoryProvider(JSONCountriesReader jsonCountriesReader, RealmDataBase realmDataBase) {
        return new RepositoryDataProvider(jsonCountriesReader, realmDataBase);
    }


    @Provides
    @PerActivity
    SaveCustomerDetailsUseCase provideSaveCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new SaveCustomerDetailsUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    GetCustomerDetailsUseCase provideGetCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new GetCustomerDetailsUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    GetCountriesUseCase provideGetCountriesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new GetCountriesUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }


    @Provides
    @PerActivity
    GetProvincesUseCase provideProvincesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new GetProvincesUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

}
