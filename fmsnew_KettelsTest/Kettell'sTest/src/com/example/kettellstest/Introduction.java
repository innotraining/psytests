package com.example.kettellstest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class Introduction extends Activity {

	String login = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduction);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = Introduction.this.getIntent();
		login = intent.getStringExtra("login");
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introduction, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onMainMenuClick(View view) {
		Intent intent = new Intent(Introduction.this, MainMenu.class);
		intent.putExtra("login", login);
		Introduction.this.startActivity(intent);
		finish();
    }
	
	public void onDebugGoToClick(View view) {
		//TODO add user form text field
		Intent intent = new Intent(Introduction.this, Part1Subtest2.class);
		intent.putExtra("login", login);
		Introduction.this.startActivity(intent);
		finish();
    }
	
	
	public void onStartClick(View view) {
		//TODO add user form text field
		Intent intent = new Intent(Introduction.this, Part1Subtest1.class);
		intent.putExtra("login", login);
		Introduction.this.startActivity(intent);
		finish();
    }
	
	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
