package com.congnitive.test.mmpitest.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultActivity extends ListActivity {
	private ArrayAdapter<String> adapter;
	private UUID userId;
	Map<Date, List<QuizResult>> tests;
	List<QuizResult> quizResults = new ArrayList<QuizResult>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		tests = Utility.getDataBase().getAllTestsOfUser(this, userId);
		String[] adapterTexts;
		if (tests != null) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
					Locale.US);
			List<String> quizDescs = new ArrayList<String>();
			for (Date date : tests.keySet()) {
				int num = 1;
				for (QuizResult it : tests.get(date)) {
					quizResults.add(it);
					quizDescs.add(dateFormatter.format(date) + " "
							+ getText(R.string.quiz_num) + " " + num++);
				}
			}
			adapterTexts = quizDescs.toArray(new String[quizDescs.size()]);
		} else {
			adapterTexts = new String[1];
			adapterTexts[0] = getText(R.string.there_is_no_quizes).toString();
		}
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, adapterTexts);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (position != 0 || !quizResults.isEmpty()) {
			Intent intent = new Intent(ViewResultActivity.this,
					ViewResultOfTheQuiz.class);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			intent.putExtra(Utility.QUIZ_RESULT_TAG, quizResults.get(position));
			startActivity(intent);
			finish();
		}
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
