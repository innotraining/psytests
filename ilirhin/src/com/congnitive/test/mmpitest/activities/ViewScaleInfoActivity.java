package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewScaleInfoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_scale_info);
		((TextView) findViewById(R.id.result_of_the_scale)).setText(getIntent()
				.getStringExtra(Utility.SCALE_RESULT_TAG));
	}
}
