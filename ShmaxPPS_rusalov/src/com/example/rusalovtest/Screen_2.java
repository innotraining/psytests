package com.example.rusalovtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Screen_2 extends Activity {
	private Button startButton;
	private String userName;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_2);
		userName = getIntent().getStringExtra("userName");
		addListenerOnButton();
    }

	public void addListenerOnButton() {
		startButton = (Button) findViewById(R.id.start);
		
		startButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) 
				{
				    case R.id.start:
				        Intent intent = new Intent(Screen_2.this, Screen_3.class);
				        intent.putExtra("userName", userName);
				        startActivity(intent);
				        break;
				    default:
				        break;
			    }
			}
			
    	});
		
	}
}
