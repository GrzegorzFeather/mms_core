package com.mms.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.mms.vendetta.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class SplashActivity extends ActionBarActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @InjectView(R.id.btn_sign_in) SignInButton mBtnSignIn;

    private ProgressDialog mProgressLogin;
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash);
        this.setup();
    }

    private void setup(){
        ButterKnife.inject(this);
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
        this.mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        this.mProgressLogin.dismiss();
        Toast.makeText(this, "Connection Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.mProgressLogin.dismiss();
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }
}
