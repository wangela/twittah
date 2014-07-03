package com.codepath.wangela.apps.twittah.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.listeners.EndlessScrollListener;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class UserTimelineFragment extends TweetsListFragment {
	private String screenname;
	private String aMaxId = "0";
	private String aSinceId = "0";
	private View v;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		screenname = getArguments().getString("screen_name", "");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_tweets_list, container,
				false);
		setupViews(v);
		return v;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ProgressBar pb = (ProgressBar) v.findViewById(R.id.pbTimeline);
		pb.bringToFront();
		pb.setVisibility(ProgressBar.VISIBLE);
		clear();
		populateTimeline("LOAD");
		setupListeners();
		pb.setVisibility(ProgressBar.INVISIBLE);
	}

	public void setupViews(View v) {
		lvTweets = (PullToRefreshListView) v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
	}
	
	public void setupListeners() {
		// Attach the listener to the AdapterView onCreate
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Triggered only when new data needs to be appended to the list
				// Add whatever code is needed to append new items to your
				// AdapterView
			populateTimeline("MORE");

			}
		});

		// Set a listener to be invoked when the list should be refreshed.
		lvTweets.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
			populateTimeline("REFRESH");
				lvTweets.onRefreshComplete();
			}
		});

		lvTweets.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Intent i = new Intent(getApplicationContext(),
//						TweetDetailActivity.class);
//				Tweet selectedTweet = aTweets.getItem(position);
//				i.putExtra("tweet", selectedTweet);
//				startActivity(i);
			}

		});

	}
	
    public static UserTimelineFragment newInstance(String sn) {
        UserTimelineFragment userTimeline = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", sn);
        userTimeline.setArguments(args);
        return userTimeline;
    }
	
    public void populateTimeline(String code) {
    	TwitterApplication.getRestClient().getUserTimeline(screenname, code, aSinceId, aMaxId,
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
