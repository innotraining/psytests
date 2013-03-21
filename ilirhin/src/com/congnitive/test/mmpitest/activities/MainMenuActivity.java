package com.congnitive.test.mmpitest.activities;

import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.utilities.Utility;

public class MainMenuActivity extends Activity {
	private UUID userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
	}

	public void onMainMenuViewResultsButtonClick(View v) {
		Intent intent = new Intent(MainMenuActivity.this,
				ViewResultActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId.toString());
		startActivity(intent);
		finish();
	}

	public void onMainMenuStartTestButtonClick(View v) {
		Intent intent = new Intent(MainMenuActivity.this,
				StartTestActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId.toString());
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					UserActionActivity.class);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
