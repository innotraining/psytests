package com.congnitive.test.mmpitest.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.QuizResult.QuizEnty;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultActivity extends ListActivity {
	private ArrayAdapter<Date> adapter;
	private UUID userId;
	Map<Date, List<QuizResult>> tests;
	Date[] dates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		tests = Utility.getDataBase().getAllTestsOfUser(this, userId);
		dates = new Date[0];
		if (tests != null)
			dates = tests.keySet().toArray(new Date[tests.keySet().size()]);
		adapter = new ArrayAdapter<Date>(this,
				android.R.layout.simple_list_item_1, dates);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		ArrayList<QuizEnty> quizRes = tests.get(dates[position]).get(0)
				.getResults();
		String str = "";
		for (int i = 0; i < quizRes.size(); i++) {
			str += quizRes.get(i).getSkillName() + "\n\n"
					+ quizRes.get(i).getDescribtion() + "\n\n\n\n\n";
		}
		Intent intent = new Intent(ViewResultActivity.this,
				ViewResultOfTheQuiz.class);
		intent.putExtra(Utility.USER_ID_TAG, userId.toString());
		intent.putExtra(Utility.QUIZ_RESULT_TAG, str);
		startActivity(intent);
		finish();
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
