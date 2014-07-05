
package com.codepath.wangela.apps.twittah.adapters;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

    // View lookup cache
    private static class ViewHolder {
        ImageView profileImage;
        TextView userName;
        TextView userFullName;
        TextView tweetBody;
        TextView relativeTime;
    }

    public TweetArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { 
        Tweet tweet = getItem(position); // Find or inflate the template
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.tweet_item, parent, false); 
            viewHolder.profileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.userFullName = (TextView) convertView.findViewById(R.id.tvUserFullName);
            viewHolder.tweetBody = (TextView) convertView.findViewById(R.id.tvTweetBody);
            viewHolder.relativeTime = (TextView) convertView.findViewById(R.id.tvRelativeTime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.profileImage.setImageResource(android.R.color.transparent);
        ImageLoader imageLoader = ImageLoader.getInstance();
        
        imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), viewHolder.profileImage);
        viewHolder.userName.setText("@" + tweet.getUser().getScreenname());
        viewHolder.userFullName.setText(tweet.getUser().getName());
        viewHolder.tweetBody.setText(tweet.getBody());
        viewHolder.relativeTime.setText(tweet.getCreatedAgo());
        
        return convertView;
    }
}