package ru.bt_enterprise.example.raven_test;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserOperationActivity extends Activity {

	SharedPreferences sPref;	
	ListView lvU;
	String name;
	private String sex;
	private int day;
	private int month;
	private int year;

	private QuizUsers users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_operation);
		sPref = getSharedPreferences("RavenQuizUser",MODE_PRIVATE);
		name = sPref.getString("name", "???");
//		sex = sPref.getString("sex", "???");
		day = sPref.getInt("day", 1);
		month = sPref.getInt("month", 1);
		year = sPref.getInt("year", 1);
		TextView tvUserName = (TextView)findViewById(R.id.textViewUserName);
		tvUserName.setText(name);
		TextView tvUserBDay = (TextView)findViewById(R.id.textViewUserBDay);
		tvUserBDay.setText( "Date : " + month + "/" + day + "/" + year );
		users = new QuizUsers(UserOperationActivity.this);
		
	}
	
	public void OnDeleteUserClick(View v){
		//2DO: ask whether he is sure
		try {
			users.deleteUser(name);
		} catch (IllegalArgumentException e) {
			Toast toast = Toast.makeText(getApplicationContext(), 
					   "Такого пользователя не существует!", Toast.LENGTH_SHORT); 
			toast.show();
		}
		finish();
	}

	public void OnResultsClick(View v){
		//2DO: put code here
		
	}
	public void OnBeginTestClick(View v){
		Intent intent = new Intent(UserOperationActivity.this, TutorialActivity.class);
        startActivity(intent);
        finish();
	}

	public void OnExitClick(View v){
		finish();
	}
	
}