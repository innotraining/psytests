package com.congnitive.test.mmpitest.activities;

import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultOfTheQuiz extends Activity {
	private UUID userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_result_of_the_text);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		((TextView) findViewById(R.id.result_of_the_quiz)).setText(getIntent()
				.getStringExtra(Utility.QUIZ_RESULT_TAG));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					MainMenuActivity.class);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
