package com.vk.hpotter.gottshaldtquiz.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;

import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class UserActionsActivity extends Activity {

	private QuizUsers users;
	private SimpleDateFormat formatter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_actions);
		
		users = new QuizUsers(UserActionsActivity.this);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_actions, menu);
		return true;
	}

	public void showResultsButtonClickHandler(View view) {
		//TODO: fix ResultActivity:showPreviousResultButtonClickHandler copypaste
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
				UserActionsActivity.this);
		builder.setSingleChoiceItems(adapter, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent resultsActivity = new Intent(
								UserActionsActivity.this,
								DetailedResultActivity.class);

						Date date = resultsDates.get(which);
						resultsActivity.putExtra("EXTRA_I",
								results.get(date));
						resultsActivity.putExtra("EXTRA_id", users.getUserResultIdByDate(date));
						startActivity(resultsActivity);
					}
				}).show();
	}

	public void startQuizButtonClickHandler(View view) {
		Intent quizActivity = new Intent(UserActionsActivity.this, QuizActivity.class);
		startActivity(quizActivity);
		finish();
	}

	public void deleteUserButtonClickHandler(View view) {
		SimpleConfirmDialog userDeleteDialog = new SimpleConfirmDialog(UserActionsActivity.this, R.string.user_delete_message, new Runnable() {
			@Override
			public void run() {
				users.deleteUser(users.getCurrentUserId());
				Intent mainActivity = new Intent(UserActionsActivity.this, MainActivity.class);
				startActivity(mainActivity);
			}
		}, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		userDeleteDialog.show();
	}

}
