package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.GottshaldtQuiz;
import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private static final long NANOSECONDS_IN_MINUTE = 60000000000L;

	private static final SparseIntArray buttons = new SparseIntArray() {
		{
			put(R.id.figuresImage1, 1);
			put(R.id.figuresImage2, 2);
			put(R.id.figuresImage3, 3);
			put(R.id.figuresImage4, 4);
			put(R.id.figuresImage5, 5);
		}
	};

	private QuizUsers users;

	private GottshaldtQuiz quiz = new GottshaldtQuiz();
	private int currentImageId;
	private String titleString;

	private long beginTime = System.nanoTime();
	private int sum = 0;
	private int currentImageNumber = 0;
	private int totalImageNumber = quiz.remaining();

	private int lastTappedButton = 0;

	ImageView quizImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		users = new QuizUsers(QuizActivity.this);

		setContentView(R.layout.activity_quiz);
		normaliseButtons();
		
		titleString = getResources().getString(R.string.quiz_dynamic_title);

		quizImage = (ImageView) findViewById(R.id.quizImage);

		if (!setNextImage()) {
			throw new IllegalArgumentException("No tests!");
		}
		
		Toast.makeText(getApplicationContext(), R.string.quiz_usage_help_toast, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		quizImage.setImageResource(currentImageId);
	}

	private void processAnswer(int buttonNumber) {
		if (quiz.checkNextAnswer(buttonNumber)) {
			++sum;
		}

		if (!setNextImage()) {
			finishQuiz();
		}
	}

	private void normaliseButtons() {
		for (int i = 0; i < buttons.size(); ++i) {
			int buttonId = buttons.keyAt(i);
			ImageButton button = (ImageButton) findViewById(buttonId);
			button.setBackgroundResource(R.color.white);
		}
	}

	private void highlightButton(int buttonId) {
		ImageButton button = (ImageButton) findViewById(buttonId);
		button.setBackgroundResource(R.color.yellow);
	}

	public void buttonClickHandler(View view) {
		int buttonNumber = buttons.get(view.getId());
		if (lastTappedButton == buttonNumber) {
			processAnswer(buttonNumber);
			normaliseButtons();
			lastTappedButton = 0;
		} else {
			normaliseButtons();
			highlightButton(view.getId());
			lastTappedButton = buttonNumber;
		}
	}

	private void finishQuiz() {
		long elapsedTime = (System.nanoTime() - beginTime)
				/ NANOSECONDS_IN_MINUTE;
		double I;
		if (elapsedTime != 0) {
			I = sum / elapsedTime;
		} else {
			I = 666; // TODO: ask a psychologist about too quick users
		}
		users.saveUserResult(I);

		Intent resultsActivity = new Intent(QuizActivity.this,
				DetailedResultActivity.class);
		resultsActivity.putExtra("EXTRA_I", I);
		startActivity(resultsActivity);

		finish();
	}

	@Override
	public void onBackPressed() {
		SimpleConfirmDialog dialog = new SimpleConfirmDialog(QuizActivity.this,
				R.string.exitDialogMessage, new Runnable() {
					@Override
					public void run() {
						QuizActivity.super.onBackPressed();
					}
				}, new Runnable() {
					@Override
					public void run() {
					}
				});
		dialog.show();
	}

	private boolean setNextImage() {
		if (quiz.isEmpty()) {
			return false;
		}

		++currentImageNumber;
		setTitle(String.format(titleString, currentImageNumber,
				totalImageNumber));

		int newImageId = quiz.getNext();
		ImageView testImage = (ImageView) findViewById(R.id.quizImage);
		testImage.setImageResource(newImageId);
		currentImageId = newImageId;
		return true;
	}

}
