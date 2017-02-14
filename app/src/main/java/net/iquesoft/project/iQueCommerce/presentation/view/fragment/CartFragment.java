package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CartComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.CartActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.presenter.adapters.CartAdapter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CartActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.CartListView;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.LoadDataView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CartFragment extends BaseFragment implements CartListView, LoadDataView {

    @Inject
    CartActivityPresenter presenter;
    @Inject
    CartAdapter cartAdapter;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rv_products_cart_fragment)
    RecyclerView rv_products;
    @BindView(R.id.tv_product_total_price_fragment_cart)
    TextView tv_product_total_price;
    @BindView(R.id.btn_continue)
    TextView btn_continue;

    private JazzyRecyclerViewScrollListener jazzyScrollListener;
    private int mCurrentTransitionEffect = JazzyHelper.FLIP;
    private ProductsListListener productsListListener;
    final CartAdapter.OnItemClickListener onItemClickListener = new CartAdapter.OnItemClickListener() {
        @Override
        public void onProductItemClicked(ProductModel productModel) {
            if (CartFragment.this.presenter != null && productModel != null) {
                CartFragment.this.productsListListener.onProductClicked(productModel);
            }
        }

        @Override
        public void onDecreaseItemQuantity(int position) {
            if (CartFragment.this.presenter != null) {
                CartFragment.this.presenter.decreaseItemQuantity(position);
                CartFragment.this.updateCart();
            }
        }

        @Override
        public void onIncreaseItemQuantity(int position) {
            if (CartFragment.this.presenter != null) {
                CartFragment.this.presenter.increaseItemQuantity(position);
                CartFragment.this.updateCart();
            }
        }

        @Override
        public void onRemoveItemFromCart(int position) {
            if (CartFragment.this.presenter != null) {
                CartFragment.this.presenter.removeItem(position);
                CartFragment.this.updateCart();
            }
        }
    };

    @Override
    void initializeInjection() {
        getComponent(CartComponent.class).inject(this);
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
        this.fragmentManager = getFragmentManager();
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, null);
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
    }

    @Override
    public void onResume() {
        this.getCurrentActivity().changeBadgeCount(CartFragment.this.presenter.getCartItemCount());
        super.onResume();
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

    @Override
    public void showContent(List<ProductModel> productModelList, HashMap<Long, Integer> quantityList, float totalPrice, String currency) {
        if (productModelList != null && quantityList != null) {
            this.cartAdapter.setProductsCollection(this.getContext(), productModelList, quantityList, currency);
            this.setTotalPrice(totalPrice);
        }
    }


    @OnClick(R.id.btn_continue)
    void showSummary() {
        this.presenter.updateCart();
        this.navigator.goToPaymentActivity(this.getCurrentActivity());
    }


    private CartActivity getCurrentActivity() {
        return (CartActivity) getActivity();
    }

    private void setupRecyclerView() {
        this.cartAdapter.setOnItemClickListener(this.onItemClickListener);

        this.jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        this.rv_products.setOnScrollListener(jazzyScrollListener);

        this.rv_products.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.rv_products.setAdapter(this.cartAdapter);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                CartFragment.this.presenter.removeItem(viewHolder.getAdapterPosition());
                CartFragment.this.updateCart();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(this.rv_products);
    }

    private void updateCart() {
        CartFragment.this.setTotalPrice(CartFragment.this.presenter.getTotalPrice());
        CartFragment.this.getCurrentActivity().changeBadgeCount(CartFragment.this.presenter.getCartItemCount());
        CartFragment.this.cartAdapter.notifyDataSetChanged();
    }

    private void setTotalPrice(float totalPrice) {
        String s = "Total price: " + this.presenter.getMoneyFormat() + totalPrice;
        this.tv_product_total_price.setText(s);
    }

    private void setupJazziness(int effect) {
        this.mCurrentTransitionEffect = effect;
        this.jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }

    public interface ProductsListListener {
        void onProductClicked(final ProductModel productModel);
    }

}
