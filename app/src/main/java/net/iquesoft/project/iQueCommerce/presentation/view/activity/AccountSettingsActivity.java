package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.presentation.di.HasComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.AccountSettingsComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.components.DaggerAccountSettingsComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.AccountSettingsModule;
import net.iquesoft.project.iQueCommerce.presentation.presenter.AccountSettingsPresenter;
import net.iquesoft.project.iQueCommerce.presentation.view.fragment.AccountSettingsFragment;

import javax.inject.Inject;

public class AccountSettingsActivity extends BaseActivity implements HasComponent<AccountSettingsComponent> {

    @Inject
    AccountSettingsPresenter settingsPresenter;

    private AccountSettingsComponent accountSettingsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_account_settings, null, false);
        this.drawer.addView(contentView, 0);


        this.inject();

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer_activity_account_settings, new AccountSettingsFragment());
        }

        this.showUserInfoInUI(baseActivityPresenter.getCurrentUser());
    }

    private void inject() {
        this.getComponent().inject(this);
    }

    @Override
    protected void setupActivityComponent() {
        this.accountSettingsComponent = DaggerAccountSettingsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .accountSettingsModule(new AccountSettingsModule())
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty, menu);
        this.toolbar.setTitle(R.string.title_account_settings);
        return true;
    }

    @Override
    public AccountSettingsComponent getComponent() {
        return this.accountSettingsComponent;
    }
}
