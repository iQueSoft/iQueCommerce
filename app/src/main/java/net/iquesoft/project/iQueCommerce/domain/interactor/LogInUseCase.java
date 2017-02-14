package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;

import javax.inject.Inject;

import rx.Observable;

public class LogInUseCase extends UseCase {

    private final CustomerProvider customerProvider;
    private String password;
    private String email;

    @Inject
    public LogInUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }


    public void setCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.logIn(email, password);
    }
}
