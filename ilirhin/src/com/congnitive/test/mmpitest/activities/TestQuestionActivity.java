package com.congnitive.test.mmpitest.activities;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.PsychologyTextedTest;
import com.congnitive.test.mmpitest.domainObjects.User;
import com.congnitive.test.mmpitest.utilities.Utility;

public class TestQuestionActivity extends Activity {
	static final private int ASK_FOR_REPEAT = 0;
	private int userId;
	private User user;
	private PsychologyTextedTest test;
	private int askedQuestions = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_question);
		userId = getIntent().getIntExtra(Utility.USER_ID_TAG, -1);
		user = Utility.database.getUserById(userId);
		test = Utility.getTest(user.getGender(), getResources());
		((TextView) findViewById(R.id.TestQuestionText)).setText(test
				.getQuestion(askedQuestions));
	}

	public void onTestQuestionButtonClick(View v) {
		int result = 0;
		switch (v.getId()) {
		case R.id.TestQuestionYesButton:
			result = 2;
			break;
		case R.id.TestQuestionDontKnowButton:
			result = 1;
			break;
		case R.id.TestQuestionNoButton:
			result = 0;
			break;
		default:
			break;
		}
		boolean needStop = test.setAnswer(askedQuestions, result);
		if (needStop) {
			Intent intent = new Intent(TestQuestionActivity.this,
					AgainOrToMenuActivity.class);
			intent.putExtra(StartTestActivity.EXIT_MESSAGE_TAG,
					getText(R.string.liar_text));
			startActivityForResult(intent, ASK_FOR_REPEAT);
		} else {
			askedQuestions++;
			if (askedQuestions == test.getLength()) {
				Utility.database.addTestToUser(userId, test.getResults(),
						new Date());
				Intent intent = new Intent(TestQuestionActivity.this,
						ViewResultActivity.class);
				intent.putExtra(Utility.USER_ID_TAG, userId);
				startActivity(intent);
				finish();
			} else {
				((TextView) findViewById(R.id.TestQuestionText)).setText(test
						.getQuestion(askedQuestions));
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ASK_FOR_REPEAT) {
			if (resultCode == RESULT_OK) {
				if (data.getBooleanExtra(
						StartTestActivity.AGAIN_OR_EXIT_RESULT_TAG, false)) {
					askedQuestions = 0;
					test = Utility.getTest(user.getGender(), getResources());
					((TextView) findViewById(R.id.TestQuestionText))
							.setText(test.getQuestion(0));
				} else {
					Intent intent = new Intent(TestQuestionActivity.this,
							MainMenuActivity.class);
					intent.putExtra(Utility.USER_ID_TAG, userId);
					startActivity(intent);
					finish();
				}
			} else {
				Intent intent = new Intent(TestQuestionActivity.this,
						AgainOrToMenuActivity.class);
				intent.putExtra(StartTestActivity.EXIT_MESSAGE_TAG,
						getText(R.string.liar_text));
				startActivityForResult(intent, ASK_FOR_REPEAT);
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					StartTestActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
