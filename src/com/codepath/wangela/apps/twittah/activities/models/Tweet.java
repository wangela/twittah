
package com.codepath.wangela.apps.twittah.activities.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    private String body;
    private String createdAt;
    private User user;
    private String tid;

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

    public static Tweet fromJson(JSONObject object) {
        Tweet tweet = new Tweet();
        // Extract values from JSON to populate the member variables
        try {
            tweet.body = object.getString("text");
            tweet.createdAt = object.getString("created_at");
            tweet.user = User.fromJson(object.getJSONObject("user"));
            tweet.tid = object.getString("id_str");
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

    @Override
    public String toString() {
        return getBody() + " - " + getUser().getScreenname();
    }


}
