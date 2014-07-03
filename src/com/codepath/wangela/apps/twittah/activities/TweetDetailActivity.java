package com.codepath.wangela.apps.twittah.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetDetailActivity extends Activity {
	private ImageView ivTweeterPic;
	private TextView tvTweeterFullName;
	private TextView tvTweeterHandle;
	private TextView tvTweeterTweet;
	private TextView tvTimestamp;
	private Tweet tweet;

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
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public void setupViews() {
		ivTweeterPic = (ImageView) findViewById(R.id.ivTweeterPic);
		tvTweeterFullName = (TextView) findViewById(R.id.tvTweeterFullName);
		tvTweeterHandle = (TextView) findViewById(R.id.tvTweeterHandle);
		tvTweeterTweet = (TextView) findViewById(R.id.tvTweeterTweet);
		tvTimestamp = (TextView) findViewById(R.id.tvTimestamp);

		// Populate tweet
		tweet = (Tweet) getIntent().getSerializableExtra("tweet");
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),
				ivTweeterPic);
		tvTweeterFullName.setText(tweet.getUser().getName());
		tvTweeterHandle.setText("@" + tweet.getUser().getScreenname());
		tvTweeterTweet.setText(tweet.getBody());
		tvTimestamp.setText(tweet.getCreatedAgo());

	}

	public void onReply(View v) {
		Intent i = new Intent(this, ComposeActivity.class);
		i.putExtra("replyTo", tweet);
		startActivity(i);
	}
}
