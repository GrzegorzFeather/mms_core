package com.mms;

import android.app.Application;

import com.mms.app.MMSPreferences;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class MMSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMSPreferences.init(this);
    }
}
