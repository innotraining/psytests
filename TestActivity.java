package com.example.quizbook;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TestActivity extends Activity {
  
	private String login = "";
	private String testName = "";
	private IEngine iEngine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		Intent intent = getIntent(); 
		login  = intent.getStringExtra("login").toString();
		testName  = intent.getStringExtra("testName").toString();
		iEngine = QuizFactory.getEngineInstance(testName);	
		iEngine.refresh();
		
		if (iEngine.getAnswerType().equals("YN")) {
			createYNLayout();
		}
		if (iEngine.getAnswerType().equals("1M")) {
			create1MLayout();
		}
		if (iEngine.getAnswerType().equals("MM")) {
			createMMLayout();	
		}
	
	}

	// build textview for question and "Yes" + "No" buttons
	private void createYNLayout() {
		//TODO make more layout params according to design
		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		mainLayout.setBackgroundResource(R.drawable.background);
		final TextView questionDescription = new TextView(getApplicationContext());
		questionDescription.setText(iEngine.getQuestionDescription(iEngine.getCurrentQuestionNumber()));
		questionDescription.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		questionDescription.setBackgroundResource(R.drawable.background);
		questionDescription.setMovementMethod(new ScrollingMovementMethod());
		questionDescription.setTextColor(Color.BLACK);
		mainLayout.addView(questionDescription);
		
		LinearLayout innerLayout = new LinearLayout(getApplicationContext());
		innerLayout.setOrientation(LinearLayout.HORIZONTAL);
		innerLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2f));
		innerLayout.setBackgroundResource(R.drawable.background);
		mainLayout.addView(innerLayout);
		final ImageButton yesButton = new ImageButton(getApplicationContext());
		yesButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		yesButton.setImageResource(R.drawable.yes_button_selector);
		yesButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
		yesButton.setBackgroundResource(R.drawable.background);
		final ImageButton noButton = new ImageButton(getApplicationContext());
		noButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
		noButton.setImageResource(R.drawable.no_button_selector);
		noButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
		noButton.setBackgroundResource(R.drawable.background);
		innerLayout.addView(yesButton);
		//TODO set onTouch listener
		yesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				iEngine.setAnswersOfUser(iEngine.getCurrentQuestionNumber(), new ArrayList<Integer>() {{ add(1); }});
				if (!iEngine.nextQuestion()) {
					noButton.setClickable(false);
					noButton.setImageDrawable(null);					
					yesButton.setClickable(false);
					yesButton.setImageDrawable(null);
					//TODO make conclusion
					//just an example:
					questionDescription.setText(((EysenckConclusion)iEngine.getConclusion()).resultExample);
				}
				else {
					questionDescription.setText(iEngine.getQuestionDescription(iEngine.getCurrentQuestionNumber()));
				}
			}
		});
		innerLayout.addView(noButton);
		//TODO set onTouch listener
		noButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				iEngine.setAnswersOfUser(iEngine.getCurrentQuestionNumber(), new ArrayList<Integer>() {{ add(0); }});
				if (!iEngine.nextQuestion()) {
					noButton.setClickable(false);
					noButton.setImageDrawable(null);					
					yesButton.setClickable(false);
					yesButton.setImageDrawable(null);
					//TODO make conclusion
					//just an example:
					questionDescription.setText(((EysenckConclusion)iEngine.getConclusion()).resultExample);
				}
				else {
					questionDescription.setText(iEngine.getQuestionDescription(iEngine.getCurrentQuestionNumber()));
				}
			}
		});
	}
	
	// build textview for question, listview for answers and the "Next" button
	private void create1MLayout() {
		//TODO make layout
	}

	// build textview for question, listview for answers and the "Next" button
	private void createMMLayout() {
		//TODO make layout
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

}
