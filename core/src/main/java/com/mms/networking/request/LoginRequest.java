package com.mms.networking.request;

import com.mms.app.AppConfiguration;
import com.mms.networking.MMSApiManager;
import com.mms.networking.MMSRequest;
import com.mms.networking.model.MMSResponse;
import com.mms.networking.model.MMSUser;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class LoginRequest extends MMSRequest<MMSUser> {

    private AccessType mAccessType;
    private String mEmail;
    private String mPassword;

    public LoginRequest(String email, String password){
        this.mAccessType = AccessType.EMAIL;
        this.mEmail = email;
        this.mPassword = password;
    }

    public LoginRequest(String email, SocialNetwork socialNetwork){
        this.mAccessType = socialNetwork.getAccessType();
        this.mEmail = email;
    }

    @Override
    protected String getTag() {
        return LoginRequest.class.getSimpleName();
    }

    @Override
    protected MMSResponse perform() {
        MMSApiManager.MMSApiServiceDefinition serviceDefinition
                = MMSApiManager.getInstance().getMMSApiDefinition();
        switch (this.mAccessType){
            case FACEBOOK: case GOOGLE_PLUS: {
                return serviceDefinition.signIn(AppConfiguration.getBackendConfiguration().ALIAS,
                        this.mAccessType.ordinal(), this.mEmail);
            }
            default: {
                return serviceDefinition.signIn(AppConfiguration.getBackendConfiguration().ALIAS,
                        this.mAccessType.ordinal(), this.mEmail, this.mPassword);
            }
        }
    }

    public static enum SocialNetwork {
        FACEBOOK(AccessType.FACEBOOK), GOOGLE_PLUS(AccessType.GOOGLE_PLUS);

        private AccessType mAccessType;

        private SocialNetwork(AccessType accessType){
            this.mAccessType = accessType;
        }

        private AccessType getAccessType(){
            return this.mAccessType;
        }

    }

    private enum AccessType {
        FACEBOOK, EMAIL, GOOGLE_PLUS;
    }

}
