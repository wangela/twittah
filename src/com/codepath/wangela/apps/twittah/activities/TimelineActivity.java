package com.codepath.wangela.apps.twittah.activities;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.activeandroid.util.Log;
import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.fragments.HomeTimelineFragment;
import com.codepath.wangela.apps.twittah.fragments.MentionsTimelineFragment;
import com.codepath.wangela.apps.twittah.fragments.TweetsListFragment;
import com.codepath.wangela.apps.twittah.listeners.SupportFragmentTabListener;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.codepath.wangela.apps.twittah.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupTabs();
	}

	private void setupTabs() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
				.newTab()
				.setText("HOME")
				.setIcon(R.drawable.ic_home)
				.setTag("HomeTimelineFragment")
				.setTabListener(
						new SupportFragmentTabListener<HomeTimelineFragment>(
								R.id.flTimeline, this, "home",
								HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("MENTIONS")
				.setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
						new SupportFragmentTabListener<MentionsTimelineFragment>(
								R.id.flTimeline, this, "mentions",
								MentionsTimelineFragment.class));
		actionBar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case R.id.miTop:
			onTop(item);
			return true;
			// Respond to the action bar's Tweet button
		case R.id.miProfile:
			onProfileView(item);
			return true;
		case R.id.miCompose:
			onCompose(item);
		}
		return super.onOptionsItemSelected(item);
	}

	public void onCompose(MenuItem mi) {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivityForResult(i, 18);
	}

	public void onTop(MenuItem mi) {
		String fragTag = (String) getSupportActionBar().getSelectedTab()
				.getTag();
		TweetsListFragment fragmentTimeline = (TweetsListFragment) getSupportFragmentManager()
				.findFragmentByTag(fragTag);
		fragmentTimeline.toTop();
	}

	public void onProfileView(MenuItem mi) {
		TwitterApplication.getRestClient().getMyProfile(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User u = User.fromJson(json);
						Intent i = new Intent(getApplicationContext(),
								ProfileActivity.class);
						i.putExtra("User", u);
						startActivity(i);
					}

					public void onFailure(Throwable e) {
						Log.d("DEBUG", "Fetch timeline error: " + e.toString());
					}
				});
	}

	public void onShowDetailedTweet(Tweet selectedTweet) {
		Intent i = new Intent(this, TweetDetailActivity.class);
		i.putExtra("tweet", selectedTweet);
		startActivity(i);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// REQUEST_CODE is defined above
		if (resultCode == RESULT_OK && requestCode == 18) {
			// TODO add newly composed tweet to array and call
			// populateTimeline("REFRESH");
		}
	}
}
