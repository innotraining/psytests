package com.congnitive.test.mmpitest.activities;

import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.User;
import com.congnitive.test.mmpitest.utilities.Utility;

public class UserActionActivity extends Activity {
	private UUID userId;
	private User user;
	private AlertDialog.Builder ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_action);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		user = Utility.getDataBase().getUserById(this, userId);
		((TextView) findViewById(R.id.UserActionDeleteUser))
				.setText(getText(R.string.delete_user) + " " + user.getName()
						+ " " + getText(R.string.and_his_results));

		ad = new AlertDialog.Builder(UserActionActivity.this);
		ad.setTitle(getText(R.string.user_deleting));
		ad.setMessage(getText(R.string.do_you_want_to_del) + " "
				+ user.getName());
		ad.setPositiveButton(getText(R.string.yes), new OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				Utility.getDataBase().removeUser(UserActionActivity.this,
						userId);
				Intent intent = new Intent(UserActionActivity.this,
						RegistrationActivity.class);
				startActivity(intent);
				finish();
			}
		});
		ad.setNegativeButton(getText(R.string.no), new OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
			}
		});
		// ad.setCancelable(true);
		// ad.setOnCancelListener(new OnCancelListener() {
		// public void onCancel(DialogInterface dialog) {
		// }
		// });
	}

	public void onUserActionGoTestClick(View v) {
		Intent intent = new Intent(UserActionActivity.this,
				MainMenuActivity.class);
		intent.putExtra(Utility.USER_ID_TAG, userId.toString());
		startActivity(intent);
		finish();
	}

	public void onUserActionReturnClick(View v) {
		Intent intent = new Intent(UserActionActivity.this,
				ChooseExistingUserActivity.class);
		startActivity(intent);
		finish();
	}

	public void onUserActionDeleteUserClick(View v) {
		ad.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					RegistrationActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
