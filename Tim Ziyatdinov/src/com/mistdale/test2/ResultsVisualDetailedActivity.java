package com.mistdale.test2;

import java.util.ArrayList;

import org.achartengine.GraphicalView;
import org.achartengine.model.SeriesSelection;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ResultsVisualDetailedActivity extends Activity {

	private Context context = this;
	private BarGraph bg = ResultsVisualActivity.bg;
	private AlertDialog.Builder ad;
	private GraphicalView dChartView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		ArrayList<Integer> results = intent.getIntegerArrayListExtra("results_array");
		
		dChartView = bg.getDetailedView(context, results);
		dChartView.setBackgroundColor(Color.BLACK);
		
		dChartView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 SeriesSelection seriesSelection = dChartView.getCurrentSeriesAndPoint();
		          if (seriesSelection == null) {
		            Toast.makeText(context, "Нажмите на столбец для подробного описания", Toast.LENGTH_SHORT).show();
		          } else {
		            dChartView.repaint();
		            
		            ad = new AlertDialog.Builder(context);
		    		int selected = seriesSelection.getPointIndex() + 1;
		    		switch (selected) {
		    		case 1: ad.setTitle("Физическая агрессия"); ad.setMessage(R.string.more_phys_agr); break;
		    		case 2: ad.setTitle("Косвенная агрессия"); ad.setMessage(R.string.more_indirect_agr); break;
		    		case 3: ad.setTitle("Раздражение"); ad.setMessage(R.string.more_irritation); break;
		    		case 4: ad.setTitle("Негативизм"); ad.setMessage(R.string.more_negativism); break;
		    		case 5: ad.setTitle("Обида"); ad.setMessage(R.string.more_offence); break;
		    		case 6: ad.setTitle("Подозрительность"); ad.setMessage(R.string.more_suspicion); break;
		    		case 7: ad.setTitle("Вербальная агрессия"); ad.setMessage(R.string.more_verbal_agr); break;
		    		case 8: ad.setTitle("Чувство вины"); ad.setMessage(R.string.more_guilt); break;
		    		}
		    		 // сообщение
		    		ad.setPositiveButton("Ок", null);
		    		ad.setCancelable(true);
		    		ad.show();
		          }
			}
		});
		
		setContentView(dChartView);
		
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
		getMenuInflater().inflate(R.menu.results_visual_detailed, menu);
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
