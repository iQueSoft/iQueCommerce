package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;

import javax.inject.Inject;

import rx.Observable;

public class GetCustomerTokenUseCase extends UseCase {


    private final CustomerProvider customerProvider;

    @Inject
    public GetCustomerTokenUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.getCustomerToken();
    }
}
