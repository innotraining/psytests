package ru.yandex.quiz.mkhaikin.activitiy;

import ru.yandex.quiz.mkhaikin.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickRegistered (View view){
		Intent k = new Intent(StartActivity.this, SelectUserActivity.class);
		startActivity(k);
		finish();
	}
	public void onClickNewuser (View view){
		Intent k = new Intent(StartActivity.this, RegisterActivity.class);
		startActivity(k);
		finish();
	}
}
