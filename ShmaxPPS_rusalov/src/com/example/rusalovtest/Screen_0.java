package com.example.rusalovtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Screen_0 extends Activity {
	private Button exitButton;
	private Button registeredButton;
	private Button newUserButton;
	private DbOpenHelper dbOpenHelper;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_0);
		final Resources res = getResources();

		
        
		exitButton = (Button) findViewById(R.id.exitButton);
		exitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String goodBay = res.getString(R.string.goodBay);
				Toast.makeText(getApplicationContext(), goodBay, Toast.LENGTH_LONG).show();
				finish();
			}
			
    	});
		
		
		newUserButton = (Button) findViewById(R.id.newUserButton);
		newUserButton.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Screen_0.this);
				String writeDownYourName = res.getString(R.string.writeDownYourName);
				builder.setTitle(writeDownYourName);
				final LayoutInflater li = Screen_0.this.getLayoutInflater();
				final View view = li.inflate(R.layout.dialog_signin, null);
				builder.setView(view);
				String makeUser = res.getString(R.string.makeUser);
				builder.setPositiveButton(makeUser, new DialogInterface.OnClickListener() {
						   @Override
						   public void onClick(DialogInterface dialog, int which) {
							   dbOpenHelper = new DbOpenHelper(Screen_0.this);
							   SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
						       ContentValues cv = new ContentValues();
						       EditText cur = (EditText) view.findViewById(R.id.userText);
						       String userName = cur.getText().toString();
						       if(userName.equals("")){
						    	   String writeOrChoose = res.getString(R.string.writeOrChoose);
						    	   Toast.makeText(getApplicationContext(), writeOrChoose, Toast.LENGTH_LONG).show();
						       }else{
						    	   cv.put(DbOpenHelper.USER, userName);
							       db.insert(DbOpenHelper.TABLE_NAME_USER, null, cv);
							       Intent intent = new Intent(Screen_0.this, Screen_1.class);
							       intent.putExtra("userName", userName);
							       startActivity(intent);
							       finish();
						       }
						       db.close();
						   }
				});   
				String notMake = res.getString(R.string.notMake);
				builder.setNegativeButton(notMake, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
				builder.show();
			}
		});
		
		registeredButton = (Button) findViewById(R.id.registeredButton);
		registeredButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
					case R.id.registeredButton:
						Intent myIntent = new Intent(Screen_0.this, Screen_0_2.class);
						startActivity(myIntent);
						finish();
						break;
				}
			}
		});
	}
}




