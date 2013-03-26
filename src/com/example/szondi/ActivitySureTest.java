package com.example.szondi;

import java.util.Calendar;

import com.example.szondi.ActivityTest.DBHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivitySureTest extends Activity implements OnClickListener {
	final String LOG_TAG = "myLogs";
	Button start;
	Button results;
	int userID;
	String name;
	String date;
	DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suretest);
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		start = (Button)findViewById(R.id.start);
		results = (Button)findViewById(R.id.getResults);
		start.setOnClickListener(this);
		results.setOnClickListener(this);
		
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c;
   	 	String selection = "id = ?";
   	 	String[] selectionArgs = new String[] { ""+userID };
        c = db.query("listOfUsers", null, selection, 
        		selectionArgs, null, null, null);
        if (c.moveToFirst()) Log.d(LOG_TAG, "okay");
        int nameColIndex = c.getColumnIndex("name");
        int dateColIndex = c.getColumnIndex("date");
        name = c.getString(nameColIndex);
        date = c.getString(dateColIndex);
        db.close();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	    case R.id.start:
	    	dbHelper = new DBHelper(this);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
	        Calendar calendar = Calendar.getInstance();
	        String selection2 = "name = ? AND birthDate = ? AND " +
	        		"testDate = ?";
	        int month = calendar.get(calendar.MONTH) + 1;
	        String[] selectionArgs2 = new String[] { name, date,
	        		calendar.get(calendar.YEAR) + "/" 
			    			 + month + "/" 
			    			 + calendar.get(calendar.DAY_OF_MONTH)
	        		};	        
	        Cursor c = db.query("ResultsOfUsers", null, selection2, 
	        		selectionArgs2, null, null, null);
	        int counter = 0;
	        if (c.moveToFirst()) {
		        do {
		        	counter++;
		        } while (c.moveToNext());
		   	} else {}
		   	c.close();
			dbHelper.close();
			if (counter >= 3) {
				break;
			}
			Intent intent1;
			int yearOfUser = 1000*(date.charAt(0)-'0')+100*(date.charAt(1)-'0')
	        		  +10*(date.charAt(2)-'0')+(date.charAt(3)-'0');
	        int monthOfUser = 10*(date.charAt(5)-'0')+(date.charAt(6)-'0');
	        int dayOfUser = 10*(date.charAt(8)-'0')+(date.charAt(9)-'0');
	        int year = calendar.get(Calendar.YEAR);
	        int day = calendar.get(Calendar.DAY_OF_MONTH);
			int age = (int)((year + month/12 + day/365)
	        		  - (yearOfUser + monthOfUser/12 + dayOfUser/365));
	    	if (age >= 7) intent1 = new Intent(this, ActivityTestForOld.class);
	    	else intent1 = new Intent(this, ActivityTest.class);
	    	intent1.putExtra("userID", userID);
	        startActivity(intent1);
	        break;
	    case R.id.getResults:
	    	Intent intent2 = new Intent(this, ActivityResults.class);
	    	intent2.putExtra("userID", userID);
	    	intent2.putExtra("name", name);
	    	intent2.putExtra("date", date);
	        startActivity(intent2);
	    	break;
	    default:
	    	break;
	    }
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