package com.codepath.wangela.apps.twittah.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;

public class HomeTimelineFragment extends TweetsListFragment {
//	private TwitterClient client;
//	private ArrayList<Tweet> tweets;
//	private ArrayAdapter<Tweet> aTweets;
//	private PullToRefreshListView lvTweets;
	private String aHomeMaxId = "0";
	private String aHomeSinceId = "0";
//	private String timelineType = "HOME";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		setupListeners();
		
		clear();
		populateTimeline("LOAD");
		Log.d("DEBUG", aHomeMaxId);
		return v;
	}

	@Override
	public void populateTimeline(String code) {
		TwitterApplication.getRestClient().getHomeTimeline(code, "0", "0",
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray array) {
						getAdapter().addAll(Tweet.fromJsonArray(array));
					}

					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("ERROR", e.toString());
						Log.d("ERROR", s);
					}
				});
	}
	// public void populateFromDb() {
	// // TODO
	// super.populateFromDb();
	// }

}
