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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private int currentPage;
    private Context context;
    private List<ProductModel> productsCollection;
    private ProductsAdapterListener productsAdapterListener;
    private String currency;
    private boolean isListLoaded;

    @Inject
    public ProductsAdapter() {
        this.productsCollection = Collections.emptyList();
        this.currentPage = 1;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item_view, viewGroup, false);
        return new ProductsAdapter.ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
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
            if (ProductsAdapter.this.productsAdapterListener != null) {
                ProductsAdapter.this.productsAdapterListener.onProductItemClicked(productModel);
            }
        });
        holder.btn_add_to_cart.setOnClickListener(view -> {
            if (ProductsAdapter.this.productsAdapterListener != null) {
                ProductsAdapter.this.productsAdapterListener.onAddToCartButtonClicked(productModel);
            }
        });
        if (!isListLoaded && position == getItemCount() - 2) {
            currentPage++;
            ProductsAdapter.this.productsAdapterListener.onListEndReached(currentPage);
        }
    }

    @Override
    public int getItemCount() {
        return (this.productsCollection != null) ? this.productsCollection.size() : 0;
    }

    public void setProductsCollection(Context context, Collection<ProductModel> productsCollection, String currency) {
        this.validateProductsCollection(productsCollection);
        this.context = context;
        if (productsCollection.size() == 0) {
            this.isListLoaded = true;
        }
        if (this.productsCollection.size() == 0) {
            this.productsCollection = (List<ProductModel>) productsCollection;
        } else {
            this.addToCollection(productsCollection);
        }
        this.currency = currency;
        this.notifyDataSetChanged();
    }

    private void addToCollection(Collection<ProductModel> productsCollection) {
        Collection<ProductModel> modelCollection = new ArrayList<>();
        for (ProductModel productModel :
                productsCollection) {
            boolean exist = false;
            for (ProductModel model : this.productsCollection) {
                if (model.getProductId().equals(productModel.getProductId())) {
                    exist = true;
                }
            }
            if (!exist) {
                modelCollection.add(productModel);
            }
        }
        this.productsCollection.addAll(modelCollection);
    }

    private void validateProductsCollection(Collection<ProductModel> productsCollection) {
        if (productsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setProductsAdapterListener(ProductsAdapterListener productsAdapterListener) {
        this.productsAdapterListener = productsAdapterListener;
    }

    public interface ProductsAdapterListener {
        void onProductItemClicked(ProductModel productModel);

        void onAddToCartButtonClicked(ProductModel productModel);

        void onListEndReached(int nextPageNumber);
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_product_title)
        TextView tv_product_title;
        @BindView(R.id.tv_product_price)
        TextView tv_product_price;
        @BindView(R.id.btn_add_to_cart)
        Button btn_add_to_cart;
        @BindView(R.id.iv_product_image)
        ImageView iv_product_image;


        ProductsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
