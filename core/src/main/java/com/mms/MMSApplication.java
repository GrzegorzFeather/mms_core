package com.mms;

import android.app.Application;

import com.mms.app.AppConfiguration;
import com.mms.app.BackendConfiguration;
import com.mms.app.MMSPreferences;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class MMSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMSPreferences.init(this);
        AppConfiguration.setBackendConfiguration(this.getBackendConfiguration());
    }

    public boolean isLoggedIn(){
        return MMSPreferences.containsPreference(MMSPreferences.AUTH_TOKEN);
    }

    protected BackendConfiguration getBackendConfiguration(){
        return AppConfiguration.getDefaultBackendConfiguration();
    }

}
