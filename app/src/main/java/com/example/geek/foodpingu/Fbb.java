package com.example.geek.foodpingu;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.util.Arrays;


public class Fbb extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "Success";
    // private static final String TAG = "SUCCESS";

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    private Bundle bundle;
        private LoginButton loginBtn;
        private TextView username;
        private UiLifecycleHelper uiHelper;
    //private CharSequence mTitle;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            uiHelper = new UiLifecycleHelper(this, statusCallback);
            uiHelper.onCreate(savedInstanceState);

            setContentView(R.layout.activity_fbb);
            findViewById(R.id.sign_in_button).setOnClickListener(this);
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Plus.API)
                    .addScope(new Scope(Scopes.PROFILE))
                    .build();

            username = (TextView) findViewById(R.id.username);
            loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
            loginBtn.setReadPermissions(Arrays.asList("email"));
            loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
                @Override
                public void onUserInfoFetched(GraphUser user) {
                    if (user != null) {
                        //username.setText("You are currently logged in as " + user.getName());
                        String s = user.getName();
                        Intent i = new Intent(Fbb.this, MyActivity.class);
                        i.putExtra("Name", s);
                        startActivity(i);
                    } else {
                        username.setText(" ");
                    }
                }
            });
        }

        private Session.StatusCallback statusCallback = new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {
                if (state.isOpened()) {
                  //  Log.d("MainActivity", "Facebook session opened.");
                } else if (state.isClosed()) {
                //    Log.d("MainActivity", "Facebook session closed.");
                }
            }
        };
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
        @Override
        public void onResume() {
            super.onResume();
            uiHelper.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            uiHelper.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            uiHelper.onDestroy();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            uiHelper.onActivityResult(requestCode, resultCode, data);
            Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

            if (requestCode == RC_SIGN_IN) {
                // If the error resolution was not successful we should not resolve further.
                if (resultCode != RESULT_OK) {
                    mShouldResolve = false;
                }

                mIsResolving = false;
                mGoogleApiClient.connect();
            }
        }

        @Override
        public void onSaveInstanceState(Bundle savedState) {
            super.onSaveInstanceState(savedState);
            uiHelper.onSaveInstanceState(savedState);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.my, menu);
          //  restoreActionBar();
            return true;
        }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                showErrorDialog(connectionResult);
            }
        }
    }

    private void showErrorDialog(ConnectionResult connectionResult) {
    }

    @Override
    public void onClick(View v) {
        // ...
        if (v.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }


    }


    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        //mStatusTextView.setText(R.string.signing_in);
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
        showSignedInUI();
    }

    private void showSignedInUI() {
        Intent i = new Intent(this,MyActivity.class);
        startActivity(i);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }



}