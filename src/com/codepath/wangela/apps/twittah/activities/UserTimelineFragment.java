package com.codepath.wangela.apps.twittah.activities;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.fragments.TweetsListFragment;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	private String screenname;
	private String aUserMaxId = "0";
	private String aUserSinceId = "0";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		screenname = getArguments().getString("screen_name", "");
		populateTimeline("LOAD");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout
		View v = inflater.inflate(R.layout.fragment_tweets_list, container,
				false);
		// Assign our view references
		setupViews(v);
		//setupListeners();
		
		clear();
		populateTimeline("LOAD");
		Log.d("DEBUG", aUserMaxId);
		return v;
	}
	
    public static UserTimelineFragment newInstance(String screenname) {
        UserTimelineFragment userTimeline = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenname);
        userTimeline.setArguments(args);
        return userTimeline;
    }
	
    public void populateTimeline(String code) {
    	TwitterApplication.getRestClient().getUserTimeline(screenname, code, "0", "0",
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray array) {
						addAll(Tweet.fromJsonArray(array));
					}
					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("ERROR", e.toString());
						Log.d("ERROR", s);
					}
				});
	}
}
