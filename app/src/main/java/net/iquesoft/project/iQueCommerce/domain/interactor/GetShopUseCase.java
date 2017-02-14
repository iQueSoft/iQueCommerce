package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import rx.Observable;

public class GetShopUseCase extends UseCase {

    private final ShopProvider shopProvider;

    public GetShopUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        super(threadExecutor, postExecutionThread);
        this.shopProvider = shopProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.shopProvider.shop();
    }
}
