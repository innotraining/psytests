package com.example.kettellstest;

//import java.lang.reflect.Field;

import java.util.Timer;

import android.R.drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity {

	static String login = "";
	static public long setTime = 15*1000;
	static long tickTime = 100;
	static Bundle final_savedInstanceState;
	static public int part1_score = 0; 
	static public int part2_score = 0;
	private final int buttonsCount = 5;
	static public int current_question = 1;
	static public int task_number = 1;
	static public long millisUntilFinishedToSave = setTime;
	static CountDownTimer timer = null;
	String resource_prefix = "";
	Test entity = null;
	int[][] correct_answers = {
			{4, 2, 1, 5, 3, 5, 2, 3, 2, 1, 4, 3},
			{2, 3, 4, 3, 3, 1, 4, 3, 5, 3, 3, 5, 3, 2},
			{3, 2, 1, 5, 4, 1, 3, 2, 5, 1, 2, 2},
			{2, 4, 2, 3, 1, 1, 3, 2},
			{3, 4, 4, 3, 2, 3, 3, 5, 4, 1, 3, 1},
			{2, 3, 4, 3, 3, 1, 4, 3, 1, 1, 3, 1, 3, 4},
			{3, 2, 1, 5, 4, 1, 3, 2, 5, 1, 2, 2},
			{3, 4, 2, 3, 1, 1, 2, 2}
	};
	
	String [] taskDescription = {
			"Choose the most proper picture below to the next group of pictures.",
			"Choose one extra picture.",
			"Choose the missing part of the next picture from pictures below.",
			"Find out a relative location of the point at the next picture and choose the picture below, on which it is possible to put a point with the similar relative location.",
			"Choose the most proper picture below to the next group of pictures.",
			"Choose one extra picture.",
			"Choose the missing part of the next picture from pictures below.",
			"Find out a relative location of the point at the next picture and choose the picture below, on which it is possible to put a point with the similar relative location."
	};
	
	private String getCurrentDescription() {
		return taskDescription[task_number - 1];
	}
	
	private void nextAction() {
		millisUntilFinishedToSave = setTime;
		current_question++;
		boolean toBeUpdated = true;
		if (current_question > correct_answers[task_number - 1].length) {
	 		current_question = 1;
	 		task_number++;
	 		if (task_number == 5) {
	 			toBeUpdated = false;
	 			Intent intent = new Intent(Test.this, ShortBreakBetweenParts.class);
		 		intent.putExtra("login", login);
				intent.putExtra("part1_score", part1_score);
				Test.this.startActivity(intent);
				entity.finish();
	 		} 
	 		else 
	 		if (task_number == 9) {
	 			toBeUpdated = false;
	 			Intent intent = new Intent(Test.this, FinalizeTesting.class);
		 		intent.putExtra("login", login);
				intent.putExtra("part1_score", part1_score);
				intent.putExtra("part2_score", part2_score);
				Test.this.startActivity(intent);
				entity.finish();
	 		}
	 	} 
		if (toBeUpdated) {
			entity.update();
		}
		else {
			if (timer != null) timer.cancel();
			timer = null;
		}
	}
	
	@SuppressLint("NewApi")
	private void update() {
		if (timer != null) timer.cancel();
		timer = null;
		if (task_number >= 9) {
			timer = null;
			task_number = 1;
			part1_score = 0;
			part2_score = 0;
			current_question = 1;
			millisUntilFinishedToSave = setTime;
		}
		if (task_number == 1 || task_number == 5) {
			Intent intent = Test.this.getIntent();
			login = intent.getStringExtra("login");
			part1_score = intent.getIntExtra("part1_score", Integer.MIN_VALUE);
			part2_score = intent.getIntExtra("part2_score", Integer.MIN_VALUE);
			//task_number = intent.getIntExtra("task_number", 1);
		}
		resource_prefix = "t_" + Integer.toString((int)(task_number-1)/4 + 1) + "_s_" + Integer.toString((task_number - 1)%4 + 1) + "_";
		
		TextView taskDescription = (TextView)findViewById(R.id.textView1);
		taskDescription.setText(getCurrentDescription());
		
		TextView questionNumberField = (TextView)findViewById(R.id.textView2);
		questionNumberField.setText("question " + Integer.toString(current_question) + " of " + Integer.toString(correct_answers[task_number - 1].length));
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int dWidth = size.x;
		int dHeight = size.y;
//		init template image
		ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
//		imageView1.getLayoutParams().width = (int) (dWidth*0.98);
		imageView1.getLayoutParams().height = (int) (dHeight*0.2);
		String res_image_name = resource_prefix + "q_" + Integer.toString(current_question);
		int image_id = this.getResources().getIdentifier(res_image_name, "drawable", this.getPackageName());
		imageView1.setImageResource(image_id);
//		init buttons
		for (int i = 1; i <= buttonsCount; i++) {
			String button_name = "button" + Integer.toString(i);
			int button_id = this.getResources().getIdentifier(button_name, "id", this.getPackageName());
			Button button = (Button)findViewById(button_id);
			button.getLayoutParams().width = (int) (min(dHeight, dWidth)/(buttonsCount+1));
			button.getLayoutParams().height = (int) (min(dHeight, dWidth)/(buttonsCount+1));
			String resource_name = resource_prefix + "q_" + Integer.toString(current_question) + "_a_" + Integer.toString(i);
			final int answer_id = this.getResources().getIdentifier(resource_name, "drawable", this.getPackageName());
			button.setBackgroundResource(answer_id);
			final int answer_id_chosen = this.getResources().getIdentifier(resource_name + "_chosen", "drawable", this.getPackageName());
			button.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					int action = arg1.getAction();
					//if (action == MotionEvent.AXIS_PRESSURE) {
					if (action == MotionEvent.ACTION_DOWN) {
						arg0.setBackgroundResource(answer_id_chosen);
					}
					if (action == MotionEvent.ACTION_UP) {
						arg0.setBackgroundResource(answer_id);
					}
					return false;
				}
			
			});
			final int final_i = i;
			button.setOnClickListener(new View.OnClickListener(){
				public void onClick(View arg0) {
				 	if (final_i == correct_answers[task_number - 1][final_i]) {
				 		if (task_number < 5) part1_score++;
				 		else part2_score++;
				 	}
				 	entity.nextAction();
				}
			});
		}
	
		timer = new CountDownTimer(millisUntilFinishedToSave, tickTime) {
			public void onFinish() {
			 	entity.nextAction();
			}
			@Override
			public void onTick(long millisUntilFinished) {		
				millisUntilFinishedToSave = millisUntilFinished;
				//TextView questionNumberField = (TextView)findViewById(R.id.textView2);
				//questionNumberField.setText("question (" + Integer.toString(current_question) + "/" + Integer.toString(correct_answers[task_number - 1].length) + ")" + Integer.toString((int)millisUntilFinished/1000));
			}
		};
		timer.start();
		
	}
	
	private int min(int x, int y) {
		// TODO Auto-generated method stub
		return x < y ? x : y;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		final_savedInstanceState = savedInstanceState;
		entity = this;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		update();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    // Read values from the "savedInstanceState"-object and put them in your textview
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    // Save the values you need from your textview into "outState"-object
		    super.onSaveInstanceState(outState);
	}

	public void onMainMenuClick(View view) {
		Intent intent = new Intent(Test.this, MainMenu.class);
		intent.putExtra("login", login);
		Test.this.startActivity(intent);
		part1_score = 0; 
		part2_score = 0;
		current_question = 1;
		millisUntilFinishedToSave = setTime;
		if (timer != null) timer.cancel();
		timer = null;
		task_number = 1;
		finish();
    }
	
}
