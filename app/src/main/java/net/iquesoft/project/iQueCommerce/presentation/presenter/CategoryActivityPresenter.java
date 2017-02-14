package net.iquesoft.project.iQueCommerce.presentation.presenter;

import android.content.Context;

import net.iquesoft.project.iQueCommerce.domain.PreferencesManager;
import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListUseCase;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.SelectedProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CategoryFragment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class CategoryActivityPresenter implements Presenter {

    private final GetProductsListUseCase getAllProductsUseCase;
    private final ShopModel shopModel;
    private final PreferencesManager preferencesManager;
    private final SelectedProductModel selectedProductModel;
    private final CartModel cartModel;
    private final ModelDataMapper categoriesModelDataMapper;
    private CategoryFragment fragmentView;

    @Inject
    public CategoryActivityPresenter(GetProductsListUseCase getAllProductsUseCase,
                                     ModelDataMapper modelDataMapper,
                                     CartModel cartModel,
                                     ShopModel shopModel,
                                     SelectedProductModel selectedProductModel,
                                     PreferencesManager preferencesManager) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.categoriesModelDataMapper = modelDataMapper;
        this.cartModel = cartModel;
        this.shopModel = shopModel;
        this.selectedProductModel = selectedProductModel;
        this.preferencesManager = preferencesManager;
    }

    public void setView(CategoryFragment view) {
        this.fragmentView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        if (this.getAllProductsUseCase != null) {
            this.getAllProductsUseCase.unsubscribe();
        }
        this.fragmentView = null;
    }

    public void initialize() {
        this.fragmentView.showLoading();
        this.getListOfProducts(1);
    }

    private void getListOfProducts(int nextPageNumber) {
        this.fragmentView.showLoading();
        this.getAllProductsUseCase.setPageNumber(nextPageNumber);
        this.getAllProductsUseCase.execute(new GetAllProductsUseCaseSubscriber());
    }

    private void showProductsInView(List<com.shopify.buy.model.Product> productsCollection) {
        Collection<ProductModel> productsModelCollection = this.categoriesModelDataMapper.transformProductCollection(productsCollection);
        this.fragmentView.loadContent(productsModelCollection, this.shopModel.getMoneyFormat());
    }

    public ProductModel getSelectedProduct() {
        return this.selectedProductModel.getSelectedProductModel();
    }

    public void addToCart(ProductModel selectedProduct) {
        if (isExistInList(selectedProduct)) {
            this.cartModel.getQuantityList().put(selectedProduct.getProductId(), this.cartModel.getQuantityList().get(selectedProduct.getProductId()) + 1);
        } else {
            this.cartModel.setItemCount(this.cartModel.getItemCount() + 1);
            this.cartModel.getOfferedProducts().add(selectedProduct);
            this.cartModel.getQuantityList().put(selectedProduct.getProductId(), 1);
        }
        this.fragmentView.showSnackbar();
    }

    private boolean isExistInList(ProductModel selectedProduct) {
        return this.cartModel.getQuantityList().containsKey(selectedProduct.getProductId());
    }


    public String getCategoryView(Context context) {
        return this.preferencesManager.loadString(context, PreferencesManager.CATEGORY_VIEW);
    }

    public void setCategoryView(Context context, String categoryView) {
        this.preferencesManager.saveString(context, PreferencesManager.CATEGORY_VIEW, categoryView);
    }

    public int getCartItemCount() {
        return this.cartModel.getItemCount();
    }

    public void loadNextPage(int nextPageNumber) {
        this.getListOfProducts(nextPageNumber);
    }


    private class GetAllProductsUseCaseSubscriber extends DefaultSubscriber<List<com.shopify.buy.model.Product>> {

        @Override
        public void onCompleted() {
            CategoryActivityPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            CategoryActivityPresenter.this.fragmentView.hideLoading();
            CategoryActivityPresenter.this.fragmentView.showError(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

        @Override
        public void onNext(List<com.shopify.buy.model.Product> products) {
            CategoryActivityPresenter.this.showProductsInView(products);
        }
    }

}
