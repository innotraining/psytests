package com.example.szondi;

import java.util.ArrayList;

import com.example.szondi.ActivityLog.DBHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityResults extends Activity implements OnItemClickListener {
	final String LOG_TAG = "myLogs";
	ListView listOfTests;
	DBHelper dbHelper;
	int userID;
	String name;
	String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		name = intent.getStringExtra("name");
		date = intent.getStringExtra("date");
		ArrayList<String> userResultsToShow = new ArrayList<String>();
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c;   
        String selection2 = "name = ? AND birthDate = ?";
        String[] selectionArgs2 = new String[] { name, date };
        c = db.query("ResultsOfUsers", null, selection2, 
        		selectionArgs2, null, null, null);
        if (c.moveToFirst()) {
	        int dateColIndex2 = c.getColumnIndex("testDate");
	        do {
	          String date = c.getString(dateColIndex2);
	          userResultsToShow.add(0,  "Тест от " + date);
	        } while (c.moveToNext());
	      } else {}
	      c.close();
	      dbHelper.close();
	      listOfTests = (ListView) findViewById(R.id.listOfTests);
	      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
			android.R.layout.simple_list_item_1, userResultsToShow);
	      listOfTests.setAdapter(adapter);
	      listOfTests.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
			long arg3) {
		Intent intentTest = new Intent(this, ActivityFinalResult.class);
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c;
		String selection2 = "name = ? AND birthDate = ?";
        String[] selectionArgs2 = new String[] { name, date };
        c = db.query("ResultsOfUsers", null, selection2, 
        		selectionArgs2, null, null, null);
        int counter = c.getCount()-1;
		if (c.moveToFirst()) {
	        int idr1 = c.getColumnIndex("right1");
	        int idr2 = c.getColumnIndex("right2");
	        int idr3 = c.getColumnIndex("right3");
	        int idr4 = c.getColumnIndex("right4");
	        int idr5 = c.getColumnIndex("right5");
	        int idw1 = c.getColumnIndex("wrong1");
	        int idw2 = c.getColumnIndex("wrong2");
	        int idw3 = c.getColumnIndex("wrong3");
	        int idw4 = c.getColumnIndex("wrong4");
	        int idw5 = c.getColumnIndex("wrong5");
	        do {
	        	if ((int)arg3 == counter) {
	        		intentTest.putExtra("right1", c.getInt(idr1));
	        		intentTest.putExtra("right2", c.getInt(idr2));
	        		intentTest.putExtra("right3", c.getInt(idr3));
	        		intentTest.putExtra("right4", c.getInt(idr4));
	        		intentTest.putExtra("right5", c.getInt(idr5));
	        		intentTest.putExtra("wrong1", c.getInt(idw1));
	        		intentTest.putExtra("wrong2", c.getInt(idw2));
	        		intentTest.putExtra("wrong3", c.getInt(idw3));
	        		intentTest.putExtra("wrong4", c.getInt(idw4));
	        		intentTest.putExtra("wrong5", c.getInt(idw5));
	        		break;
	        	}
	        	counter--;
	        } while (c.moveToNext());
	   	} 
	    c.close();
	    dbHelper.close();
	    startActivity(intentTest);
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{}
	}
}