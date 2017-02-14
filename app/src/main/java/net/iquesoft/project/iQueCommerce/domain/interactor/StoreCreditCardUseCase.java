package net.iquesoft.project.iQueCommerce.domain.interactor;

import com.shopify.buy.model.CreditCard;

import net.iquesoft.project.iQueCommerce.domain.executor.PostExecutionThread;
import net.iquesoft.project.iQueCommerce.domain.executor.ThreadExecutor;
import net.iquesoft.project.iQueCommerce.domain.provider.CustomerProvider;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class StoreCreditCardUseCase extends UseCase {
    private final CustomerProvider customerProvider;
    private CreditCard creditCard;

    @Inject
    public StoreCreditCardUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CustomerProvider customerProvider) {
        super(threadExecutor, postExecutionThread);
        this.customerProvider = customerProvider;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.customerProvider.storeCreditCard(creditCard);
    }

}
