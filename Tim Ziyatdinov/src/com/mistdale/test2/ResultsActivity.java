package com.mistdale.test2;

import java.util.ArrayList;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends Activity {

	private ArrayList<Integer> answers;
	private ArrayList<Integer> results;
	private Resources res;
	private String[] features;
	private static final int numRecordsPerDay = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		// Show the Up button in the action bar.
		setupActionBar();
		
		initFields();
		
		Intent intent = getIntent();
		String caller = intent.getStringExtra("caller");
		if ( caller.equals("test") ) {
			// вызваны из теста
			answers = intent.getIntegerArrayListExtra(MainActivity.EXTRA_ANSWERS);
			CountResults();
			fillResults();
			saveResults();
			deleteOlderRecords();
			
		} else if ( caller.equals("menu")) {
			// вызваны из меню (ResultsMenuActivity)
			if (canLoadResults()) {
				deleteOlderRecords();
				fillResults();
			}
		} else {
			// вызваны из списка результатов (ResultsListActivity)
			String params = intent.getStringExtra("params");
			results = new ArrayList<Integer>();
			parseParams(params);
			fillResults();
		}
		
	}
	
	private Integer n(Integer num) {
		return (num == 0) ? 1 : 0;
	}
	
	private void CountResults() {
		results = new ArrayList<Integer>();

		// физическая агрессия
		results.add(answers.get(0) + answers.get(24) + answers.get(32) + 
					answers.get(47) + answers.get(54) + answers.get(61) + answers.get(67)
					+ n(answers.get(8)) + n(answers.get(16)) + n(answers.get(40)));
		// косвенная агрессия
		results.add(answers.get(1) + answers.get(17) + answers.get(33) + 
				answers.get(41) + answers.get(55) + answers.get(62)
				+ n(answers.get(9)) + n(answers.get(25)) + n(answers.get(48)));
		// раздражение
		results.add(answers.get(2) + answers.get(18) + answers.get(26) + 
				answers.get(42) + answers.get(49) + answers.get(56) +  answers.get(63) + answers.get(71)
				+ n(answers.get(10)) + n(answers.get(34)) + n(answers.get(68)));
		// негативизм
		results.add(answers.get(3) + answers.get(11) + answers.get(19) + 
				answers.get(22) + answers.get(35));
		// обида
		results.add(answers.get(4) + answers.get(12) + answers.get(20) + 
				answers.get(28) + answers.get(36) + answers.get(50) + answers.get(57)
				+ n(answers.get(43)));
		// подозрительность
		results.add(answers.get(5) + answers.get(13) + answers.get(21) + 
				answers.get(29) + answers.get(37) + answers.get(44) + answers.get(51) + answers.get(58)
				+ n(answers.get(69)) + n(answers.get(64)));
		// вербальная агрессия
		results.add(answers.get(6) + answers.get(14) + answers.get(27) + 
				answers.get(30) + answers.get(45) + answers.get(52) + 
				answers.get(59) + answers.get(70) + answers.get(72) 
				+ n(answers.get(38)) + n(answers.get(65)) + n(answers.get(73)) + n(answers.get(74)) );
		// чувство вины
		results.add(answers.get(7) + answers.get(15) + answers.get(23) + 
				answers.get(31) + answers.get(39) + answers.get(46) + answers.get(53)
				+ answers.get(60) + answers.get(66));
		// индекс враждебности
		results.add(results.get(4) + results.get(5));
		// индекс агрессивности
		results.add(results.get(0) + results.get(2) + results.get(6));
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
		getMenuInflater().inflate(R.menu.results, menu);
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
	
	public void homeScreen(View view) {
		finish();
	}
	
	private void fillResults() {
		/*EditText e;
		
		e = (EditText) findViewById(R.id.rEditText1);
		e.setText(results.get(0).toString());

		e = (EditText) findViewById(R.id.rEditText2);
		e.setText(results.get(1).toString());
		
		e = (EditText) findViewById(R.id.rEditText3);
		e.setText(results.get(2).toString());
		
		e = (EditText) findViewById(R.id.rEditText4);
		e.setText(results.get(3).toString());
		
		e = (EditText) findViewById(R.id.rEditText5);
		e.setText(results.get(4).toString());
		
		e = (EditText) findViewById(R.id.rEditText6);
		e.setText(results.get(5).toString());
		
		e = (EditText) findViewById(R.id.rEditText7);
		e.setText(results.get(6).toString());
		
		e = (EditText) findViewById(R.id.rEditText8);
		e.setText(results.get(7).toString());
		
		e = (EditText) findViewById(R.id.rEditText9);
		e.setText(results.get(8).toString());
		
		e = (EditText) findViewById(R.id.rEditText10);
		e.setText(results.get(9).toString());*/
		
		Intent intent = new Intent(this, ResultsVisualActivity.class);
		intent.putExtra("results_array", results);
		startActivity(intent);
		finish();
	}
	
	private void saveResults() {
		//SharedPreferences pref = getPreferences(MODE_PRIVATE);
		//SharedPreferences.Editor editor = pref.edit();
		//for (int i = 0; i < 10; ++i) {
			//editor.putInt(Integer.toString(i), results.get(i));
		//}
		//editor.commit();
		
		Integer now = (int) (System.currentTimeMillis() / 1000L);
		Integer now_day = (int) (System.currentTimeMillis() / (24*60*60*1000L) );
		
		Log.w("now_day = ", String.valueOf(now_day));
		
		String params = results.get(0).toString();
		for (int i = 1; i < 10; ++i) {
			params = params + ":" + results.get(i).toString();
		}
		
		String user = MainActivity.current_user;
		
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		try {
			ContentValues values = new ContentValues();
			values.put(TestDbContract.TableResults.COLUMN_NAME_USER, user);
			values.put(TestDbContract.TableResults.COLUMN_NAME_DATE, now);
			values.put(TestDbContract.TableResults.COLUMN_NAME_PARAMS, params);
			values.put(TestDbContract.TableResults.COLUMN_NAME_DATE_DAY, now_day);
			
			db.insert(TestDbContract.TableResults.TABLE_NAME, null, values);
		} finally {
			db.close();
		}
	}
	
	private void initFields() {
		res = getResources();
		features = res.getStringArray(R.array.features_array);
		
		TextView f;
		
		f = (TextView) findViewById(R.id.rTextView1);
		f.setText(features[0]);
		
		f = (TextView) findViewById(R.id.rTextView2);
		f.setText(features[1]);
		
		f = (TextView) findViewById(R.id.rTextView3);
		f.setText(features[2]);
		
		f = (TextView) findViewById(R.id.rTextView4);
		f.setText(features[3]);
		
		f = (TextView) findViewById(R.id.rTextView5);
		f.setText(features[4]);
		
		f = (TextView) findViewById(R.id.rTextView6);
		f.setText(features[5]);
		
		f = (TextView) findViewById(R.id.rTextView7);
		f.setText(features[6]);
		
		f = (TextView) findViewById(R.id.rTextView8);
		f.setText(features[7]);
		
		f = (TextView) findViewById(R.id.rTextView9);
		f.setText(features[8]);
		
		f = (TextView) findViewById(R.id.rTextView10);
		f.setText(features[9]);
	}
	
	private boolean canLoadResults() {
				
		results = new ArrayList<Integer>();
		
		String user = MainActivity.current_user;
		
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
			
		String[] projection = { TestDbContract.TableResults.COLUMN_NAME_PARAMS };
		String sortOrder = TestDbContract.TableResults.COLUMN_NAME_DATE + " DESC";
		
		String selection = TestDbContract.TableResults.COLUMN_NAME_USER + " = ?";
		String[] selectionArgs = new String[] { user };
		
		Cursor c = db.query(
				TestDbContract.TableResults.TABLE_NAME,
				projection, 
				selection, 
				selectionArgs, 
				null, 
				null,
				sortOrder);
		try {	
			if (c.moveToFirst()) {
				parseParams(c.getString(c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_PARAMS)));
				return true;
			} else {
				return false;
			}
		} finally {
			db.close();
			c.close();
		}
		
	}
	
	private Cursor outerGroupQuery(SQLiteDatabase db) {
		
		String user = MainActivity.current_user;
		
		String[] projection = {TestDbContract.TableResults.COLUMN_NAME_DATE_DAY, "COUNT (" 
								+ TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + ")" };
							
		
		String sortOrder = TestDbContract.TableResults.COLUMN_NAME_DATE + " DESC";
		
		String selection = TestDbContract.TableResults.COLUMN_NAME_USER + " = ?";
		String[] selectionArgs = new String[] { user };
		
		String groupBy = TestDbContract.TableResults.COLUMN_NAME_DATE_DAY;
		String having = "COUNT (" + TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + ") > 3";
		
		
		// Query to know how many of 
		Cursor c = db.query(
				TestDbContract.TableResults.TABLE_NAME,
				projection, 
				selection, 
				selectionArgs, 
				groupBy,
				having, 
				sortOrder);
		return c;
	}
	
	private Cursor innerGroupQuery(SQLiteDatabase db, Cursor c) {
		
		String user = MainActivity.current_user;
		
		String[] inProjection = {TestDbContract.TableResults.COLUMN_NAME_DATE,
								TestDbContract.TableResults.COLUMN_NAME_PARAMS};
			
		String sortOrder = TestDbContract.TableResults.COLUMN_NAME_DATE + " DESC";
		
		String inSelection = TestDbContract.TableResults.COLUMN_NAME_USER + " = ? AND " + 
							TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + " = ?";
		
		int someday = c.getInt(c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_DATE_DAY));
		Log.w("someday = ", String.valueOf(someday));
		
		String[] inSelectionArgs = new String[] { user, 
				String.valueOf(someday) };
		
		
		Cursor inner_c = db.query(
				TestDbContract.TableResults.TABLE_NAME,
				inProjection, 
				inSelection, 
				inSelectionArgs, 
				null, 
				null, 
				sortOrder);
		return inner_c;
	}
	
	
	private void deleteOlderRecords() {
		// TODO
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		Cursor c = outerGroupQuery(db);
		Cursor inner_c = null;
		
		try {
		
			//DateFormat format = DateFormat.getDateInstance();
			//SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
			
			if (c.moveToFirst()) {
				do {
					
					inner_c = innerGroupQuery(db, c);
					
					if (inner_c.moveToFirst()) {
						Integer currDate = inner_c.getInt(inner_c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_DATE));///
						
						for (int i = 0; i < numRecordsPerDay-1; ++i) {
							if (! inner_c.moveToNext()) break;
							currDate = inner_c.getInt(inner_c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_DATE));
						}
						
						int currDay = c.getInt(c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_DATE_DAY));
						
						String selection = TestDbContract.TableResults.COLUMN_NAME_DATE + " < ? AND " + 
						TestDbContract.TableResults.COLUMN_NAME_USER + " = ? AND " + 
						TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + " = ?";
						String[] selectionArgs = {currDate.toString(), MainActivity.current_user, String.valueOf(currDay)};
						db.delete(TestDbContract.TableResults.TABLE_NAME, selection, selectionArgs);
						
						// c.set Pos 4, delete where date < curr_date
					}
					
				} while (c.moveToNext());
			}
			
		} finally {
			db.close();
			c.close();
			if (inner_c != null) {
				inner_c.close();
			}
		}
	}
	
	private void parseParams(String params) {
		String[] paramsArray = params.split(":");

		for (int i = 0; i < 10; ++i) {
			results.add(Integer.valueOf(paramsArray[i]));
		}
	}
}
