package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;

import javax.inject.Inject;

import rx.Observable;

public class RenewCustomerTokenUseCase extends UseCase {

    private final CustomerProvider customerProvider;

    @Inject
    public RenewCustomerTokenUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.renewCustomerToken();
    }
}
