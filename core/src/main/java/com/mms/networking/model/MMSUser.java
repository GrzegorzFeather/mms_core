package com.mms.networking.model;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class MMSUser implements MMSModel {

    private int userId;
    private String birthday;
    private String name;
    private String gender;
    private String cookie;
    private MMSAccount account;

    public int getUserId() {
        return userId;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCookie() {
        return cookie;
    }
}
