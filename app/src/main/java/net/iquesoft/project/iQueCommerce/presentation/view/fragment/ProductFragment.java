package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.shopify.buy.model.Image;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.model.ProductModel;
import net.iquesoft.project.iQueCommerce.presentation.presenter.ProductPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.CategoryActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.interfaces.SelectedProductView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductFragment extends BaseFragment implements SelectedProductView {

    @Inject
    ProductPresenter presenter;

    @BindView(R.id.tv_product_title_fragment_product)
    TextView tv_product_title;
    @BindView(R.id.tv_product_price_fragment_product)
    TextView tv_product_price;
    @BindView(R.id.tv_product_description_fragment_product)
    TextView tv_product_description;
    //    @BindView(R.id.iv_product_image_fragment_product)
//    ViewPager iv_product_image;
    @BindView(R.id.iv_favourite)
    ImageView iv_favourite;
    @BindView(R.id.iv_share)
    ImageView iv_share;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.sl_product)

    SliderLayout sl_product;

    @Override
    void initializeInjection() {
        getComponent(CategoryComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.sl_product != null) {
            this.sl_product.startAutoCycle();
        }
        this.getCurrentActivity().changeBadgeCount(this.presenter.getCartItemCount());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.sl_product != null) {
            this.sl_product.stopAutoCycle();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getCurrentActivity().showFAB();
    }

    @Override
    public void loadContent(ProductModel productModel, String currency) {
        String price = currency + productModel.getMinimumPrice();
        this.tv_product_title.setText(productModel.getTitle());
        this.tv_product_price.setText(price);
        this.tv_product_description.setText(productModel.getBodyHtml());

        for (Image image : productModel.getImages()) {
            DefaultSliderView sliderView = new DefaultSliderView(this.getCurrentActivity());
            sliderView.image(image.getSrc())
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            this.sl_product.addSlider(sliderView);
        }
        this.sl_product.setPresetTransformer(SliderLayout.Transformer.Fade);
        this.sl_product.setDuration(3000);
        this.sl_product.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        if (productModel.getImages().size() == 1) {
            this.sl_product.stopAutoCycle();
        } else {
            this.sl_product.startAutoCycle();
        }
    }

    public void setFavouriteIcon(Boolean aBoolean) {
//        if (aBoolean) {
//            this.iv_favourite.setImageResource(R.drawable.heart_activated);
//        } else {
//            this.iv_favourite.setImageResource(R.drawable.ic_heart);
//        }
    }

    @OnClick(R.id.iv_favourite)
    void addToFavourites() {
//        if (!this.presenter.isFavourite()) {
//            this.presenter.addToFavourite();
//            this.iv_favourite.setImageResource(R.drawable.heart_activated);
//        } else {
//            this.iv_favourite.setImageResource(R.drawable.ic_heart);
//            this.presenter.removeFromFavourite();
//        }

    }

    @OnClick(R.id.iv_share)
    void shareProduct() {
        this.presenter.shareProduct(this.getCurrentActivity());
    }

    public void setToolbarTitle(String title) {
        this.getCurrentActivity().setToolbarTitle(title);
    }

    private CategoryActivity getCurrentActivity() {
        return (CategoryActivity) getActivity();
    }

}
