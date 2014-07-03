package com.codepath.wangela.apps.twittah.fragments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.TwitterApplication;
import com.codepath.wangela.apps.twittah.adapters.TweetArrayAdapter;
import com.codepath.wangela.apps.twittah.helpers.EndlessScrollListener;
import com.codepath.wangela.apps.twittah.helpers.TwitterClient;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class TweetsListFragment extends Fragment {
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private PullToRefreshListView lvTweets;
//	private OnClickListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(getActivity(), tweets); // use
																// getActivity
																// very
																// sparingly
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout
		View v = inflater.inflate(R.layout.fragment_tweets_list, container,
				false);
		// Assign our view references
		setupViews(v);
	
		return v;
	}

	public void setupViews(View v) {
		lvTweets = (PullToRefreshListView) v.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(aTweets);
	}


	
//	public interface OnClickListener {
//		public void onShowDetailedTweet(Tweet selectedTweet);
//	}
	
//	  @Override
//	  public void onAttach(Activity activity) {
//	    super.onAttach(activity);
//	      if (activity instanceof OnClickListener) {
//	        listener = (OnClickListener) activity;
//	      } else {
//	        throw new ClassCastException(activity.toString()
//	            + " must implement TweetsListFragment.OnClickListener");
//	      }
//	  }


	public void populateFromDb() {
		//		ProgressBar pb = (ProgressBar) this.getView().findViewById(R.id.pbHome);
		//pb.bringToFront();
		//pb.setVisibility(ProgressBar.VISIBLE);
		notifyDataSetInvalidated();
		List<Tweet> oldTweets = Tweet.findAll();
		addAll((ArrayList<Tweet>) oldTweets);
		//pb.setVisibility(ProgressBar.INVISIBLE);
	}

	// Delegate the methods to the internal adapter
	public void addAll(ArrayList<Tweet> tweets) {
		aTweets.addAll(tweets);
	}

	public void sort(Comparator<Tweet> comparator) {
		aTweets.sort(comparator);
	}

	public ArrayAdapter<Tweet> getAdapter() {
		return aTweets;
	}

	public void notifyDataSetChanged() {
		aTweets.notifyDataSetChanged();
	}

	public void notifyDataSetInvalidated() {
		aTweets.notifyDataSetInvalidated();
	}

	public int getCount() {
		return aTweets.getCount();
	}

	public Tweet getItem(int position) {
		return aTweets.getItem(position);
	}

	public String toString() {
		return aTweets.toString();
	}

	public void clear() {
		aTweets.clear();
	}

	public void toTop() {
		lvTweets.setSelection(0);
	}

}
