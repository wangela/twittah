package com.codepath.wangela.apps.twittah.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User implements Serializable {
    private static final long serialVersionUID = -2028099947760397602L;
    private String name;
	private long uid;
	private String screenname;
	private String profileImageUrl;

	// factory method User.fromJson(...)
	public static User fromJson(JSONObject object) {
		User u = new User();
		try {
			u.name = object.getString("name");
			u.uid = object.getLong("id");
			u.screenname = object.getString("screen_name");
			u.profileImageUrl = object.getString("profile_image_url");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return u;
	}

	public String getName() {
		return name;
	}

	public long getUid() {
		return uid;
	}

	public String getScreenname() {
		return screenname;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

}
