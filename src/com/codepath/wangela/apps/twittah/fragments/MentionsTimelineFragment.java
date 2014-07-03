package com.codepath.wangela.apps.twittah.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TweetsListFragment {
	private String aMentionsMaxId = "0";
	private String aMentionsSinceId = "0";
	
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
		Log.d("DEBUG", aMentionsMaxId);
		return v;
	}

	@Override
	public void populateTimeline(String code) {
		TwitterApplication.getRestClient().getMentionsTimeline(code, "0",
				"0", new JsonHttpResponseHandler() {
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

}
