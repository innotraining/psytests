package com.example.kettellstest;

//import java.lang.reflect.Field;

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

public class Part1Subtest1 extends Activity {

	String login = "";
	static int part1_score = 0; 
	private final int buttonsCount = 5;
	static int current_score = 0;
	static int current_question = 1;
	static CountDownTimer timer = null;
	final String resource_prefix = "t_1_s_1_";
	Part1Subtest1 entity = null;
	int[] correct_answers = {2, 3, 4, 3, 3, 1, 4, 3, 5, 3, 3, 5, 3, 2};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part1_subtest1);
		entity = this;
		final Bundle final_savedInstanceState = savedInstanceState;
		
		Intent intent = Part1Subtest1.this.getIntent();
		login = intent.getStringExtra("login");
		if (timer == null) {
			timer = new CountDownTimer(15000, 1000) {
	
				public void onFinish() {
				 	current_question++;
				 	timer = null;
				 	if (current_question > correct_answers.length) {
				 		Intent intent = new Intent(Part1Subtest1.this, Part1Subtest2.class);
				 		intent.putExtra("login", login);
						intent.putExtra("part1_score", part1_score + current_score);
						Part1Subtest1.this.startActivity(intent);
						finish();
				 	}
				 	entity.onCreate(null);
				}
				@Override
				public void onTick(long millisUntilFinished) {				
				}
			};
			timer.start();
		}
		
		TextView questionNumberField = (TextView)findViewById(R.id.textView2);
		questionNumberField.setText("question (" + Integer.toString(current_question) + "/" + Integer.toString(correct_answers.length) + ")");
		
//		init template image
		ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
		String res_image_name = resource_prefix + "q_" + Integer.toString(current_question);
		int image_id = this.getResources().getIdentifier(res_image_name, "drawable", this.getPackageName());
		imageView1.setBackgroundResource(image_id);
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
					current_question++;
				 	if (current_question > correct_answers.length) {
				 		Intent intent = new Intent(Part1Subtest1.this, Part1Subtest2.class);
				 		intent.putExtra("login", login);
						intent.putExtra("part1_score", part1_score + current_score);
						Part1Subtest1.this.startActivity(intent);
						finish();
				 	}
					if (final_i == correct_answers[final_i]) current_score++;
					timer.cancel();
					timer = null;
					entity.onCreate(final_savedInstanceState);
				}
			});
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.part1_subtest2, menu);
		return true;
	}

	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
