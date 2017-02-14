package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;


@PerActivity
public class GetCountriesUseCase extends UseCase {

    private final RepositoryProvider repositoryProvider;

    @Inject
    public GetCountriesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.repositoryProvider.getCountries();
    }
}
