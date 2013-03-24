package ru.bt_enterprise.example.raven_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainMenuActivity extends Activity {
	private QuizUsers users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		users = new QuizUsers(MainMenuActivity.this);

	}
	
	public void onMainMenuSignUpButtonClick(View v){
		Intent intent = new Intent(MainMenuActivity.this, SignUpActivity.class);
		//intent.putExtra("userId", userId);
		startActivity(intent);
	}
	
	public void onMainMenuLoginButtonClick(View v){
		Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
//		intent.putExtra("userId", userId);
		startActivity(intent);
	}

	public void onDeleteDBButtonClick(View v){
		users.recreate();
	}
	
}
