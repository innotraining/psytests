package com.example.szondi;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityFinalResult extends Activity implements OnClickListener {
	TextView finalPercent;
	Button buttonRight, buttonWrong;
	int right1, right2, right3, right4, right5;
	int wrong1, wrong2, wrong3, wrong4, wrong5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finalres);
		buttonRight = (Button)findViewById(R.id.buttonRight);
		buttonWrong = (Button)findViewById(R.id.buttonWrong);
		buttonRight.setOnClickListener(this);
		buttonWrong.setOnClickListener(this);
		Intent intent = getIntent();
		right1 = intent.getIntExtra("right1", -1);
		right2 = intent.getIntExtra("right2", -1);
		right3 = intent.getIntExtra("right3", -1);
		right4 = intent.getIntExtra("right4", -1);
		right5 = intent.getIntExtra("right5", -1);
		wrong1 = intent.getIntExtra("wrong1", -1);
		wrong2 = intent.getIntExtra("wrong2", -1);
		wrong3 = intent.getIntExtra("wrong3", -1);
		wrong4 = intent.getIntExtra("wrong4", -1);
		wrong5 = intent.getIntExtra("wrong5", -1);
		finalPercent = (TextView)findViewById(R.id.finalPercent);
		finalPercent.setText("Итоговый результат: "+right5
				+" правильных ответов и "+wrong5
				+" неправильных ответов, точность = "
				+(right5*100/(right5+wrong5))+"%");
    }
 
	public void onClick(View v) {
		Intent intent2;
		switch (v.getId()) {
	    case R.id.buttonRight:
	    	intent2 = buildRightIntent();
	    	startActivity(intent2);
	    	break;
	    case R.id.buttonWrong:
	    	intent2 = buildWrongIntent();
	    	startActivity(intent2);
	    	break;
	    default:
	    	break;
		}
	}
	
    public Intent buildRightIntent() {
        int[] values = new int[] { 
        		right1, 
        		right2-right1, 
        		right3-right2, 
        		right4-right3, 
        		right5-right4 };
        String[] bars = new String[] { 
        		"Первая минута",  
        		"Вторая минута", 
                "Третья минута", 
                "Четвёртая минута", 
                "Пятая минута" };
        int[] colors = new int[] { 
        		Color.BLUE, 
        		Color.GREEN, 
        		Color.MAGENTA, 
                Color.YELLOW, 
                Color.CYAN };
        CategorySeries series = new CategorySeries("Pie Chart");
        DefaultRenderer dr = new DefaultRenderer();
        for (int v=0; v<5; v++) {
            series.add(bars[v], values[v]);
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[v]);
            dr.addSeriesRenderer(r);
        }
        dr.setZoomButtonsVisible(true);
        dr.setZoomEnabled(true);
        dr.setChartTitleTextSize(20);
        return ChartFactory.getPieChartIntent(this, series, 
        		dr, "Корректурная проба");
    }
 
    public Intent buildWrongIntent() {
    	int[] values = new int[] { 
        		wrong1, 
        		wrong2-wrong1, 
        		wrong3-wrong2, 
        		wrong4-wrong3, 
        		wrong5-wrong4 };
        String[] bars = new String[] { 
        		"Первая минута",  
        		"Вторая минута", 
                "Третья минута", 
                "Четвёртая минута", 
                "Пятая минута" };
        int[] colors = new int[] { 
        		Color.BLUE, 
        		Color.GREEN,
        		Color.MAGENTA, 
                Color.YELLOW, 
                Color.CYAN };
        CategorySeries series = new CategorySeries("Pie Chart");
        DefaultRenderer dr = new DefaultRenderer();
        for (int v=0; v<5; v++) {
            series.add(bars[v], values[v]);
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[v]);
            dr.addSeriesRenderer(r);
        }
        dr.setZoomButtonsVisible(true);
        dr.setZoomEnabled(true);
        dr.setChartTitleTextSize(20);
        return ChartFactory.getPieChartIntent(this, series, 
        		dr, "Корректурная проба");
    }
}