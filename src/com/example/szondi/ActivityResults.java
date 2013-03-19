package com.example.szondi;

import java.util.ArrayList;
import java.util.Calendar;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		Log.d(LOG_TAG, "vooooo-> "+userID);
		
		ArrayList<String> userResultsToShow = new ArrayList<String>();
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c;
   	 	String selection = "id = ?";
   	 	String[] selectionArgs = new String[] { ""+userID };
        c = db.query("listOfUsers", null, selection, selectionArgs, null, null, null);
        if (c.moveToFirst()) Log.d(LOG_TAG, "okay");
        else Log.d(LOG_TAG, "not okay");
        int nameColIndex = c.getColumnIndex("name");
        int dateColIndex = c.getColumnIndex("date");
        
        String selection2 = "name = ? AND birthDate = ?";
        String[] selectionArgs2 = new String[] { c.getString(nameColIndex), c.getString(dateColIndex) };
        c = db.query("ResultsOfUsers", null, selection2, selectionArgs2, null, null, null);
        if (c.moveToFirst()) {
        	
	        int dateColIndex2 = c.getColumnIndex("testDate");

	        do {
	       /*   Log.d(LOG_TAG,
	              "ID = " + c.getInt(idColIndex) + 
	              ", name = " + c.getString(nameColIndex) + 
	              ", date = " + c.getString(dateColIndex));*/
	          String date = c.getString(dateColIndex2);
	          userResultsToShow.add(0,  "Тест от " + date);
	        } while (c.moveToNext());
	      } else {}
	      c.close();
	      
	      listOfTests = (ListView) findViewById(R.id.listOfTests);
	      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
			android.R.layout.simple_list_item_1, userResultsToShow);
	      listOfTests.setAdapter(adapter);
	      listOfTests.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intentTest = new Intent(this, ActivityFinalResult.class);
	//	intentTest.putExtra("right", n);
	    startActivity(intentTest);
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      // конструктор суперкласса
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {
		  //    Log.d(LOG_TAG, "--- onCreate database ---");
		      // создаем таблицу с полями
				db.execSQL("create table listOfUsers ("
			          + "id integer primary key autoincrement," 
			          + "name text,"
			          + "date text" + ");");
			      db.execSQL("create table ResultsOfUsers ("
				          + "id integer primary key autoincrement," 
				          + "name text,"
				          + "birthDate text,"
				          + "testDate text,"
				          + "right integer," + "wrong integer" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}
