package com.mms.ui.fragment;

import com.mms.app.AppConfiguration;
import com.mms.app.UserMenu;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class EventsFragment extends MenuOptionFragment {

    @Override
    protected AppConfiguration.HomeMenuOption getMenuOption() {
        return UserMenu.EVENTS;
    }

}
