package ru.yandex.quiz.mkhaikin.activitiy;

import java.util.TreeMap;

import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.db.Users;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectUserActivity extends Activity {
	private TreeMap<Integer, String> userList;
	String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select_user);
	//spinner1	
		Users u = new Users(SelectUserActivity.this);
		userList = (TreeMap<Integer,String>)u.getUsers();
		String[] usernames = userList.values().toArray(new String[userList.size()]);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, usernames);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        username = (String)parent.getItemAtPosition(pos);
		        Button confirmButton = (Button)findViewById(R.id.button2);
		        confirmButton.setEnabled(true);
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_user, menu);
		return true;
	}
	
	public void onClickReturn(View view){
		Intent k = new Intent(SelectUserActivity.this, StartActivity.class);
		startActivity(k);
		finish();
	}
	public void onClickConfirm(View view){
		Users u = new Users(SelectUserActivity.this);
		int id = u.getUserId(username);
		u.logOff();
		u.logIn(id);
		Intent k = new Intent(SelectUserActivity.this, UserManagementActivity.class);
		startActivity(k);
		finish();
	}
}
