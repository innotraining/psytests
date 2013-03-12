package com.example.rusalovtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Screen_0_3 extends Activity {
	private Button toTestAndResultButton;
	private Button deleteUserButton;
	private Button toMenuButton;
	private String userName;
	private DbOpenHelper dbOpenHelper;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_0_3);
		final Resources res = getResources();

		userName = getIntent().getStringExtra("userName");
		toTestAndResultButton = (Button) findViewById(R.id.toTestAndResult);
		deleteUserButton = (Button) findViewById(R.id.deleteUser);
		toMenuButton = (Button) findViewById(R.id.toMenuButton);
		String deleteUserAndAllHisData = res.getString(R.string.deleteUser) + " " + userName + " " + res.getString(R.string.andAllHisData);
		deleteUserButton.setText(deleteUserAndAllHisData);
		toTestAndResultButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Screen_0_3.this, Screen_1.class);
				intent.putExtra("userName", userName);
				startActivity(intent);
				finish();
			}
		});
		
		deleteUserButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(Screen_0_3.this);
				String areYouSureToDeleteUser = res.getString(R.string.areYouSureToDeleteUser);
				builder.setTitle(areYouSureToDeleteUser + " " + userName);
				String yes = res.getString(R.string.yes);
				builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
						   @Override
						   public void onClick(DialogInterface dialog, int which) {
							   dbOpenHelper = new DbOpenHelper(Screen_0_3.this);
							   SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
							   db.delete(DbOpenHelper.TABLE_NAME_USER, DbOpenHelper.USER + " = " + "'"+ userName + "'", null);
							   db.delete(DbOpenHelper.TABLE_NAME_RESULT,  DbOpenHelper.USER + " = " + "'"+ userName + "'", null);
							   db.close();
						   }
				});   
				String no = res.getString(R.string.no);
				builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
				builder.show();
			}
		});
		
		toMenuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Screen_0_3.this, Screen_0.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
