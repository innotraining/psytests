package com.vk.hpotter.gottshaldtquiz.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.SortedMap;

import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;
import com.vk.hpotter.gottshaldtquiz.util.SimpleListDialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class UserActionsActivity extends Activity {

	private QuizUsers users;
	private SimpleDateFormat formatter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_actions);

		users = new QuizUsers(UserActionsActivity.this);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

		setTitle(users.getUsers().get(
				Integer.valueOf((int) users.getCurrentUserId())));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_actions, menu);
		return true;
	}

	public void showResultsButtonClickHandler(View view) {
		// TODO: fix ResultActivity:showPreviousResultButtonClickHandler
		// copypaste
		final SortedMap<Date, Double> results = users.getUserResults(users
				.getCurrentUserId());

		if (results.size() == 0) {
			Toast.makeText(getApplicationContext(), R.string.no_quiz_results_toast, Toast.LENGTH_SHORT).show();
		} else {
			final ArrayList<Date> resultsDates = new ArrayList<Date>();
			final ArrayList<String> resultsDateStrings = new ArrayList<String>();
			for (Date key : results.keySet()) {
				resultsDates.add(key);
				resultsDateStrings.add(formatter.format(key));
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, resultsDateStrings);

			new SimpleListDialog(UserActionsActivity.this, adapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent resultsActivity = new Intent(
									UserActionsActivity.this,
									DetailedResultActivity.class);

							Date date = resultsDates.get(which);
							resultsActivity.putExtra("EXTRA_I",
									results.get(date));
							resultsActivity.putExtra("EXTRA_id",
									users.getUserResultIdByDate(date));
							resultsActivity.putExtra("EXTRA_dateString",
									resultsDateStrings.get(which));
							startActivity(resultsActivity);
							dialog.dismiss();
						}
					}).show();
		}
	}

	public void startQuizButtonClickHandler(View view) {
		Intent quizActivity = new Intent(UserActionsActivity.this,
				QuizActivity.class);
		startActivity(quizActivity);
	}

	public void deleteUserButtonClickHandler(View view) {

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_logoff:
			users.logOff();

			Intent mainActivity = new Intent(UserActionsActivity.this,
					MainActivity.class);
			startActivity(mainActivity);
			finish();
			return true;
		case R.id.menu_delete_user:
			SimpleConfirmDialog userDeleteDialog = new SimpleConfirmDialog(
					UserActionsActivity.this, R.string.user_delete_message,
					new Runnable() {
						@Override
						public void run() {
							users.deleteUser(users.getCurrentUserId());
							Intent mainActivity = new Intent(
									UserActionsActivity.this,
									MainActivity.class);
							startActivity(mainActivity);
							finish();
						}
					}, new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

						}
					});
			userDeleteDialog.show();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
