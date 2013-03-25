package com.example.kettellstest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class FinalizeTesting extends Activity {

	static String login = "";
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
//		results.setText(login + ", your result: \n part I: (" + Integer.toString(part1_score) + "/46) \n partII: (" + Integer.toString(part2_score) + "/46) \n total: (" + Integer.toString(part1_score + part2_score) + "/92)");
		DatabaseHandler db = new DatabaseHandler(this);
		Date userBirthDate = null;
		try {
			userBirthDate = db.getBirthDate(login);
			if (userBirthDate == null) userBirthDate = new Date();
			Date currentDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			long ageInDays = (currentDate.getTime() - userBirthDate.getTime())/(1000 * 60 * 60 * 24);
			double ageInYears = ((double)ageInDays)/365.24;
			int iq = IQTables.getIQ(ageInYears, part1_score + part2_score);
			results.setText(login + ", your result\nbirthdate " + dateFormat.format(userBirthDate).toString() + "\ncurrentdate " + dateFormat.format(currentDate).toString() + "\nage(years * 10):" + Integer.toString((int)(ageInYears*10)) + "\n part I: (" + Integer.toString(part1_score) + "/46) \n partII: (" + Integer.toString(part2_score) + "/46) \n total: (" + Integer.toString(part1_score + part2_score) + "/92)" + "\nIQ: " + iq);
			db.addNode(login, new Attempt(iq, currentDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
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
