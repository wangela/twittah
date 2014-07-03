# twittah for Android

twittah is an Android client for Twitter. This is a project for the CodePath Intro to Android Bootcamp which you can sign up for at (http://codepath.com/androidbootcamp).


Time spent: 31 hours spent in week 1, 49 hours spent in week 2

## Required user stories:

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
 * [x] User can switch between Timeline and Mention views using tabs.
    * [x] User can view their home timeline tweets.
    * [x] User can view the recent mentions of their username.
    * [x] User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
    * [x] Optional: Implement tabs in a gingerbread-compatible approach
 * [x] User can navigate to view their own profile
    * [x] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
 * [ ] User can click on the profile image in any tweet to see another user's profile.
    * [ ] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
    * [ ] Profile view should include that user's timeline
    * [ ] Optional: User can view following / followers list through the profile

## Optional user stories:
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
 * [ ] Advanced: User can search for tweets matching a particular query and see results
 * [ ] Bonus: User can view their direct messages (or send new ones)
 * [x] Extra: User can click "skip to top" button to jump to most recent tweet in the currently viewed timeline

## Notes:

Had to disable ActionBar custom styling theme in order to prevent app crashes at login. Will revisit theme syntax to correct.

Optimized pagination performance with ViewHolder pattern.

## Walkthrough of all user stories:  
Week 2 implementation:  
![Video walkthrough](anim_twittah2.gif)

Week 1 implementation:  
![Video Walkthrough](anim_twittah.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).
