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
import net.iquesoft.project.iQueCommerce.presentation.di.components.CategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.CategoryModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.PrimaryActivityPresenter;
import net.iquesoft.project.iQueCommerce.presentation.presenter.adapters.CategoriesAdapter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.PrimaryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.CategoriesGridView;
import net.iquesoft.project.iQueCommerce.utils.ToastMaker;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends BaseFragment implements CategoriesGridView {



    private static final int SPAN_COUNT = 2;
    @Inject
    PrimaryActivityPresenter presenter;
    @Inject
    CategoriesAdapter categoriesAdapter;

    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rv_categories)
    RecyclerView rv_categories;
    private int mCurrentTransitionEffect = JazzyHelper.GROW;
    private JazzyRecyclerViewScrollListener jazzyScrollListener;
    private CategoriesListListener categoriesListListener;
    private final CategoriesAdapter.CategoriesAdapterListener categoriesAdapterListener = new CategoriesAdapter.CategoriesAdapterListener() {
        @Override
        public void onCategoryItemClicked(CategoryModel categoryModel) {
            if (MainFragment.this.presenter != null && categoryModel != null) {
                MainFragment.this.categoriesListListener.onCategoryClicked(categoryModel);
            }
        }

        @Override
        public void onListEndReached(int nextPageNumber) {
            if (MainFragment.this.presenter != null) {
                MainFragment.this.presenter.loadNextPage(nextPageNumber);
            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CategoriesListListener) {
            this.categoriesListListener = (CategoriesListListener) activity;
        }
    }

    @Override
    void initializeInjection() {
        getComponent(CategoryComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
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
        this.getCurrentActivity().setToolbarTitle("SeedEcommerce");
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getCurrentActivity().changeBadgeCount(this.presenter.getCartItemCount());
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showError(String message) {
        ToastMaker.showMessage(getActivity(), message, ToastMaker.duration.LONG);
    }

    @Override
    public void loadContent(Collection<CategoryModel> modelCollection) {
        if (modelCollection != null) {
            this.categoriesAdapter.setCategoriesCollection(getCurrentActivity(), modelCollection);
        }
    }

    @Override
    public void onItemClicked(CategoryModel categoryModel) {
        if (this.categoriesListListener != null) {
            this.categoriesListListener.onCategoryClicked(categoryModel);
        }
    }

    private void setupJazziness(int effect) {
        this.mCurrentTransitionEffect = effect;
        this.jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }

    private void setupRecyclerView() {
        this.categoriesAdapter.setCategoriesAdapterListener(categoriesAdapterListener);

        this.jazzyScrollListener = new JazzyRecyclerViewScrollListener();
        this.rv_categories.setOnScrollListener(jazzyScrollListener);

        this.rv_categories.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        this.rv_categories.setAdapter(categoriesAdapter);
    }

    private PrimaryActivity getCurrentActivity() {
        return (PrimaryActivity) getActivity();
    }

    public interface CategoriesListListener {
        void onCategoryClicked(final CategoryModel categoryModel);
    }


}
