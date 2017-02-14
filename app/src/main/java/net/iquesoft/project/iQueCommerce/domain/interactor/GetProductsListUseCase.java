package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import javax.inject.Inject;

import rx.Observable;

public class GetProductsListUseCase extends UseCase {

    private final ShopProvider shopProvider;
    private final long categoryId;
    private int pageNumber;

    @Inject
    public GetProductsListUseCase(long categoryId, ShopProvider shopProvider, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.categoryId = categoryId;
        this.shopProvider = shopProvider;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    @Override
    public Observable buildUseCaseObservable() {
        return this.shopProvider.products(this.pageNumber, this.categoryId);
    }

}

