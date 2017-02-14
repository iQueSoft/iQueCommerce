package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;

import javax.inject.Inject;

import rx.Observable;


public class GetCustomerDetailsUseCase extends UseCase {

    private final RepositoryProvider repositoryProvider;
    private Long customerId;

    @Inject
    public GetCustomerDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }

    public void setArguments(Long customerId) {
        this.customerId = customerId;
    }


    @Override
    protected Observable<UserModel> buildUseCaseObservable() {
        return this.repositoryProvider.getCustomerDetails(this.customerId);
    }
}
