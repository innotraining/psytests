package ru.bt_enterprise.example.raven_test;


import java.util.Map;


import android.app.ListActivity;
import android.app.Activity;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView; 
import android.widget.ArrayAdapter; 
import android.widget.ListView;
import android.widget.TextView; 
import android.widget.Toast;

public class LoginActivity extends Activity {
	SharedPreferences sPref;	
	ListView lvU;

	private QuizUsers users;
	private Map<Integer, String> registeredUsers;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
//		userId = getIntent().getIntExtra("userId", -1);
		users = new QuizUsers(LoginActivity.this);
		registeredUsers = users.getUsers();
		final String[] registeredUsersValues = registeredUsers.values().toArray(new String[registeredUsers.size()]);

		
		sPref = getPreferences(MODE_PRIVATE);

		lvU = (ListView) findViewById(R.id.ListOfUsers);
		lvU.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    // создаем адаптер
	    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        android.R.layout.simple_list_item_1, registeredUsersValues);

	    // присваиваем адаптер списку
	    lvU.setAdapter(adapter);
	    lvU.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView parent, View clickedview, int position, long id)
	        {
	        	String selectedName = (String) ((TextView) clickedview).getText();
//	        	String selectedName = "example";

	        	
	        	sPref = getSharedPreferences("RavenQuizUser",MODE_PRIVATE);
	    		SharedPreferences.Editor editor = sPref.edit();
	    	    editor.putString("name", selectedName);
//	    	    editor.putString("sex", sex);
	    	    editor.putInt("day", users.getUserDay(selectedName));
	    	    editor.putInt("month", users.getUserMonth(selectedName));
	    	    editor.putInt("year", users.getUserYear(selectedName));

	    	    editor.commit();

	    	    goto_profile();
	            

	        }
	    });
	}

	public void goto_profile(){

	    Intent intent = new Intent(LoginActivity.this, UserOperationActivity.class);
	    startActivity(intent);
		finish();
	}

	public void OnLogInExitClick(View v){
		finish();
	}
	
	
	
}

