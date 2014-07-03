package com.codepath.wangela.apps.twittah.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.fragments.UserTimelineFragment;
import com.codepath.wangela.apps.twittah.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {
	private ImageView ivProfile;
	private TextView tvProfileName;
	private TextView tvDescription;
	private TextView tvLocation;
	private TextView tvFollowing;
	private TextView tvFollowers;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_profile);
		loadProfileInfo();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void loadProfileInfo() {
		user = (User) getIntent().getSerializableExtra("User");
		populateProfileHeader(user);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserTimelineFragment myTimeline = UserTimelineFragment.newInstance(user.getScreenname());
		ft.replace(R.id.flUserTimeline, myTimeline);
		ft.commit();
	}

	private void populateProfileHeader(User user) {
		ivProfile = (ImageView) findViewById(R.id.ivProfile);
		tvProfileName = (TextView) findViewById(R.id.tvProfileName);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(user.getProfileImageUrl(),
				ivProfile);
		tvProfileName.setText(user.getName());
		tvDescription.setText(user.getDescription());
		tvLocation.setText(user.getLocation());
		tvFollowing.setText(user.getFollowingCount() + " following");
		tvFollowers.setText(user.getFollowersCount() + " followers");
		getActionBar().setTitle("@" + user.getScreenname());
	}
}
