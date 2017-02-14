package net.iquesoft.project.iQueCommerce.presentation.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<ProductModel> productsCollection;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private HashMap<Long, Integer> quantityList;
    private String currency;

    @Inject
    public CartAdapter() {
        this.productsCollection = Collections.emptyList();
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item_cart, viewGroup, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final ProductModel productModel = this.productsCollection.get(position);
        holder.tv_product_title.setText(productModel.getTitle());
        String price = this.currency + productModel.getMinimumPrice();
        holder.tv_product_price.setText(price);
        Picasso.with(context)
                .load(productModel.getImages().get(0).getSrc())
                .placeholder(R.drawable.ic_folder_image)
                .error(R.drawable.place_holder_no_image)
                .into(holder.iv_product_image);
        holder.itemView.setOnClickListener(view -> {
            if (CartAdapter.this.onItemClickListener != null) {
                CartAdapter.this.onItemClickListener.onProductItemClicked(productModel);
            }
        });
        holder.iv_decrease_item_cart.setOnClickListener(view -> {
            if (CartAdapter.this.onItemClickListener != null) {
                CartAdapter.this.onItemClickListener.onDecreaseItemQuantity(position);
            }
        });
        holder.iv_increase_item_cart.setOnClickListener(view -> {
            if (CartAdapter.this.onItemClickListener != null) {
                CartAdapter.this.onItemClickListener.onIncreaseItemQuantity(position);
            }
        });
        holder.btn_close_item_cart.setOnClickListener(view -> {
            if (CartAdapter.this.onItemClickListener != null) {
                CartAdapter.this.onItemClickListener.onRemoveItemFromCart(position);
            }
        });
        String productQuantity = "Quantity: " + this.quantityList.get(productModel.getProductId());
        holder.tv_product_quantity.setText(productQuantity);
        if (this.quantityList.get(productModel.getProductId()) == 1) {
            holder.iv_decrease_item_cart.setVisibility(View.GONE);
        } else {
            holder.iv_decrease_item_cart.setVisibility(View.VISIBLE);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setProductsCollection(Context context, Collection<ProductModel> productsCollection, HashMap<Long, Integer> quantityList, String currency) {
        this.validateProductsCollection(productsCollection);
        this.quantityList = quantityList;
        this.context = context;
        this.currency = currency;
        this.productsCollection = (List<ProductModel>) productsCollection;
        this.notifyDataSetChanged();
    }

    private void validateProductsCollection(Collection<ProductModel> productsCollection) {
        if (productsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @Override
    public int getItemCount() {
        return (this.productsCollection != null) ? this.productsCollection.size() : 0;
    }

    public void removeItem(int adapterPosition) {
        this.productsCollection.remove(adapterPosition);
    }


    public interface OnItemClickListener {
        void onProductItemClicked(ProductModel productModel);

        void onDecreaseItemQuantity(int position);

        void onIncreaseItemQuantity(int position);

        void onRemoveItemFromCart(int position);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product_image_cart)
        ImageView iv_product_image;
        @BindView(R.id.tv_product_quantity)
        TextView tv_product_quantity;
        @BindView(R.id.tv_product_title_cart)
        TextView tv_product_title;
        @BindView(R.id.tv_product_price_cart)
        TextView tv_product_price;
        @BindView(R.id.btn_close_item_cart)
        ImageView btn_close_item_cart;
        @BindView(R.id.iv_decrease_item_cart)
        ImageView iv_decrease_item_cart;
        @BindView(R.id.iv_increase_item_cart)
        ImageView iv_increase_item_cart;

        public CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
