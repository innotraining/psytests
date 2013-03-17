package com.example.kettellstest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FinalizeTesting extends Activity {

	String login = "";
	static int part1_score = 0;
	static int part2_score = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finalize_testing);
		
		Intent intent = FinalizeTesting.this.getIntent();
		login = intent.getStringExtra("login");
		part1_score = intent.getIntExtra("part1_score", Integer.MIN_VALUE);
		part2_score = intent.getIntExtra("part2_score", Integer.MIN_VALUE);
		
		final TextView results = (TextView)findViewById(R.id.textView1);
//		TODO calc IQ and add score to the results db
		results.setText("your result: \n part I: (" + Integer.toString(part1_score) + "/46) \n partII: (" + Integer.toString(part2_score) + "/46) \n total: (" + Integer.toString(part1_score + part2_score) + "/96)");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finalize_testing, menu);
		return true;
	}
	
	
	public void onMainMenuClick(View view) {
		Intent intent = new Intent(FinalizeTesting.this, MainMenu.class);
		intent.putExtra("login", login);
		FinalizeTesting.this.startActivity(intent);
		finish();
    }

	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
	
}
