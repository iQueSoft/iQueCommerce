package net.iquesoft.project.iQueCommerce.presentation.presenter;

import com.shopify.buy.model.Product;

import net.iquesoft.project.iQueCommerce.domain.interactor.DefaultSubscriber;
import net.iquesoft.project.iQueCommerce.domain.interactor.GetProductsListByIdUseCase;
import net.iquesoft.project.iQueCommerce.domain.interactor.SetProductQuantityUseCase;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.model.ShopModel;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.CartFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CartActivityPresenter implements Presenter {

    private static final int PRODUCT_VARIANT = 0;// Sending 0 as product variant. Should be changed to real variant which depends on customer choice
    private final CartModel cartModel;
    private final ShopModel shopModel;
    private final SetProductQuantityUseCase setProductQuantityUseCase;
    private final GetProductsListByIdUseCase getProductsListByIdUseCase;
    private CartFragment fragmentView;

    @Inject
    public CartActivityPresenter(GetProductsListByIdUseCase getProductsListByIdUseCase,
                                 SetProductQuantityUseCase setProductQuantityUseCase,
                                 CartModel cartModel,
                                 ShopModel shopModel) {
        this.getProductsListByIdUseCase = getProductsListByIdUseCase;
        this.setProductQuantityUseCase = setProductQuantityUseCase;
        this.cartModel = cartModel;
        this.shopModel = shopModel;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setView(CartFragment cartFragment) {
        this.fragmentView = cartFragment;
    }

    public void initialize() {
        this.fragmentView.showContent(this.cartModel.getOfferedProducts(), this.cartModel.getQuantityList(), this.calculateTotalPrice(), this.shopModel.getMoneyFormat());
    }

    public void removeItem(int adapterPosition) {
        this.cartModel.getQuantityList().remove(this.cartModel.getOfferedProducts().get(adapterPosition).getProductId());
        this.cartModel.getOfferedProducts().remove(adapterPosition);
        this.cartModel.setItemCount(this.cartModel.getOfferedProducts().size());
    }

    public float getTotalPrice() {
        return this.calculateTotalPrice();
    }


    public int getCartItemCount() {
        return this.cartModel.getItemCount();
    }

    public void decreaseItemQuantity(int position) {
        int oldQuantity = this.cartModel.getQuantityList().get(this.cartModel.getOfferedProducts().get(position).getProductId());
        this.cartModel.getQuantityList().put(this.cartModel.getOfferedProducts().get(position).getProductId(), --oldQuantity);
    }

    public void increaseItemQuantity(int position) {
        int oldQuantity = this.cartModel.getQuantityList().get(this.cartModel.getOfferedProducts().get(position).getProductId());
        this.cartModel.getQuantityList().put(this.cartModel.getOfferedProducts().get(position).getProductId(), ++oldQuantity);
    }

    public String getMoneyFormat() {
        return this.shopModel.getMoneyFormat();
    }

    public void updateCart() {
        this.getProductsById();
    }


    private float calculateTotalPrice() {
        float totalPrice = 0;
        for (ProductModel productModel : this.cartModel.getOfferedProducts()) {
            totalPrice += Float.parseFloat(productModel.getMinimumPrice()) * this.cartModel.getQuantityList().get(productModel.getProductId());
        }
        this.cartModel.setTotalPrice(totalPrice);
        return totalPrice;
    }

    private void getProductsById() {
        List<Long> ids = new ArrayList<>(this.cartModel.getOfferedProducts().size());
        for (ProductModel productModel : this.cartModel.getOfferedProducts()) {
            ids.add(productModel.getProductId());
        }
        this.getProductsListByIdUseCase.setArguments(ids);
        this.getProductsListByIdUseCase.execute(new ProductListSubscriber());
    }

    private void addProductsToCart(List<Product> products) {
        for (Product product : products) {
            this.setProductQuantityUseCase.setArguments(product, PRODUCT_VARIANT, this.cartModel.getQuantityList().get(product.getProductId()));
            this.setProductQuantityUseCase.execute(new DefaultSubscriber());
        }
    }


    private class ProductListSubscriber extends DefaultSubscriber<List<Product>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Product> products) {
            super.onNext(products);
            CartActivityPresenter.this.addProductsToCart(products);
        }
    }

}
