package net.iquesoft.project.iQueCommerce.presentation.presenter;

import com.shopify.buy.model.Shop;

import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetShopUseCase;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SplashFragment;
import net.iquesoft.project.iQueCommerce.utils.Constants;

import javax.inject.Inject;

public class SplashActivityPresenter implements Presenter {

    private final ModelDataMapper modelDataMapper;
    private final GetShopUseCase shopUseCase;
    private SplashFragment fragmentView;

    @Inject
    public SplashActivityPresenter(GetShopUseCase shopUseCase,
                                   ModelDataMapper modelDataMapper) {
        this.shopUseCase = shopUseCase;
        this.modelDataMapper = modelDataMapper;
    }

    public void setView(SplashFragment splashFragment) {
        this.fragmentView = splashFragment;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.shopUseCase.unsubscribe();
    }

    public void initiateAppLaunching() {
        this.fragmentView.showLoading();
        this.shopUseCase.execute(new UseCaseSubscriber());
    }

    private class UseCaseSubscriber extends DefaultSubscriber<Shop> {
        @Override
        public void onCompleted() {
            SplashActivityPresenter.this.fragmentView.hideLoading();
            SplashActivityPresenter.this.fragmentView.navigateToMainActivity();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            SplashActivityPresenter.this.fragmentView.hideLoading();
            SplashActivityPresenter.this.fragmentView.showToast(Constants.ERROR_MESSAGE_UNKNOWN_ERROR);
        }

        @Override
        public void onNext(Shop shop) {
            SplashActivityPresenter.this.modelDataMapper.transformShop(shop);
        }
    }
}
