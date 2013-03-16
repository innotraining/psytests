package com.example.eysencktest;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LineGraphView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
	}
	GraphView graphView = new LineGraphView(this, "example") {  
   @Override  
   protected String formatLabel(double value, boolean isValueX) {  
      if (isValueX) {  
         // convert unix time to human time  
         return "hi";//dateTimeFormatter.format(new Date((long) value*1000));  
      } else return super.formatLabel(value, isValueX); // let the y-value be normal-formatted  
   }  
};  
		
		  
		LinearLayout layoutMy = (LinearLayout) findViewById(R.layout.activity_result);  
		//layoutMy.addView();  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
