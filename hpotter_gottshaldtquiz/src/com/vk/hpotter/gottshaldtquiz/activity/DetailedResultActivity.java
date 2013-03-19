package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.GottshaldtQuiz;
import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class DetailedResultActivity extends Activity {
	public static int RESULT_DELETED = 1;
	
	private GottshaldtQuiz quiz;
	private QuizUsers users;
	private int resultId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed_result);

		double I = getIntent().getExtras().getDouble("EXTRA_I");
		resultId = getIntent().getExtras().getInt("EXTRA_id"); 
		
		quiz = new GottshaldtQuiz();
		users = new QuizUsers(DetailedResultActivity.this);
		
//		TextView answersCount = (TextView) findViewById(R.id.answersCount);
//		answersCount.setText(String.valueOf(I));
		TextView detailedResultDescription = (TextView) findViewById(R.id.detailedResultDescription);
		detailedResultDescription.setText(quiz.getDescription(I));
	}
	
	public void deleteUserResultButtonClickHandler(View view) {
		SimpleConfirmDialog userDeleteDialog = new SimpleConfirmDialog(DetailedResultActivity.this, R.string.user_result_delete_message, new Runnable() {
			@Override
			public void run() {
				users.deleteUserResult(resultId);
				finish();
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
