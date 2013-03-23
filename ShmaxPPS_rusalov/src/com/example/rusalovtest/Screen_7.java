package com.example.rusalovtest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Screen_7 extends Activity {
	private Button backButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_7);
		Intent i = getIntent();
		int xValue = i.getIntExtra("xValue", 0);
		if(xValue != 0){
			TextView text = (TextView) findViewById(R.id.characterInfo);
			String Info = findInfo(xValue);
			text.setText(Info);
		}
		
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
		        finish();
			}
			
    	});
	}
	public String findInfo(int xValue){
		String Ans = null;
		Resources res = getResources();
		
		if(xValue == 1) {
			Ans = res.getString(R.string.energy) + ": " + "\n" + res.getString(R.string.energyHigh) + 
					"\n" +  res.getString(R.string.energyLow);	
		}
		
		if(xValue == 2) {
			Ans = res.getString(R.string.socialEnergy) + ": " + "\n" + res.getString(R.string.socialEnergyHigh) + 
					"\n" +  res.getString(R.string.socialEnergyLow);	
		}
		
		if(xValue == 3) {
			Ans = res.getString(R.string.plasticity) + ": " + "\n" + res.getString(R.string.plasticityHigh) + 
					"\n" +  res.getString(R.string.plasticityLow);	
		}
		
		if(xValue == 4) {
			Ans = res.getString(R.string.socialPlasticity) + ": " + "\n" + res.getString(R.string.socialPlasticityHigh) + 
					"\n" +  res.getString(R.string.socialPlasticityLow);	
		}
		
		if(xValue == 5) {
			Ans = res.getString(R.string.pace) + ": " + "\n" + res.getString(R.string.paceHigh) + 
					"\n" +  res.getString(R.string.paceLow);	
		}
		
		if(xValue == 6) {
			Ans = res.getString(R.string.socialPace) + ": " + "\n" + res.getString(R.string.socialPaceHigh) + 
					"\n" +  res.getString(R.string.socialPaceLow);	
		}
		
		if(xValue == 7) {
			Ans = res.getString(R.string.emotionality) + ": " + "\n" + res.getString(R.string.emotionalityHigh) + 
					"\n" +  res.getString(R.string.emotionalityLow);	
		}
		
		if(xValue == 8) {
			Ans = res.getString(R.string.socialEmotionality) + ": " + "\n" + res.getString(R.string.socialEmotionalityHigh) + 
					"\n" +  res.getString(R.string.socialEmotionalityLow);	
		}
		
		if(xValue == 9) {
			Ans = res.getString(R.string.k) + ": " + "\n" + res.getString(R.string.kHigh) + 
					"\n" +  res.getString(R.string.kLow);	
		}
		return Ans;
	}
}
