package net.iquesoft.project.iQueCommerce.presentation.presenter;

import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetCategoriesListUseCase;
import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.mapper.ModelDataMapper;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.MainFragment;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PrimaryActivityPresenter implements Presenter {

    private final GetCategoriesListUseCase getAllCategories;
    private final CartModel cartModel;
    private final ModelDataMapper categoriesModelDataMapper;
    private MainFragment fragmentView;

    @Inject
    public PrimaryActivityPresenter(GetCategoriesListUseCase getAllCategoriesUseCase,
                                    ModelDataMapper modelDataMapper,
                                    CartModel cartModel) {
        this.getAllCategories = getAllCategoriesUseCase;
        this.categoriesModelDataMapper = modelDataMapper;
        this.cartModel = cartModel;
    }

    public void setView(MainFragment view) {
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
        this.getAllCategories.unsubscribe();
    }

    public void initialize() {
        this.getListOfAllCategories(1);
    }

    private void getListOfAllCategories(int nextPageNumber) {
        this.fragmentView.showLoading();
        this.getAllCategories.setPageNumber(nextPageNumber);
        this.getAllCategories.execute(new UseCaseSubscriber());
    }

    private void showCategoriesInView(List<com.shopify.buy.model.Collection> categoriesCollection) {
        Collection<CategoryModel> categoriesModelCollection = this.categoriesModelDataMapper.transformCategoriesCollection(categoriesCollection);
        this.fragmentView.loadContent(categoriesModelCollection);

    }

    public int getCartItemCount() {
        return this.cartModel.getItemCount();
    }

    public void loadNextPage(int nextPageNumber) {
        this.getListOfAllCategories(nextPageNumber);
    }

    private class UseCaseSubscriber extends DefaultSubscriber<List<com.shopify.buy.model.Collection>> {
        @Override
        public void onCompleted() {
            PrimaryActivityPresenter.this.fragmentView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            PrimaryActivityPresenter.this.fragmentView.hideLoading();
            PrimaryActivityPresenter.this.fragmentView.showError(e.getLocalizedMessage());
        }

        @Override
        public void onNext(List<com.shopify.buy.model.Collection> collections) {
            PrimaryActivityPresenter.this.showCategoriesInView(collections);
        }
    }

}
