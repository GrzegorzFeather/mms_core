package com.mms;

import android.app.Application;

import com.google.gson.Gson;
import com.mms.app.AppConfiguration;
import com.mms.app.BackendConfiguration;
import com.mms.app.MMSPreferences;
import com.mms.networking.model.MMSUser;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public abstract class MMSApplication extends Application {

    private MMSUser mLoggedInUser = null;

    @Override
    public void onCreate() {
        super.onCreate();
        MMSPreferences.init(this);
        AppConfiguration.setBackendConfiguration(this.getBackendConfiguration());
    }

    public boolean isLoggedIn(){
        return MMSPreferences.containsPreference(MMSPreferences.AUTH_TOKEN);
    }

    public void loginUser(MMSUser user){
        MMSPreferences.saveString(MMSPreferences.AUTH_TOKEN, user.getCookie());
        MMSPreferences.saveString(MMSPreferences.USER, new Gson().toJson(user));
    }

    public void logoutUser(){
        MMSPreferences.clearPreferences();
        this.mLoggedInUser = null;
    }

    public MMSUser getLoggedInUser(){
        if(this.mLoggedInUser == null){
            this.mLoggedInUser = new Gson().fromJson(
                    MMSPreferences.loadString(MMSPreferences.USER, ""), MMSUser.class);
        }

        return this.mLoggedInUser;
    }

    protected abstract BackendConfiguration getBackendConfiguration();

}
