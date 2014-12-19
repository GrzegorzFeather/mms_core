package com.mms.networking.model;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class MMSNewsEntry implements MMSModel {

    private int newsId;
    private String title;
    private String content;
    private String date;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
