package com.example.rusalovtest;


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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Screen_6 extends Activity {
	
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
		
		
		GraphViewSeries exampleSeries = new GraphViewSeries("Your score", new GraphViewSeriesStyle(Color.rgb(200, 50, 00), 3), new GraphViewData[] {  
			      new GraphViewData(1, scores[0])  
			      , new GraphViewData(2, scores[1])  
			      , new GraphViewData(3, scores[2])  
			      , new GraphViewData(4, scores[3])  
			      , new GraphViewData(5, scores[4])  
			      , new GraphViewData(6, scores[5])
			      , new GraphViewData(7, scores[6])  
			      , new GraphViewData(8, scores[7])  
			      , new GraphViewData(9, scores[8])  
			});  
		
		GraphViewSeries highSeries = new GraphViewSeries("High rate", new GraphViewSeriesStyle(Color.rgb(90, 250, 00), 3), new GraphViewData[] {  
		      new GraphViewData(1, 9)  
		      , new GraphViewData(2, 9)  
		      , new GraphViewData(3, 9)  
		      , new GraphViewData(4, 9)  
		      , new GraphViewData(5, 9)  
		      , new GraphViewData(6, 9)
		      , new GraphViewData(7, 9)  
		      , new GraphViewData(8, 9)  
		      , new GraphViewData(9, 9)  
		});  
		GraphViewSeries lowSeries = new GraphViewSeries("Low rate", new GraphViewSeriesStyle(Color.rgb(00, 50, 250), 3), new GraphViewData[] {  
		      new GraphViewData(1, 4)  
		      , new GraphViewData(2, 4)  
		      , new GraphViewData(3, 4)  
		      , new GraphViewData(4, 4)  
		      , new GraphViewData(5, 4)  
		      , new GraphViewData(6, 4)
		      , new GraphViewData(7, 4)  
		      , new GraphViewData(8, 4)  
		      , new GraphViewData(9, 4)  
		}); 
		
		GraphViewSeries point_1 = new GraphViewSeries(new GraphViewData[] {  
		      new GraphViewData(1, 12)    
		}); 
		
		GraphViewSeries point_2 = new GraphViewSeries(new GraphViewData[] {  
			      new GraphViewData(1, 0)    
		}); 
			
			  
		GraphView graphView = new LineGraphView( this, "GraphTest");
		graphView.addSeries(exampleSeries); // data 
		graphView.addSeries(highSeries);
		graphView.addSeries(lowSeries);
		graphView.addSeries(point_1);
		graphView.addSeries(point_2);
		graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "k"});  
		graphView.setVerticalLabels(new String[] {"12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"});  
		graphView.setShowLegend(true);  
		graphView.setLegendAlign(LegendAlign.BOTTOM);  
		graphView.setLegendWidth(200);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);  
		layout.addView(graphView);  

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
}
