package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.User;
import com.congnitive.test.mmpitest.utilities.Utility;

public class NewUserActivity extends Activity {
	private RadioButton maleBut;
	private RadioButton femaleBut;
	private EditText userNameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		maleBut = (RadioButton) findViewById(R.id.NewUserRadioButtonMale);
		femaleBut = (RadioButton) findViewById(R.id.NewUserRadioButtonFemale);
		userNameView = (EditText) findViewById(R.id.NewUserUserName);
	}

	public void onButtonExitClick(View v) {
		Intent intent = new Intent(NewUserActivity.this, ExitTestActivity.class);
		startActivity(intent);
		finish();
	}

	public void onButtonReturnClick(View v) {
		Intent intent = new Intent(NewUserActivity.this,
				RegistrationActivity.class);
		startActivity(intent);
		finish();
	}

	public void onButtonContinueClick(View v) {
		Intent intent = new Intent(NewUserActivity.this, MainMenuActivity.class);
		if (!userNameView.getText().toString().equals("")
				&& (maleBut.isChecked() || femaleBut.isChecked())) {
			int userId = Utility.database.saveUser(new User(userNameView
					.getText().toString(), maleBut.isChecked()));
			intent.putExtra(Utility.USER_ID_TAG, userId);
			startActivity(intent);
			finish();
		}
	}

	public void onNewUserRadioButtonClick(View v) {
		boolean isMale = (v.getId() == maleBut.getId());
		maleBut.setChecked(isMale);
		femaleBut.setChecked(!isMale);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					RegistrationActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
