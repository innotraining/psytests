package com.example.rusalovtest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;


public class Screen_7 extends Activity {
	
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
	}
	public String findInfo(int xValue){
		String Ans = null;
		Resources res = getResources();
		
		if(xValue == 1) {
			Ans = res.getString(R.string.energy) + ": " + "\n\n" + res.getString(R.string.energyHigh) + 
					"\n\n" +  res.getString(R.string.energyLow);	
		}
		
		if(xValue == 2) {
			Ans = res.getString(R.string.socialEnergy) + ": " + "\n\n" + res.getString(R.string.socialEnergyHigh) + 
					"\n\n" +  res.getString(R.string.socialEnergyLow);	
		}
		
		if(xValue == 3) {
			Ans = res.getString(R.string.plasticity) + ": " + "\n\n" + res.getString(R.string.plasticityHigh) + 
					"\n\n" +  res.getString(R.string.plasticityLow);	
		}
		
		if(xValue == 4) {
			Ans = res.getString(R.string.socialPlasticity) + ": " + "\n\n" + res.getString(R.string.socialPlasticityHigh) + 
					"\n\n" +  res.getString(R.string.socialPlasticityLow);	
		}
		
		if(xValue == 5) {
			Ans = res.getString(R.string.pace) + ": " + "\n\n" + res.getString(R.string.paceHigh) + 
					"\n\n" +  res.getString(R.string.paceLow);	
		}
		
		if(xValue == 6) {
			Ans = res.getString(R.string.socialPace) + ": " + "\n\n" + res.getString(R.string.socialPaceHigh) + 
					"\n\n" +  res.getString(R.string.socialPaceLow);	
		}
		
		if(xValue == 7) {
			Ans = res.getString(R.string.emotionality) + ": " + "\n\n" + res.getString(R.string.emotionalityHigh) + 
					"\n\n" +  res.getString(R.string.emotionalityLow);	
		}
		
		if(xValue == 8) {
			Ans = res.getString(R.string.socialEmotionality) + ": " + "\n\n" + res.getString(R.string.socialEmotionalityHigh) + 
					"\n\n" +  res.getString(R.string.socialEmotionalityLow);	
		}
		
		if(xValue == 9) {
			Ans = res.getString(R.string.k) + ": " + "\n\n" + res.getString(R.string.kHigh) + 
					"\n\n" +  res.getString(R.string.kLow);	
		}
		return Ans;
	}
}
