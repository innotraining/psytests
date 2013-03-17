package com.example.kettellstest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
		ad_birth = new AlertDialog.Builder(context);
		ad_birth.setTitle("error");  
		ad_birth.setMessage("date format is wrong"); 
		ad_birth.setCancelable(true);
		ad_birth.setOnCancelListener(
			new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(context, "date format should be \"yyyy/MM/dd\"",	Toast.LENGTH_LONG).show();
				}
			}
		);
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
	
	public void onRegisterClick(View view) {
		//TODO add user form text field
		EditText loginField = (EditText)findViewById(R.id.inputLogin);
		EditText dateField = (EditText)findViewById(R.id.editText1);
		
		String login = loginField.getText().toString();
		String date = dateField.getText().toString();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date birth_date;
		try {
			birth_date = dateFormat.parse(date);
		} catch (ParseException e) {
			ad_birth.show();	
			return;
		}
        
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
	
	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
	
}
