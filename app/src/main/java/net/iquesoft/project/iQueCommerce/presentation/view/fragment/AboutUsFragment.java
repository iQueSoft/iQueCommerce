package net.iquesoft.project.iQueCommerce.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.components.CategoryComponent;
import net.iquesoft.project.iQueCommerce.presentation.presenter.AboutUsPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsFragment extends BaseFragment {

    @Inject
    AboutUsPresenter presenter;

    @BindView(R.id.wv_about_us)
    WebView wv_about_us;

    @Override
    void initializeInjection() {
        this.getComponent(CategoryComponent.class).inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    public void showWebPage(String aboutUsWebPage) {
        wv_about_us.loadUrl(aboutUsWebPage);
    }
}
