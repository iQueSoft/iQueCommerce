package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.WishListComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.WishListPresenter;
import net.iquesoft.project.iQueCommerce.presentation.presenter.adapters.ProductsAdapter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.WishListActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.ProductsGridView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WishListFragment extends BaseFragment implements ProductsGridView {

    private static final String WISH_LIST = "Wish list";
    private static final int SPAN_COUNT = 2;
    @Inject
    WishListPresenter presenter;
    @Inject
    ProductsAdapter productsAdapter;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rv_products_wish_list)
    RecyclerView rv_products;
    private int mCurrentTransitionEffect = JazzyHelper.HELIX;
    private JazzyRecyclerViewScrollListener jazzyScrollListener;
    private ProductsListListener productsListListener;
    private final ProductsAdapter.ProductsAdapterListener productsAdapterListener = new ProductsAdapter.ProductsAdapterListener() {
        @Override
        public void onProductItemClicked(ProductModel productModel) {
            if (WishListFragment.this.presenter != null && productModel != null) {
                WishListFragment.this.productsListListener.onProductClicked(productModel);
            }
        }

        @Override
        public void onAddToCartButtonClicked(ProductModel productModel) {
            if (WishListFragment.this.presenter != null && productModel != null) {
                WishListFragment.this.productsListListener.onAddToCartButtonClicked(productModel);
            }
        }

        @Override
        public void onListEndReached(int nextPageNumber) {
        }
    };

    @Override
    void initializeInjection() {
//        getComponent(WishListComponent.class).inject(this);
    }

    public void showSnackbar() {
        this.getCurrentActivity().showSnackbar(getActivity().getCurrentFocus());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof WishListFragment.ProductsListListener) {
            this.productsListListener = (ProductsListListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(WishListComponent.class).inject(this);
        this.fragmentManager = getFragmentManager();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, null);
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
        this.getCurrentActivity().hideFAB();
        this.getCurrentActivity().setToolbarTitle(WISH_LIST);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
        this.getCurrentActivity().changeBadgeCount(this.presenter.getCartItemCount());
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

    private WishListActivity getCurrentActivity() {
        return (WishListActivity) getActivity();
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

    public interface ProductsListListener {
        void onProductClicked(final ProductModel productModel);

        void onAddToCartButtonClicked(ProductModel productModel);
    }

}
