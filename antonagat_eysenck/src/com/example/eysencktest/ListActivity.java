package com.example.eysencktest;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {
	ArrayList<String> users = new ArrayList<String>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ListView lv = (ListView)findViewById(R.id.listView1);		 

		DbOpenHelper dbOpenHelper = new DbOpenHelper(ListActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor userCursor= db.rawQuery("SELECT DISTINCT login FROM users ", null);//  WHERE login = ? and fix = ? ", new String[] {name, "fix"});
		userCursor.moveToFirst();
		if(!userCursor.isAfterLast()) {
			do {
				String name = userCursor.getString(0);
				users.add(name);
			} while (userCursor.moveToNext());
		}
		userCursor.close();

		db.close(); 

		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, users );
		lv.setAdapter(adapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.scheduleLayoutAnimation();
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
					long id) {
				TextView itemname = (TextView) itemClicked;
				String strText = itemname.getText().toString(); // получаем текст нажатого элемента

				Intent intent = new Intent();
				intent.setClass(ListActivity.this, ProfileActivity.class);
				intent.putExtra("name", strText);
				startActivity(intent);	
				// finish();
			}
		});			
		//lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);	
	}	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	public void onClickItem(View arg0, int item){
		Intent intent = new Intent();
		intent.putExtra("name", users.get(item)); //mylv.getCheckedItemPosition()));   
		startActivity(intent);	
	}
	public void onClickDelete(View view){
		//TODO
	}
}

