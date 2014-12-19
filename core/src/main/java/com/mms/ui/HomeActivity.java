package com.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mms.MMSApplication;
import com.mms.R;
import com.mms.app.AppConfiguration;
import com.mms.networking.model.MMSUser;
import com.mms.ui.fragment.HomeMenuFragment;

public class HomeActivity extends MenuHostActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    public static final int DEFAULT_FRAGMENT_TRANSITION = FragmentTransaction.TRANSIT_FRAGMENT_FADE;

    private Toolbar mToolbar;
    private TextView mLblTitle;
    private TextView mLblSubtitle;

    private HomeMenuFragment mMenuFragment;

    private MMSUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!((MMSApplication) this.getApplication()).isLoggedIn()){
            this.requireUserLogin();
            return;
        }

        this.setContentView(R.layout.activity_home);
        this.mUser = ((MMSApplication) this.getApplication()).getLoggedInUser();
        this.setupHome();
    }

    private void setupHome(){
        this.mMenuFragment = (HomeMenuFragment) this.getSupportFragmentManager()
                .findFragmentByTag(HomeMenuFragment.TAG);

        this.mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.mLblTitle = (TextView) this.findViewById(R.id.lbl_main_title);
        this.mLblSubtitle = (TextView) this.findViewById(R.id.lbl_main_subtitle);

        this.mToolbar.inflateMenu(R.menu.main);
        this.mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });

        this.mLblTitle.setText(R.string.app_name);
        this.pushToStack(AppConfiguration.getDefaultMenuOption().getContentClass(),
                null, -1, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_logout){
            ((MMSApplication) this.getApplication()).logoutUser();
            this.requireUserLogin();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    public void setSubtitle(int subtitleRes){
        this.mLblSubtitle.setText(subtitleRes);
    }

    @Override
    public void onHomeMenuOptionSelected(AppConfiguration.HomeMenuOption menuOption) {
        //this.mLblSubtitle.setText(menuOption.getTitleRes());

        if(menuOption.equals(AppConfiguration.getDefaultMenuOption())){
            this.clearStack();
        } else {
            this.replaceStack(menuOption.getContentClass(), null);
        }

        this.supportInvalidateOptionsMenu();
    }

    private void requireUserLogin(){
        this.startActivity(new Intent(this, SplashActivity.class));
        this.finish();
    }

    public void clearStack(){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void replaceStack(Class<? extends Fragment> newContentClass, Bundle extras){
        this.clearStack();
        this.pushToStack(newContentClass, extras);
    }

    public void pushToStack(Fragment fragment, String tag){
        this.pushToStack(fragment, tag, DEFAULT_FRAGMENT_TRANSITION);
    }

    public void pushToStack(Fragment fragment, String tag, int transition){
        this.pushToStack(fragment, tag, transition, true);
    }

    private void pushToStack(Fragment fragment, String tag, int transition, boolean addToBackStack){
        FragmentManager fm = this.getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment, tag);

        if(transition > 0){
            transaction.setTransition(transition);
        }
        if(addToBackStack){
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    private void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras,
                             int transition, boolean addToBackStack){
        Fragment fragment = this.getFragmentInstance(fragmentClass, extras);

        if(fragment == null){
            Log.d(TAG, "Unable to create the fragment instance");
            return;
        }

        this.pushToStack(fragment, fragmentClass.getName(), transition, addToBackStack);
    }

    public void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras, int transition){
        this.pushToStack(fragmentClass, extras, transition, true);
    }

    public void pushToStack(Class<? extends Fragment> fragmentClass, Bundle extras) {
        this.pushToStack(fragmentClass, extras, DEFAULT_FRAGMENT_TRANSITION);
    }

    public void singlePop(){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack();
    }

    private Fragment getFragmentInstance(Class<? extends Fragment> fragmentClass, Bundle extras){
        Fragment fragment = null;

        try {
            fragment = fragmentClass.newInstance();
            if(extras != null){
                fragment.setArguments(extras);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void onBackPressed() {
        if(this.mMenuFragment == null || !this.mMenuFragment.isDrawerOpen()){
            super.onBackPressed();
        } else {
            this.mMenuFragment.closeDrawer();
        }
    }
}
