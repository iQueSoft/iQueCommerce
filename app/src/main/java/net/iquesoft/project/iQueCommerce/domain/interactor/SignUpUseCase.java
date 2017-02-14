package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;

import javax.inject.Inject;

import rx.Observable;

public class SignUpUseCase extends UseCase {


    private final CustomerProvider customerProvider;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Inject
    public SignUpUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    public void setCredentials(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.signUp(email, password, firstName, lastName);
    }
}
