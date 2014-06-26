# twittah for Android

twittah is an Android client for Twitter. This is a project for the CodePath Intro to Android Bootcamp which you can sign up for at (http://codepath.com/androidbootcamp).


Time spent: 27 hours spent so far

Required user stories:

 * [x] Required: User can sign in to Twitter using OAuth login
 * [x] Required: User can view the tweets from their home timeline
    * [x] User can see the username, name, body and timestamp for each tweet
    * [x] User sees the relative timestamp for a tweet "8m", "7h"
    * [x] User can view more tweets as they scroll with infinite pagination
    * [x] Optional: Links in tweets are clickable and will launch the web browser
 * [x] Required: User can compose a new tweet
    * [x] User can click a “Compose” icon in the Action Bar on the top right
    * [x] User can then enter a new tweet and post this to twitter
    * [x] User is taken back to home timeline with new tweet visible in timeline
    * [x] Optional: User can see a counter with total number of characters left for tweet
    * [x] Optional: Total number of characters left turn red when user is close to reaching limit

Optional user stories:
 * [x] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
 * [ ] Advanced: User can open the twitter app offline and see last loaded tweets
    * [ ] Tweets are persisted into sqlite and can be displayed from the local DB
 * [x] Advanced: User can tap a tweet to display a "detailed" view of that tweet
 * [x] Advanced: User can select "reply" from detail view to respond to a tweet
 * [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"
 * [ ] Bonus: User can see embedded image media within the tweet detail view
 * [ ] Bonus: Compose activity is replaced with a modal overlay
 * [x] Extra: User can click "skip to top" button to jump to most recent tweet in home timeline

Notes:

I had so much debugging related to the pagination using Twitter API params of since ID and max ID!
That took all my time, so will plan to implement offline persistence in a future week.

Optimized pagination performance with ViewHolder pattern.

Walkthrough of all user stories:

![Video Walkthrough](anim_twittah.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).
