package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.congnitive.test.mmpitest.R;

public class AgainOrToMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_again_or_start);
		((TextView) findViewById(R.id.AgainOrdStartText)).setText(getIntent()
				.getStringExtra(StartTestActivity.EXIT_MESSAGE_TAG));
	}

	public void onAgainOrStartClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.AgainOrStartAgainButton:
			intent.putExtra(StartTestActivity.AGAIN_OR_EXIT_RESULT_TAG, true);
			break;
		case R.id.AgainOrStartExitMenuButton:
			intent.putExtra(StartTestActivity.AGAIN_OR_EXIT_RESULT_TAG, false);
			break;
		default:
			break;
		}
		setResult(RESULT_OK, intent);
		finish();
	}
}
