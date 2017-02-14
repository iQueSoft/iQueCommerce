package net.iquesoft.project.iQueCommerce.presentation.presenter;

import com.shopify.buy.model.Product;

import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsIdFromWishListUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListByIdUseCase;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.WishListFragment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class WishListPresenter implements Presenter {

    private final GetProductsListByIdUseCase getProductsListByIdUseCase;
    private final CartModel cartModel;
    private final ModelDataMapper categoriesModelDataMapper;
    private final ShopModel shopModel;
    private final SelectedProductModel selectedProductModel;
    private final GetProductsIdFromWishListUseCase getProductsIdFromWishListUseCase;
    private WishListFragment fragmentView;

    @Inject
    public WishListPresenter(CartModel cartModel,
                             ShopModel shopModel,
                             ModelDataMapper modelDataMapper,
                             SelectedProductModel selectedProductModel,
                             GetProductsListByIdUseCase getProductsListByIdUseCase,
                             GetProductsIdFromWishListUseCase getProductsIdFromWishListUseCase) {
        this.categoriesModelDataMapper = modelDataMapper;
        this.cartModel = cartModel;
        this.shopModel = shopModel;
        this.selectedProductModel = selectedProductModel;
        this.getProductsListByIdUseCase = getProductsListByIdUseCase;
        this.getProductsIdFromWishListUseCase = getProductsIdFromWishListUseCase;
    }

    public void resume() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        if (this.getProductsListByIdUseCase != null) {
            this.getProductsListByIdUseCase.unsubscribe();
        }
    }


    public int getCartItemCount() {
        return this.cartModel.getItemCount();
    }

    public void setView(WishListFragment wishListFragment) {
        this.fragmentView = wishListFragment;
    }

    public void initialize() {
        this.fragmentView.showLoading();
        this.getProductsIdFromWishListUseCase.executeRealm(new GetProductsIdFromWishListSubscriber());
    }


    public void addToCart(ProductModel selectedProduct) {
        if (isExistInList(selectedProduct)) {
            this.cartModel.getQuantityList().put(selectedProduct.getProductId(), this.cartModel.getQuantityList().get(selectedProduct.getProductId()) + 1);
        } else {
            this.cartModel.setItemCount(this.cartModel.getItemCount() + 1);
            this.cartModel.getOfferedProducts().add(selectedProduct);
            this.cartModel.getQuantityList().put(selectedProduct.getProductId(), 1);
        }
    }

    public ProductModel getSelectedProduct() {
        return this.selectedProductModel.getSelectedProductModel();
    }

    private void getProductsListById(List<Long> productIds) {
        this.getProductsListByIdUseCase.setArguments(productIds);
        this.getProductsListByIdUseCase.execute(new GetProductsByIdSubscriber());
    }


    private boolean isExistInList(ProductModel selectedProduct) {
        return this.cartModel.getQuantityList().containsKey(selectedProduct.getProductId());
    }

    private void showProductsInView(List<com.shopify.buy.model.Product> productsCollection) {
        Collection<ProductModel> productsModelCollection = this.categoriesModelDataMapper.transformProductCollection(productsCollection);
        this.fragmentView.loadContent(productsModelCollection, this.shopModel.getMoneyFormat());
    }


    private class GetProductsIdFromWishListSubscriber extends DefaultSubscriber<List<Long>> {

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            WishListPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onNext(List<Long> productIds) {
            super.onNext(productIds);
            WishListPresenter.this.getProductsListById(productIds);
        }
    }

    private class GetProductsByIdSubscriber extends DefaultSubscriber<List<Product>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
            WishListPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            WishListPresenter.this.fragmentView.hideLoading();
            WishListPresenter.this.fragmentView.showError(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

        @Override
        public void onNext(List<Product> products) {
            super.onNext(products);
            WishListPresenter.this.showProductsInView(products);
        }
    }


}
