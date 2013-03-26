package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int NEW_USER_ACTIVITY_REQUEST_ID = 1;
	private static final int USER_SELECT_ACTIVITY_REQUEST_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		QuizUsers users = new QuizUsers(MainActivity.this);
		
		if(users.isLogged()) {
			Intent userActionsActivity = new Intent(MainActivity.this, UserActionsActivity.class);
			startActivity(userActionsActivity);
			finish();
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		if (users.getUsers().size() == 0) {
			Button loginButton = (Button) findViewById(R.id.loginButton);
			loginButton.setVisibility(Button.GONE);
		}
	}

	public void registerButtonClickHandler(View view) {
		Intent newUserActivity = new Intent(MainActivity.this,
				NewUserActivity.class);
		startActivityForResult(newUserActivity, NEW_USER_ACTIVITY_REQUEST_ID);
	}

	public void loginButtonClickHandler(View view) {
		Intent userSelectActivity = new Intent(MainActivity.this,
				UserSelectActivity.class);
		startActivityForResult(userSelectActivity, USER_SELECT_ACTIVITY_REQUEST_ID);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case NEW_USER_ACTIVITY_REQUEST_ID:
				Intent quizActivity = new Intent(
						MainActivity.this, QuizActivity.class);
				startActivity(quizActivity);
				finish();
				break;
			case USER_SELECT_ACTIVITY_REQUEST_ID:
				Intent userActionsActivity = new Intent(
						MainActivity.this, UserActionsActivity.class);
				startActivity(userActionsActivity);
				finish();
				break;
			}
		}
	}

}
