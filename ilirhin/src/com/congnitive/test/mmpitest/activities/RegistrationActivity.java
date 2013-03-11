package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.congnitive.test.mmpitest.R;

public class RegistrationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
	}

	public void onExitTestButtonClick(View v) {
		Intent intent = new Intent(RegistrationActivity.this,
				ExitTestActivity.class);
		startActivity(intent);
		finish();
	}

	public void onNewUserButtonClick(View v) {
		Intent intent = new Intent(RegistrationActivity.this,
				NewUserActivity.class);
		startActivity(intent);
		finish();
	}

	public void onExistingUserButtonClick(View v) {
		Intent intent = new Intent(RegistrationActivity.this,
				ChooseExistingUserActivity.class);
		startActivity(intent);
		finish();
	}

}
