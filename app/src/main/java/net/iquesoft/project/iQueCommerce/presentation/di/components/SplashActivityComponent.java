package net.iquesoft.project.iQueCommerce.presentation.di.components;

import net.iquesoft.project.iQueCommerce.presentation.di.PerActivity;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.SplashActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.view.activity.SplashActivity;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.SplashFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, SplashActivityModule.class})
public interface SplashActivityComponent extends ActivityComponent {
    void inject(SplashActivity splashActivity);

    void inject(SplashFragment splashFragment);
}
