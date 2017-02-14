package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;


@PerActivity
public class GetCategoriesListUseCase extends UseCase {

    private final ShopProvider shopProvider;
    private int pageNumber;

    @Inject
    public GetCategoriesListUseCase(ShopProvider shopProvider, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.shopProvider = shopProvider;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.shopProvider.collection(pageNumber);
    }
}
