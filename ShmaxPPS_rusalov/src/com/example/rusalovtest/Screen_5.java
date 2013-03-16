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
	private Button backButton;
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
		
		/*		
		if(socialCharacter.equals("sanguine")) result += res.getString(R.string.sanguine); 
		if(socialCharacter.equals("choleric")) result += res.getString(R.string.choleric); 
		if(socialCharacter.equals("phlegmatic")) result += res.getString(R.string.phlegmatic); 
		if(socialCharacter.equals("melancholiac")) result += res.getString(R.string.melancholiac); 
		if(socialCharacter.equals("mixedCharacter")) result += res.getString(R.string.mixedCharacter);*/
		
		result += ";" + "\n\n";
		
		
		/*if(scores[0] >= 9) result = result + res.getString(R.string.energyHigh) + ";\n\n";
		if(scores[0] <= 4) result = result + res.getString(R.string.energyLow) + ";\n\n";
		
		if(scores[1] >= 9) result = result + res.getString(R.string.socialEnergyHigh) + ";\n\n";
		if(scores[1] <= 4) result = result + res.getString(R.string.socialEnergyLow) + ";\n\n";
		
		if(scores[2] >= 9) result = result + res.getString(R.string.plasticityHigh) + ";\n\n";
		if(scores[2] <= 4) result = result + res.getString(R.string.plasticityLow) + ";\n\n";
		
		if(scores[3] >= 9) result = result + res.getString(R.string.socialPlasticityHigh) + ";\n\n";
		if(scores[3] <= 4) result = result + res.getString(R.string.socialPlasticityLow) + ";\n\n";
		
		if(scores[4] >= 9) result = result + res.getString(R.string.paceHigh) + ";\n\n";
		if(scores[4] <= 4) result = result + res.getString(R.string.paceLow) + ";\n\n";
		
		if(scores[5] >= 9) result = result + res.getString(R.string.socialPaceHigh) + ";\n\n";
		if(scores[5] <= 4) result = result + res.getString(R.string.socialPaceLow) + ";\n\n";
		
		if(scores[6] >= 9) result = result + res.getString(R.string.emotionalityHigh) + ";\n\n";
		if(scores[6] <= 4) result = result + res.getString(R.string.emotionalityLow) + ";\n\n";
		
		if(scores[7] >= 9) result = result + res.getString(R.string.socialEmotionalityHigh) + ";\n\n";
		if(scores[7] <= 4) result = result + res.getString(R.string.socialEmotionalityLow) + ";\n\n";
		
		if(scores[8] >= 9) result = result + res.getString(R.string.kHigh) + ";\n\n";
		if(scores[8] <= 4) result = result + res.getString(R.string.kLow) + ";\n\n";*/
		
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
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Screen_5.this, Screen_4.class);
				intent.putExtra("userName", userName);
		        startActivity(intent);
		        finish();
			}
		});
	}
}
