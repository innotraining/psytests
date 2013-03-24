package com.example.rusalovtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Screen_1 extends Activity {
	private Button toTestButton;
	private Button toResultsButton;
	private String userName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_1);
		userName = getIntent().getStringExtra("userName");
		toTestButton = (Button) findViewById(R.id.startTestButton);
		toResultsButton = (Button) findViewById(R.id.watchResultsButton);
		
		toTestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Screen_1.this, Screen_2.class);
				intent.putExtra("userName", userName);
				startActivity(intent);
				finish();
			}
		});
		
		toResultsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Screen_1.this, Screen_4.class);
				intent.putExtra("userName", userName);
				startActivity(intent);
				finish();
			}
		});
	}
}
