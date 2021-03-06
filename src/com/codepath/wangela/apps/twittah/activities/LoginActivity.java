package com.codepath.wangela.apps.twittah.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActivity;
import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	// Inflate the menu; this adds items to the action bar if it is present.
	/* @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	} */
	
	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {
    	 Intent i = new Intent(this, TimelineActivity.class);
    	 startActivity(i);
    	Toast.makeText(this, "Logged in with OAuth", Toast.LENGTH_SHORT).show();
    }
    
    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }
    
    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
    	ProgressBar pb = (ProgressBar) findViewById(R.id.pbLogin);
    	pb.setVisibility(ProgressBar.VISIBLE);
        getClient().connect();
    }

}
