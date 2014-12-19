package com.mms.networking.model;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class MMSResponse {

    private int status;
    private String message;
    private ContentType type;
    private MMSContent content;

    protected MMSResponse(){}

    public ContentType getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public MMSContent getContent() {
        return this.content;
    }
}
