package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.utilities.Utility;

public class MainMenuActivity extends Activity {
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		userId = getIntent().getIntExtra(Utility.USER_ID_TAG, -1);
	}

	public void onMainMenuExitButtonClick(View v) {
		Intent intent = new Intent(MainMenuActivity.this,
				ExitTestActivity.class);
		startActivity(intent);
		finish();
	}

	public void onMainMenuViewResultsButtonClick(View v) {
		Intent intent = new Intent(MainMenuActivity.this,
				ViewResultActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId);
		startActivity(intent);
		finish();
	}

	public void onMainMenuStartTestButtonClick(View v) {
		Intent intent = new Intent(MainMenuActivity.this,
				StartTestActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId);
		startActivity(intent);
		finish();
	}
}
