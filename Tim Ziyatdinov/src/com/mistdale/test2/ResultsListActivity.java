package com.mistdale.test2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;

public class ResultsListActivity extends Activity {
	
	abstract class MyOnClickListener implements OnClickListener {
		public abstract void setParams(String param);
		
	};
	
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_list);
		// Show the Up button in the action bar.
		setupActionBar();
		
		fillResults();
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
		getMenuInflater().inflate(R.menu.results_list, menu);
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
	
	private Cursor outerGroupQuery(SQLiteDatabase db) {
		
		String user = MainActivity.current_user;
		
		String[] projection = {TestDbContract.TableResults.COLUMN_NAME_DATE_DAY, "COUNT (" 
								+ TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + ")" };
							
		
		String sortOrder = TestDbContract.TableResults.COLUMN_NAME_DATE + " DESC";
		
		String selection = TestDbContract.TableResults.COLUMN_NAME_USER + " = ?";
		String[] selectionArgs = new String[] { user };
		
		String groupBy = TestDbContract.TableResults.COLUMN_NAME_DATE_DAY;
		//String having = "COUNT (" + TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + ") > 3";
		
		
		// Query to know how many of 
		Cursor c = db.query(
				TestDbContract.TableResults.TABLE_NAME,
				projection, 
				selection, 
				selectionArgs, 
				groupBy, null, 
				//having, 
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
	
	private void xmlLayoutMess(Cursor inner_c, TableRow rowDate, DateFormat format, SimpleDateFormat sFormat) {
		
		int iDate = inner_c.getInt(inner_c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_DATE));
		String paramsString = inner_c.getString(inner_c.getColumnIndex(TestDbContract.TableResults.COLUMN_NAME_PARAMS));
		
		MyOnClickListener l = new MyOnClickListener() {
			
			private String paramsS;
			
			public void setParams(String param) {
				paramsS = param;
			}
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(context, ResultsActivity.class);
				intent.putExtra("params", paramsS);
				intent.putExtra("caller", "results_list_activity");
				startActivity(intent);
			}
			
		};
		
		l.setParams(paramsString);
		
		Date date = new Date((long)iDate*1000);
		
		
		//String sDate = format.format(date) + " " + sFormat.format(date);;
		String sDate = " " + sFormat.format(date);;
		
		
		if (inner_c.isFirst()) {
			// Base column: e.g. 14.06.1992
			
			TextView baseTitle = new TextView(this);  
		    baseTitle.setText(format.format(date)+":");  
		    baseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		    baseTitle.setTypeface(null, Typeface.BOLD);
		    
		    //title.setGravity(Gravity.CENTER); 
		    
		    TableRow.LayoutParams baseParams = new TableRow.LayoutParams();
		    baseParams.setMargins(10, 10, 0, 10);
		    
		    rowDate.addView(baseTitle, baseParams);
		}
		
		
		TextView title = new TextView(this);  
		    //title.setText(sDate);
			SpannableString content = new SpannableString(sDate);
			content.setSpan(new UnderlineSpan(), 0, sDate.length(), 0);
			title.setText(content);
		
		    title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		    //title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
		    //title.setGravity(Gravity.CENTER); 
		    
		    TableRow.LayoutParams params = new TableRow.LayoutParams();
		    params.setMargins(0, 10, 7, 10);
		    
		    title.setOnClickListener(l);
		    
		rowDate.addView(title, params);
		//rowDate.setOnClickListener(l);
		
	}
	
	private void fillResults() {
		
		TestDbHelper mDbHelper = new TestDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		Cursor c = outerGroupQuery(db);
		Cursor inner_c = null;
		
		try {
		
			DateFormat format = DateFormat.getDateInstance();
			SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
			
			if (c.moveToFirst()) {
				do {
					
					inner_c = innerGroupQuery(db, c);
					
					TableLayout layout = (TableLayout) findViewById(R.id.table_results_layout);
					// form a Row
					TableRow rowDate = new TableRow(this);
					if (inner_c.moveToFirst()) {
						
						do {
							xmlLayoutMess(inner_c, rowDate, format, sFormat);
						} while (inner_c.moveToNext());
						
						layout.addView(rowDate);
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

}
