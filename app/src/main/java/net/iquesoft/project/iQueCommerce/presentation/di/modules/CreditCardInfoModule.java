package net.iquesoft.project.iQueCommerce.presentation.di.modules;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.interactor.CompleteCheckoutUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.StoreCreditCardUseCase;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CreditCardInfoPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class CreditCardInfoModule {

    public CreditCardInfoModule() {
    }


    @Provides
    @PerActivity
    StoreCreditCardUseCase provideStoreCreditCardUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new StoreCreditCardUseCase(threadExecutor, postExecutionThread, customerProvider);
    }


    @Provides
    @PerActivity
    CompleteCheckoutUseCase provideCompleteCheckoutUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        return new CompleteCheckoutUseCase(threadExecutor, postExecutionThread, customerProvider);
    }


    @Provides
    @PerActivity
    CreditCardInfoPresenter provideCreditCardInfoPresenter(StoreCreditCardUseCase storeCreditCardUseCase,
                                                           CompleteCheckoutUseCase completeCheckoutUseCase) {
        return new CreditCardInfoPresenter(storeCreditCardUseCase, completeCheckoutUseCase);
    }
}
