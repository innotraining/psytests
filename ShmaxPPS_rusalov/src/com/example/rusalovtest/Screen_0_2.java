package com.example.rusalovtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Screen_0_2 extends Activity {
	
	private DbOpenHelper dbOpenHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_0_2);
		final Resources res = getResources();
		
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(Screen_0_2.this);
		String chooseUser = res.getString(R.string.chooseUser);
		builder.setTitle(chooseUser);
		LayoutInflater li = Screen_0_2.this.getLayoutInflater();
		View view = li.inflate(R.layout.dialog_list, null);
		final ListView listView = (ListView) view.findViewById(R.id.listUsers);
		List<String> list = new ArrayList<String>();
		dbOpenHelper = new DbOpenHelper(Screen_0_2.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(DbOpenHelper.TABLE_NAME_USER, new String[]{DbOpenHelper.USER} , null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				list.add(cursor.getString(0));
			}while(cursor.moveToNext());
		}
		cursor.close();
	    db.close();
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Screen_0_2.this, android.R.layout.simple_list_item_1, list);
	    listView.setAdapter(adapter);
		builder.setView(view);
		builder.show();
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
					Intent intent = new Intent(Screen_0_2.this, Screen_0_3.class);
					String userName = (String)listView.getItemAtPosition(arg2);
					intent.putExtra("userName", userName);
					startActivity(intent);
					finish();
			}
			
		});
	}
}
