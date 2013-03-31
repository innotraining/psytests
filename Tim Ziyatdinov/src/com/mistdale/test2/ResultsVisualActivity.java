package com.mistdale.test2;

import java.util.ArrayList;

import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class ResultsVisualActivity extends Activity {

	private GraphicalView mChartView;
	private Context context = this;
	public static BarGraph bg;
	private ArrayList<Integer> results;
	static final int agrL = 17, agrR = 25, hosL = 4, hosR = 10;
	
	//private DefaultRenderer mRenderer = new DefaultRenderer();
	//private CategorySeries mSeries = new CategorySeries("");
	
	private String getResultDescription() {
		int agr = results.get(9), hos = results.get(8);
		String res = "";
		if (agrL <= agr && agr <= agrR && hosL <= hos && hos <= hosR) { 
			res = getResources().getString(R.string.results_ok);
		} else {
			if (agr < agrL || hos < hosL) {
				res += getResources().getString(R.string.results_less_part1);
				if (agr >= agrL) {
					res += " " + getResources().getString(R.string.results_hostility);
				} else {
					res += " " + getResources().getString(R.string.results_agression);
					if (hos < hosL) {
						res += " è " + getResources().getString(R.string.results_hostility);
					}
				}
				res += getResources().getString(R.string.results_less_part2);
			}
			
			if (agr > agrR || hos > hosR) {
				res += " " + getResources().getString(R.string.results_greater_part1);
				if (agr <= agrR) {
					res += " " + getResources().getString(R.string.results_hostility);
				} else {
					res += " " + getResources().getString(R.string.results_agression);
					if (hos > hosR) {
						res += " è " + getResources().getString(R.string.results_hostility);
					}
				}
				
				res += getResources().getString(R.string.results_greater_part2);
			}
		}
		return res;
	}
	
	private LinearLayout buildMainLayout() {
		
		LinearLayout l = new LinearLayout(context);
		l.setOrientation(LinearLayout.VERTICAL);
		LinearLayout lInner = new LinearLayout(context);
		LinearLayout lInnerButton = new LinearLayout(context);
		lInnerButton.setOrientation(LinearLayout.HORIZONTAL);
		lInnerButton.setBackgroundColor(Color.BLACK);
		TextView tw = new TextView(context);
		//tw.setText(R.string.results_ok);
		tw.setText(getResultDescription());
		tw.setPadding(0, 10, 0, 10);
	
		// magic
		LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
		LinearLayout.LayoutParams tableParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0f);
		LinearLayout.LayoutParams tableBtnParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f);
		
		lInner.addView(mChartView);
		lInner.setBackgroundColor(Color.BLACK);
		tw.setBackgroundColor(Color.BLACK);
		tw.setTextColor(Color.WHITE);
		
		Button btnMore = new Button(context);
		btnMore.setText(R.string.button_more);
		Button btnBack = new Button(context);
		btnBack.setText(R.string.button_back);
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btnMore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// call activity with detailed diagram
				Intent intent = new Intent(context, ResultsVisualDetailedActivity.class);
				intent.putExtra("results_array", results);
				startActivity(intent);
				

			}
		});
		
		lInnerButton.addView(btnMore, tableBtnParams);
		lInnerButton.addView(btnBack, tableBtnParams);
		
		l.addView(lInner, linearParams);
		l.addView(tw, tableParams);
		l.addView(lInnerButton);
		
		return l;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_results_visual);
		//setContentView(new DiagramResultsView(this));
		
		Intent intent = getIntent();
		results = intent.getIntegerArrayListExtra("results_array");
		
		bg = new BarGraph();
		mChartView = bg.getMainView(this, results);
		
		LinearLayout l = buildMainLayout();
		setContentView(l);
		
		//Show the Up button in the action bar.
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
		getMenuInflater().inflate(R.menu.results_visual, menu);
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
