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
//	int  = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		ArrayList<String> ourUsersToShow = new ArrayList<String>();
		
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Log.d(LOG_TAG, "--- Rows in listOfUsers: ---");
		Cursor c = db.query("listOfUsers", null, null, null, null, null, null);
		if (c.moveToFirst()) {
	        // определяем номера столбцов по имени в выборке
	        int idColIndex = c.getColumnIndex("id");
	        int nameColIndex = c.getColumnIndex("name");
	        int dateColIndex = c.getColumnIndex("date");

	        do {
	          // получаем значения по номерам столбцов и пишем все в лог
	        	
	          Log.d(LOG_TAG,
	              "ID = " + c.getInt(idColIndex) + 
	              ", name = " + c.getString(nameColIndex) + 
	              ", date = " + c.getString(dateColIndex));
	          String date = c.getString(dateColIndex);
	          int yearOfUser = 1000*(date.charAt(0)-'0')+100*(date.charAt(1)-'0')
	        		  +10*(date.charAt(2)-'0')+(date.charAt(3)-'0');
	          int monthOfUser = 10*(date.charAt(5)-'0')+(date.charAt(6)-'0');
	          int dayOfUser = 10*(date.charAt(8)-'0')+(date.charAt(9)-'0');
	          Calendar calendar = Calendar.getInstance();
	          int year = calendar.get(Calendar.YEAR);
	          int month = calendar.get(Calendar.MONTH);
	          int day = calendar.get(Calendar.DAY_OF_MONTH);
	          /*if ((year + month/12 + day/365) 
	        		  - (yearOfUser + monthOfUser/12 + dayOfUser/365) >= 7.0) {
	        	  
	          }*/
	          ourUsersToShow.add(0, c.getString(nameColIndex)+", " 
	            + (int)((year + month/12 + day/365) - (yearOfUser + monthOfUser/12 + dayOfUser/365))/*c.getString(dateColIndex)*/);
	          // переход на следующую строку 
	          // а если следующей нет (текущая - последняя), то false - выходим из цикла
	        } while (c.moveToNext());
	      } else
	        Log.d(LOG_TAG, "0 rows");
	      c.close();
		
		
		listOfUsers = (ListView) findViewById(R.id.listOfUsers);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, ourUsersToShow);
		/*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		        this, R.array.names, android.R.layout.simple_list_item_1);*/
		listOfUsers.setAdapter(adapter);
		
		listOfUsers.setOnItemClickListener(this);
		dbHelper.close();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intentTest = new Intent(this, ActivityTestOrDel.class);
		dbHelper = new DBHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Log.d(LOG_TAG, "--- Rows in listOfUsers: ---");
		Cursor c = db.query("listOfUsers", null, null, null, null, null, null);
		int counter = 0;
		int id = -1;
		if (c.moveToFirst()) {
	        int idColIndex = c.getColumnIndex("id");
	        int nameColIndex = c.getColumnIndex("name");
	        int dateColIndex = c.getColumnIndex("date");

	        do {
	        	if ((int)arg3 == counter) {
	        		id = c.getInt(idColIndex);
	        		break;
	        	}
	        	Log.d(LOG_TAG,
	              "ID = " + c.getInt(idColIndex) + 
	              ", name = " + c.getString(nameColIndex) + 
	              ", date = " + c.getString(dateColIndex));
	        	counter++;
	        } while (c.moveToNext());
	   	} 
		else
			Log.d(LOG_TAG, "0 rows");
	    c.close();
	    dbHelper.close();
		intentTest.putExtra("userID", id);
	    startActivity(intentTest);
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      // конструктор суперкласса
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {
		      Log.d(LOG_TAG, "--- onCreate database ---");
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
