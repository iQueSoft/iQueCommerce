package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.RepositoryProvider;

import javax.inject.Inject;

import rx.Observable;

public class IsProductInWishListUseCase extends UseCase {

    private final RepositoryProvider repositoryProvider;
    private Long productId;

    @Inject
    public IsProductInWishListUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RepositoryProvider repositoryProvider) {
        super(threadExecutor, postExecutionThread);
        this.repositoryProvider = repositoryProvider;
    }

    public void setArguments(Long productId) {
        this.productId = productId;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return this.repositoryProvider.isProductInWishList(this.productId);
    }
}
