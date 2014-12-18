package com.mms.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mms.networking.model.ContentType;
import com.mms.networking.model.MResponse;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ContentType.class, ContentType.gsonAdapter)
                .create();
        this.mMMSApiDefinition = new RestAdapter.Builder()
                .setEndpoint(MMS_ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build().create(MMSApiServiceDefinition.class);
    }

    public MMSApiServiceDefinition getMMSApiDefinition(){
        return this.mMMSApiDefinition;
    }

    public interface MMSApiServiceDefinition {
        @FormUrlEncoded
        @POST("/api/user")
        MResponse signIn(@Field("alias") String alias);
    }

}
