package com.mistdale.test2;

import java.util.ArrayList;
//import java.util.logging.Logger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

public class TestingActivity extends Activity {
	
	private int current_question = 0;
	public final static int NUMBER_OF_QUESTIONS = 75; // Do not change without updating question list
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	private String[] questions;
	private TextView q;
	private Resources res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testing);
		// Show the Up button in the action bar.
		setupActionBar();
		
		res = getResources();
		questions = res.getStringArray(R.array.questions_array);
		
		q = (TextView) findViewById(R.id.textView1);
		if (MainActivity.individual_app && MainActivity.numerate_questions) {
			q.setText(Integer.valueOf(current_question+1).toString() + ". " + questions[current_question]);
		} else {
			q.setText(questions[current_question]);
		}
		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.testing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void homeScreen(View view) {
		finish();
	}
	
	public void restartTest(View view) {
		finish();
		Intent intent = new Intent(this, TestingActivity.class);
		startActivity(intent);
	}
	
	public void yesAction(View view) {
		answers.add(1);
		
		
		if (MainActivity.DEBUG_QUESTIONS) {
			for (int i = 1; i < NUMBER_OF_QUESTIONS; ++i) {
				answers.add(1);
			}
			current_question = NUMBER_OF_QUESTIONS-1;
		} // DEBUG
		
		action();
	}
	
	public void noAction(View view) {
		answers.add(0);
		action();
	}
	
	private void action() {
		try {
		++current_question;
		if (current_question == NUMBER_OF_QUESTIONS) {
			if (MainActivity.individual_app) {
				
				//Log.d("TestingActivity", "h");
				//Toast.makeText(getApplicationContext(), "Testing activity", Toast.LENGTH_SHORT).show();
				
				finish();
				Intent intent = new Intent(this, ResultsActivity.class);
				// transmit results to other activity
				intent.putIntegerArrayListExtra(MainActivity.EXTRA_ANSWERS, answers);
				intent.putExtra("caller", "test");
				startActivity(intent);
			} else {
				q.setText(res.getString(R.string.test_finished));
				
				Button yes, no;
				yes = (Button) findViewById(R.id.button_yes);
				no = (Button) findViewById(R.id.button_no);
				yes.setVisibility(View.INVISIBLE);
				no.setVisibility(View.INVISIBLE);
			}
		} else {
			if (MainActivity.individual_app && MainActivity.numerate_questions) {
				q.setText(Integer.valueOf(current_question+1).toString() + ". " + questions[current_question]);
			} else {
				q.setText(questions[current_question]);
			}
		}
		} catch(Exception e) {}
	}
}
