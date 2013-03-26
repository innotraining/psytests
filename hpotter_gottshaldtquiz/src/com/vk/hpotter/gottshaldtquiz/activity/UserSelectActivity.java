package com.vk.hpotter.gottshaldtquiz.activity;

import java.util.Map;

import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserSelectActivity extends Activity {

	private QuizUsers users;
	private Map<Integer, String> registeredUsers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_select);
		
		setResult(RESULT_CANCELED);

		users = new QuizUsers(UserSelectActivity.this);
		registeredUsers = users.getUsers();
		final String[] registeredUsersValues = registeredUsers.values()
				.toArray(new String[registeredUsers.size()]);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, registeredUsersValues);

		ListView listView = (ListView) findViewById(R.id.registeredUsersList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int id = users.getUserId(registeredUsersValues[arg2]);
				users.logIn(id);
				
				UserSelectActivity.this.setResult(RESULT_OK);
				UserSelectActivity.this.finish();
			}
		});
	}

}
