package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

public class ResultVisualisationActivity extends Activity {
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
