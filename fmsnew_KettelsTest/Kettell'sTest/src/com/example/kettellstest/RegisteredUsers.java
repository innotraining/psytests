package com.example.kettellstest;

import java.util.List;
import java.util.ListIterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisteredUsers extends Activity {

	String login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered_users);
	
		DatabaseHandler db = new DatabaseHandler(this);
		List<String> logins = db.getAllLogins();
		// building logins list
		ListView listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisteredUsers.this, android.R.layout.simple_list_item_1, logins);
		listView.setAdapter(adapter);
		
		final List<String> final_logins = logins;
		listView.setOnItemClickListener(
			new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					int index = 1;
					ListIterator<String> it = final_logins.listIterator();
					while (index <= position) {
						index++;
						it.next();
					}
					login = it.next();
					TextView loginField = (TextView)findViewById(R.id.textView2);
					loginField.setText(login);
				}
			}
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registered_users, menu);
		return true;
	}

	public void onOKClick(View view) {
		Intent intent = new Intent(RegisteredUsers.this, MainMenu.class);
		intent.putExtra("login", login);
		RegisteredUsers.this.startActivity(intent);
		finish();
    }
	
	public void onQuitButtonClick(View view) {
    	finish();
    	System.exit(0);
    }
		
}
