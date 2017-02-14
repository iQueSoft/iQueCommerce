package net.iquesoft.project.iQueCommerce.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.iquesoft.project.iQueCommerce.R;
import net.iquesoft.project.iQueCommerce.domain.PreferencesManager;
import net.iquesoft.project.iQueCommerce.presentation.AndroidApplication;
import net.iquesoft.project.iQueCommerce.presentation.di.components.ApplicationComponent;
import net.iquesoft.project.iQueCommerce.presentation.di.modules.ActivityModule;
import net.iquesoft.project.iQueCommerce.presentation.model.CartModel;
import net.iquesoft.project.iQueCommerce.presentation.model.UserModel;
import net.iquesoft.project.iQueCommerce.presentation.navigation.Navigator;
import net.iquesoft.project.iQueCommerce.presentation.presenter.BaseActivityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected MenuItem cart_menu;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    BaseActivityPresenter baseActivityPresenter;
    @Inject
    Navigator navigator;
    @Inject
    CartModel cartModel;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_primary)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_primary)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_primary)
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_primary);

        initializeInjection();
        setupActivityComponent();

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseContext()));
        }

        this.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        this.fab.hide();
        this.setSupportActionBar(toolbar);
        this.toolbar.setTitle(R.string.app_name);
        this.toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.setDrawerListener(toggle);
        this.toggle.syncState();

        this.navigationView.setNavigationItemSelectedListener(this);
        this.navigationView.setItemIconTintList(null);

    }


    protected void showUserInfoInUI(UserModel currentUserUser) {
        View view = this.navigationView.getHeaderView(0);
        Menu menu = this.navigationView.getMenu();
        TextView tvNavHeaderName = (TextView) view.findViewById(R.id.tvNavHeaderName_primary);
        TextView tvNavHeaderEmail = (TextView) view.findViewById(R.id.tvNavHeaderEmail_primary);
        MenuItem tvNavLogOut = menu.findItem(R.id.nav_logOut);
        String s = currentUserUser.getFirstName() + " " + currentUserUser.getLastName();
        tvNavHeaderName.setText(s);
        tvNavHeaderEmail.setText(currentUserUser.getUserEmail());
        tvNavLogOut.setVisible(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.primary, menu);
        cart_menu = menu.findItem(R.id.btn_cart_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_cart_menu) {
            if (!(this instanceof CartActivity)) {
                this.navigator.navigateToCartActivity(this);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (!(this instanceof PrimaryActivity)) {
                this.navigator.navigateToPrimaryActivity(this);
            }
        } else if (id == R.id.nav_account_settings) {
            if (!(this instanceof AccountSettingsActivity)) {
                this.navigator.navigateToAccountSettingsActivity(this);
            }
        } else if (id == R.id.nav_app_settings) {

        } else if (id == R.id.nav_wish_list) {
            this.navigator.navigateToWishList(this);
        } else if (id == R.id.nav_share) {
            this.baseActivityPresenter.shareApp(this);
        } else if (id == R.id.nav_about) {
            this.navigator.navigateToAboutUsFragment(getSupportFragmentManager());
        } else if (id == R.id.nav_contact_us) {
            this.navigator.navigateToContactUsActivity(this);
        } else if (id == R.id.nav_logOut) {
            signOut();
        }
        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        this.preferencesManager.setRelogging(this, true);
        finish();
        this.navigator.navigateToMainActivity(this, "SignOut");
    }

    private void initializeInjection() {
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment)
                .commit();
    }

    protected void replaceFragment(FragmentManager fragmentManager, Fragment destinationFragment, int containerViewId) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, destinationFragment)
                .addToBackStack(null)
                .commit();
    }

    protected abstract void setupActivityComponent();

    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


}
