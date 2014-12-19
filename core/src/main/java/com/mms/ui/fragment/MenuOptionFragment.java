package com.mms.ui.fragment;

import android.support.v4.app.Fragment;

import com.mms.app.AppConfiguration;
import com.mms.ui.MenuHostActivity;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public abstract class MenuOptionFragment extends Fragment {

    private AppConfiguration.HomeMenuOption mMenuOption;

    public MenuOptionFragment(){
        this.mMenuOption = this.getMenuOption();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getMenuHostActivity().setSubtitle(this.mMenuOption.getTitleRes());
    }

    private MenuHostActivity getMenuHostActivity(){
        MenuHostActivity menuHostActivity = null;

        try {
            menuHostActivity = (MenuHostActivity) this.getActivity();
        } catch (ClassCastException e) {
            throw e;
        }

        return menuHostActivity;
    }

    protected abstract AppConfiguration.HomeMenuOption getMenuOption();

}
