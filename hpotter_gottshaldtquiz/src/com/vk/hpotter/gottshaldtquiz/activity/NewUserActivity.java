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

		try {
			users.logIn(users.addUser(login.getText().toString()));
		} catch (IllegalArgumentException e) {
			SimpleConfirmDialog dialog = new SimpleConfirmDialog(
					NewUserActivity.this, R.string.user_exists_dialog,
					new Runnable() {
						@Override
						public void run() {
							users.logIn(users.getUserId(login.getText()
									.toString()));
							NewUserActivity.this.setResult(RESULT_OK);
							NewUserActivity.this.finish();
						}
					}, new Runnable() {
						@Override
						public void run() {
							NewUserActivity.this.setResult(RESULT_CANCELED);
							NewUserActivity.this.finish();
						}
					});
			dialog.show();
		}

		if (users.isLogged()) {
			setResult(RESULT_OK);
			finish();
		}

	}

}
