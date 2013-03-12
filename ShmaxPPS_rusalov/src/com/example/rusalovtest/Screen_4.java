package com.example.rusalovtest;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Screen_4 extends Activity {
	
	
	private Button toMenuButton;
	private String userName;
	private DbOpenHelper dbOpenHelper;
	private Resources res;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_4);
		userName = getIntent().getStringExtra("userName");
		res = getResources();
		showResults();
	}
	
	
	public void showResults(){
		dbOpenHelper = new DbOpenHelper(Screen_4.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		final Cursor cursor = db.query(DbOpenHelper.TABLE_NAME_RESULT, new String[]{DbOpenHelper.USER, "energy", "socialEnergy", "plasticity", "socialPlasticity", "pace", "socialPace", "emotionality", "socialEmotionality", "K"} , DbOpenHelper.USER + " = '" + userName + "'", null, null, null, null);
		
		int num = cursor.getCount();
		final ListView listView = (ListView) findViewById(R.id.listResults);
		List<String> list = new ArrayList<String>();
		int i = 1;
		final String testNumber = res.getString(R.string.testNumber);
		while(i <= num){
			list.add(testNumber + i);
			i++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Screen_4.this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(adapter);
	    listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
					String curItem = (String)listView.getItemAtPosition(arg2);
					curItem = curItem.replaceAll(testNumber, "");
					int numTest =  Integer.parseInt(curItem);

					cursor.moveToPosition(numTest-1);

					process(cursor);
			}
			
		});
	    
		     
		
		toMenuButton = (Button) findViewById(R.id.toMenuButton);
		toMenuButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Screen_4.this, Screen_0.class);
		        startActivity(intent);
		        finish();
			}
			
    	});
	}
	
	void process(Cursor cursor){
		int energy = cursor.getInt(cursor.getColumnIndex("energy"));
		int socialEnergy = cursor.getInt(cursor.getColumnIndex("socialEnergy"));
		int plasticity = cursor.getInt(cursor.getColumnIndex("plasticity"));
		int socialPlasticity = cursor.getInt(cursor.getColumnIndex("socialPlasticity"));
		int pace = cursor.getInt(cursor.getColumnIndex("pace"));
		int socialPace = cursor.getInt(cursor.getColumnIndex("socialPace"));
		int emotionality = cursor.getInt(cursor.getColumnIndex("emotionality"));
		int socialEmotionality = cursor.getInt(cursor.getColumnIndex("socialEmotionality"));
		int K = cursor.getInt(cursor.getColumnIndex("k"));
		int[] scores = {energy, socialEnergy, plasticity, socialPlasticity, pace, socialPace, emotionality, socialEmotionality, K};
		String character;
		String socialCharacter;
		
		if(isMedium(energy) && isMedium(plasticity) && isMedium(pace) && isMedium(emotionality)){
			character = "sanguine";
		}
		
		else if(isHigh(energy) && isHigh(emotionality) && isHigh(pace) && (isMedium(plasticity) || isHigh(plasticity))){
			character = "choleric";
		}

		else if(isLow(energy) && isLow(plasticity) && isLow(pace) && isLow(emotionality)){
			character = "phlegmatic";
		}
		
		else if(isLow(energy) && isLow(plasticity) && isLow(pace) && (isMedium(emotionality) || isHigh(emotionality))){
			character = "melancholiac";
		}
		
		else character = "mixedCharacter";
		
		
		
		
		if(isMedium(socialEnergy) && isMedium(socialPlasticity) && isMedium(socialPace) && isMedium(socialEmotionality)){
			socialCharacter = "sanguine";
		}
		
		else if(isHigh(socialEnergy) && isHigh(socialEmotionality) && isHigh(socialPace) && (isMedium(socialPlasticity) || isHigh(socialPlasticity))){
			socialCharacter = "choleric";
		}
		
		else if(isLow(socialEnergy) && isLow(socialPlasticity) && isLow(socialPace) && isLow(socialEmotionality)){
			socialCharacter = "phlegmatic";
		}
		
		else if(isLow(socialEnergy) && isLow(socialPlasticity) && isLow(socialPace) && (isMedium(socialEmotionality) || isHigh(socialEmotionality))){
			socialCharacter = "melancholiac";
		}
		
		else socialCharacter = "mixedCharacter";
		
		Intent intent = new Intent(Screen_4.this, Screen_5.class);
		intent.putExtra("userName", userName);
		intent.putExtra("character", character);
		intent.putExtra("socialCharacter", socialCharacter);
		intent.putExtra("scores", scores);
		startActivity(intent);
		finish();
	}
	
	public Boolean isHigh(int cur){	
		return cur >= 9;
	}
	
	public Boolean isLow(int cur){	
		return cur <= 4;
	}
    
	public Boolean isMedium(int cur){	
		return (cur <= 8) && (cur >= 5);
	}
}
