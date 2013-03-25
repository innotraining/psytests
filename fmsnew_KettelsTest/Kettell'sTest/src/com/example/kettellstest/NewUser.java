package com.example.kettellstest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.content.DialogInterface.OnCancelListener;

public class NewUser extends Activity {
	
	AlertDialog.Builder ad;
	AlertDialog.Builder ad_birth;
	Context context;
	final Calendar c = Calendar.getInstance();
	private int mYear = c.get(Calendar.YEAR);
	private int mMonth = c.get(Calendar.MONTH);
	private int mDay = c.get(Calendar.DAY_OF_MONTH);
	private TextView mDateDisplay;
	private Button mPickDate;
	private OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, 
                int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };
    
    private String strDay(int day) {
    	if (day < 10) return "0"+Integer.toString(day);
    	else return Integer.toString(day);
    }
    
    private String strMonth(int month) {
    	month++;
    	if (month < 10) return "0"+Integer.toString(month);
    	else return Integer.toString(month);
    }
    
    private void updateDisplay() {
    	mDateDisplay.setText(strDay(mDay) + "/" + strMonth(mMonth) + "/" + mYear);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		// Show the Up button in the action bar.
		setupActionBar();
		
		context = NewUser.this;
		ad = new AlertDialog.Builder(context);
		ad.setTitle("error");  
		ad.setMessage("user is already exist"); 
		ad.setCancelable(true);
		ad.setOnCancelListener(
			new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(context, "input another login",	Toast.LENGTH_LONG).show();
				}
			}
		);
		
		mDateDisplay = (TextView) findViewById(R.id.textView2);
	    mPickDate = (Button) findViewById(R.id.button3);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	new DatePickerDialog(NewUser.this, mDateSetListener, mYear, mMonth, mDay).show();
	        }
	    });
	    updateDisplay();
	}
	
	public void onDateSet(DatePicker view, int year, 
            int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
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
		getMenuInflater().inflate(R.menu.new_user, menu);
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
	
	public void onRegisterClick(View view) throws ParseException {
		//TODO add user form text field
		EditText loginField = (EditText)findViewById(R.id.inputLogin);
		
		String login = loginField.getText().toString();
		String birth_date;
        
		birth_date = Integer.toString(mYear) + "/" + strMonth(mMonth) + "/" + strDay(mDay);
		DatabaseHandler db = new DatabaseHandler(this);
		if (db.userExists(login)) {
			ad.show();
		}
		else {	
			
			db.addNode(login, new Attempt(-1));	// ONLY FOR DEBUG
			db.addLoginBirthDate(login, birth_date);
			Intent intent = new Intent(NewUser.this, MainMenu.class);
			intent.putExtra("login", login);
			NewUser.this.startActivity(intent);
			finish();
		}
		db.close();
    }
	
	public void onBackButtonClick(View view) {
		Intent intent = new Intent(NewUser.this, MainActivity.class);
		NewUser.this.startActivity(intent);	
		finish();
    }
	
}
