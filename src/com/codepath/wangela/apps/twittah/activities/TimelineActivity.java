
package com.codepath.wangela.apps.twittah.activities;

import com.codepath.wangela.apps.twittah.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TimelineActivity extends ActionBarActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
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
