package net.iquesoft.project.iQueCommerce.domain.interactor;

import com.shopify.buy.model.Product;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import java.util.List;

import rx.Observable;

public class GetProductsListByIdUseCase extends UseCase {

    private final ShopProvider shopProvider;
    private List<Long> ids;

    public GetProductsListByIdUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        super(threadExecutor, postExecutionThread);
        this.shopProvider = shopProvider;
    }

    public void setArguments(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    protected Observable<List<Product>> buildUseCaseObservable() {
        return this.shopProvider.products(ids);
    }
}
