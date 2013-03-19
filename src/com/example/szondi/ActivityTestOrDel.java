package com.example.szondi;

//import com.example.szondi.ActivityDelete.DBHelper;

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

public class ActivityTestOrDel extends Activity implements OnClickListener {
	Button goToTest;
	Button deleteUser;
	int userID;
	final String LOG_TAG = "myLogs";
	DBHelper dbHelper;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_delete);
        
        deleteUser = (Button)findViewById(R.id.deleteUser);
        Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		Log.d(LOG_TAG, "current id is "+userID);
		dbHelper = new DBHelper(this);
	    SQLiteDatabase db = dbHelper.getWritableDatabase();
	    Cursor c;
	    
	   	String selection = "id = ?";
	   	String[] selectionArgs = new String[] { ""+userID };
	   	c = db.query("listOfUsers", null, selection, selectionArgs, null, null, null);
	    int nameColIndex = -1;
	  	int dateColIndex = -1;
	   	if (c.moveToFirst()) { 
	   	 		Log.d(LOG_TAG, "okay");
	   	 		nameColIndex = c.getColumnIndex("name");
	   	 		dateColIndex = c.getColumnIndex("date");
	   	}
	   	else Log.d(LOG_TAG, "not okay");
	   	deleteUser.setText("Удалить пользователя "+ c.getString(nameColIndex)+" "+c.getString(dateColIndex) + 
        		" и все его результаты тестирования?");
	   	goToTest = (Button)findViewById(R.id.goToTest);
	   	goToTest.setOnClickListener(this);
	   	deleteUser.setOnClickListener(this);
	   	dbHelper.close();
        
    }
	
	 public void onClick(View v) {
		 switch (v.getId()) {
		 case R.id.goToTest:
			 Log.d(LOG_TAG, "!!!!!!!!!!!!!");
			 Intent intentTest = new Intent(this, ActivitySureTest.class);
			 intentTest.putExtra("userID", userID);
			 startActivity(intentTest);
		     break;
		 case R.id.deleteUser:
			 Log.d(LOG_TAG, "*****************");
			 Intent intentDelete = new Intent(this, ActivityDelete.class);
			 intentDelete.putExtra("userID", userID);
			 startActivity(intentDelete);
			 break;
		 default:
			 break;
		 }
	 }
	 
	 class DBHelper extends SQLiteOpenHelper {
			public DBHelper(Context context) {
			      // конструктор суперкласса
			      super(context, "myDB", null, 1);
			    }
			public void onCreate(SQLiteDatabase db) {/*
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
					          + "right integer," + "wrong integer" + ");");*/
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			}
		}
	 
}
