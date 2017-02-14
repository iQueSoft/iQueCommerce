package net.iquesoft.project.iQueCommerce.presentation.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishListProductsAdapter extends RecyclerView.Adapter<WishListProductsAdapter.ProductsViewHolder> {

    private Context context;
    private List<ProductModel> productsCollection;
    private ProductsAdapter.ProductsAdapterListener productsAdapterListener;
    private String currency;

    @Inject
    public WishListProductsAdapter() {
        this.productsCollection = Collections.emptyList();
    }

    @Override
    public WishListProductsAdapter.ProductsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item_view, viewGroup, false);
        return new WishListProductsAdapter.ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishListProductsAdapter.ProductsViewHolder holder, int position) {
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
            if (WishListProductsAdapter.this.productsAdapterListener != null) {
                WishListProductsAdapter.this.productsAdapterListener.onProductItemClicked(productModel);
            }
        });
        holder.btn_add_to_cart.setOnClickListener(view -> {
            if (WishListProductsAdapter.this.productsAdapterListener != null) {
                WishListProductsAdapter.this.productsAdapterListener.onAddToCartButtonClicked(productModel);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (this.productsCollection != null) ? this.productsCollection.size() : 0;
    }

    public void setProductsCollection(Context context, Collection<ProductModel> productsCollection, String currency) {
        this.validateProductsCollection(productsCollection);
        this.context = context;
        this.productsCollection = (List<ProductModel>) productsCollection;
        this.currency = currency;
        this.notifyDataSetChanged();
    }

    private void validateProductsCollection(Collection<ProductModel> productsCollection) {
        if (productsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setProductsAdapterListener(ProductsAdapter.ProductsAdapterListener productsAdapterListener) {
        this.productsAdapterListener = productsAdapterListener;
    }


    public interface OnItemClickListener {
        void onProductItemClicked(ProductModel productModel);

        void onAddToCartButtonClicked(ProductModel productModel);
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_product_title)
        TextView tv_product_title;
        @BindView(R.id.tv_product_price)
        TextView tv_product_price;
        @BindView(R.id.btn_add_to_cart)
        Button btn_add_to_cart;
        @BindView(R.id.iv_product_image)
        ImageView iv_product_image;


        public ProductsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

