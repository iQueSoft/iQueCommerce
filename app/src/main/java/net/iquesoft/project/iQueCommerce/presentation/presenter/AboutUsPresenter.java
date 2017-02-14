package net.iquesoft.project.iQueCommerce.presentation.presenter;

import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AboutUsFragment;

import javax.inject.Inject;

public class AboutUsPresenter implements Presenter {

    private static final String ABOUT_US_WEB_PAGE = "https://seedecommerce.myshopify.com/pages/about-us";
    private AboutUsFragment fragmentView;

    @Inject
    AboutUsPresenter() {
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

    public void setView(AboutUsFragment view) {
        this.fragmentView = view;
    }

    public void initialize() {
        this.fragmentView.showWebPage(ABOUT_US_WEB_PAGE);
    }
}
