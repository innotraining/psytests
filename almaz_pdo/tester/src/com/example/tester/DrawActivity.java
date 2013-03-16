package com.example.tester;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;

public class DrawActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);

		Intent intent = getIntent();
		String username = intent.getStringExtra(USER_SERVICE);

		String query = "SELECT * FROM " + DbOpenHelper.TABLE_NAME
				+ " WHERE username" + " = ?";
		DbOpenHelper dbOpenHelper = new DbOpenHelper(DrawActivity.this);
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery(query, new String[] { username });

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

		int[] results = new int[] { hypertim, cycloid, labil, as_nervous,
				sensetive, psyhantic, shizoid, epileptic, isteroid, unstable,
				conform };
		GraphViewData[] data = new GraphViewData[results.length];

		for (int i = 0; i < results.length; ++i) {
			data[i] = new GraphViewData(i, results[i]);
		}

		GraphViewSeries resultSeries = new GraphViewSeries("График", null, data);
		
		//GraphViewSeries resultSeries = new GraphViewSeries("График", Color.rgb(200, 50, 0), data);
		
		GraphView graphView = new BarGraphView(this // context
				, "График результата" // heading
		);
		graphView.setHorizontalLabels(new String[] {"", "Г", "Ц", "Л", "А", "С", "П", "Ш", "Э", "И", "Н", "К", ""});
		graphView.setVerticalLabels(new String[] { "high", "middle", "low" });
		graphView.addSeries(resultSeries); // data

		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
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
		getMenuInflater().inflate(R.menu.draw, menu);
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
}
