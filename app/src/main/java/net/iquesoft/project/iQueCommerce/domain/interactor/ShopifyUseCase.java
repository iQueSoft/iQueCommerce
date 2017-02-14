package net.iquesoft.project.iQueCommerce.domain.interactor;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import javax.inject.Inject;

import rx.Observable;

public class ShopifyUseCase extends UseCase {

    private final ShopProvider shopProvider;

    @Inject
    protected ShopifyUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        super(threadExecutor, postExecutionThread);
        this.shopProvider = shopProvider;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.shopProvider.shop();
    }
}
