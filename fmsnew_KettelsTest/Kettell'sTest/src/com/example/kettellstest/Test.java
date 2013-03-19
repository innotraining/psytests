package com.example.kettellstest;

//import java.lang.reflect.Field;

import java.util.Timer;

import android.R.drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

	String login = "";
	static Bundle final_savedInstanceState;
	static int part1_score = 0; 
	static int part2_score = 0;
	private final int buttonsCount = 5;
	static int current_score = 0;
	static int current_question = 1;
	static int task_number = 0;
	static CountDownTimer timer = null;
	String resource_prefix = "";
	Test entity = null;
	static Object waiter = new Object();
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
	
	private String getCurrentDescription() {
		return "Description: task " + resource_prefix;
	}
	
	private void nextAction() {
		current_question++;
//		boolean toBeUpdated = true;
		if (current_question > correct_answers[task_number - 1].length) {
	 		current_question = 1;
	 		task_number++;
	 		if (task_number == 5) {
//	 			toBeUpdated = false;
	 			Intent intent = new Intent(Test.this, ShortBreakBetweenParts.class);
		 		intent.putExtra("login", login);
				intent.putExtra("part1_score", part1_score);
				Test.this.startActivity(intent);
				entity.finish();
	 		} 
	 		else 
	 		if (task_number == 9) {
//	 			toBeUpdated = false;
	 			Intent intent = new Intent(Test.this, FinalizeTesting.class);
		 		intent.putExtra("login", login);
				intent.putExtra("part1_score", part1_score);
				intent.putExtra("part2_score", part2_score);
				Test.this.startActivity(intent);
				entity.finish();
	 		}
	 	} 
		timer = null;
//		if (toBeCreated) entity.onCreate(final_savedInstanceState);
//		if (toBeUpdated) entity.update();
	}
	
	private void update() {
		if (task_number == 0) {
			Intent intent = Test.this.getIntent();
			login = intent.getStringExtra("login");
			part1_score = intent.getIntExtra("part1_score", Integer.MIN_VALUE);
			part2_score = intent.getIntExtra("part2_score", Integer.MIN_VALUE);
			task_number = intent.getIntExtra("task_number", 1);
		}
		resource_prefix = "t_" + Integer.toString((int)(task_number-1)/4 + 1) + "_s_" + Integer.toString((task_number - 1)%4 + 1) + "_";
		
		TextView taskDescription = (TextView)findViewById(R.id.textView1);
		taskDescription.setText(getCurrentDescription());
		
		TextView questionNumberField = (TextView)findViewById(R.id.textView2);
		questionNumberField.setText("question (" + Integer.toString(current_question) + "/" + Integer.toString(correct_answers[task_number - 1].length) + ")");
		
		
//		init template image
		ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
		String res_image_name = resource_prefix + "q_" + Integer.toString(current_question);
		int image_id = this.getResources().getIdentifier(res_image_name, "drawable", this.getPackageName());
		imageView1.setImageResource(image_id);
//		init buttons
		for (int i = 1; i <= buttonsCount; i++) {
			String button_name = "button" + Integer.toString(i);
			int button_id = this.getResources().getIdentifier(button_name, "id", this.getPackageName());
			Button button = (Button)findViewById(button_id);
			String resource_name = resource_prefix + "q_" + Integer.toString(current_question) + "_a_" + Integer.toString(i);
			final int answer_id = this.getResources().getIdentifier(resource_name, "drawable", this.getPackageName());
			button.setBackgroundResource(answer_id);
			final int answer_id_chosen = this.getResources().getIdentifier(resource_name + "_chosen", "drawable", this.getPackageName());
			button.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					int action = arg1.getAction();
					if (action == MotionEvent.AXIS_PRESSURE) {
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
					if (timer != null) timer.cancel();
				 	entity.nextAction();
				}
			});
		}
		if (timer == null) {
			timer = new CountDownTimer(15000, 1000) {
				public void onFinish() {
				 	entity.nextAction();
				 	synchronized (waiter) { waiter.notify(); }
				}
				@Override
				public void onTick(long millisUntilFinished) {		
					TextView questionNumberField = (TextView)findViewById(R.id.textView2);
					questionNumberField.setText("question (" + Integer.toString(current_question) + "/" + Integer.toString(correct_answers[task_number - 1].length) + ")" + Integer.toString((int)millisUntilFinished/1000));
				}
			};
			timer.start();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		final_savedInstanceState = savedInstanceState;
		entity = this;
		while (true) {
			update();
			synchronized (waiter) {	
				try {
					while (timer != null) { waiter.wait(); }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	
			}
		}
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

	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
