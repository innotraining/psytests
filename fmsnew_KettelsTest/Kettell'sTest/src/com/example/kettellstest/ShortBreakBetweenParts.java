package com.example.kettellstest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ShortBreakBetweenParts extends Activity {

	String login = "";
	static int part1_score = 0;
	static ShortBreakBetweenParts entity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_short_break_between_parts);
		entity = this;
		Intent intent = ShortBreakBetweenParts.this.getIntent();
		login = intent.getStringExtra("login");
		part1_score = intent.getIntExtra("part1_score", Integer.MIN_VALUE);
		final TextView short_break = (TextView)findViewById(R.id.textView1);
		new CountDownTimer(15000, 1000) {
			public void onFinish() {
				Intent intent = new Intent(ShortBreakBetweenParts.this, Test.class);
				intent.putExtra("login", login);
				intent.putExtra("part1_score", part1_score);
				intent.putExtra("part2_score", 0);
				intent.putExtra("task_number", 5);
				ShortBreakBetweenParts.this.startActivity(intent);
				entity.finish();
			}
			@Override
			public void onTick(long millisUntilFinished) {	
				short_break.setText("short break: " + Integer.toString((int)millisUntilFinished/1000) + " seconds left");
			}
		}.start(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.short_break_between_parts, menu);
		return true;
	}
	
	public void onSkipClick(View view) {
		Intent intent = new Intent(ShortBreakBetweenParts.this, Test.class);
		intent.putExtra("login", login);
		ShortBreakBetweenParts.this.startActivity(intent);
		finish();
    }

	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
