package com.mistdale.test2;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class UsersActivity extends ListActivity {

	
	private String selectedItem;
	private ArrayAdapter<String> adapter;
	private final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Show the Up button in the action bar.
		setupActionBar();
				
		// Get Users from DB and fill List Adapter
		getUsers();	
		
		
		OnItemClickListener itemListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(context, MainActivity.class);
				intent.putExtra("user", parent.getItemAtPosition(position).toString());
				startActivity(intent);
				finish();
			}
		};
		getListView().setOnItemClickListener(itemListener);
		
		OnItemLongClickListener itemLongListener = new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedItem = parent.getItemAtPosition(position).toString();

				// Build new AlertDialog
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Вы хотите удалить пользователя \"" + selectedItem
						+ "\"?");
				builder.setCancelable(false);
				builder.setPositiveButton("Да",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								adapter.remove(selectedItem);
								deleteUser(selectedItem);
								adapter.notifyDataSetChanged();

								Toast.makeText(getApplicationContext(), "Пользователь \"" +
										selectedItem + "\" удалён",
										Toast.LENGTH_SHORT).show();
							}
						});
				builder.setNegativeButton("Нет",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				// выводим диалоговое окно
				builder.show();				
				
				return true;
			}
		};
		getListView().setOnItemLongClickListener(itemLongListener);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.users, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getUsers() {
		
		
		//String[] dummy_arr = new String[] {"Tim", "Вася", "петруха"};
		String[] dummy_arr = new String[] {};
		ArrayList<String> userList = new ArrayList<String>(Arrays.asList(dummy_arr));

		
		
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		String[] projection = {TestDbContract.TableUsers.COLUMN_NAME_USERNAME};
		String sortOrder = TestDbContract.TableUsers.COLUMN_NAME_USERNAME + " DESC";
		
		Cursor c = db.query(
				TestDbContract.TableUsers.TABLE_NAME,
				projection, 
				null, 
				null, 
				null, 
				null, 
				sortOrder);
		
		try {
		
			if (c.moveToFirst()) {
				String name;
				do {
					name = c.getString(c.getColumnIndex(TestDbContract.TableUsers.COLUMN_NAME_USERNAME));
					userList.add(name);
				} while (c.moveToNext()); 
			}
		} finally {
			db.close();
			c.close();
		}
		
		
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList);
		setListAdapter(adapter);
	}
	
	private void deleteUser(String userName) {
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		try {
			String selection = TestDbContract.TableUsers.COLUMN_NAME_USERNAME + " LIKE ?";
			String[] selectionArgs = {userName};
			db.delete(TestDbContract.TableUsers.TABLE_NAME, selection, selectionArgs);
			
			String selection2 = TestDbContract.TableResults.COLUMN_NAME_USER + " = ?";
			String[] selectionArgs2 = {userName};
			db.delete(TestDbContract.TableResults.TABLE_NAME, selection2, selectionArgs2);
		} finally {
			db.close();
		}
	}

}
