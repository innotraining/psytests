package ru.yandex.quiz.mkhaikin.activitiy;


import ru.yandex.quiz.mkhaikin.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import ru.yandex.quiz.mkhaikin.db.Users;
import ru.yandex.quiz.mkhaikin.frontend.DialogWrapper;

public class UserManagementActivity extends Activity {
	public Users users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_management);
		users = new Users(UserManagementActivity.this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_management, menu);
		return true;
	}

	public void onClickProceedToQuiz (View view){
		Intent i = new Intent(UserManagementActivity.this, MainMenuActivity.class);
		startActivity(i);
		finish();
	}
	
	public void onClickShowDeleteDialogue(View view){
		DialogWrapper confirmDeletingDialog = new DialogWrapper(UserManagementActivity.this, 
				R.string.Confirm_Deleting_User,
				new Runnable() {
					
					@Override
					public void run() {
						users.deleteUser(users.getCurrentUserId());
						Intent i = new Intent(UserManagementActivity.this, StartActivity.class);
						startActivity(i);
						finish();
						
					}
				},
				new Runnable() {
					
					@Override
					public void run() {
						//nothing to do here, move along
					}
				});
		confirmDeletingDialog.show();
	}
}
