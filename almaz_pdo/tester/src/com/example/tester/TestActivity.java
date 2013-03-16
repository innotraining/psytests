package com.example.tester;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TestActivity extends Activity {
	public int hypertim = 0;
	public int cycloid = 0;
	public int labil = 0;
	public int as_nervous = 0;
	public int sensetive = 0;
	public int psyhantic = 0;
	public int shizoid = 0;
	public int epileptic = 0;
	public int isteroid = 0;
	public int unstable = 0;
	public int conform = 0;
	public int male = 0;
	public int female = 0;
	public int oNegative = 0;
	public int disstimulation = 0;
	public int tOtkrov = 0;
	public int emancypation = 0;
	public int vPsyco = 0;
	public int delinkvation = 0;
	public int vAlcoholic = 0;

	public String[] questionTitles = { "Самочувствие", "Настроение",
			"Cон и сновидения", "Пробуждение ото сна",
			"Аппетит и отношение к еде", "Отношение к спиртным напиткам",
			"Сексуальные проблемы", "Отношение к одежде",
			"Отношение к деньгам", "Отношение к родителям",
			"Отношение к друзьям", "Отношение к окружающим",
			"Отношение к незнакомым людям", "Отношение к одиночеству",
			"Отношение к будущему", "Отношение к новому",
			"Отношение к неудачам", "Отношение к приключениям и риску",
			"Отношение к лидерству", "Отношение к критике",
			"Отношение к опеке", "Отношение к правилам и закону",
			"Оценка себя в детстве", "Отношение к школе",
			"Оценка себя в данный момент" };
	public int currentQuestion = 0;
	public CheckBox[] answers = new CheckBox[20];
	ArrayList<String[]> ans = new ArrayList<String[]>();
	public int answerCount = 0;
	public boolean onReverse = false;
	public String username = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_test);
		ans.add(getResources().getStringArray(R.array.Answers0));
		ans.add(getResources().getStringArray(R.array.Answers1));
		ans.add(getResources().getStringArray(R.array.Answers2));
		ans.add(getResources().getStringArray(R.array.Answers3));
		ans.add(getResources().getStringArray(R.array.Answers4));
		ans.add(getResources().getStringArray(R.array.Answers5));
		ans.add(getResources().getStringArray(R.array.Answers6));
		ans.add(getResources().getStringArray(R.array.Answers7));
		ans.add(getResources().getStringArray(R.array.Answers8));
		ans.add(getResources().getStringArray(R.array.Answers9));
		ans.add(getResources().getStringArray(R.array.Answers10));
		ans.add(getResources().getStringArray(R.array.Answers11));
		ans.add(getResources().getStringArray(R.array.Answers12));
		ans.add(getResources().getStringArray(R.array.Answers13));
		ans.add(getResources().getStringArray(R.array.Answers14));
		ans.add(getResources().getStringArray(R.array.Answers15));
		ans.add(getResources().getStringArray(R.array.Answers16));
		ans.add(getResources().getStringArray(R.array.Answers17));
		ans.add(getResources().getStringArray(R.array.Answers18));
		ans.add(getResources().getStringArray(R.array.Answers19));
		ans.add(getResources().getStringArray(R.array.Answers20));
		ans.add(getResources().getStringArray(R.array.Answers21));
		ans.add(getResources().getStringArray(R.array.Answers22));
		ans.add(getResources().getStringArray(R.array.Answers23));
		ans.add(getResources().getStringArray(R.array.Answers24));

		prepareAnswers();
		// Show the Up button in the action bar.
		Intent intent = getIntent();
		username = intent.getStringExtra(USER_SERVICE);
		/*
		 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 * builder.setMessage("|" + message + "|"); builder.show();
		 */
		// setupActionBar();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Инструкция");
		builder.setMessage("На каждом экране вам предлагается выбрать не более, чем три утверждения, которые вам наиболее подходят");
		builder.show();
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
		getMenuInflater().inflate(R.menu.test, menu);
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

	public void readAnswersForFirstPartFor() {
		boolean[] checkedAnswers = new boolean[answerCount];

		for (int i = 0; i < answerCount; ++i)
			checkedAnswers[i] = answers[i].isChecked();

		if (currentQuestion == 0) {
			if (checkedAnswers[0]) {
				as_nervous++;
			}
			if (checkedAnswers[1]) {
				hypertim++;
				male += 2;
			}
			if (checkedAnswers[2]) {
				cycloid++;
			}
			if (checkedAnswers[3]) {
				psyhantic++;
			}
			if (checkedAnswers[4]) {
				as_nervous += 2;
			}
			if (checkedAnswers[7]) {
				cycloid++;
			}
			if (checkedAnswers[8]) {
				unstable += 2;
				disstimulation++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 1) {
			if (checkedAnswers[0]) {
				hypertim++;
				unstable++;
			}
			if (checkedAnswers[1]) {
				psyhantic++;
			}
			if (checkedAnswers[5]) {
				cycloid++;
				as_nervous++;
			}
			if (checkedAnswers[6]) {
				shizoid++;
			}
			if (checkedAnswers[9]) {
				sensetive++;
				labil++;
			}
			if (checkedAnswers[10]) {
				tOtkrov++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 2) {
			if (checkedAnswers[0]) {
				hypertim++;
				unstable++;
				male += 2;
			}
			if (checkedAnswers[2]) {
				conform++;
			}
			if (checkedAnswers[3]) {
				delinkvation++;
				as_nervous++;
			}
			if (checkedAnswers[5]) {
				cycloid++;
			}
			if (checkedAnswers[6]) {
				as_nervous++;
			}
			if (checkedAnswers[9]) {
				labil++;
				as_nervous++;
			}
			if (checkedAnswers[14]) {
				oNegative++;
			}
		}
		if (currentQuestion == 3) {
			if (checkedAnswers[0]) {
				epileptic++;
			}
			if (checkedAnswers[2]) {
				tOtkrov++;
			}
			if (checkedAnswers[3]) {
				cycloid++;
			}
			if (checkedAnswers[5]) {
				female++;
			}
			if (checkedAnswers[7]) {
				as_nervous++;
			}
			if (checkedAnswers[8]) {
				unstable += 2;
			}
			if (checkedAnswers[10]) {
				delinkvation++;
			}
			if (checkedAnswers[11]) {
				cycloid++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 4) {
			if (checkedAnswers[0]) {
				shizoid++;
			}
			if (checkedAnswers[1]) {
				tOtkrov++;
			}
			if (checkedAnswers[2]) {
				as_nervous++;
			}
			if (checkedAnswers[4]) {
				labil += 2;
				psyhantic++;
			}
			if (checkedAnswers[5]) {
				sensetive++;
				tOtkrov++;
			}
			if (checkedAnswers[6]) {
				male++;
			}
			if (checkedAnswers[10]) {
				unstable++;
			}
			if (checkedAnswers[14]) {
				male++;
			}
			if (checkedAnswers[16]) {
				cycloid++;
			}
			if (checkedAnswers[19]) {
				oNegative++;
			}
		}
		if (currentQuestion == 5) {
			if (checkedAnswers[0]) {
				vAlcoholic += 2;
			}
			if (checkedAnswers[2]) {
				male++;
				vAlcoholic++;
			}
			if (checkedAnswers[3]) {
				hypertim += 2;
				vAlcoholic += 2;
			}
			if (checkedAnswers[4]) {
				vAlcoholic--;
			}
			if (checkedAnswers[5]) {
				vAlcoholic--;
			}
			if (checkedAnswers[6]) {
				vAlcoholic -= 3;
				sensetive++;
			}
			if (checkedAnswers[7]) {
				vAlcoholic++;
			}
			if (checkedAnswers[8]) {
				psyhantic++;
			}
			if (checkedAnswers[9]) {
				vAlcoholic -= 3;
				sensetive++;
				shizoid++;
				psyhantic++;
			}
			if (checkedAnswers[11]) {
				cycloid++;
				delinkvation++;
			}
			if (checkedAnswers[12]) {
				as_nervous++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 6) {
			if (checkedAnswers[0]) {
				unstable += 2;
				delinkvation++;
			}
			if (checkedAnswers[9]) {
				male++;
			}
			if (checkedAnswers[13]) {
				labil++;
				isteroid++;
			}
			if (checkedAnswers[14]) {
				oNegative++;
			}
		}
		if (currentQuestion == 7) {
			if (checkedAnswers[3]) {
				isteroid += 2;
			}
			if (checkedAnswers[4]) {
				as_nervous++;
				sensetive++;
			}
			if (checkedAnswers[5]) {
				conform++;
				female++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 8) {
			if (checkedAnswers[1]) {
				cycloid++;
			}
			if (checkedAnswers[3]) {
				isteroid++;
				male++;
			}
			if (checkedAnswers[5]) {
				labil++;
				as_nervous++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 9) {
			if (checkedAnswers[0]) {
				psyhantic++;
			}
			if (checkedAnswers[2]) {
				labil += 2;
				vPsyco++;
			}
			if (checkedAnswers[4]) {
				emancypation++;
			}
			if (checkedAnswers[7]) {
				emancypation++;
				female += 2;
			}
			if (checkedAnswers[8]) {
				psyhantic++;
			}
			if (checkedAnswers[9]) {
				male++;
			}
			if (checkedAnswers[10]) {
				emancypation++;
			}
			if (checkedAnswers[11]) {
				shizoid++;
				emancypation++;
			}
			if (checkedAnswers[12]) {
				epileptic++;
				delinkvation++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
				emancypation++;
			}
		}
		if (currentQuestion == 10) {
			if (checkedAnswers[0]) {
				disstimulation++;
			}
			if (checkedAnswers[2]) {
				hypertim++;
				cycloid++;
			}
			if (checkedAnswers[3]) {
				conform++;
			}
			if (checkedAnswers[6]) {
				sensetive += 2;
			}
			if (checkedAnswers[13]) {
				labil++;
			}
			if (checkedAnswers[14]) {
				as_nervous++;
			}
			if (checkedAnswers[15]) {
				oNegative++;
			}
		}
		if (currentQuestion == 11) {
			if (checkedAnswers[4]) {
				male += 2;
			}
			if (checkedAnswers[6]) {
				hypertim++;
			}
			if (checkedAnswers[9]) {
				vPsyco++;
			}
			if (checkedAnswers[15]) {
				delinkvation++;
			}
			if (checkedAnswers[17]) {
				oNegative++;
			}
		}
		if (currentQuestion == 12) {
			if (checkedAnswers[0]) {
				epileptic += 2;
			}
			if (checkedAnswers[7]) {
				cycloid++;
			}
			if (checkedAnswers[8]) {
				cycloid++;
				hypertim++;
			}
			if (checkedAnswers[11]) {
				sensetive += 2;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 13) {
			if (checkedAnswers[0]) {
				isteroid++;
			}
			if (checkedAnswers[1]) {
				sensetive++;
				shizoid += 2;
			}
			if (checkedAnswers[2]) {
				hypertim++;
				epileptic++;
				unstable++;
			}
			if (checkedAnswers[3]) {
				labil++;
				as_nervous++;
			}
			if (checkedAnswers[5]) {
				delinkvation++;
			}
			if (checkedAnswers[7]) {
				shizoid++;
			}
			if (checkedAnswers[10]) {
				labil++;
			}
			if (checkedAnswers[11]) {
				oNegative++;
			}
		}
		if (currentQuestion == 14) {
			if (checkedAnswers[2]) {
				male++;
			}
			if (checkedAnswers[4]) {
				hypertim += 2;
			}
			if (checkedAnswers[6]) {
				psyhantic++;
			}
			if (checkedAnswers[7]) {
				epileptic++;
			}
			if (checkedAnswers[9]) {
				psyhantic++;
			}
			if (checkedAnswers[10]) {
				delinkvation++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 15) {
			if (checkedAnswers[4]) {
				shizoid++;
			}
			if (checkedAnswers[7]) {
				delinkvation++;
			}
			if (checkedAnswers[8]) {
				epileptic++;
				labil++;
			}
			if (checkedAnswers[9]) {
				shizoid += 2;
				psyhantic++;
				epileptic++;
			}
			if (checkedAnswers[10]) {
				oNegative++;
			}
		}
		if (currentQuestion == 16) {
			if (checkedAnswers[0]) {
				shizoid++;
				vPsyco++;
			}
			if (checkedAnswers[2]) {
				epileptic++;
			}
			if (checkedAnswers[4]) {
				isteroid++;
			}
			if (checkedAnswers[6]) {
				delinkvation++;
				epileptic += 2;
			}
			if (checkedAnswers[9]) {
				psyhantic++;
			}
			if (checkedAnswers[12]) {
				conform++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 17) {
			if (checkedAnswers[0]) {
				hypertim++;
			}
			if (checkedAnswers[1]) {
				sensetive++;
			}
			if (checkedAnswers[2]) {
				sensetive++;
				male++;
			}
			if (checkedAnswers[5]) {
				vPsyco++;
			}
			if (checkedAnswers[6]) {
				female++;
			}
			if (checkedAnswers[8]) {
				hypertim++;
			}
			if (checkedAnswers[10]) {
				oNegative++;
			}
		}
		if (currentQuestion == 18) {
			if (checkedAnswers[0]) {
				cycloid++;
			}
			if (checkedAnswers[6]) {
				psyhantic += 2;
			}
			if (checkedAnswers[7]) {
				labil++;
				sensetive++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 19) {
			if (checkedAnswers[0]) {
				shizoid += 2;
				emancypation++;
			}
			if (checkedAnswers[4]) {
				emancypation++;
			}
			if (checkedAnswers[6]) {
				emancypation++;
				unstable++;
				delinkvation++;
			}
			if (checkedAnswers[7]) {
				tOtkrov++;
			}
			if (checkedAnswers[8]) {
				as_nervous++;
			}
			if (checkedAnswers[9]) {
				emancypation++;
			}
			if (checkedAnswers[10]) {
				cycloid++;
			}
			if (checkedAnswers[11]) {
				emancypation++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
				emancypation++;
			}
		}
		if (currentQuestion == 20) {
			if (checkedAnswers[0]) {
				cycloid++;
				psyhantic++;
				male++;
			}
			if (checkedAnswers[1]) {
				epileptic += 2;
			}
			if (checkedAnswers[2]) {
				disstimulation++;
				emancypation++;
			}
			if (checkedAnswers[3]) {
				emancypation++;
			}
			if (checkedAnswers[5]) {
				cycloid++;
				labil++;
			}
			if (checkedAnswers[7]) {
				emancypation++;
			}
			if (checkedAnswers[10]) {
				isteroid += 2;
				emancypation++;
				female++;
			}
			if (checkedAnswers[12]) {
				emancypation++;
				female += 3;
			}
			if (checkedAnswers[13]) {
				emancypation++;
				female += 2;
			}
		}
		if (currentQuestion == 21) {
			if (checkedAnswers[0]) {
				emancypation++;
			}
			if (checkedAnswers[1]) {
				hypertim += 2;
				emancypation++;
			}
			if (checkedAnswers[3]) {
				delinkvation++;
			}
			if (checkedAnswers[4]) {
				emancypation++;
			}
			if (checkedAnswers[6]) {
				emancypation++;
			}
			if (checkedAnswers[9]) {
				delinkvation++;
			}
			if (checkedAnswers[10]) {
				labil++;
				female++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
				emancypation++;
			}
		}
		if (currentQuestion == 22) {
			if (checkedAnswers[1]) {
				hypertim++;
			}
			if (checkedAnswers[3]) {
				disstimulation++;
			}
			if (checkedAnswers[6]) {
				hypertim++;
			}
			if (checkedAnswers[7]) {
				sensetive += 2;
			}
			if (checkedAnswers[9]) {
				labil++;
			}
			if (checkedAnswers[12]) {
				isteroid++;
			}
			if (checkedAnswers[13]) {
				epileptic += 2;
			}
			if (checkedAnswers[15]) {
				oNegative++;
			}
		}
		if (currentQuestion == 23) {
			if (checkedAnswers[0]) {
				hypertim++;
				epileptic++;
				isteroid++;
				unstable += 2;
			}
			if (checkedAnswers[1]) {
				epileptic++;
			}
			if (checkedAnswers[2]) {
				cycloid++;
			}
			if (checkedAnswers[3]) {
				female++;
			}
			if (checkedAnswers[5]) {
				delinkvation++;
			}
			if (checkedAnswers[12]) {
				cycloid++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 24) {
			if (checkedAnswers[0]) {
				male++;
			}
			if (checkedAnswers[1]) {
				tOtkrov += 2;
			}
			if (checkedAnswers[2]) {
				as_nervous++;
			}
			if (checkedAnswers[7]) {
				epileptic++;
				isteroid++;
			}
			if (checkedAnswers[10]) {
				delinkvation++;
			}
			if (checkedAnswers[11]) {
				shizoid++;
				isteroid++;
			}
			if (checkedAnswers[12]) {
				sensetive++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}

		}
	}

	public void readAnswersForSecondPart() {
		boolean[] checkedAnswers = new boolean[answerCount];

		for (int i = 0; i < answerCount; ++i)
			checkedAnswers[i] = answers[i].isChecked();

		if (currentQuestion == 0) {
			if (checkedAnswers[1]) {
				delinkvation++;
			}
			if (checkedAnswers[9]) {
				sensetive++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 1) {
			if (checkedAnswers[1]) {
				hypertim++;
			}
			if (checkedAnswers[9]) {
				delinkvation++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 2) {
			if (checkedAnswers[1]) {
				epileptic++;
			}
			if (checkedAnswers[3]) {
				vPsyco++;
			}
			if (checkedAnswers[4]) {
				cycloid++;
			}
			if (checkedAnswers[13]) {
				as_nervous++;
			}
			if (checkedAnswers[14]) {
				oNegative++;
			}
		}
		if (currentQuestion == 3) {
			if (checkedAnswers[0]) {
				labil++;
			}
			if (checkedAnswers[1]) {
				conform++;
			}
			if (checkedAnswers[6]) {
				labil++;
				male++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 4) {
			if (checkedAnswers[2]) {
				cycloid++;
				epileptic++;
				male++;
			}
			if (checkedAnswers[15]) {
				isteroid++;
			}
			if (checkedAnswers[19]) {
				oNegative++;
			}
		}
		if (currentQuestion == 5) {
			if (checkedAnswers[0]) {
				cycloid++;
			}
			if (checkedAnswers[3]) {
				cycloid++;
				vAlcoholic--;
			}
			if (checkedAnswers[4]) {
				vAlcoholic += 2;
			}
			if (checkedAnswers[5]) {
				vAlcoholic++;
			}
			if (checkedAnswers[6]) {
				cycloid++;
				labil++;
				vAlcoholic += 2;
			}
			if (checkedAnswers[7]) {
				female++;
			}
			if (checkedAnswers[9]) {
				vAlcoholic++;
			}
			if (checkedAnswers[10]) {
				isteroid++;
			}
			if (checkedAnswers[12]) {
				labil++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
				epileptic++;
			}
		}
		if (currentQuestion == 6) {
			if (checkedAnswers[0]) {
				male++;
			}
			if (checkedAnswers[1]) {
				cycloid++;
			}
			if (checkedAnswers[4]) {
				unstable += 2;
			}
			if (checkedAnswers[6]) {
				conform++;
				vPsyco++;
			}
			if (checkedAnswers[7]) {
				isteroid += 2;
			}
			if (checkedAnswers[10]) {
				labil++;
				sensetive++;
			}
			if (checkedAnswers[12]) {
				hypertim++;
				labil++;
			}
			if (checkedAnswers[14]) {
				oNegative++;
			}
		}
		if (currentQuestion == 7) {
			if (checkedAnswers[0]) {
				as_nervous++;
				vPsyco++;
			}
			if (checkedAnswers[3]) {
				conform++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 8) {
			if (checkedAnswers[0]) {
				male++;
			}
			if (checkedAnswers[1]) {
				epileptic++;
				isteroid++;
				disstimulation++;
			}
			if (checkedAnswers[5]) {
				isteroid++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 9) {
			if (checkedAnswers[4]) {
				conform++;
			}
			if (checkedAnswers[10]) {
				hypertim++;
				labil++;
				psyhantic++;
				epileptic++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 10) {
			if (checkedAnswers[2]) {
				delinkvation++;
			}
			if (checkedAnswers[5]) {
				isteroid++;
			}
			if (checkedAnswers[11]) {
				disstimulation++;
			}
			if (checkedAnswers[15]) {
				oNegative++;
			}
		}
		if (currentQuestion == 11) {
			if (checkedAnswers[1]) {
				epileptic++;
				female++;
			}
			if (checkedAnswers[6]) {
				sensetive++;
			}
			if (checkedAnswers[9]) {
				sensetive++;
			}
			if (checkedAnswers[12]) {
				psyhantic++;
			}
			if (checkedAnswers[17]) {
				oNegative++;
			}
		}
		if (currentQuestion == 12) {
			if (checkedAnswers[0]) {
				hypertim++;
			}
			if (checkedAnswers[1]) {
				epileptic += 2;
			}
			if (checkedAnswers[7]) {
				hypertim++;
			}
			if (checkedAnswers[8]) {
				sensetive++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 13) {
			if (checkedAnswers[0]) {
				shizoid += 2;
			}
			if (checkedAnswers[2]) {
				sensetive++;
				psyhantic++;
				shizoid += 3;
			}
			if (checkedAnswers[4]) {
				epileptic++;
				isteroid++;
				vPsyco++;
			}
			if (checkedAnswers[11]) {
				vPsyco++;
			}
		}
		if (currentQuestion == 14) {
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 15) {
			if (checkedAnswers[5]) {
				epileptic++;
			}
			if (checkedAnswers[6]) {
				isteroid++;
				delinkvation++;
			}
			if (checkedAnswers[10]) {
				oNegative++;
			}
		}
		if (currentQuestion == 16) {
			if (checkedAnswers[0]) {
				psyhantic += 2;
				epileptic++;
			}
			if (checkedAnswers[1]) {
				epileptic += 2;
			}
			if (checkedAnswers[7]) {
				labil++;
			}
			if (checkedAnswers[9]) {
				cycloid++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 17) {
			if (checkedAnswers[0]) {
				sensetive += 2;
			}
			if (checkedAnswers[4]) {
				epileptic += 2;
			}
			if (checkedAnswers[9]) {
				labil++;
				unstable++;
			}
			if (checkedAnswers[10]) {
				oNegative++;
			}
		}
		if (currentQuestion == 18) {
			if (checkedAnswers[3]) {
				isteroid++;
			}
			if (checkedAnswers[8]) {
				isteroid++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 19) {
			if (checkedAnswers[0]) {
				conform++;
			}
			if (checkedAnswers[4]) {
				labil++;
			}
			if (checkedAnswers[9]) {
				psyhantic += 2;
			}
			if (checkedAnswers[10]) {
				delinkvation++;
			}
			if (checkedAnswers[11]) {
				isteroid++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
				as_nervous++;
			}
		}
		if (currentQuestion == 20) {
			if (checkedAnswers[8]) {
				isteroid++;
			}
			if (checkedAnswers[11]) {
				isteroid++;
			}
			if (checkedAnswers[14]) {
				oNegative++;
			}
		}
		if (currentQuestion == 21) {
			if (checkedAnswers[0]) {
				conform++;
			}
			if (checkedAnswers[1]) {
				sensetive++;
			}
			if (checkedAnswers[3]) {
				hypertim++;
			}
			if (checkedAnswers[4]) {
				labil++;
			}
			if (checkedAnswers[6]) {
				cycloid++;
			}
			if (checkedAnswers[7]) {
				delinkvation += 3;
			}
			if (checkedAnswers[8]) {
				isteroid++;
			}
			if (checkedAnswers[12]) {
				oNegative++;
			}
		}
		if (currentQuestion == 22) {
			if (checkedAnswers[3]) {
				cycloid++;
			}
			if (checkedAnswers[6]) {
				sensetive++;
			}
			if (checkedAnswers[10]) {
				isteroid += 2;
			}
			if (checkedAnswers[11]) {
				labil++;
			}
			if (checkedAnswers[13]) {
				labil++;
			}
			if (checkedAnswers[15]) {
				oNegative++;
			}
		}
		if (currentQuestion == 23) {
			if (checkedAnswers[1]) {
				epileptic += 2;
				unstable++;
			}
			if (checkedAnswers[8]) {
				isteroid++;
			}
			if (checkedAnswers[10]) {
				shizoid += 2;
				delinkvation += 2;
			}
			if (checkedAnswers[12]) {
				delinkvation++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
		if (currentQuestion == 24) {
			if (checkedAnswers[0]) {
				hypertim++;
			}
			if (checkedAnswers[5]) {
				psyhantic += 2;
			}
			if (checkedAnswers[7]) {
				as_nervous++;
			}
			if (checkedAnswers[9]) {
				male++;
			}
			if (checkedAnswers[12]) {
				male++;
			}
			if (checkedAnswers[13]) {
				oNegative++;
			}
		}
	}

	public void getResults() {
		int[][] resultTable = new int[11][3];
		resultTable[0][0] = hypertim;
		resultTable[1][0] = cycloid;
		resultTable[2][0] = labil;
		resultTable[3][0] = as_nervous;
		resultTable[4][0] = sensetive;
		resultTable[5][0] = psyhantic;
		resultTable[6][0] = shizoid;
		resultTable[7][0] = epileptic;
		resultTable[8][0] = isteroid;
		resultTable[9][0] = unstable;
		resultTable[10][0] = conform;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Ваш тип: ").setTitle("Итог теста");
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void quitTest(View view) {
		System.exit(0);
	}

	public void prepareAnswers() {
		if (!onReverse)
			setTitle(questionTitles[currentQuestion]
					+ "(выберите наиболее соответсвующее вам )");
		else
			setTitle(questionTitles[currentQuestion]
					+ "(выберите наименее соответсвующее вам )");

		answerCount = ans.get(currentQuestion).length;

		ScrollView sv = (ScrollView) findViewById(R.id.scrollView1);
		sv.removeAllViews();

		final LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);

		for (int i = 0; i < ans.get(currentQuestion).length; ++i) {

			answers[i] = new CheckBox(this);
			answers[i].setText(ans.get(currentQuestion)[i]);

			ll.addView(answers[i]);
		}
		sv.addView(ll);
	}

	public void activityNext(View view) {
		int checkedCount = 0;
		for (int i = 0; i < answerCount; ++i) {
			if (answers[i].isChecked())
				checkedCount++;
		}
		if (checkedCount > 3) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("!");
			builder.setMessage("Нельзя выбирать больше трех ответов");
			builder.show();
		} else if (checkedCount == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("!");
			builder.setMessage("Выберите хотя бы один ответ");
			builder.show();
		} else {

			if (!onReverse) {
				readAnswersForFirstPartFor();
			} else {
				readAnswersForSecondPart();
			}
			if (currentQuestion < questionTitles.length - 1) {
				currentQuestion++;
				prepareAnswers();
			} else {
				if (!onReverse) {
					onReverse = true;
					currentQuestion = 0;
					AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
					builder.setTitle("Инструкция");
					builder.setMessage("На каждом экране вам предлагается выбрать не более, чем три утверждения, которые вам наименее подходят");
					builder.show();
					prepareAnswers();
				} else {
					if (hypertim < 2) {
						psyhantic++;
						sensetive++;
					}
					if (cycloid > 5) {
						labil++;
					}
					if (as_nervous > 3) {
						labil++;
					}
					if (psyhantic < 2) {
						unstable++;
					}
					if (unstable < 2) {
						psyhantic++;
					}
					if (conform == 0) {
						shizoid += 2;
						isteroid++;
					}
					if (conform == 1) {
						shizoid++;
					}
					if (disstimulation > 5) {
						unstable++;
					}
					if (tOtkrov > disstimulation) {
						psyhantic += 2;
						cycloid++;
					}
					if (vPsyco == 5) {
						epileptic++;
					}
					if (vPsyco > 5) {
						epileptic += 2;
					}
					if (emancypation > 5) {
						shizoid++;
						isteroid++;
					}
					if (delinkvation > 4) {
						shizoid++;
					}
					if (oNegative > 5) {
						sensetive++;
					}
					if (vAlcoholic < -5) {
						sensetive++;
					}
					if (vAlcoholic > 5) {
						isteroid++;
					}
					if (emancypation > 3) {
						sensetive = 0;
						psyhantic = 0;
					}
					if (disstimulation - tOtkrov > 3) {
						cycloid = 0;
						conform = 0;
					}
					DbOpenHelper dbOpenHelper = new DbOpenHelper(TestActivity.this);
					SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
					ContentValues args = new ContentValues();
					args.put("hypertim", hypertim);
					args.put("cycloid", cycloid);
					args.put("labil", labil);
					args.put("as_nervous", as_nervous);
					args.put("sensetive", sensetive);
					args.put("psyhantic", psyhantic);
					args.put("shizoid", shizoid);
					args.put("epileptic", epileptic);
					args.put("isteroid", isteroid);
					args.put("unstable", unstable);
					args.put("conform", conform);
					
					db.update(DbOpenHelper.TABLE_NAME, args, "username=?", new String[]{username});
					getResults();
					typeResolver resolver = new typeResolver();
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle("Тест окончен" + "\n" +
							"Ваш результат: " + resolver.resolve(new int[] {hypertim, cycloid, labil, as_nervous, sensetive, psyhantic, shizoid, epileptic, isteroid, unstable, conform}));
					builder.setMessage(
							"hypertim: " + hypertim + "\n" +
							"cycloid: " + cycloid + "\n" +
							"labil: " + labil + "\n" + 
							"as_nervous: " + as_nervous + "\n" + 
							"sensetive: " + sensetive + "\n" +
							"psyhantic: " + psyhantic + "\n" +
							"shizoid: " + shizoid + "\n" +
							"epileptic: " + epileptic + "\n" +
							"isteroid: " + isteroid + "\n" +
							"unstable: " + unstable + "\n" +
							"conform: " + conform
							);
					builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
						
						@Override
						public void onCancel(DialogInterface dialog) {
							// TODO Auto-generated method stub
								finish();
							}
					});
					builder.show();
				}
			}
		}
	}
}
