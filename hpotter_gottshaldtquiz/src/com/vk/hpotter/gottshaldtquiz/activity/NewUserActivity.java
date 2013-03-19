package com.vk.hpotter.gottshaldtquiz.activity;

import com.vk.hpotter.gottshaldtquiz.R;
import com.vk.hpotter.gottshaldtquiz.storage.QuizUsers;
import com.vk.hpotter.gottshaldtquiz.util.SimpleConfirmDialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class NewUserActivity extends Activity {

	private QuizUsers users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);

		users = new QuizUsers(NewUserActivity.this);
	}

	public void createUserButtonClickHandler(View view) {
		final TextView login = (TextView) findViewById(R.id.userLogin);

		long id = -1;
		try {
			id = users.addUser(login.getText().toString());
		} catch (IllegalArgumentException e) {
			SimpleConfirmDialog dialog = new SimpleConfirmDialog(
					NewUserActivity.this, R.string.user_exists_dialog,
					new Runnable() {
						@Override
						public void run() {
							long id = users.getUserId(login.getText()
									.toString());
							users.logOff();
							users.logIn(id);
							Intent quizActivity = new Intent(
									NewUserActivity.this, QuizActivity.class);
							startActivity(quizActivity);
							NewUserActivity.this.finish();
						}
					}, new Runnable() {
						@Override
						public void run() {
						}
					});
			dialog.show();
		}

		if (id > 0) {
			users.logOff();
			users.logIn(id);
			Intent quizActivity = new Intent(NewUserActivity.this,
					QuizActivity.class);
			startActivity(quizActivity);
			NewUserActivity.this.finish();
		}
	}

}
