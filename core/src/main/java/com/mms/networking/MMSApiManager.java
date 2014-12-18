package com.mms.networking;

import retrofit.RestAdapter;
import retrofit.http.POST;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class MMSApiManager {

    public static final String TAG = MMSApiManager.class.getSimpleName();
    private static final String MMS_ENDPOINT = "http://musicmultimediastore.com";
    private static MMSApiManager singleton = null;

    private MMSApiServiceDefinition mMMSApiDefinition;

    public static MMSApiManager getInstance(){
        if(singleton == null){
            singleton = new MMSApiManager();
        }
        return singleton;
    }

    private MMSApiManager(){
        this.mMMSApiDefinition = new RestAdapter.Builder()
                .setEndpoint(MMS_ENDPOINT)
                .build().create(MMSApiServiceDefinition.class);
    }

    public MMSApiServiceDefinition getMMSApiDefinition(){
        return this.mMMSApiDefinition;
    }

    public interface MMSApiServiceDefinition {
        @POST("/api/user")
        Void signIn();
    }

}
