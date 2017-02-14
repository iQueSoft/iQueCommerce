package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.domain.PreferencesManager;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CategoryActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.presenter.adapters.ProductsAdapter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CategoryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.ProductsGridView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends BaseFragment implements ProductsGridView {

    private static final int SPAN_COUNT = 2;
    @Inject
    CategoryActivityPresenter presenter;
    @Inject
    ProductsAdapter productsAdapter;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rv_products)
    RecyclerView rv_products;
    private int mCurrentTransitionEffect = JazzyHelper.HELIX;
    private JazzyRecyclerViewScrollListener jazzyScrollListener;
    private ProductsListListener productsListListener;
    private final ProductsAdapter.ProductsAdapterListener productsAdapterListener = new ProductsAdapter.ProductsAdapterListener() {
        @Override
        public void onProductItemClicked(ProductModel productModel) {
            if (CategoryFragment.this.presenter != null && productModel != null) {
                CategoryFragment.this.productsListListener.onProductClicked(productModel);
            }
        }

        @Override
        public void onAddToCartButtonClicked(ProductModel productModel) {
            if (CategoryFragment.this.presenter != null && productModel != null) {
                CategoryFragment.this.productsListListener.onAddToCartButtonClicked(productModel);
            }
        }

        @Override
        public void onListEndReached(int nextPageNumber) {
            if (CategoryFragment.this.presenter != null) {
                CategoryFragment.this.presenter.loadNextPage(nextPageNumber);
            }
        }
    };

    public void showSnackbar() {
        this.getCurrentActivity().showSnackbar(getActivity().getCurrentFocus());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProductsListListener) {
            this.productsListListener = (ProductsListListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, view);
        this.setupRecyclerView();
        this.setupJazziness(mCurrentTransitionEffect);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getCurrentActivity().hideFAB();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_view_menu) {
            this.changeCategoriesView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeCategoriesView() {
        if (this.presenter.getCategoryView(this.getContext()).equals(PreferencesManager.GRID_VIEW)) {
            this.presenter.setCategoryView(this.getContext(), PreferencesManager.LIST_VIEW);
            this.rv_products.setLayoutManager(new LinearLayoutManager(this.getCurrentActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            this.presenter.setCategoryView(this.getContext(), PreferencesManager.GRID_VIEW);
            this.rv_products.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
        this.setToolbarTitle();
        this.getCurrentActivity().changeBadgeCount(this.presenter.getCartItemCount());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    void initializeInjection() {
        this.getComponent(CategoryComponent.class).inject(this);
    }

    @Override
    public void onItemClicked(ProductModel productModel) {

    }

    @Override
    public void loadContent(Collection<ProductModel> modelCollection, String currency) {
        if (modelCollection != null) {
            this.productsAdapter.setProductsCollection(this.getContext(), modelCollection, currency);
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (this.rl_progress != null) {
            this.rl_progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        ToastMaker.showMessage(getActivity(), message, ToastMaker.duration.LONG);
    }

    private void setupRecyclerView() {
        this.productsAdapter.setProductsAdapterListener(this.productsAdapterListener);

        this.jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        this.rv_products.setOnScrollListener(jazzyScrollListener);

        this.rv_products.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        this.rv_products.setAdapter(this.productsAdapter);
    }

    private void setupJazziness(int effect) {
        this.mCurrentTransitionEffect = effect;
        this.jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }

    public void setToolbarTitle() {
        this.getCurrentActivity().setToolbarTitle(CategoryActivity.categoryTitle);
    }

    private CategoryActivity getCurrentActivity() {
        return (CategoryActivity) getActivity();
    }

    public interface ProductsListListener {
        void onProductClicked(final ProductModel productModel);

        void onAddToCartButtonClicked(ProductModel productModel);
    }


}
