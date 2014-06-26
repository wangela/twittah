
package com.codepath.wangela.apps.twittah.activities;

import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.activeandroid.util.Log;
import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.models.Tweet;
import com.codepath.wangela.apps.twittah.adapters.TweetArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class TimelineActivity extends Activity {
    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private PullToRefreshListView lvTweets;
    private String aMaxId = "0";
    private String aSinceId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        client = TwitterApplication.getRestClient();

        lvTweets = (PullToRefreshListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<Tweet>();
        aTweets = new TweetArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);

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
                // Your code to refresh the list contents
                // Make sure you call listView.onRefreshComplete()
                // once the loading is done. This can be done from here or any
                // place such as when the network request has completed
                // successfully.
                populateTimeline("REFRESH");

                lvTweets.onRefreshComplete();
            }
        });

        aTweets.clear();
        populateTimeline("LOAD");

        Log.d("DEBUG", aMaxId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
     * public void populateTimeline(String since_id, String max_id) {
     * client.getHomeTimeline(new JsonHttpResponseHandler() {
     * @Override public void onSuccess(JSONArray array) {
     * aTweets.addAll(Tweet.fromJsonArray(array)); }
     * @Override public void onFailure(Throwable e, String s) { Log.d("ERROR",
     * e.toString()); Log.d("ERROR", s); } }, since_id, max_id); }
     */

    public void populateTimeline(String code) {
        client.getHomeTimeline(code, aSinceId, aMaxId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray array) {
                aTweets.addAll(Tweet.fromJsonArray(array));
                aTweets.sort(new Comparator<Tweet>() {
                    public int compare(Tweet object1, Tweet object2) {
                        return object2.getId().compareTo(object1.getId());
                    }
                });
                aTweets.notifyDataSetChanged();
                if (aTweets.getCount() > 0) {
                    int listSize = aTweets.getCount();
                    listSize--;
                    aSinceId = aTweets.getItem(0).getId();
                    aMaxId = aTweets.getItem(listSize).getId();
                    Log.d("DEBUG", aTweets.toString());
                }
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }

    public void onCompose(MenuItem mi) {
        Intent i = new Intent(this, Compose.class);
        startActivityForResult(i, 18);
    }

    public void onTop(MenuItem mi) {
        lvTweets.setSelection(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == 18) {
            populateTimeline("REFRESH");
            aSinceId = aTweets.getItem(0).getId();
        }
    }
}
