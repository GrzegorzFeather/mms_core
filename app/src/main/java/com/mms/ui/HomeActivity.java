package com.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.mms.MMSApplication;
import com.mms.app.AppConfiguration;
import com.mms.vendetta.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends MenuHostActivity {

    @InjectView(R.id.toolbar) Toolbar mToolbar;
    @InjectView(R.id.lbl_main_title) TextView mLblTitle;
    @InjectView(R.id.lbl_main_subtitle) TextView mLblSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!((MMSApplication) this.getApplication()).isLoggedIn()){
            this.requireUserLogin();
            return;
        }

        this.setContentView(R.layout.activity_home);
        this.setupHome();
    }

    private void setupHome(){
        ButterKnife.inject(this);

        this.mToolbar.inflateMenu(R.menu.main);
        this.mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });

        this.mLblTitle.setText(R.string.app_name);
        this.updateContent(AppConfiguration.getDefaultMenuOption());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    @Override
    public void updateContent(AppConfiguration.HomeMenuOption menuOption) {
        this.mLblSubtitle.setText(menuOption.getTitleRes());
    }

    private void requireUserLogin(){
        this.startActivity(new Intent(this, SplashActivity.class));
    }

}
