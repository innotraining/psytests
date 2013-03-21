package com.congnitive.test.mmpitest.activities;

import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.User;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ChooseExistingUserActivity extends Activity {
	private Spinner spinner;
	User[] users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_existing_user);
		users = Utility.getDataBase().getAllUsers(this);
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
				android.R.layout.simple_spinner_item, users);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner = (Spinner) findViewById(R.id.user_list);
		spinner.setAdapter(adapter);
		spinner.setPromptId(R.string.available_users);
	}

	public void OnChooseExistingUserButtonReturnClick(View v) {
		Intent intent = new Intent(ChooseExistingUserActivity.this,
				RegistrationActivity.class);
		startActivity(intent);
		finish();
	}

	public void OnChooseExistingUserButtonContinueClick(View v) {
		if (spinner.getSelectedItem() != null) {
			int num = spinner.getSelectedItemPosition();
			Intent intent = new Intent(ChooseExistingUserActivity.this,
					UserActionActivity.class);
			UUID userId = Utility.getDataBase().getIdByUser(this, users[num]);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			startActivity(intent);
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(ChooseExistingUserActivity.this,
					RegistrationActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
