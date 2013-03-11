package com.congnitive.test.mmpitest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.utilities.Utility;

public class StartTestActivity extends Activity {
	static final public String AGAIN_OR_EXIT_RESULT_TAG = "StartTestActivity.againOrExitResult";
	static final public String QUESTION_FOR_TEST_TAG = "StartTestActivity.questionForTest";
	static final public String ANSWER_FOR_TEST_TAG = "StartTestActivity.answerForTest";
	static final public String EXIT_MESSAGE_TAG = "StartTestActivity.exitMessage";
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_test);
		userId = getIntent().getIntExtra(Utility.USER_ID_TAG, -1);
	}

	public void onStartTestExitTestButtonClick(View v) {
		Intent intent = new Intent(StartTestActivity.this,
				ExitTestActivity.class);
		startActivity(intent);
		finish();
	}

	public void onStartTestExitMenuButtonClick(View v) {
		Intent intent = new Intent(StartTestActivity.this,
				MainMenuActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId);
		startActivity(intent);
		finish();
	}

	public void onStartTestStartTestButtonClick(View v) {
		Intent intent = new Intent(StartTestActivity.this,
				TestQuestionActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId);
		startActivity(intent);
		finish();
	}
}
