
package com.codepath.wangela.apps.twittah.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateUtils;

public class Tweet implements Serializable {
    private static final long serialVersionUID = -8206447314307801065L;
    private String body;
    private String createdAt;
    private User user;
    private String tid; 
    private String createdAgo;

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
    
    public String getId() {
        return tid;
    }
    
    public String getCreatedAgo() {
        return createdAgo;
    }

    public static Tweet fromJson(JSONObject object) {
        Tweet tweet = new Tweet();
        // Extract values from JSON to populate the member variables
        try {
            tweet.body = object.getString("text");
            tweet.createdAt = object.getString("created_at");
            tweet.user = User.fromJson(object.getJSONObject("user"));
            tweet.tid = object.getString("id_str");
            tweet.createdAgo = getRelativeTimeAgo(tweet.createdAt);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJsonArray(JSONArray array) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(array.length());
        // Process each result in json array, decode and convert to tweet object
        for (int i = 0; i < array.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = array.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tweet tweet = Tweet.fromJson(tweetJson);
            if (tweet != null) {
                tweets.add(tweet);
            }
        }

        return tweets;
    }
    
    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeTime = "";
        try {
            long epochTime = sf.parse(rawJsonDate).getTime();
            relativeTime = DateUtils.getRelativeTimeSpanString(epochTime,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeTime;
    }

    @Override
    public String toString() {
        return getBody() + " - " + getUser().getScreenname();
    }


}
