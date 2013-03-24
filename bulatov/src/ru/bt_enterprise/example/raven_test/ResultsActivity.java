package ru.bt_enterprise.example.raven_test;

import android.content.Intent;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ResultsActivity extends Activity {
	    @Override
	    protected void onCreate(Bundle savedInstanceState) 
		{
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.results_screen);
	    	
	    	int corr_count = getIntent().getIntExtra("correct", 0);
	    	int incorr_count = getIntent().getIntExtra("incorrect", 0);
	    	
			TextView tv1 = (TextView)findViewById(R.id.textView1);
			TextView tv2 = (TextView)findViewById(R.id.textView2);
			
			tv1.setText("You have " + corr_count + " correct answers");
			tv2.setText("You have " + incorr_count + " incorrect answers");
		}

}
