package ru.yandex.quiz.mkhaikin.activitiy;

import ru.yandex.quiz.mkhaikin.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ViewDetailedResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view_detailed_result);
		
		Intent k = getIntent();
		int measure = k.getIntExtra("Measure", 0);
		
		TextView title = (TextView)findViewById(R.id.textView1);
		TextView details = (TextView)findViewById(R.id.textView2);
		if (measure <= 9){
			title.setText(R.string.Text_not_depressed_title);
			details.setText(R.string.Text_not_depressed_description);
		} else if (measure >= 10 && measure <= 15){
			title.setText(R.string.Text_lightly_depressed_title);
			details.setText(R.string.Text_lightly_depressed_description);
		} else if (measure >= 16 && measure <= 19){
			title.setText(R.string.Text_moderately_depressed_title);
			details.setText(R.string.Text_moderately_depressed_description);
		} else if (measure >= 20 && measure <= 29){
			title.setText(R.string.Text_heavily_depressed_title);
			details.setText(R.string.Text_heavily_depressed_description);
		} else if (measure >= 30){
			title.setText(R.string.Text_extremely_depressed_title);
			details.setText(R.string.Text_extremely_depressed_description);
		} 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_detailed_result, menu);
		return true;
	}
	
	public void onClickReturnToMainMenu(View view){
		Intent k = new Intent(ViewDetailedResultActivity.this, MainMenuActivity.class);
		startActivity(k);
		finish();
	}
	public void onClickReturnToStatistics(View view){
		Intent k = new Intent(ViewDetailedResultActivity.this, StatisticsActivity.class);
		startActivity(k);
		finish();
	}
}
