package com.example.tester;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startTest(View view) {
		Intent intent = new Intent(this, TestActivity.class);
		startActivity(intent);
	}

	public void registerNewUser(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Новый пользователь");
		final LayoutInflater li = this.getLayoutInflater();
		final View v = li.inflate(R.layout.r_layout, null);
		builder.setView(v);
		builder.setPositiveButton(R.string.create_user,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						EditText editText = (EditText) v
								.findViewById(R.id.username);
						String username = editText.getText().toString();
						if (username.equals("")) {

						} else {
							DbOpenHelper dbOpenHelper = new DbOpenHelper(
									MainActivity.this);
							SQLiteDatabase db = dbOpenHelper
									.getWritableDatabase();
							ContentValues cv = new ContentValues();
							cv.put("username", username);
							db.insert(DbOpenHelper.TABLE_NAME, null, cv);
							db.close();
							Intent intent = new Intent(getBaseContext(),
									RegistrationActivity.class);
							intent.putExtra(USER_SERVICE, username);
							startActivity(intent);
						}
					}
				});
		builder.show();
	}

	public void showUserChooser(View view) {
		List<String> userList = new ArrayList<String>();
		String selectQuery = "SELECT username FROM " + DbOpenHelper.TABLE_NAME;
		DbOpenHelper dbOpenHelper = new DbOpenHelper(MainActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(0);
				userList.add(name);
			} while (cursor.moveToNext());
		}
		final String[] usernames = userList
				.toArray(new String[userList.size()]);
		if (usernames.length != 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Список пользователей");
			builder.setItems(usernames, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int item) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getBaseContext(),
							RegistrationActivity.class);
					intent.putExtra(USER_SERVICE, usernames[item]);
					startActivity(intent);
				}
			});
			builder.show();
		} else {
			registerNewUser(view);
		}
	}

	public void finishTest(View view) {
		finish();
	}

}
