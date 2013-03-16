package com.example.tester;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class RegistrationActivity extends Activity {
	public String username = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		Intent intent = getIntent();
		username = intent.getStringExtra(USER_SERVICE);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void deleteUser(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Удаление записи");
		builder.setMessage("Вы действительно хотите удалить запись пользователя "
				+ username + "?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// delete from database

				DbOpenHelper dbOpenHelper = new DbOpenHelper(
						RegistrationActivity.this);
				SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
				db.delete(DbOpenHelper.TABLE_NAME, "username" + " = ?",
						new String[] { username });
				db.close();
				finish();
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// nothing to do
			}
		});
		builder.show();
	}

	public void startTest(View view) {
		Intent intent = new Intent(getBaseContext(), TestActivity.class);
		intent.putExtra(USER_SERVICE, username);
		finish();
		startActivity(intent);
	}

	public void showResults(View view) {
		String query = "SELECT * FROM " + DbOpenHelper.TABLE_NAME
				+ " WHERE username" + " = ?";
		DbOpenHelper dbOpenHelper = new DbOpenHelper(RegistrationActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery(query, new String[] { username });

		AlertDialog.Builder builder = new AlertDialog.Builder(
				RegistrationActivity.this);

		cursor.moveToFirst();

		int hypertim = cursor.getInt(2);
		int cycloid = cursor.getInt(3);
		int labil = cursor.getInt(4);
		int as_nervous = cursor.getInt(5);
		int sensetive = cursor.getInt(6);
		int psyhantic = cursor.getInt(7);
		int shizoid = cursor.getInt(8);
		int epileptic = cursor.getInt(9);
		int isteroid = cursor.getInt(10);
		int unstable = cursor.getInt(11);
		int conform = cursor.getInt(12);
		cursor.close();
		db.close();
		
		typeResolver resolver = new typeResolver(); 
		
		builder.setTitle("Последний результат: " + resolver.resolve(new int[] {hypertim, cycloid, labil, as_nervous, sensetive, psyhantic, shizoid, epileptic, isteroid, unstable, conform}));
		builder.setMessage("hypertim: " + hypertim + "\n" +
				"cycloid: " + cycloid + "\n" +
				"labil: " + labil + "\n" + 
				"as_nervous: " + as_nervous + "\n" + 
				"sensetive: " + sensetive + "\n" +
				"psyhantic: " + psyhantic + "\n" +
				"shizoid: " + shizoid + "\n" +
				"epileptic: " + epileptic + "\n" +
				"isteroid: " + isteroid + "\n" +
				"unstable: " + unstable + "\n" +
				"conform: " + conform
				);
		builder.show();
	}
	public void drawGraph(View view) {
	    // init example series data  
		Intent intent = new Intent(getBaseContext(), DrawActivity.class);
		intent.putExtra(USER_SERVICE, username);
		startActivity(intent);
	    //layout.addView(graphView);
	}
}
