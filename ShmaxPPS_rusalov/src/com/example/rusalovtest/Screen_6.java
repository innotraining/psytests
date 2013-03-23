package com.example.rusalovtest;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;



import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Screen_6 extends Activity {
	final String LOG_TAG = "myLogs";
	
	private String userName;
	private int[] scores;
	private Resources res;
	private Button backButton;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_6);
		userName = getIntent().getStringExtra("userName");
		scores = getIntent().getIntArrayExtra("scores");
		makeChart();
		makeTable();
		
		backButton = (Button) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
		        finish();
			}
			
    	});
	}
	
	public void makeTable(){
		res = getResources();
		
		TextView textView12 = (TextView) findViewById(R.id.textView12);
		TextView textView22 = (TextView) findViewById(R.id.textView22);
		TextView textView32 = (TextView) findViewById(R.id.textView32);
		TextView textView42 = (TextView) findViewById(R.id.textView42);
		TextView textView52 = (TextView) findViewById(R.id.textView52);
		TextView textView62 = (TextView) findViewById(R.id.textView62);
		TextView textView72 = (TextView) findViewById(R.id.textView72);
		TextView textView82 = (TextView) findViewById(R.id.textView82);
		TextView textView92 = (TextView) findViewById(R.id.textView92);
		
		textView12.setText(Integer.toString(scores[0]));
		textView22.setText(Integer.toString(scores[1]));
		textView32.setText(Integer.toString(scores[2]));
		textView42.setText(Integer.toString(scores[3]));
		textView52.setText(Integer.toString(scores[4]));
		textView62.setText(Integer.toString(scores[5]));
		textView72.setText(Integer.toString(scores[6]));
		textView82.setText(Integer.toString(scores[7]));
		textView92.setText(Integer.toString(scores[8]));
		
		TextView textView13 = (TextView) findViewById(R.id.textView13);
		TextView textView23 = (TextView) findViewById(R.id.textView23);
		TextView textView33 = (TextView) findViewById(R.id.textView33);
		TextView textView43 = (TextView) findViewById(R.id.textView43);
		TextView textView53 = (TextView) findViewById(R.id.textView53);
		TextView textView63 = (TextView) findViewById(R.id.textView63);
		TextView textView73 = (TextView) findViewById(R.id.textView73);
		TextView textView83 = (TextView) findViewById(R.id.textView83);
		TextView textView93 = (TextView) findViewById(R.id.textView93);
		
		if(isHigh(scores[0])) textView13.setText(res.getText(R.string.high));
		if(isMedium(scores[0])) textView13.setText(res.getText(R.string.medium));
		if(isLow(scores[0])) textView13.setText(res.getText(R.string.low));
		
		if(isHigh(scores[1])) textView23.setText(res.getText(R.string.high));
		if(isMedium(scores[1])) textView23.setText(res.getText(R.string.medium));
		if(isLow(scores[1])) textView23.setText(res.getText(R.string.low));
		
		if(isHigh(scores[2])) textView33.setText(res.getText(R.string.high));
		if(isMedium(scores[2])) textView33.setText(res.getText(R.string.medium));
		if(isLow(scores[2])) textView33.setText(res.getText(R.string.low));
		
		if(isHigh(scores[3])) textView43.setText(res.getText(R.string.high));
		if(isMedium(scores[3])) textView43.setText(res.getText(R.string.medium));
		if(isLow(scores[3])) textView43.setText(res.getText(R.string.low));
		
		if(isHigh(scores[4])) textView53.setText(res.getText(R.string.high));
		if(isMedium(scores[4])) textView53.setText(res.getText(R.string.medium));
		if(isLow(scores[4])) textView53.setText(res.getText(R.string.low));
		 
		if(isHigh(scores[5])) textView63.setText(res.getText(R.string.high));
		if(isMedium(scores[5])) textView63.setText(res.getText(R.string.medium));
		if(isLow(scores[5])) textView63.setText(res.getText(R.string.low));
		
		if(isHigh(scores[6])) textView73.setText(res.getText(R.string.high));
		if(isMedium(scores[6])) textView73.setText(res.getText(R.string.medium));
		if(isLow(scores[6])) textView73.setText(res.getText(R.string.low));
		
		if(isHigh(scores[7])) textView83.setText(res.getText(R.string.high));
		if(isMedium(scores[7])) textView83.setText(res.getText(R.string.medium));
		if(isLow(scores[7])) textView83.setText(res.getText(R.string.low));
		
		if(isHigh(scores[8])) textView93.setText(res.getText(R.string.high));
		if(isMedium(scores[8])) textView93.setText(res.getText(R.string.medium));
		if(isLow(scores[8])) textView93.setText(res.getText(R.string.low));


	}
	
	public void makeChart(){
		
		TimeSeries series = new TimeSeries("your results");
		for(int i = 0; i < scores.length; i++){
			series.add(i+1, scores[i]);
		}
		
		TimeSeries lowBarrierSeries = new TimeSeries("low barrier");
		lowBarrierSeries.add(0, 4);
		lowBarrierSeries.add(10, 4);
		
		TimeSeries highBarrierSeries = new TimeSeries("high barrier");
		highBarrierSeries.add(0, 9);
		highBarrierSeries.add(10, 9);
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(lowBarrierSeries);
		dataset.addSeries(highBarrierSeries);
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.WHITE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setLineWidth(3f);
		renderer.setFillPoints(true);
		
		XYSeriesRenderer rendererLow = new XYSeriesRenderer();
		rendererLow.setColor(Color.YELLOW);
		rendererLow.setFillPoints(true);
		
		XYSeriesRenderer rendererHigh = new XYSeriesRenderer();
		rendererHigh.setColor(Color.RED);
		rendererHigh.setFillPoints(true);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(rendererLow);
		mRenderer.addSeriesRenderer(rendererHigh);
		
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(12);
		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(10);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setPanEnabled(false, false);
		mRenderer.setZoomEnabled(false, false);
		mRenderer.setPointSize(15f);
		mRenderer.setXLabels(10);
		mRenderer.setYLabels(12);
		mRenderer.addYTextLabel(0, "0");
		mRenderer.addYTextLabel(12, "12");
		mRenderer.setShowGridX(true);
		mRenderer.setShowGridY(true);
		
		
		mRenderer.setClickEnabled(true);
		mRenderer.setSelectableBuffer(10);
		
		final GraphicalView lineChart = ChartFactory.getLineChartView(this, dataset, mRenderer);
		
		lineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	SeriesSelection seriesSelection = lineChart.getCurrentSeriesAndPoint();
            	if (seriesSelection != null) {
            		int num = seriesSelection.getSeriesIndex();
            		if(num == 0){
            			int xValue = (int) seriesSelection.getXValue();
            			viewDetailsCharacter(xValue);
            		}
                }
            }
          });
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);  
		layout.addView(lineChart); 

	}
	
	
	
	public Boolean isHigh(int cur){	
		return cur >= 9;
	}
	
	public Boolean isLow(int cur){	
		return cur <= 4;
	}
    
	public Boolean isMedium(int cur){	
		return (cur <= 8) && (cur >= 5);
	}
	
	public void viewDetailsCharacter(int xValue){
		Intent intent = new Intent(Screen_6.this, Screen_7.class);
		intent.putExtra("xValue", xValue);
		startActivity(intent);
	}
}
