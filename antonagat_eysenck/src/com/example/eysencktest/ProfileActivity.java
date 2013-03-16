package com.example.eysencktest;
import java.util.ArrayList;
import com.example.eysencktest.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ProfileActivity extends Activity {
	ArrayList<String> fieldsUser = new ArrayList<String>();
	String[] colName = {"date", "fakseM", "introextraM", "stableM"};
	String name = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ListView listView = (ListView) findViewById(R.id.listView1);	
		DbOpenHelper dbOpenHelper = new DbOpenHelper(ProfileActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor userCursor=null;
		Intent intent = getIntent(); 
		name  = intent.getStringExtra("name").toString();

		userCursor = db.rawQuery("SELECT date, falseM, introextroM, stableM FROM users  WHERE login = ? and fix = ? ", new String[] {name, "norm"});
		// Button my = (Button) findViewById(R.id.button1);
		userCursor.moveToFirst();
		if(!userCursor.isAfterLast()) {
			do {
				String temp = ""; 
				for(int i=0; i<4; i++){
					temp+= colName[i]+ ": "+userCursor.getString(i)+ " ";
				}
				fieldsUser.add(temp);
			} while (userCursor.moveToNext());
		}
		userCursor.close();
		db.close(); 
		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fieldsUser );

		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setAdapter(adapter); 

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}


	public void deletefixRowAll(View view){
		DbOpenHelper dbOpenHelper = new DbOpenHelper(ProfileActivity.this);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.close();
		finish();
	}

	public void onClickNewTest(View view){
		Intent intent = new Intent();
		intent.setClass(ProfileActivity.this, TestActivity.class);
		intent.putExtra("name", name.toString());
		startActivity(intent);	
		finish();		
	}

}
