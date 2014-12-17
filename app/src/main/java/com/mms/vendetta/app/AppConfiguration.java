package com.mms.vendetta.app;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class AppConfiguration {

    private static final UIConfiguration defaultConfiguration = UIConfiguration.USER;

    private static UIConfiguration currentConfiguration =
            UIConfiguration.values()[MMSPreferences.loadInt(
                    MMSPreferences.APP_CONFIG, UIConfiguration.USER.ordinal())];

    public static void setConfiguration(UIConfiguration uiConfiguration){
        currentConfiguration = uiConfiguration;
        MMSPreferences.saveInt(MMSPreferences.APP_CONFIG, uiConfiguration.ordinal());
    }

    public static void restoreConfiguration(){
        currentConfiguration = defaultConfiguration;
        MMSPreferences.saveInt(MMSPreferences.APP_CONFIG, defaultConfiguration.ordinal());
    }

    public static HomeMenuOption getDefaultMenuOption(){
        return currentConfiguration.getDefaultMenuOption();
    }

    public static HomeMenuOption[] getMenuOptions(){
        return currentConfiguration.getMenuOptions();
    }

    public static UIConfiguration getCurrentConfiguration(){
        return currentConfiguration;
    }

    public enum UIConfiguration {

        USER(UserMenu.VISIBLE_NAME, UserMenu.NEWS, UserMenu.values());

        private int mVisibleName;
        private HomeMenuOption mDefaultMenuOption;
        private HomeMenuOption[] mMenuOptions;

        private UIConfiguration(int mVisibleName, HomeMenuOption mDefaultMenuOption,
                        HomeMenuOption[] mMenuOptions) {
            this.mVisibleName = mVisibleName;
            this.mDefaultMenuOption = mDefaultMenuOption;
            this.mMenuOptions = mMenuOptions;
        }

        private HomeMenuOption getDefaultMenuOption(){
            return this.mDefaultMenuOption;
        }

        private HomeMenuOption[] getMenuOptions(){
            return this.mMenuOptions;
        }

        public static CharSequence[] asCharSequences(Context ctx){
            UIConfiguration[] configs = UIConfiguration.values();
            CharSequence[] sequences = new CharSequence[configs.length];
            for(int i = 0 ; i < configs.length ; i++){
                sequences[i] = ctx.getString(configs[i].mVisibleName);
            }
            return sequences;
        }
    }

    public interface HomeMenuOption {
        public Class<? extends Fragment> getContentClass();
        public int getTitleRes();
        public int getIconRes();
        public boolean isVisible();
    }

}
