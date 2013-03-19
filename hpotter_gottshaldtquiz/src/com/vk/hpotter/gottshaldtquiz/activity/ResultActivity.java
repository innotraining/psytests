package com.vk.hpotter.gottshaldtquiz.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;
import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class ResultActivity extends Activity {

	private double I;
	private QuizUsers users;
	private SimpleDateFormat formatter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		users = new QuizUsers(ResultActivity.this);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		I = getIntent().getExtras().getDouble("EXTRA_I");
		users.saveUserResult(I);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	public void showLastResultButtonClickHandler(View view) {
		Intent resultsActivity = new Intent(ResultActivity.this,
				DetailedResultActivity.class);
		resultsActivity.putExtra("EXTRA_I", I);
		startActivity(resultsActivity);
	}

	public void showPreviousResultButtonClickHandler(View view) {
		// TODO: fix UserActionsActivity:showResultsButtonClickHandler copypaste
		final SortedMap<Date, Double> results = users.getUserResults(users
				.getCurrentUserId());

		final ArrayList<Date> resultsDates = new ArrayList<Date>();
		final ArrayList<String> resultsDateStrings = new ArrayList<String>();
		for (Date key : results.keySet()) {
			resultsDates.add(key);
			resultsDateStrings.add(formatter.format(key));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, resultsDateStrings);

		AlertDialog.Builder builder = new AlertDialog.Builder(
				ResultActivity.this);
		builder.setSingleChoiceItems(adapter, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent resultsActivity = new Intent(
								ResultActivity.this,
								DetailedResultActivity.class);

						Date date = resultsDates.get(which);
						resultsActivity.putExtra("EXTRA_I",
								results.get(date));
						resultsActivity.putExtra("EXTRA_id", users.getUserResultIdByDate(date));
						startActivity(resultsActivity);
						dialog.cancel();
					}
				}).show();
	}

}
