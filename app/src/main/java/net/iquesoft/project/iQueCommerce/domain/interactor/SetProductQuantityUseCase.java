package net.iquesoft.project.iQueCommerce.domain.interactor;

import com.shopify.buy.model.Product;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.ShopProvider;

import javax.inject.Inject;

import rx.Observable;

public class SetProductQuantityUseCase extends UseCase {

    private final ShopProvider shopProvider;
    private int productVariant;
    private Integer quantity;
    private Product product;

    @Inject
    public SetProductQuantityUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopProvider shopProvider) {
        super(threadExecutor, postExecutionThread);
        this.shopProvider = shopProvider;
    }

    public void setArguments(Product product, int productVariant, Integer quantity) {
        this.product = product;
        this.productVariant = productVariant;
        this.quantity = quantity;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.shopProvider.setProductQuantity(this.product, this.productVariant, this.quantity);
    }

}
