package com.example.kettellstest;

import java.text.ParseException;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class Results extends Activity {

	static String login = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = Results.this.getIntent();
		login = intent.getStringExtra("login");
		
		DatabaseHandler db = new DatabaseHandler(this);
		List<String> attempts;
		try {
			attempts = db.getAllUserAttempts(login);
			attempts.remove(0);
			// building attempts list
			ListView listView = (ListView) findViewById(R.id.listView1);
			//Toast.makeText(this, Integer.toString(attempts.size()), Toast.LENGTH_LONG).show();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(Results.this, android.R.layout.simple_list_item_1, attempts);
			listView.setAdapter(adapter);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void onMainMenuClick(View view) {
		Intent intent = new Intent(Results.this, MainMenu.class);
		intent.putExtra("login", login);
		Results.this.startActivity(intent);
		finish();
    }
	
}
