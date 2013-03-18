package com.example.eysencktest;
import static java.util.Arrays.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends Activity {

	List<Integer> theNumbersFalseYes = asList(6, 24, 36);
	List<Integer> theNumbersFalseNo = asList(12, 18, 30, 42, 48, 54);
	List<Integer> theNumbersNerotism = asList(2, 4, 7, 9, 11, 14, 16, 19, 21, 23, 26, 28, 31, 33, 35, 38, 40, 43, 45, 47, 50, 52, 55, 57);
	List<Integer> theNumbersExtroIntroversionYes = asList(1, 3, 8, 10, 13, 17, 22, 25, 27, 39, 44, 46, 49, 53, 56);
	List<Integer> theNumbersExtroIntroversionNo = asList(5, 15, 20, 29, 32, 34, 37, 41, 51); 
	public List<String> stringQuestion;  
	String name;
	int numFalseAnswer = 0; 
	Integer numCurrentQuestion = 0; 
	int numNerotizm = 0; 
	int numExtroIntrovers = 0; 
	private TextView testText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		stringQuestion= readTxt1(); 		
		setContentView(R.layout.activity_test);
		stringQuestion= readTxt1(); 		
		Intent intent = getIntent(); 
		TextView nameView = (TextView) findViewById(R.id.textView2); 
		name = intent.getStringExtra("name");
		nameView.setText(name+"!");
		NextQuestion(); 		
	}
	AlertDialog.Builder ad;
	Context context;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);

		//String text = readRawTextFile(context, getResources().getIdentifier(itemname, "raw", "com.webster"));

		return true;
	}

	private List<String> readTxt1(){
		List<String> lines    = new ArrayList();
		InputStream inputStream;
		int chooseTest = (int) (Math.random()*0 + Math.random()*1);
		if( chooseTest ==0 ) {
			inputStream = getResources().openRawResource(R.raw.qw2);
		}
		else inputStream = getResources().openRawResource(R.raw.qw1);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		String newline = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line + newline);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}		 	

	public void NextQuestion(){		

		testText = (TextView) findViewById(R.id.textView1);
		testText.setText(stringQuestion.get(numCurrentQuestion));	

		if(numCurrentQuestion <5) 	++numCurrentQuestion; //56
		else { 
			//deletefixRow();
			saveDate();			 
			changeToResultActivity();
			finish(); 
		}		
	}

	public void deletefixRow(){
		DbOpenHelper dbOpenHelper = new DbOpenHelper(TestActivity.this);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("DELETE *  FROM users WHERE fix = ? " , new String[]{"fix"});
		//db.delete(DbOpenHelper.TABLE_NAME, DbOpenHelper.FIX, new String[]{"fix"});
		db.close();

	}
	@SuppressWarnings({ "unused" })
	@SuppressLint("SimpleDateFormat")
	public void saveDate(){

		DbOpenHelper dbOpenHelper = new DbOpenHelper(TestActivity.this);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	       
		ContentValues cv = new ContentValues();	       
		cv.put(DbOpenHelper.LOGIN, name);
		java.util.Date sdf;// = new SimpleDateFormat("yyyyMMdd  HH:mm");
		Calendar Current_Calendar = Calendar.getInstance();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
		
		sdf = Current_Calendar.getTime();
		String myString = DateFormat.getDateInstance().format(sdf);
		//sdf.format(format, args)
		//sdf.sgetCalendar();
		cv.put(DbOpenHelper.DATE, myString);//.format("yyyyMMdd  HH:mm"));//dateCurrent.toString());
		cv.put(DbOpenHelper.FIX, "norm");
		cv.put(DbOpenHelper.FALSEM, numFalseAnswer);
		cv.put(DbOpenHelper.INEXTROM, numExtroIntrovers);
		cv.put(DbOpenHelper.STABLEM, numNerotizm);
		db.insert(DbOpenHelper.TABLE_NAME, null,cv);
		db.close();

	}

	public void dialogAlert(){

		context = TestActivity.this;
		ad = new AlertDialog.Builder(context);
		ad.setMessage("повторите «Ваши ответы признаны недостоверными. Для прохождения теста заново нажмите кнопку «Заново». Для выхода нажмите «В главное меню»попытку");
		ad.setPositiveButton("Заново", new OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				
				 Intent intent = new Intent();
				 intent.setClass(TestActivity.this, TestActivity.class);
				 intent.putExtra("name", name);
				 startActivity(intent);
				 finish(); 
			}
		});
		ad.setNegativeButton("В главное меню", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				 intent.setClass(TestActivity.this, FirstActivity.class);
				 startActivity(intent);
				 finish(); 
			}
		} );
		ad.show();
		finish();
	}
	public void clickOnYes(View view){
		if( theNumbersFalseYes.contains(numCurrentQuestion))  ++numFalseAnswer; 
		if( numFalseAnswer >4 ){ dialogAlert();} 
		else {
			if( theNumbersExtroIntroversionYes.contains(numCurrentQuestion)) ++numExtroIntrovers; 
			if( theNumbersNerotism.contains(numCurrentQuestion)) ++numNerotizm; 	
			NextQuestion(); 
		}
	}
	public void clickOnNo(View view){			
		if( theNumbersFalseNo.contains(numCurrentQuestion) )  ++numFalseAnswer; 

		if( numFalseAnswer >4 ){dialogAlert();}
		else {
			if( theNumbersExtroIntroversionNo.contains(numCurrentQuestion)) ++numExtroIntrovers; 
			NextQuestion(); 
		}		
	}
	public void changeToResultActivity(){
		 Intent intent = new Intent();
	        intent.setClass(TestActivity.this, ResultActivity.class);
	          //Bundle b = new Bundle();
	          //b.putString("defStrID", itemname); //defStrID - уникальная строка, отправим itemname в другое Activity
	          intent.putExtra("false", numFalseAnswer);
	          intent.putExtra("extrointro", numExtroIntrovers); 
	          intent.putExtra("nero",numNerotizm ); 
	          startActivity(intent);	
	          //finish(); 
	}
	
	public void onClickButtonExit(View view){
		 Intent intent = new Intent();
	        intent.setClass(TestActivity.this, FirstActivity.class);
	          //Bundle b = new Bundle();
	          //b.putString("defStrID", itemname); //defStrID - уникальная строка, отправим itemname в другое Activity

		finish(); 
		//System.exit(0);
	}
}


