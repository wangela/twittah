package com.codepath.wangela.apps.twittah.fragments;

import java.util.Comparator;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.helpers.EndlessScrollListener;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class HomeTimelineFragment extends TweetsListFragment {
	private TwitterClient client;
	private String aMaxId = "0";
	private String aSinceId = "0";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
		clear();
		populateTimeline("LOAD");
		// setupListeners();
	}

	// public void setupListeners() {
	//
	public void populateTimeline(String code) {
		// ProgressBar pb = (ProgressBar) getView().findViewById(R.id.pbHome);
		// pb.bringToFront();
		// pb.setVisibility(ProgressBar.VISIBLE);
		client.getHomeTimeline(code, aSinceId, aMaxId, new JsonHttpResponseHandler() {
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
		// pb.setVisibility(ProgressBar.INVISIBLE);
	}
}
