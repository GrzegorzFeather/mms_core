package com.mms.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mms.networking.model.ContentType;
import com.mms.networking.model.MMSContent;
import com.mms.networking.model.MMSResponse;

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
    private Gson mGson;

    public static MMSApiManager getInstance(){
        if(singleton == null){
            singleton = new MMSApiManager();
        }
        return singleton;
    }

    private MMSApiManager(){
        this.mGson = new GsonBuilder()
                .registerTypeAdapter(ContentType.class, ContentType.gsonAdapter)
                .registerTypeAdapter(MMSContent.class, MMSContent.gsonAdapter)
                .setPrettyPrinting()
                .create();
        this.mMMSApiDefinition = new RestAdapter.Builder()
                .setEndpoint(MMS_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(this.mGson))
                .build().create(MMSApiServiceDefinition.class);
    }

    public MMSApiServiceDefinition getMMSApiDefinition(){
        return this.mMMSApiDefinition;
    }
    public Gson getGson(){ return this.mGson; }

    public interface MMSApiServiceDefinition {

        /**
         *
         * Sign in request method for social networks access
         *
         * @param alias
         * @param loginType
         * @param email
         * @return
         */
        @FormUrlEncoded
        @POST("/api/user")
        MMSResponse signIn(@Field("alias") String alias, @Field("loginType") int loginType,
                         @Field("email") String email);

        /**
         *
         * Sign in request method for user credentials
         *
         * @param alias
         * @param loginType
         * @param email
         * @param password
         * @return
         */
        @FormUrlEncoded
        @POST("/api/user")
        MMSResponse signIn(@Field("alias") String alias, @Field("loginType") int loginType,
                         @Field("email") String email, @Field("password") String password);
    }

}
