package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.GottshaldtQuiz;
import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailedResultActivity extends Activity {
	public static int RESULT_DELETED = 1;
	
	private GottshaldtQuiz quiz;
	private QuizUsers users;
	private int resultId;
	private String dateString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_result);

		double I = getIntent().getExtras().getDouble("EXTRA_I");
		resultId = getIntent().getExtras().getInt("EXTRA_id");
		dateString = getIntent().getExtras().getString("EXTRA_dateString"); 
		if(dateString == null) {
			dateString = getResources().getString(R.string.quiz_last_test_title);
		}
		
		setTitle(dateString);
		
		quiz = new GottshaldtQuiz();
		users = new QuizUsers(DetailedResultActivity.this);
		
		TextView detailedResultDescription = (TextView) findViewById(R.id.detailedResultDescription);
		detailedResultDescription.setText(quiz.getDescription(I));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detailed_result, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_delete_quiz_result:
			SimpleConfirmDialog userDeleteDialog = new SimpleConfirmDialog(DetailedResultActivity.this, R.string.user_result_delete_message, new Runnable() {
				@Override
				public void run() {
					users.deleteUserResult(resultId);
					finish();
				}
			}, new Runnable() {
				@Override
				public void run() {
					// Do nothing
				}
			});
			userDeleteDialog.show();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
