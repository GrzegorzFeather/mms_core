package com.mms.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.mms.vendetta.R;
import com.mms.ui.fragment.HomeMenuFragment;

public class HomeActivity extends ActionBarActivity {

    private HomeMenuFragment mHomeMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.setupHome();


    }

    private void setupHome(){
        this.mHomeMenu = (HomeMenuFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_home_menu);
        ActionBar ab = this.getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

}
