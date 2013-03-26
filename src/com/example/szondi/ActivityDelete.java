package com.example.szondi;

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
import android.widget.TextView;

public class ActivityDelete extends Activity implements OnClickListener {
	TextView sureDelete;
	Button noDelete;
	Button yesDelete;
	int userID;
	DBHelper dbHelper;
	final String LOG_TAG = "myLogs";
	String name;
	String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
		sureDelete = (TextView)findViewById(R.id.sureDelete);
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID", -1);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c;
   	 	String selection = "id = ?";
   	 	String[] selectionArgs = new String[] { ""+userID };
   	 	c = db.query("listOfUsers", null, selection, selectionArgs, null, null, null);
   	 	if (c.moveToFirst()) Log.d(LOG_TAG, "okay");
        int nameColIndex = c.getColumnIndex("name");
        int dateColIndex = c.getColumnIndex("date");
        sureDelete.setText("Вы действительно хотите удалить учётную запись "+
        		c.getString(nameColIndex)+" "+c.getString(dateColIndex)+"?");
        name = c.getString(nameColIndex);
        date = c.getString(dateColIndex);
        
        yesDelete = (Button)findViewById(R.id.yesDelete);
        noDelete = (Button)findViewById(R.id.noDelete);
        
        yesDelete.setOnClickListener(this);
        noDelete.setOnClickListener(this);
        
        dbHelper.close();
        
	}
	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		 case R.id.yesDelete:
			 dbHelper = new DBHelper(this);
		     SQLiteDatabase db = dbHelper.getWritableDatabase();
		     String condition[] = new String[] { ""+userID }; 
			 db.delete("listOfUsers", "id = ?",  condition);
			 String conditions[] = new String[] { name, date };
			 db.delete("ResultsOfUsers", "name = ? " +
			 		"AND birthDate = ?", conditions);
			 Cursor c = db.query("listOfUsers", null, null, null, 
					 null, null, null);
			 Intent intent;
			 
			 if (c.getCount() > 0) {
				 intent = new Intent(this, ActivityLog.class);
			 }
			 else {
				 intent = new Intent(this, ActivityReg.class);
			 }
			 dbHelper.close();
			 startActivity(intent);
		     break;
		 case R.id.noDelete:
			 Intent intentDelete = new Intent(this, ActivityTestOrDel.class);
			 intentDelete.putExtra("userID", userID);
			 startActivity(intentDelete);
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