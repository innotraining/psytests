package com.vk.hpotter.gottshaldtquiz.activity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.vk.hpotter.gottshaldtquiz.GottshaldtQuiz;
import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.util.LetterOrDigitInputFilter;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.text.InputFilter;

public class QuizActivity extends Activity {

	private long beginTime;
	private int n;

	private GottshaldtQuiz quiz;
	private int currentImage;

	ImageView quizImage;
	ArrayList<EditText> fields;

	@SuppressWarnings("serial")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_quiz);
		beginTime = System.nanoTime();

		quiz = new GottshaldtQuiz();

		quizImage = (ImageView) findViewById(R.id.testImage);
		fields = new ArrayList<EditText>() {
			{
				EditText field1 = (EditText) findViewById(R.id.field1);
				EditText field2 = (EditText) findViewById(R.id.field2);
				EditText field3 = (EditText) findViewById(R.id.field3);
				field1.setFilters(new InputFilter[]{new LetterOrDigitInputFilter()});
				field2.setFilters(new InputFilter[]{new LetterOrDigitInputFilter()});
				field3.setFilters(new InputFilter[]{new LetterOrDigitInputFilter()});
				add(field1);
				add(field2);
				add(field3);
			}
		};

		if (!setNextImage()) {
			throw new IllegalArgumentException("No tests!");
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.test, menu);
	// return true;
	// }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		quizImage.setImageResource(currentImage);
	}

	public void buttonClickHandler(View view) {
		switch (view.getId()) {
		case R.id.continueButton:
			if (setNextImage()) {
				for (EditText field : fields) {
					if (quiz.checkNextAnswer(field.getText().toString())) {
						++n;
						System.err.println(field.getText().toString());
						field.setText(new String());
					}
				}
			} else {
				Intent resultActivity = new Intent(QuizActivity.this,
						ResultActivity.class);
				double elapsedTime = TimeUnit.MINUTES.convert(
						(System.nanoTime() - beginTime), TimeUnit.NANOSECONDS);
				resultActivity.putExtra("EXTRA_I", (double) n / elapsedTime);
				startActivity(resultActivity);
				finish();
			}
			break;
		case R.id.exitButton:
			openQuitConfirmationDialog();
			break;
		}
	}

	private void openQuitConfirmationDialog() {
		SimpleConfirmDialog dialog = new SimpleConfirmDialog(QuizActivity.this,
				R.string.exitDialogMessage, new Runnable() {
					@Override
					public void run() {
						QuizActivity.this.finish();
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
		} else {
			int newImage = quiz.getNext();
			ImageView testImage = (ImageView) findViewById(R.id.testImage);
			testImage.setImageResource(newImage);
			currentImage = newImage;
			return true;
		}
	}

}
