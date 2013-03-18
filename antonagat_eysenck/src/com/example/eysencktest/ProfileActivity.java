package com.example.eysencktest;
import java.sql.Date;
import java.util.ArrayList;
import com.example.eysencktest.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity {
	ArrayList<String> fieldsUser = new ArrayList<String>();
	String[] colName = {"date", "fakseM", "introextraM", "stableM"};
	String name = null;
	AlertDialog.Builder ad;
	Context context;
	ArrayList<String> date = new ArrayList<String>();
	ArrayList<Integer> falseM = new ArrayList<Integer>();
	ArrayList<Integer> introextroM = new ArrayList<Integer>();
	ArrayList<Integer> stableM = new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ListView listView = (ListView) findViewById(R.id.listView1);	
		DbOpenHelper dbOpenHelper = new DbOpenHelper(ProfileActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor userCursor=null;
		Intent intent = getIntent(); 
		name  = intent.getStringExtra("name").toString();
		userCursor = db.rawQuery("SELECT date, falseM, introextroM, stableM FROM users  WHERE login = ? and fix = ? ", new String[] {name, "norm"});
		userCursor.moveToFirst();
		if(!userCursor.isAfterLast()) {
			do {
				falseM.add(userCursor.getInt(1));
				introextroM.add(userCursor.getInt(2));
				stableM.add(userCursor.getInt(3));				
				date.add(userCursor.getString(0));

				fieldsUser.add(userCursor.getString(0));
			} while (userCursor.moveToNext());
		}
		userCursor.close();
		db.close(); 

		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fieldsUser );

		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setAdapter(adapter); 

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
					long id) {
				TextView itemname = (TextView) itemClicked;
				
				 int strId= position;//itemname.getLineCount();//date.indexOf(strFind);
				
				Intent intent = new Intent();
				intent.setClass(ProfileActivity.this, ResultActivity.class);
				intent.putExtra("false", falseM.get(strId));
				intent.putExtra("extrointro", introextroM.get(strId)); 
				intent.putExtra("nero",stableM.get(strId) ); 
				startActivity(intent);	
				finish();
			}
		});		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	public void deletefixRowAll(){
		DbOpenHelper dbOpenHelper = new DbOpenHelper(ProfileActivity.this);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.delete(DbOpenHelper.TABLE_NAME, DbOpenHelper.LOGIN+ " =? " , new String[]{name});
		db.close();
		/*Intent intent = new Intent();
		intent.setClass(ProfileActivity.this, ListActivity.class);
		startActivity(intent);			*/
		
	}
	public void onClickNewTest(View view){
		Intent intent = new Intent();
		intent.setClass(ProfileActivity.this, TestActivity.class);
		intent.putExtra("name", name.toString());
		startActivity(intent);	
		finish();		
	}
	
	public void dialogAlert(View view){
		context = ProfileActivity.this;
		ad = new AlertDialog.Builder(context);
		ad.setMessage(getResources().getString(R.string.deleteUserQuestion) + name + "?");
		ad.setPositiveButton("Да", new OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				deletefixRowAll();
				finish();
			}
		});
		ad.setNegativeButton("Нет", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int arg2) {
				Toast.makeText(context, "Вы ничего не изменили",
						Toast.LENGTH_LONG).show();
			}
		} );
		ad.show();
	}
	
}
