package com.example.szondi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityReg extends Activity implements OnClickListener {
	EditText regName;
	EditText regDate;
	Button pushRegInfo;
	DBHelper dbHelper;
	final String LOG_TAG = "myLogs";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		regName = (EditText) findViewById(R.id.regName);
		regDate = (EditText) findViewById(R.id.regDate);
		pushRegInfo = (Button) findViewById(R.id.pushRegInfo);
		pushRegInfo.setOnClickListener(this);
		
		dbHelper = new DBHelper(this);
	}
	
	@Override
	  public void onClick(View v) {
		ContentValues cv = new ContentValues();
		String name = regName.getText().toString();
	    String date = regDate.getText().toString();
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    switch (v.getId()) {
	    case R.id.pushRegInfo:
	    	Log.d(LOG_TAG, "--- Insert in listOfUsers: ---");
	    	cv.put("name", name);
	        cv.put("date", date);
	        long rowID = db.insert("listOfUsers", null, cv);
	        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
	        Intent intentReg = new Intent(this, ActivitySureTest.class);
	    	intentReg.putExtra("userID", (int)rowID);
	        dbHelper.close();
	        startActivity(intentReg);
	        break;
	    default:
	    	dbHelper.close();
	    	break;
	    }
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



