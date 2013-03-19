package com.example.szondi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivitySureTest extends Activity implements OnClickListener {
	final String LOG_TAG = "myLogs";
	Button start;
	Button results;
	int userID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suretest);
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		start = (Button)findViewById(R.id.start);
		results = (Button)findViewById(R.id.getResults);
		start.setOnClickListener(this);
		results.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	    case R.id.start:
	    	Intent intent1 = new Intent(this, ActivityTest.class);
	    	Log.d(LOG_TAG, "hHHhf " + userID);
	    	intent1.putExtra("userID", userID);
	        startActivity(intent1);
	        break;
	    case R.id.getResults:
	    	Intent intent2 = new Intent(this, ActivityResults.class);
	    	intent2.putExtra("userID", userID);
	        startActivity(intent2);
	    	break;
	    default:
	    	break;
	    }
	}
}
