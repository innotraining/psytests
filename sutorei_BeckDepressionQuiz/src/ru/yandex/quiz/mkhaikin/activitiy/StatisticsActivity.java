package ru.yandex.quiz.mkhaikin.activitiy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;

import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.db.Users;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StatisticsActivity extends Activity {

	private SimpleDateFormat formatter;
	private Users users;
	SortedMap<Date, Integer> userResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_statistics);

		ListView resultsList = (ListView) findViewById(R.id.listView1);
		users = new Users(StatisticsActivity.this);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		userResults = users.getUserResults(users.getCurrentUserId());

		final ArrayList<Date> resultDates = new ArrayList<Date>();
		final ArrayList<String> resultStrings = new ArrayList<String>();
		for (Date key : userResults.keySet()) {
			resultDates.add(key);
			resultStrings.add(formatter.format(key));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, resultStrings);
		resultsList.setAdapter(adapter);
		resultsList.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
//				int measure = userResults.get(userResults.keySet().toArray(
//					new Date[userResults.size()])[arg2]);
//				Crocs must die
				Date clickedDate = resultDates.get(position);
				int measure = userResults.get(clickedDate);
				Intent k = new Intent(StatisticsActivity.this,
					ViewDetailedResultActivity.class);
				k.putExtra("Measure", measure);
				startActivity(k);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistics, menu);
		return true;
	}

	public void onClickReturnToMainMenu(View view) {
		Intent k = new Intent(StatisticsActivity.this, MainMenuActivity.class);
		startActivity(k);
		finish();
	}

}
