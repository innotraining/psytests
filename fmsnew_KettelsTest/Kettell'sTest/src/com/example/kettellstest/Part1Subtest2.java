package com.example.kettellstest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Part1Subtest2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part1_subtest2);
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
