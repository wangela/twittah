package com.codepath.wangela.apps.twittah.fragments;

import java.util.Comparator;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TweetsListFragment {
	private TwitterClient client;
	private String aMaxId = "0";
	private String aSinceId = "0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
	
		// setupListeners();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tweets_list, container,
				false);
		setupViews(v);
		ProgressBar pb = (ProgressBar) v.findViewById(R.id.pbTimeline);
		pb.bringToFront();
		pb.setVisibility(ProgressBar.VISIBLE);
		clear();
		populateTimeline("LOAD");
		pb.setVisibility(ProgressBar.INVISIBLE);
		return v;
		// setupListeners();
	}

	// public void setupListeners() {
	//
	public void populateTimeline(String code) {
		client.getMentionsTimeline(code, aSinceId, aMaxId, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray array) {
						addAll(Tweet.fromJsonArray(array));
						sort(new Comparator<Tweet>() {
							public int compare(Tweet object1, Tweet object2) {
								return object2.getTid().compareTo(
										object1.getTid());
							}
						});
						notifyDataSetChanged();
						if (getCount() > 0) {
							int listSize = getCount();
							listSize--;
							aSinceId = getItem(0).getTid();
							aMaxId = getItem(listSize).getTid();
							Log.d("DEBUG", toString());
						}
					}

//					@Override
//					public void onFailure(Exception e String s) {
//						Log.d("ERROR", e.toString());
//						Log.d("ERROR", s);
//					}
				});
	}
}
