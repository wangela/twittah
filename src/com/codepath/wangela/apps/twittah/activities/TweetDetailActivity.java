
package com.codepath.wangela.apps.twittah.activities;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.R.layout;
import com.codepath.wangela.apps.twittah.activities.models.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetDetailActivity extends Activity {
    private ImageView ivTweeterPic;
    private TextView tvTweeterFullName;
    private TextView tvTweeterHandle;
    private TextView tvTweeterTweet;
    //private Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setupViews();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void setupViews() {
        ivTweeterPic = (ImageView) findViewById(R.id.ivTweeterPic);
        tvTweeterFullName = (TextView) findViewById(R.id.tvTweeterFullName);
        tvTweeterHandle = (TextView) findViewById(R.id.tvTweeterHandle);

        // Populate tweet

    }
}
