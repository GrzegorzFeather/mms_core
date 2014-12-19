package com.mms.app;

import android.support.v4.app.Fragment;

import com.mms.R;
import com.mms.ui.fragment.DiscographyFragment;
import com.mms.ui.fragment.EventsFragment;
import com.mms.ui.fragment.GalleryFragment;
import com.mms.ui.fragment.MenuOptionFragment;
import com.mms.ui.fragment.NewsFragment;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public enum UserMenu implements AppConfiguration.HomeMenuOption {

    NEWS(NewsFragment.class, R.string.home_menu_news, R.drawable.ic_news, true),
    EVENTS(EventsFragment.class, R.string.home_menu_events, R.drawable.ic_launcher, true),
    DISCOGRAPHY(DiscographyFragment.class, R.string.home_menu_discography, R.drawable.ic_launcher, true),
    GALLERY(GalleryFragment.class, R.string.home_menu_gallery, R.drawable.ic_launcher, true);

    public static final int VISIBLE_NAME = R.string.user_config_visible_name;

    private Class<? extends MenuOptionFragment> mContentClass;
    private int mTitleRes;
    private int mIconRes;
    private boolean mIsVisible;

    private UserMenu(Class<? extends MenuOptionFragment> contentClass, int titleRes,
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
