package com.mms.networking.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mms.networking.MMSApiManager;

import java.lang.reflect.Type;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class MMSContent {

    public static final JsonDeserializer<MMSContent> gsonAdapter = new MMSModelGsonAdapter();

    private JsonElement mAsJson;
    private MMSModel mContentModel = null;

    private MMSContent(JsonElement json){
        this.mAsJson = json;
    }

    public MMSModel asResponseModel(ContentType contentType){
        if(this.mContentModel == null){
            this.mContentModel = MMSApiManager.getInstance().getGson().fromJson(
                    this.mAsJson, contentType.getAssociatedModelClass());
        }
        return this.mContentModel;
    }

    private static class MMSModelGsonAdapter implements JsonDeserializer<MMSContent> {

        private static final String TAG = MMSModelGsonAdapter.class.getSimpleName();

        @Override
        public MMSContent deserialize(
                JsonElement json, Type typeOfT,
                JsonDeserializationContext context)
                throws JsonParseException {
            return new MMSContent(json);
        }
    }

}
