
package com.codepath.wangela.apps.twittah.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.activities.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

    /* // View lookup cache
    private static class ViewHolder {
        ImageView profileImage;
        TextView userName;
        TextView userFullName;
        TextView tweetBody;
        TextView relativeTime;
    } */

    public TweetArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    /*
     * @Override public View getView(int position, View convertView, ViewGroup
     * parent) { // Get the data item for position Tweet tweet =
     * getItem(position); // Find or inflate the template ViewHolder viewHolder;
     * if (convertView == null) { viewHolder = new ViewHolder(); LayoutInflater
     * inflater = LayoutInflater.from(getContext()); convertView =
     * inflater.inflate(R.layout.tweet_item, parent, false); // Find the views
     * within template viewHolder.profileImage = (ImageView)
     * convertView.findViewById(R.id.ivProfileImage); viewHolder.userName =
     * (TextView) convertView.findViewById(R.id.tvUserName);
     * viewHolder.userFullName = (TextView)
     * convertView.findViewById(R.id.tvUserFullName); viewHolder.tweetBody =
     * (TextView) convertView.findViewById(R.id.tvTweetBody);
     * viewHolder.relativeTime = (TextView)
     * convertView.findViewById(R.id.tvRelativeTime);
     * convertView.setTag(viewHolder); } else { viewHolder = (ViewHolder)
     * convertView.getTag(); }
     * viewHolder.profileImage.setImageResource(android.R.color.transparent);
     * ImageLoader imageLoader = ImageLoader.getInstance(); String relativeTime
     * = getRelativeTimeAgo(tweet.getCreatedAt()); // Populate the data into the
     * template view using the tweet object
     * imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),
     * viewHolder.profileImage); viewHolder.userName.setText("@" +
     * tweet.getUser().getScreenname());
     * viewHolder.userFullName.setText(tweet.getUser().getName());
     * viewHolder.tweetBody.setText(tweet.getBody());
     * viewHolder.relativeTime.setText(relativeTime); return convertView; }
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for position
        Tweet tweet = getItem(position);
        // Find or inflate the template
        View v;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.tweet_item, parent, false);
        } else {
            v = convertView;
        }

        // Find the views within template
        ImageView profileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
        TextView userName = (TextView) v.findViewById(R.id.tvUserName);
        TextView userFullName = (TextView) v.findViewById(R.id.tvUserFullName);
        TextView tweetBody = (TextView) v.findViewById(R.id.tvTweetBody);
        TextView relativeTime = (TextView) v.findViewById(R.id.tvRelativeTime);

        profileImage.setImageResource(android.R.color.transparent);
        ImageLoader imageLoader = ImageLoader.getInstance();
        String relativeTimeString = getRelativeTimeAgo(tweet.getCreatedAt());

        // Populate the data into the template view using the tweet object
        imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), profileImage);
        userName.setText("@" + tweet.getUser().getScreenname());
        userFullName.setText(tweet.getUser().getName());
        tweetBody.setText(tweet.getBody());
        relativeTime.setText(relativeTimeString);

        return v;
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
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

}
