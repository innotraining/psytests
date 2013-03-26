package com.example.szondi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;

public class ActivityReg extends Activity implements OnClickListener {
	EditText regName;
	EditText regDate;
	Button pushRegInfo;
	DBHelper dbHelper;
	int DIALOG_DATE = 1;
	int myYear = 1000;
	int myMonth = 10;
	int myDay = 10;
	final String LOG_TAG = "myLogs";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		regName = (EditText) findViewById(R.id.regName);
		regDate = (EditText) findViewById(R.id.regDate);
		pushRegInfo = (Button) findViewById(R.id.pushRegInfo);
		pushRegInfo.setOnClickListener(this);
		regDate.setOnClickListener(this);		
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
	    	cv.put("name", name);
	        cv.put("date", date);
	        long rowID = db.insert("listOfUsers", null, cv);
	        Intent intentReg = new Intent(this, ActivitySureTest.class);
	    	intentReg.putExtra("userID", (int)rowID);
	        dbHelper.close();
	        startActivity(intentReg);
	        break;
	    case R.id.regDate:
	    	showDialog(DIALOG_DATE);
	    	break;
	    default:
	    	dbHelper.close();
	    	break;
	    }
	  }
	
	protected Dialog onCreateDialog(int id) {
	      if (id == DIALOG_DATE) {
	        DatePickerDialog tpd = 
	        		new DatePickerDialog(this, myCallBack, 
	        				myYear, myMonth, myDay);
	        return tpd;
	      }
	      return super.onCreateDialog(id);
	    }
	
	OnDateSetListener myCallBack = new OnDateSetListener() {
	    public void onDateSet(DatePicker view, int year, 
	    		int monthOfYear, int dayOfMonth) {
	      myYear = year;
	      myMonth = monthOfYear+1;
	      myDay = dayOfMonth;
	      String zeroDay = "";
	      String zeroMonth = "";
	      if (myDay < 10) zeroDay = "0";
	      if (myMonth < 10) zeroMonth = "0";
	      regDate.setText(myYear+"/"+zeroMonth+myMonth+"/"+zeroDay+myDay);
	    }
	    };
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {
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