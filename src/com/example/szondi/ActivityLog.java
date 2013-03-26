package com.example.szondi;

import java.util.ArrayList;
import java.util.Calendar;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityLog extends Activity implements OnItemClickListener {
	final String LOG_TAG = "myLogs";
	ListView listOfUsers;
	String[] names;
	DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ArrayList<String> ourUsersToShow = new ArrayList<String>();
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = db.query("listOfUsers", null, null, null, 
				null, null, null);
		if (c.moveToFirst()) {
	        int nameColIndex = c.getColumnIndex("name");
	        int dateColIndex = c.getColumnIndex("date");
	        do {
	          String date = c.getString(dateColIndex);
	          int yearOfUser = 1000*(date.charAt(0)-'0')
	        		  +100*(date.charAt(1)-'0')
	        		  +10*(date.charAt(2)-'0')
	        		  +(date.charAt(3)-'0');
	          int monthOfUser = 10*(date.charAt(5)-'0')
	        		  +(date.charAt(6)-'0');
	          int dayOfUser = 10*(date.charAt(8)-'0')
	        		  +(date.charAt(9)-'0');
	          Calendar calendar = Calendar.getInstance();
	          int year = calendar.get(Calendar.YEAR);
	          int month = calendar.get(Calendar.MONTH);
	          int day = calendar.get(Calendar.DAY_OF_MONTH);
	          ourUsersToShow.add(0, c.getString(nameColIndex)
	        		  +", " 
	        		  + (int)((year + month/12 + day/365)
	        		  - (yearOfUser + monthOfUser/12 + dayOfUser/365))
	        		  );
	        } while (c.moveToNext());
	      }
		listOfUsers = (ListView) findViewById(R.id.listOfUsers);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, ourUsersToShow);
		listOfUsers.setAdapter(adapter);
		listOfUsers.setOnItemClickListener(this);
		dbHelper.close();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, 
			int arg2, long arg3) {
		Intent intentTest = new Intent(this, ActivityTestOrDel.class);
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = db.query("listOfUsers", null, null, null, null, 
				null, null);
		int counter = c.getCount()-1;
		int id = -1;
		if (c.moveToFirst()) {
	        int idColIndex = c.getColumnIndex("id");
	        do {
	        	if ((int)arg3 == counter) {
	        		id = c.getInt(idColIndex);
	        		break;
	        	}
	        	counter--;
	        } while (c.moveToNext());
	   	}
	    c.close();
	    dbHelper.close();
		intentTest.putExtra("userID", id);
	    startActivity(intentTest);
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {
		      Log.d(LOG_TAG, "--- onCreate database ---");
		      db.execSQL("create table listOfUsers ("
			          + "id integer primary key autoincrement," 
			          + "name text,"
			          + "date text" + ");");
			      db.execSQL("create table ResultsOfUsers ("
				          + "id integer primary key autoincrement," 
				          + "name text,"
				          + "birthDate text,"
				          + "testDate text,"
				          + "right1 integer," + "wrong1 integer," 
				          + "right2 integer," + "wrong2 integer," 
				          + "right3 integer," + "wrong3 integer," 
				          + "right4 integer," + "wrong4 integer," 
				          + "right5 integer," + "wrong5 integer" + ");");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{}
	}
}