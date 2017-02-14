package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.data.net.ShopifyManager;
import net.iquesoft.project.iQueCommerce.data.providers.CustomerDataProvider;
import net.iquesoft.project.iQueCommerce.data.providers.RepositoryDataProvider;
import net.iquesoft.project.iQueCommerce.data.repository.JSONCountriesReader;
import net.iquesoft.project.iQueCommerce.data.repository.RealmDataBase;
import net.iquesoft.project.iQueCommerce.domain.PreferencesManager;
import net.iquesoft.project.iQueCommerce.domain.RegularExpressionValidation;
import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCustomerTokenUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.LogInUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.LogOutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.RenewCustomerTokenUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SaveCustomerDetailsUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SignUpUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.presenter.UserLoginPresenter;
import net.iquesoft.project.iQueCommerce.presentation.presenter.UserSignUpPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private final MainActivity mainActivity;

    public LoginModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @PerActivity
    MainActivity provideMainActivity() {
        return mainActivity;
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
    SaveCustomerDetailsUseCase provideSaveCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        return new SaveCustomerDetailsUseCase(threadExecutor, postExecutionThread, repositoryProvider);
    }

    @Provides
    @PerActivity
    SignUpUseCase provideSignUpUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new SignUpUseCase(threadExecutor, postExecutionThread, customerProvider);
    }

    @Provides
    @PerActivity
    LogInUseCase provideLogInUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new LogInUseCase(threadExecutor, postExecutionThread, customerProvider);
    }

    @Provides
    @PerActivity
    LogOutUseCase provideLogOutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new LogOutUseCase(threadExecutor, postExecutionThread, customerProvider);
    }

    @Provides
    @PerActivity
    GetCustomerTokenUseCase provideGetCustomerTokenUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new GetCustomerTokenUseCase(threadExecutor, postExecutionThread, customerProvider);
    }

    @Provides
    @PerActivity
    RenewCustomerTokenUseCase provideRenewCustomerTokenUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new RenewCustomerTokenUseCase(threadExecutor, postExecutionThread, customerProvider);
    }

    @Provides
    @PerActivity
    UserLoginPresenter provideUserLoginPresenter(MainActivity mainActivity,
                                                 GetCustomerTokenUseCase getCustomerTokenUseCase,
                                                 RenewCustomerTokenUseCase renewCustomerTokenUseCase,
                                                 SaveCustomerDetailsUseCase saveCustomerDetailsUseCase,
                                                 LogInUseCase logInUseCase,
                                                 LogOutUseCase logOutUseCase,
                                                 RegularExpressionValidation regularExpressionValidation,
                                                 ModelDataMapper modelDataMapper,
                                                 PreferencesManager preferencesManager) {
        return new UserLoginPresenter(mainActivity, getCustomerTokenUseCase, renewCustomerTokenUseCase, saveCustomerDetailsUseCase, logInUseCase, logOutUseCase, regularExpressionValidation, modelDataMapper, preferencesManager);
    }


    @Provides
    @PerActivity
    UserSignUpPresenter provideUserSignUpPresenter(SignUpUseCase signUpUseCase, MainActivity mainActivity,
                                                   RegularExpressionValidation regularExpressionValidation,
                                                   ModelDataMapper modelDataMapper) {
        return new UserSignUpPresenter(signUpUseCase, mainActivity, regularExpressionValidation, modelDataMapper);
    }

}
