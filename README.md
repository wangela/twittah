# twittah for Android

twittah is an Android client for Twitter. This is a project for the CodePath Intro to Android Bootcamp which you can sign up for at (http://codepath.com/androidbootcamp).


Time spent: 31 hours spent in week 1
            19 hours spent in week 2

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
    * [x] Extra: Tweet button is disabled when tweet is over character limit
 * [ ] User can switch between Timeline and Mention views using tabs.
    * [ ] User can view their home timeline tweets.
    * [ ] User can view the recent mentions of their username.
    * [ ] User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
    * [x] Optional: Implement tabs in a gingerbread-compatible approach
 * [ ] User can navigate to view their own profile
    * [ ] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
 * [ ] User can click on the profile image in any tweet to see another user's profile.
    * [ ] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
    * [ ] Profile view should include that user's timeline
    * [ ] Optional: User can view following / followers list through the profile

Optional user stories:
 * [x] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
 * [x] Advanced: User can open the twitter app offline and see last loaded tweets
    * [x] Tweets are persisted into sqlite and can be displayed from the local DB
 * [x] Advanced: User can tap a tweet to display a "detailed" view of that tweet
 * [x] Advanced: User can select "reply" from detail view to respond to a tweet
 * [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"
 * [ ] Bonus: User can see embedded image media within the tweet detail view
 * [ ] Bonus: Compose activity is replaced with a modal overlay
 * [ ] Advanced: Robust error handling, check if internet is available, handle error cases, network failures
 * [x] Advanced: When a network request is sent, user sees an indeterminate progress indicator
 * [x] Advanced: User can "reply" to any tweet on their home timeline
    * [x] The user that wrote the original tweet is automatically "@" replied in compose
 * [ ] Advanced: User can click on a tweet to be taken to a "detail view" of that tweet
    * [ ] Advanced: User can take favorite (and unfavorite) or reweet actions on a tweet
 * [ ] Advanced: Improve the user interface and theme the app to feel twitter branded
 * [ ] Advanced: User can search for tweets matching a particular query and see results
 * [ ] Bonus: User can view their direct messages (or send new ones)
 * [x] Extra: User can click "skip to top" button to jump to most recent tweet in the currently viewed timeline

Notes:

I had so much debugging related to the pagination using Twitter API params of since ID and max ID!
That took all my time, so will plan to implement offline persistence in a future week.

Optimized pagination performance with ViewHolder pattern.

Walkthrough of all user stories:

![Video Walkthrough](anim_twittah.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).
