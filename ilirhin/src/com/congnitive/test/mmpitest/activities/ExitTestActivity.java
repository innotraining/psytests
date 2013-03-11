package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.congnitive.test.mmpitest.R;

public class ExitTestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit_test);
	}

	public void onClick(View v) {
		finish();
	}
}
