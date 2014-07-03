package com.codepath.wangela.apps.twittah.helpers;

import org.json.JSONObject;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change
	// this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change
	// this,
	// base
	// API
	// URL
	public static final String REST_CONSUMER_KEY = "WN3SbP10m3qK1prPF14gd9t3j"; // Change
	// this
	public static final String REST_CONSUMER_SECRET = "7J24pQ6iQuvvfJb8ITZJwvf8DypaIjuW8m1jCKTcVspjCH2ybC"; // Change
	// this
	public static final String REST_CALLBACK_URL = "oauth://cptwittahtweets"; // Change

	// this
	// (here
	// and
	// in
	// manifest)
	// private
	// String
	// userScreenname
	// =
	// "";

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// public void getTimeline(String code, String timelineType, String sinceId,
	// String maxId, AsyncHttpResponseHandler handler) {
	// RequestParams params = new RequestParams();
	// String apiUrl = "";
	// if (timelineType == "HOME") {
	// apiUrl = getApiUrl("statuses/home_timeline.json");
	// } else if (code == "MENTIONS") {
	// apiUrl = getApiUrl("statuses/mentions_timeline.json");
	// } else if (code == "USER") {
	// apiUrl = getApiUrl("statuses/user_timeline.json");
	// params.put("user_id", userScreenname);
	// }
	// if (code == "LOAD") { // initial load
	// params = null;
	// } else if (code == "MORE") { // load more / pagination
	// params.put("max_id", maxId);
	// } else if (code == "REFRESH") { // refresh timeline
	// params.put("since_id", sinceId);
	// } else {
	// Log.d("ERROR", "code has invalid value of " + code);
	// }
	// client.get(apiUrl, params, handler);
	// }

	public void getHomeTimeline(String code, String sinceId, String maxId,
			AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		RequestParams params = new RequestParams();
		if (code == "LOAD") { // initial load
			params = null;
		} else if (code == "MORE") { // load more / pagination
			params.put("max_id", maxId);
		} else if (code == "REFRESH") { // refresh timeline
			params.put("since_id", sinceId);
		} else {
			Log.d("ERROR", "code has invalid value of " + code);
		}
		client.get(apiUrl, params, handler);
	}

	public void getMentionsTimeline(String code, String sinceId, String maxId,
			AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		RequestParams params = new RequestParams();
		if (code == "LOAD") { // initial load
			params = null;
		} else if (code == "MORE") { // load more / pagination
			params.put("max_id", maxId);
		} else if (code == "REFRESH") { // refresh timeline
			params.put("since_id", sinceId);
		} else {
			Log.d("ERROR", "code has invalid value of " + code);
		}
		client.get(apiUrl, params, handler);
	}

	public void getUserTimeline(String screenname, String code, String sinceId,
			String maxId, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
		params.put("screen_name", screenname);
		if (code == "LOAD") { // initial load
			params = null;
		} else if (code == "MORE") { // load more / pagination
			params.put("max_id", maxId);
		} else if (code == "REFRESH") { // refresh timeline
			params.put("since_id", sinceId);
		} else {
			Log.d("ERROR", "code has invalid value of " + code);
		}
		client.get(apiUrl, params, handler);
	}


	public void getMyProfile(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("account/verify_credentials.json");
		client.get(apiUrl, handler);
	}

	public void getUserProfile(String screenname,
			AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("users/lookup.json"); // Returns ARRAY not object
		RequestParams params = new RequestParams();
		params.put("screen_name", screenname);
		client.get(apiUrl, params, handler);
	}

	public void postTweet(String status, RequestParams params,
			AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/update.json");
		params.put("status", status);
		client.post(apiUrl, params, handler);
	}

}
