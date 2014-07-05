package com.codepath.wangela.apps.twittah.fragments;

import java.util.Comparator;

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
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.listeners.EndlessScrollListener;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class MentionsTimelineFragment extends TweetsListFragment {
	private TwitterClient client;
	private String aMaxId = "0";
	private String aSinceId = "0";
	private PullToRefreshListView lvTweets;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tweets_list, container,
				false);
		setupViews(v);
		ProgressBar pb = (ProgressBar) v.findViewById(R.id.pbTimeline);
		pb.setVisibility(ProgressBar.VISIBLE);
		clear();
		// if (Tweet.findAll().size() > 0) {
		// populateFromDb();
		// } else {
		populateTimeline("LOAD");
		// }
		setupListeners();
		return v;
	}

	@Override
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
				// Intent i = new Intent(getApplicationContext(),
				// TweetDetailActivity.class);
				// Tweet selectedTweet = aTweets.getItem(position);
				// i.putExtra("tweet", selectedTweet);
				// startActivity(i);
			}

		});

	}

	public void populateTimeline(String code) {
		client.getMentionsTimeline(code, aSinceId, aMaxId,
				new JsonHttpResponseHandler() {
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

					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("ERROR", e.toString());
						Log.d("ERROR", s);
					}
				});
	}

	@Override
	public void toTop() {
		lvTweets.setSelection(0);
	}
}
