package com.codepath.wangela.apps.twittah.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

@Table(name = "Tweets")
public class Tweet extends Model implements Serializable {
	private static final long serialVersionUID = -8206447314307801065L;
	@Column(name = "Body")
	private String body;
	@Column(name = "Created_At")
	private String createdAt;
	@Column(name = "User", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
	private User user;
	@Column(name = "Tweet_ID", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	private String tid;
	@Column(name = "offline")
	private String offlineTweet = "N";
	private String createdAgo;

	public Tweet() {
		super();
	}

	public static List<Tweet> findAll() {
		return new Select().from(Tweet.class).execute();
	}

	public static void deleteAll() {
		new Delete().from(Tweet.class).execute();
	}

	public static List<Tweet> getAllOfflineTweets(int limit) {

		return new Select().from(Tweet.class).where("offline = ?", "Y")
				.orderBy("Created_At ASC").limit(limit).execute();
	}

	public String getBody() {
		return body;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}

	public String getTid() {
		return tid;
	}

	public String getCreatedAgo() {
		this.createdAgo = Tweet.getRelativeTimeAgo(this.createdAt);
		return createdAgo;
	}

	public static Tweet fromJson(JSONObject object) {
		Tweet tweet = new Tweet(); // removed when converting to SQLite
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
		JSONObject tweetJson = null;

		// Process each result in json array, decode and convert to tweet object
		for (int i = 0; i < array.length(); i++) {
			try {
				tweetJson = array.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = Tweet.fromJson(tweetJson);
			if (tweet != null) {
				tweet.getUser().save();
				tweet.save();
				tweets.add(tweet);
			}
		}

		return tweets;
	}

	// getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
	public static String getRelativeTimeAgo(String rawJsonDate) {
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat,
				Locale.ENGLISH);
		sf.setLenient(true);

		String relativeTime = "";
		try {
			long epochTime = sf.parse(rawJsonDate).getTime();
			relativeTime = DateUtils.getRelativeTimeSpanString(epochTime,
					System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS)
					.toString();
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
