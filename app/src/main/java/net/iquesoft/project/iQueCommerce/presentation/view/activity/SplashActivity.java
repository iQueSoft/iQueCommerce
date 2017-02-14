package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.os.Bundle;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerSplashActivityComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.SplashActivityComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.SplashActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SplashFragment;

public class SplashActivity extends BaseActivity implements HasComponent<SplashActivityComponent> {


    private SplashActivityComponent splashActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainerSplash, new SplashFragment());
        }
    }

    @Override
    protected void setupActivityComponent() {
        this.splashActivityComponent = DaggerSplashActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .splashActivityModule(new SplashActivityModule(this))
                .build();
    }


    @Override
    public SplashActivityComponent getComponent() {
        return splashActivityComponent;
    }
}
