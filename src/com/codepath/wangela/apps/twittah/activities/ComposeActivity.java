
package com.codepath.wangela.apps.twittah.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.codepath.wangela.apps.twittah.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ComposeActivity extends Activity {
    private ImageView ivUserPic;
    private TextView tvUserFull;
    private TextView tvUserHandle;
    private EditText etComposeTweet;
    private TextView tvCharCount;
    private TwitterClient client;
    private Tweet replyTweet;
    private User user;
    private RequestParams params = new RequestParams();

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
        tvCharCount = (TextView) findViewById(R.id.tvCharCount);

        // Populate header with user's photo and name. Requires an API call.
        loadUser();
        
        replyTweet = (Tweet) getIntent().getSerializableExtra("replyTo");
        if (replyTweet != null) {
            params.put("in_reply_to_status_id", replyTweet.getId());
            etComposeTweet.setText("@" + replyTweet.getUser().getScreenname() + " ");
        }
        
        etComposeTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }
            
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // Fires right before text is changing
            }
            
            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                int length = 139 - s.length() - user.getScreenname().length();
                tvCharCount.setText(String.valueOf(length));
                if (length > 70) {
                    tvCharCount.setVisibility(View.INVISIBLE);
                } else if (length > 20) {
                    tvCharCount.setVisibility(View.VISIBLE);
                    tvCharCount.setTextColor(Color.GRAY);
                } else {
                    tvCharCount.setTextColor(Color.RED);
                }
            }
        });
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
        client.postTweet(tweetText, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                Tweet tweet = Tweet.fromJson(response);
            };

            @Override
            public void onFailure(Throwable e, String s) {
                Log.d("ERROR", e.toString());
                Log.d("ERROR", s);
            };
        });

        Intent i = new Intent();
        setResult(RESULT_OK, i);
        this.finish();
    }
}
