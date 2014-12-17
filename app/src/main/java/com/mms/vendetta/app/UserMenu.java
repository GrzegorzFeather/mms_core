package com.mms.vendetta.app;

import android.support.v4.app.Fragment;

import com.mms.vendetta.R;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public enum UserMenu implements AppConfiguration.HomeMenuOption {

    NEWS(Fragment.class, R.string.app_name, R.drawable.ic_launcher, false),
    EVENTS(Fragment.class, R.string.app_name, R.drawable.ic_launcher, true),
    DISCOGRAPHY(Fragment.class, R.string.app_name, R.drawable.ic_launcher, true),
    GALLERY(Fragment.class, R.string.app_name, R.drawable.ic_launcher, true);

    public static final int VISIBLE_NAME = R.string.user_config_visible_name;

    private Class<? extends Fragment> mContentClass;
    private int mTitleRes;
    private int mIconRes;
    private boolean mIsVisible;

    private UserMenu(Class<? extends Fragment> contentClass, int titleRes,
                     int iconRes, boolean isVisible){
        this.mContentClass = contentClass;
        this.mTitleRes = titleRes;
        this.mIconRes = iconRes;
        this.mIsVisible = isVisible;
    }

    @Override
    public Class<? extends Fragment> getContentClass(){
        return this.mContentClass;
    }

    @Override
    public int getTitleRes() {
        return this.mTitleRes;
    }

    @Override
    public int getIconRes() {
        return this.mIconRes;
    }

    @Override
    public boolean isVisible() {
        return this.mIsVisible;
    }
}
