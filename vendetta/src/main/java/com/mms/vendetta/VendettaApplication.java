package com.mms.vendetta;

import com.mms.MMSApplication;
import com.mms.app.BackendConfiguration;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class VendettaApplication extends MMSApplication {

    @Override
    protected BackendConfiguration getBackendConfiguration() {
        return new VendettaBackendConfiguration();
    }

    private class VendettaBackendConfiguration extends BackendConfiguration {

        public VendettaBackendConfiguration() {
            super(VendettaConfig.ALIAS);
        }
    }

}
