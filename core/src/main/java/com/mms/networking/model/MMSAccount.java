package com.mms.networking.model;

/**
 * Created by GrzegorzFeathers on 12/18/14.
 */
public class MMSAccount implements MMSModel {

    private int loginType;
    private boolean isMainAccount;
    private String email;
    private String userName;

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
