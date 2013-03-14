package com.example.kettellstest;

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

	public void onNewUserClick(View view) {
		Intent intent = new Intent(MainActivity.this, NewUser.class);
    	MainActivity.this.startActivity(intent);
    	finish();
    }
	
	public void onAlreadyRegisteredClick(View view) {
		Intent intent = new Intent(MainActivity.this, RegisteredUsers.class);
    	MainActivity.this.startActivity(intent);
    	finish();
    }
	
	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
