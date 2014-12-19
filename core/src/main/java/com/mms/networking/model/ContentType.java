package com.mms.networking.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public enum ContentType {

    user(MMSUser.class),
    newslist(MMSNews.class),

    // Failure types
    error(MMSModel.class),
    undefined(MMSModel.class);

    public static final TypeAdapter gsonAdapter = new ContentTypeAdapter();

    private Class<? extends MMSModel> mAssociatedModelClass;

    private ContentType(Class<? extends MMSModel> associatedModelClass){
        this.mAssociatedModelClass = associatedModelClass;
    }

    public Class<? extends MMSModel> getAssociatedModelClass() {
        return mAssociatedModelClass;
    }

    public static class ContentTypeAdapter extends TypeAdapter<ContentType> {

        private static final String TAG = ContentTypeAdapter.class.getSimpleName();

        @Override
        public void write(JsonWriter out, ContentType value) throws IOException {
            if(value == null){
                out.nullValue();
                return;
            }
            out.value(value.toString());
        }

        @Override
        public ContentType read(JsonReader in) throws IOException {
            if(in.peek().equals(JsonToken.NULL)){
                in.nextNull();
                return null;
            }

            String contentTypeValueString = in.nextString().toLowerCase();
            ContentType contentType = ContentType.valueOf(contentTypeValueString);

            if(contentType == null){
                contentType = undefined;
            }

            return contentType;
        }
    }

}
