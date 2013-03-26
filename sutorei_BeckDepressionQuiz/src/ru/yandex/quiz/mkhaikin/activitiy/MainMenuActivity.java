package ru.yandex.quiz.mkhaikin.activitiy;

import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.db.Users;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class MainMenuActivity extends Activity {
	
	private Users users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_menu);
		users = new Users(MainMenuActivity.this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	public void onClickBeginQuiz (View view){
		Intent k = new Intent(MainMenuActivity.this, QuizActivity.class);
		startActivity(k);
		finish();
	}
	public void onClickShowStatistics(View view){
		Intent k = new Intent(MainMenuActivity.this, StatisticsActivity.class);
		startActivity(k);
		finish();
	}
	
	public void onClickChangeUser(View view){
		users.logOff();
		Intent k = new Intent(MainMenuActivity.this, StartActivity.class);
		startActivity(k);
		finish();
	}
}
