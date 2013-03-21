package com.congnitive.test.mmpitest.activities;

import java.util.UUID;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.QuizResult.QuizEnty;
import com.congnitive.test.mmpitest.utilities.Utility;

public class ViewResultOfTheQuiz extends Activity {
	private UUID userId;
	private QuizResult quizResult;
	private GraphicalView mChart;
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	private XYSeries mCurrentSeries;
	private XYSeriesRenderer mCurrentRenderer;
	int num = 0;

	private void initChart() {
		mCurrentSeries = new XYSeries("Очки шкалы");
		mDataset.addSeries(mCurrentSeries);
		mCurrentRenderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(mCurrentRenderer);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setMarginsColor(Color.WHITE);
		mRenderer.setBarSpacing(1f);
		mRenderer.setXLabels(0);
		mRenderer.setShowGridX(true);
		mRenderer.setClickEnabled(true);
		mRenderer.setLabelsTextSize(20);
		mRenderer.setXLabelsColor(Color.BLACK);
		mRenderer.setYLabelsColor(0, Color.BLACK);
		mRenderer.setXAxisMax(15);
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(100);
	}

	private void addSampleData() {
		int num = 0;
		for (QuizEnty it : quizResult.getResults()) {
			mCurrentSeries.add(num + 2, it.getSkilLevel());
			mRenderer.addXTextLabel(num + 2, it.getShortName());
			num++;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp);
		userId = UUID.fromString(getIntent()
				.getStringExtra(Utility.USER_ID_TAG));
		quizResult = (QuizResult) getIntent().getSerializableExtra(
				Utility.QUIZ_RESULT_TAG);
	}

	protected void onResume() {
		super.onResume();
		LinearLayout layout = (LinearLayout) findViewById(R.id.result_layout);
		if (mChart == null) {
			initChart();
			addSampleData();
			mChart = ChartFactory.getBarChartView(this, mDataset, mRenderer,
					Type.DEFAULT);
			layout.addView(mChart);
		} else {
			mChart.repaint();
		}
		mChart.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				SeriesSelection seriesSelection = mChart
						.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
				} else {
					int num = seriesSelection.getPointIndex();
					Intent intent = new Intent(getApplicationContext(),
							ViewScaleInfoActivity.class);
					intent.putExtra(Utility.SCALE_RESULT_TAG, quizResult
							.getResults().get(num).getSkillName()
							+ "\n\n\n"
							+ quizResult.getResults().get(num).getDescribtion());
					startActivity(intent);
				}
				return false;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(),
					ViewResultActivity.class);
			intent.putExtra(Utility.USER_ID_TAG, userId.toString());
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
