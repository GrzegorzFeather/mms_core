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

    error, user, undefined;

    public static final TypeAdapter gsonAdapter = new ContentTypeAdapter();

    public static class ContentTypeAdapter extends TypeAdapter<ContentType> {

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
