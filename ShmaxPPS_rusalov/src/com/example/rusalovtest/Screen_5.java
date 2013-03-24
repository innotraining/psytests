package com.example.rusalovtest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Screen_5 extends Activity {
	private String userName;
	private String character;
	private String result;
	private TextView title;
	private TextView info;
	private Button toMoreInfoButton;
	private int[] scores;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_5);
		Intent i = getIntent();
		userName = i.getStringExtra("userName");
		character = i.getStringExtra("character");
		scores = i.getIntArrayExtra("scores");
		title = (TextView) findViewById(R.id.resultsTitle);
		info = (TextView) findViewById(R.id.resultsInfo);
		Resources res = getResources();
		title.setText(res.getString(R.string.yourTemper));
						
		if(character.equals("sanguine")) result = res.getString(R.string.sanguine); 
		if(character.equals("choleric")) result = res.getString(R.string.choleric); 
		if(character.equals("phlegmatic")) result = res.getString(R.string.phlegmatic); 
		if(character.equals("melancholiac")) result = res.getString(R.string.melancholiac); 
		if(character.equals("mixedCharacter")) result = res.getString(R.string.mixedCharacter);
		
		
		result += ";" + "\n\n";
		
		
		if(character.equals("sanguine")) result += res.getString(R.string.sanguineInfo); 
		if(character.equals("choleric")) result += res.getString(R.string.cholericInfo); 
		if(character.equals("phlegmatic")) result += res.getString(R.string.phlegmaticInfo); 
		if(character.equals("melancholiac")) result += res.getString(R.string.melancholiacInfo); 
		if(character.equals("mixedCharacter")) result += res.getString(R.string.mixedCharacterInfo);		
		
		info.setText(result);  
		
		toMoreInfoButton = (Button) findViewById(R.id.toMoreInfoButton);
		toMoreInfoButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Screen_5.this, Screen_6.class);
				intent.putExtra("userName", userName);
				intent.putExtra("scores", scores);
		        startActivity(intent);
			}
			
    	});
	}
}
