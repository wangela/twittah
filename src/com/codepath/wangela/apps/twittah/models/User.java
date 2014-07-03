package com.codepath.wangela.apps.twittah.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class User extends Model implements Serializable {
	private static final long serialVersionUID = -2028099947760397602L;
	@Column(name = "Name")
	private String name;
	@Column(name = "User_ID", index = true, unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
	private long uid;
	@Column(name = "Screenname")
	private String screenname;
	@Column(name = "Profile_Image_URL")
	private String profileImageUrl;
	@Column(name = "Description")
	private String description;
	@Column(name = "FollowingCount")
	private String followingCount;
	@Column(name = "FollowerCount")
	private String followersCount;


	public User() {
		super();
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
	
	public String getDescription() {
		return description;
	}

	public String getFollowingCount() {
		return followingCount;
	}

	public String getFollowersCount() {
		return followersCount;
	}	

	// factory method User.fromJson(...)
	public static User fromJson(JSONObject object) {
		User u = new User();
		try {
			u.name = object.getString("name");
			u.uid = object.getLong("id");
			u.screenname = object.getString("screen_name");
			u.profileImageUrl = object.getString("profile_image_url");
			u.description = object.getString("description");
			u.followingCount = object.getString("friends_count");
			u.followersCount = object.getString("followers_count");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return u;
	}

}