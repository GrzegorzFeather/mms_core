package com.mms.networking.request;

import com.mms.app.AppConfiguration;
import com.mms.app.MMSPreferences;
import com.mms.networking.MMSApiManager;
import com.mms.networking.MMSRequest;
import com.mms.networking.model.MMSNews;
import com.mms.networking.model.MMSResponse;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class GetNewsRequest extends MMSRequest<MMSNews> {
    @Override
    protected String getTag() {
        return GetNewsRequest.class.getSimpleName();
    }

    @Override
    protected MMSResponse perform() throws Exception {
        return MMSApiManager.getInstance().getMMSApiDefinition().getNews(
                AppConfiguration.getBackendConfiguration().ALIAS,
                MMSPreferences.loadString(MMSPreferences.AUTH_TOKEN, ""));
    }
}
