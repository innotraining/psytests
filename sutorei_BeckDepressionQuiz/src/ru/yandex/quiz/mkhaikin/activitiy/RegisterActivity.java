package ru.yandex.quiz.mkhaikin.activitiy;


import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.db.Users;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private Users users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		users = new Users(RegisterActivity.this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public void commitRegistration(View view){
		final EditText username = (EditText) findViewById(R.id.editText1);
		long id = -1;
		try {
			id = users.addUser(username.getText().toString());
		} catch (IllegalArgumentException e) {
			id = users.getUserId(username.getText().toString());
			users.logOff();
			users.logIn(id);
			Intent k = new Intent(RegisterActivity.this, MainMenuActivity.class);
			startActivity(k);
			finish();
		}
		if (id > 0) {
			users.logOff();
			users.logIn(id);
			Intent k = new Intent(RegisterActivity.this, MainMenuActivity.class);
			startActivity(k);
			finish();
		}
	}
	public void returnToMainMenu(View view){
		Intent k = new Intent(RegisterActivity.this, StartActivity.class);
		startActivity(k);
		finish();
	}
}
