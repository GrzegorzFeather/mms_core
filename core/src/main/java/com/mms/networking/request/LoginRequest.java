package com.mms.networking.request;

import com.mms.app.AppConfiguration;
import com.mms.networking.MMSApiManager;
import com.mms.networking.MMSRequest;
import com.mms.networking.model.MResponse;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class LoginRequest extends MMSRequest<MResponse> {

    public LoginRequest(){

    }

    @Override
    protected String getTag() {
        return LoginRequest.class.getSimpleName();
    }

    @Override
    protected MResponse performRequest() {
        return MMSApiManager.getInstance().getMMSApiDefinition()
                .signIn(AppConfiguration.getBackendConfiguration().ALIAS);
    }

}
