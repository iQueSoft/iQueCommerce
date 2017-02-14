package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.CustomerDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.RepositoryDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.ShopDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.CreateCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCountriesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProvincesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetShippingRatesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SetShippingRatesUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.UpdateCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CustomerInfoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andre on 13-Sep-16.
 */
@Module
public class CustomerInfoModule {
    public CustomerInfoModule() {
    }


    @Provides
    @PerActivity
    ShopProvider provideShopProvider(ShopifyManager shopifyManager) {
        return new ShopDataProvider(shopifyManager);
    }

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
    CreateCheckoutUseCase provideCreateCheckoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        return new CreateCheckoutUseCase(threadExecutor, postExecutionThread, shopProvider);
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


    @Provides
    @PerActivity
    UpdateCheckoutUseCase provideUpdateCheckoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new UpdateCheckoutUseCase(threadExecutor, postExecutionThread, customerProvider);
    }


    @Provides
    @PerActivity
    GetShippingRatesUseCase provideGetShippingRatesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new GetShippingRatesUseCase(threadExecutor, postExecutionThread, customerProvider);
    }


    @Provides
    @PerActivity
    SetShippingRatesUseCase provideSetShippingRatesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new SetShippingRatesUseCase(threadExecutor, postExecutionThread, customerProvider);
    }


    @Provides
    @PerActivity
    GetCustomerDetailsUseCase provideGetCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new GetCustomerDetailsUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }


    @Provides
    @PerActivity
    SaveCustomerDetailsUseCase provideSaveCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new SaveCustomerDetailsUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    CustomerInfoPresenter provideCustomerInfoPresenter(CreateCheckoutUseCase createCheckoutUseCase,
                                                       GetCountriesUseCase getCountriesUseCase,
                                                       GetProvincesUseCase getProvincesUseCase,
                                                       UpdateCheckoutUseCase updateCheckoutUseCase,
                                                       GetShippingRatesUseCase getShippingRatesUseCase,
                                                       SetShippingRatesUseCase setShippingRatesUseCase,
                                                       GetCustomerDetailsUseCase getCustomerDetailsUseCase,
                                                       SaveCustomerDetailsUseCase saveCustomerDetailsUseCase,
                                                       UserModel userModel) {
        return new CustomerInfoPresenter(createCheckoutUseCase, getCountriesUseCase, getProvincesUseCase, updateCheckoutUseCase, getShippingRatesUseCase, setShippingRatesUseCase, getCustomerDetailsUseCase, saveCustomerDetailsUseCase, userModel);
    }

}
