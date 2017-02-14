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
import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private int currentPage;
    private List<CategoryModel> categoriesCollection;
    private CategoriesAdapterListener categoriesAdapterListener;
    private Context context;
    private boolean isListLoaded;

    @Inject
    public CategoriesAdapter() {
        this.categoriesCollection = Collections.emptyList();
        this.currentPage = 1;
    }

    @Override
    public CategoriesAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new CategoriesAdapter.CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.CategoryViewHolder holder, int position) {
        final CategoryModel categoryModel = this.categoriesCollection.get(position);
        holder.tvCategoryTitle.setText(categoryModel.getTitle());
        Picasso.with(context)
                .load(categoryModel.getImageUrl())
                .placeholder(R.drawable.ic_folder_image)
                .error(R.drawable.place_holder_no_image)
                .into(holder.ivCategory);
        holder.itemView.setOnClickListener(v -> {
            if (CategoriesAdapter.this.categoriesAdapterListener != null) {
                CategoriesAdapter.this.categoriesAdapterListener.onCategoryItemClicked(categoryModel);
            }
        });
        if (!isListLoaded && position == getItemCount() - 2) {
            currentPage++;
            CategoriesAdapter.this.categoriesAdapterListener.onListEndReached(currentPage);
        }
    }

    @Override
    public int getItemCount() {
        return (this.categoriesCollection != null) ? this.categoriesCollection.size() : 0;
    }

    public void setCategoriesCollection(Context context, Collection<CategoryModel> categoriesCollection) {
        this.context = context;
        this.validateCategoriesCollection(categoriesCollection);
        if (categoriesCollection.size() == 0) {
            this.isListLoaded = true;
        }
        if (this.categoriesCollection.size() == 0) {
            this.categoriesCollection = (List<CategoryModel>) categoriesCollection;
        } else {
            this.categoriesCollection.addAll(categoriesCollection);
        }
        this.notifyDataSetChanged();

    }

    private void validateCategoriesCollection(Collection<CategoryModel> categoriesCollection) {
        if (categoriesCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setCategoriesAdapterListener(CategoriesAdapterListener categoriesAdapterListener) {
        this.categoriesAdapterListener = categoriesAdapterListener;
    }

    public interface CategoriesAdapterListener {
        void onCategoryItemClicked(CategoryModel categoryModel);

        void onListEndReached(int nextPageNumber);
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_category)
        ImageView ivCategory;
        @BindView(R.id.tv_category_title)
        TextView tvCategoryTitle;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
