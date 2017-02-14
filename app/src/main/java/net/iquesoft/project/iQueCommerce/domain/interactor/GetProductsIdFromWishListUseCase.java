package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class GetProductsIdFromWishListUseCase extends UseCase {

    private final RepositoryProvider repositoryProvider;

    @Inject
    public GetProductsIdFromWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }


    @Override
    protected Observable<List<Long>> buildUseCaseObservable() {
        return this.repositoryProvider.getProductsIdFromWishList();
    }
}
