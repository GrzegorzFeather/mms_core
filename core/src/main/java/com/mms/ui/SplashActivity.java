package com.mms.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.gson.Gson;
import com.mms.MMSApplication;
import com.mms.R;
import com.mms.networking.MMSResponseHandler;
import com.mms.networking.model.MMSUser;
import com.mms.networking.request.LoginRequest;

import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class SplashActivity extends ActionBarActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private SignInButton mBtnSignIn;
    private ProgressDialog mProgressLogin;

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        this.setup();
    }

    private void setup(){
        this.mBtnSignIn = (SignInButton) this.findViewById(R.id.btn_sign_in);

        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .build();
        this.mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignInClicked();
            }
        });
    }

    public void onSignInClicked(){
        this.mProgressLogin = ProgressDialog.show(this,
                null, this.getString(R.string.signing_in), true, false);

        if(!this.mGoogleApiClient.isConnected()){
            this.mGoogleApiClient.connect();
        } else {
            this.signInWithSocialNetwork(Plus.AccountApi.getAccountName(this.mGoogleApiClient),
                    LoginRequest.SocialNetwork.GOOGLE_PLUS);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();
        this.signInWithSocialNetwork(Plus.AccountApi.getAccountName(this.mGoogleApiClient),
                LoginRequest.SocialNetwork.GOOGLE_PLUS);
    }

    private void signInWithSocialNetwork(String email, LoginRequest.SocialNetwork socialNetwork){
        new LoginRequest(email, socialNetwork).executeAsync(new MMSResponseHandler<MMSUser>() {
            @Override
            public void onSuccess(MMSUser response) {
                mProgressLogin.dismiss();
                goHome(response);
                Log.d(TAG, "Success: " + new Gson().toJson(response));
            }

            @Override
            public void onFailure(List<String> errors) {
                mProgressLogin.dismiss();
                Toast.makeText(SplashActivity.this, "" + errors, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure: " + errors);
            }
        });
    }

    private void goHome(MMSUser user){
        ((MMSApplication) this.getApplication()).loginUser(user);
        this.startActivity(new Intent(this, HomeActivity.class));
        this.finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        this.mProgressLogin.dismiss();
        Toast.makeText(this, "Connection Suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.mProgressLogin.dismiss();
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }
}
