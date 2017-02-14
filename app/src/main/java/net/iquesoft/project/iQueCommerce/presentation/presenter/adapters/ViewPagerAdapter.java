package net.iquesoft.project.iQueCommerce.presentation.presenter.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shopify.buy.model.Image;
import com.squareup.picasso.Picasso;

import net.iquesoft.project.iQueCommerce.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerAdapter extends PagerAdapter {

    private final List<Image> images;
    private final LayoutInflater layoutInflater;
    private final Context context;
    @BindView(R.id.iv_pager_item)
    ImageView iv_pager_item;
    @BindView(R.id.ll_view_pager_count_dots)
    LinearLayout ll_view_pager_count_dots;

    public ViewPagerAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return this.images.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = this.layoutInflater.inflate(R.layout.pager_item, container, false);
        ButterKnife.bind(this, itemView);

        Picasso.with(this.context)
                .load(this.images.get(position).getSrc())
                .fit()
                .placeholder(R.drawable.ic_folder_image)
                .error(R.drawable.place_holder_no_image)
                .into(this.iv_pager_item);
        container.addView(itemView);
        this.setUiPageViewController(position);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    private void setUiPageViewController(int position) {

        int dotsCount = this.getCount();
        ImageView[] dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this.context);
            dots[i].setImageDrawable(this.context.getResources().getDrawable(R.drawable.nonselected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            this.ll_view_pager_count_dots.addView(dots[i], params);
        }

        dots[position].setImageDrawable(this.context.getResources().getDrawable(R.drawable.selected_item_dot));
    }

}
