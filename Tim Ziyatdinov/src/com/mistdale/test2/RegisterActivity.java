package com.mistdale.test2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	public void back(View view) {
		// ugly 
		//System.exit(0);
		finish();
    	//android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	public void saveUser(View view) {
		// TODO: save
		EditText e = (EditText) findViewById(R.id.editTextUsername);
		String username = e.getText().toString();
		
		if (username.equals("")) {
			// TODO filter names
			Context context = getApplicationContext();
			CharSequence text = "Некорректное имя!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		} else {
			TestDbHelper mDbHelper = new TestDbHelper(this);
			SQLiteDatabase db = mDbHelper.getWritableDatabase();
			try {
				ContentValues values = new ContentValues();
				values.put(TestDbContract.TableUsers.COLUMN_NAME_USERNAME, username);
			
				db.insert(TestDbContract.TableUsers.TABLE_NAME, null, values); 
			} finally {
				db.close(); 
			}
			
			this.finish();
		}
	}
}
