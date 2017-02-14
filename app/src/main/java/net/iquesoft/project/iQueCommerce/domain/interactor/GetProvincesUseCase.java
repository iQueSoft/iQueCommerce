package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class GetProvincesUseCase extends UseCase {
    private final RepositoryProvider repositoryProvider;
    private String country;

    @Inject
    public GetProvincesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.repositoryProvider.getProvinces(this.country);
    }
}
