package com.example.rusalovtest;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
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
		final SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		//final Cursor cursor = db.query(DbOpenHelper.TABLE_NAME_RESULT, new String[]{DbOpenHelper.USER, "energy", "socialEnergy", "plasticity", "socialPlasticity", "pace", "socialPace", "emotionality", "socialEmotionality", "K", "day", "month", "year", "isLast"} , DbOpenHelper.USER + " = '" + userName + "'", null, null, null, null);
		final Cursor cursor = db.query(true, DbOpenHelper.TABLE_NAME_RESULT, new String[]{"day", "month", "year"}, DbOpenHelper.USER + " = '" + userName + "'", null, null, null, null, null);
		
		final ListView listView = (ListView) findViewById(R.id.listResults);
		List<String> list = new ArrayList<String>();
		
		if(cursor.moveToFirst()){
			do{
				int day = cursor.getInt(cursor.getColumnIndex("day"));
				int month = cursor.getInt(cursor.getColumnIndex("month"));
				int year = cursor.getInt(cursor.getColumnIndex("year"));
				String date = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
				list.add(date);
			}while(cursor.moveToNext());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Screen_4.this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(adapter);
	    listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
					String curItem = (String)listView.getItemAtPosition(arg2);
					String[] curItemsDate = curItem.split("\\D");
					final Cursor curCursor = db.query(DbOpenHelper.TABLE_NAME_RESULT, new String[]{DbOpenHelper.USER, "energy", "socialEnergy", "plasticity", "socialPlasticity", "pace", "socialPace", "emotionality", "socialEmotionality", "K"} , DbOpenHelper.USER + " = '" + userName + "' AND day = " + curItemsDate[0] + " AND month = " + curItemsDate[1] + " AND year = " + curItemsDate[2], null, null, null, null);
					
					AlertDialog.Builder builder = new AlertDialog.Builder(Screen_4.this);
					String chooseTest = res.getString(R.string.chooseTest);
					String back = res.getString(R.string.back);
					builder.setTitle(chooseTest);
					
					builder.setNegativeButton(back, new DialogInterface.OnClickListener() {
						   @Override
						   public void onClick(DialogInterface dialog, int which) {
							   dialog.cancel();
						   }
					});
					
					LayoutInflater li = Screen_4.this.getLayoutInflater();
					View view = li.inflate(R.layout.dialog_list_test, null);
					final ListView listView = (ListView) view.findViewById(R.id.listTests);
					List<String> curList = new ArrayList<String>();
					int num = curCursor.getCount();
					int i = 1;
					final String testNumber = res.getString(R.string.testNumber);
					while(i <= num){
						curList.add(testNumber + i);
						i++;
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(Screen_4.this, android.R.layout.simple_list_item_1, curList);
				    listView.setAdapter(adapter);
				    builder.setView(view);
					builder.show();
				    listView.setClickable(true);
				    listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
								String curItem = (String)listView.getItemAtPosition(arg2);
								curItem = curItem.replaceAll(testNumber, "");
								int numTest =  Integer.parseInt(curItem);

								curCursor.moveToPosition(numTest-1);

								process(curCursor);
						}
						
					});
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

		
		if(isMedium(energy) && isMedium(plasticity) && isMedium(pace) && isMedium(emotionality) && isMedium(socialEnergy) && isMedium(socialPlasticity) && isMedium(socialPace) && isMedium(socialEmotionality)){
			character = "sanguine";
		}
		
		else if(isHigh(energy) && isHigh(socialEnergy) && isHigh(emotionality) && isHigh(pace) && (isMedium(plasticity) || isHigh(plasticity))){
			character = "choleric";
		}

		else if(isLow(energy) && isLow(plasticity) && isLow(pace) && isLow(emotionality) && isLow(socialEnergy) && isLow(socialPlasticity) && isLow(socialPace) && isLow(socialEmotionality)){
			character = "phlegmatic";
		}
		
		else if(isLow(energy) && isLow(socialEnergy) && isLow(plasticity) && isLow(pace) && (isMedium(emotionality) || isHigh(emotionality))){
			character = "melancholiac";
		}
		
		else character = "mixedCharacter";
		
		Intent intent = new Intent(Screen_4.this, Screen_5.class);
		intent.putExtra("userName", userName);
		intent.putExtra("character", character);
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
