package com.congnitive.test.mmpitest.activities;

import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ArrayAdapter;

import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultActivity extends ListActivity {
	private ArrayAdapter<Date> adapter;
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userId = getIntent().getIntExtra(Utility.USER_ID_TAG, -1);
		Map<Date, List<List<Pair<String, Integer>>>> tests = Utility.database
				.getAllTestsOfUser(userId);
		Date[] dates = tests.keySet().toArray(new Date[tests.keySet().size()]);
		adapter = new ArrayAdapter<Date>(this,
				android.R.layout.simple_list_item_1, dates);
		setListAdapter(adapter);
	}
}
