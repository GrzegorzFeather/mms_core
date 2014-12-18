package com.mms.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.mms.MMSApplication;
import com.mms.app.AppConfiguration;
import com.mms.R;

public class HomeActivity extends MenuHostActivity {

    private Toolbar mToolbar;
    private TextView mLblTitle;
    private TextView mLblSubtitle;

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
