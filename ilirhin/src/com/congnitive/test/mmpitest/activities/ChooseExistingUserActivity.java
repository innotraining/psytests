package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.User;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ChooseExistingUserActivity extends Activity {
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_existing_user);
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
				android.R.layout.simple_spinner_item,
				Utility.database.getAllUsers());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner = (Spinner) findViewById(R.id.user_list);
		spinner.setAdapter(adapter);
		spinner.setPromptId(R.string.available_users);
	}

	public void OnChooseExistingUserButtonExitClick(View v) {
		Intent intent = new Intent(ChooseExistingUserActivity.this,
				ExitTestActivity.class);
		startActivity(intent);
		finish();
	}

	public void OnChooseExistingUserButtonReturnClick(View v) {
		Intent intent = new Intent(ChooseExistingUserActivity.this,
				RegistrationActivity.class);
		startActivity(intent);
		finish();
	}

	public void OnChooseExistingUserButtonContinueClick(View v) {
		if (spinner.getSelectedItem() != null) {
			Intent intent = new Intent(ChooseExistingUserActivity.this,
					UserActionActivity.class);
			int userId = Utility.database.getIdByName(spinner.getSelectedItem()
					.toString());
			intent.putExtra(Utility.USER_ID_TAG, userId);
			startActivity(intent);
			finish();
		}
	}
}
