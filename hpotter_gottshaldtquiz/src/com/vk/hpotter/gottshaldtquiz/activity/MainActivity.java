package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void newUserButtonClickHandler(View view) {
		Intent newUserActivity = new Intent(MainActivity.this, NewUserActivity.class);
		startActivity(newUserActivity);
	}

	public void registeredUserButtonClickHandler(View view) {
		Intent userSelectActivity = new Intent(MainActivity.this, UserSelectActivity.class);
		startActivity(userSelectActivity);
	}
}
