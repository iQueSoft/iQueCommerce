package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class SetShippingRatesUseCase extends UseCase {
    private final CustomerProvider customerProvider;

    @Inject
    public SetShippingRatesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
