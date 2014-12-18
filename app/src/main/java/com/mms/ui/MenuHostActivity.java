package com.mms.ui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.mms.app.AppConfiguration;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public abstract class MenuHostActivity extends ActionBarActivity {

    public abstract Toolbar getToolbar();

    public abstract void updateContent(AppConfiguration.HomeMenuOption menuOption);

}
