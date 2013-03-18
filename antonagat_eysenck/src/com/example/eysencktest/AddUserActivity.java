package com.example.eysencktest;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends Activity {	 
	EditText loginEditText = null;
	EditText passEditText = null;
	Button saveButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		loginEditText = (EditText) findViewById(R.id.editText1);
		saveButton = (Button) findViewById(R.id.button1);
	}	  
	public void onClickSave(View view) {
		DbOpenHelper dbOpenHelper = new DbOpenHelper(AddUserActivity.this);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DbOpenHelper.LOGIN, loginEditText.getText().toString());
		cv.put(DbOpenHelper.FALSEM, -1);
		cv.put(DbOpenHelper.FIX, "fix");
		cv.put(DbOpenHelper.INEXTROM, 18);
		cv.put(DbOpenHelper.STABLEM, 5);
		db.insert(DbOpenHelper.TABLE_NAME, null,cv);
		db.close();
		Intent intent = new Intent();
		intent.setClass(AddUserActivity.this, ListActivity.class);
		startActivity(intent);
		finish();   
	}
}
