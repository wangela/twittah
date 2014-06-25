
package com.codepath.wangela.apps.twittah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.codepath.wangela.apps.twittah.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Compose extends Activity {
    private ImageView ivUserPic;
    private TextView tvUserFull;
    private TextView tvUserHandle;
    private EditText etComposeTweet;
    private TwitterClient client;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        client = TwitterApplication.getRestClient();
        setupViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose, menu);
        return true;
    }

    public void setupViews() {
        etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
        ivUserPic = (ImageView) findViewById(R.id.ivUserPic);
        tvUserFull = (TextView) findViewById(R.id.tvUserFull);
        tvUserHandle = (TextView) findViewById(R.id.tvUserHandle);

        // Populate header with user's photo and name. Requires an API call.
        loadUser();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
                // Respond to the action bar's Tweet button
            case R.id.miTweet:
                onTweet();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadUser() {
        client.getUserProfile(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(org.json.JSONObject userObject) {
                user = User.fromJson(userObject);
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(user.getProfileImageUrl(), ivUserPic);
                tvUserFull.setText(user.getName());
                tvUserHandle.setText("@" + user.getScreenname());

                Toast.makeText(getApplicationContext(), "User " + user.getScreenname() + " loaded",
                        Toast.LENGTH_SHORT).show();
            };

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("ERROR", e.toString());
                Log.d("ERROR", s);
            };
        });
    }

    public void onTweet() {
        String tweetText = etComposeTweet.getText().toString();

        Toast.makeText(this, "Tweet to post: " + tweetText, Toast.LENGTH_LONG).show();
        client.postTweet(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                Tweet tweet = Tweet.fromJson(response);
            };

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("ERROR", e.toString());
                Log.d("ERROR", s);
            };
        }, tweetText);

        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }
}
