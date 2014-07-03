
package com.codepath.wangela.apps.twittah.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.wangela.apps.twittah.R;
import com.codepath.wangela.apps.twittah.fragments.HomeTimelineFragment;
import com.codepath.wangela.apps.twittah.fragments.MentionsTimelineFragment;
import com.codepath.wangela.apps.twittah.listeners.SupportFragmentTabListener;

public class TimelineActivity extends ActionBarActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        setupTabs();
    }
    
	private void setupTabs() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
				.newTab()
				.setText("HOME")
				.setIcon(R.drawable.ic_home)
				.setTag("HomeTimelineFragment")
				.setTabListener(
						new SupportFragmentTabListener<HomeTimelineFragment>(
								R.id.flTimeline, this, "home",
								HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("MENTIONS")
				.setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
						new SupportFragmentTabListener<MentionsTimelineFragment>(
								R.id.flTimeline, this, "mentions",
								MentionsTimelineFragment.class));
		actionBar.addTab(tab2);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCompose:
                onCompose(item);
                return true;
            case R.id.miTop:
                onTop(item);
                return true;
            case R.id.miProfile:
            	onProfile(item);
            	return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCompose(MenuItem mi) {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, 18);
    }

    public void onTop(MenuItem mi) {
         // TODO call toTop in the active fragment
    	// lvTweets.setSelection(0);
    }
    
    public void onProfile(MenuItem mi) {
    	// TODO launch PRofile activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO take tweet back from Compose screen and add to array, then refresh timeline
        if (resultCode == RESULT_OK && requestCode == 18) {
            // populateTimeline("REFRESH");
            // aSinceId = aTweets.getItem(0).getId();
        }
    }
}
