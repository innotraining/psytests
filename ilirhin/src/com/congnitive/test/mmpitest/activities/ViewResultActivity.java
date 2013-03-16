package com.congnitive.test.mmpitest.activities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;

import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultActivity extends ListActivity {
	private ArrayAdapter<Date> adapter;
	private UUID userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		Map<Date, List<QuizResult>> tests = Utility.getDataBase()
				.getAllTestsOfUser(this, userId);
		Date[] dates = new Date[0];
		if (tests != null)
			dates = tests.keySet().toArray(new Date[tests.keySet().size()]);
		adapter = new ArrayAdapter<Date>(this,
				android.R.layout.simple_list_item_1, dates);
		setListAdapter(adapter);
	}

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
