package com.congnitive.test.mmpitest.activities;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
	private static final String askedQuestionsTag = "askedQyestionsTag";
	private UUID userId;
	private User user;
	private PsychologyTextedTest test;
	private int askedQuestions = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
			askedQuestions = savedInstanceState
					.getInt(TestQuestionActivity.askedQuestionsTag);
		setContentView(R.layout.activity_test_question);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		user = Utility.getDataBase().getUserById(this, userId);
		test = Utility.getTest(user.getGender(), getResources());
		((TextView) findViewById(R.id.TestQuestionText)).setText(test
				.getQuestion(askedQuestions));
	}

	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		askedQuestions = savedInstanceState
				.getInt(TestQuestionActivity.askedQuestionsTag);
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(TestQuestionActivity.askedQuestionsTag, askedQuestions);
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
			AlertDialog.Builder ad = new AlertDialog.Builder(
					TestQuestionActivity.this);
			ad.setMessage(getText(R.string.again_or_exit_text));
			ad.setPositiveButton(getText(R.string.again),
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int arg1) {
							askedQuestions = 0;
							test = Utility.getTest(user.getGender(),
									getResources());
							((TextView) findViewById(R.id.TestQuestionText))
									.setText(test.getQuestion(askedQuestions));
						}
					});
			ad.setCancelable(false);
			ad.setNegativeButton(getText(R.string.exit_menu),
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int arg1) {
							Intent intent = new Intent(
									TestQuestionActivity.this,
									MainMenuActivity.class);
							intent.putExtra(Utility.USER_ID_TAG,
									userId.toString());
							startActivity(intent);
							finish();
						}
					});
			ad.show();
		} else {
			askedQuestions++;
			if (askedQuestions == test.getLength()) {
				Utility.getDataBase().addTestToUser(this, userId,
						test.getResults(), new Date());
				Intent intent = new Intent(TestQuestionActivity.this,
						ViewResultActivity.class);
				intent.putExtra(Utility.USER_ID_TAG, userId.toString());
				startActivity(intent);
				finish();
			} else {
				((TextView) findViewById(R.id.TestQuestionText)).setText(test
						.getQuestion(askedQuestions));
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					StartTestActivity.class);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
