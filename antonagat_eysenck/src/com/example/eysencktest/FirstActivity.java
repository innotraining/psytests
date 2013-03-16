package com.example.eysencktest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}
	public void onClickExit(View v){
		finish();  
	}
	public void onClickChooseTest(View v){
		Intent intent = new Intent();
		intent.setClass(FirstActivity.this, ListActivity.class);
		startActivity(intent);
	}
	public void onClickChooseAddUser(View v){
		Intent intent = new Intent();
		intent.setClass(FirstActivity.this, AddUserActivity.class);
		startActivity(intent);
		//finish(); 
	}
}
