package com.example.szondi;

import java.util.Calendar;
import java.util.Random;
import java.lang.Math;

import com.example.szondi.ActivityReg.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow.LayoutParams;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ActivityTest extends Activity implements OnClickListener {
	private static final int MILLIS_PER_SECOND = 1000;
	private static final int SECONDS_TO_COUNTDOWN = 30;
	private TextView     countdownDisplay;
	private CountDownTimer timer;
	DBHelper dbHelper;
	final String LOG_TAG = "myLogs";
	TextView moves;
	Resources res;
	int n = 0;
	int m = 0;
	int surplus = 0;
	int currentPosition = -1;
	int[] countOfrightBefore;
	int userID;
	TableLayout tl;
	int[] pictures = { 
			R.drawable.circle,
			R.drawable.flag,
			R.drawable.rectangle, 
			R.drawable.semicircle, 
			R.drawable.square, 
			R.drawable.star, 
			R.drawable.triangle 
	};
	int[] pictures_check = {
			R.drawable.circle_check,
			R.drawable.flag_check,
			R.drawable.rectangle_check,
			R.drawable.semicircle_check,
			R.drawable.square_check,
			R.drawable.star_check,
			R.drawable.triangle_check
	};
	int[] resultsForTimeR;
	int[] resultsForTimeW;
	ImageButton[] buttons;
	TableRow[] rows;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		res = getResources();
		tl = (TableLayout)findViewById(R.id.TableLayout1);
		buttons = new ImageButton[5000];
		rows = new TableRow[500];
		resultsForTimeR = new int[5];
		resultsForTimeW = new int[5];
		countOfrightBefore = new int[5001];
		countOfrightBefore[0] = 0;
		int j = 0;
		for (j = 0; j < 500; j++) {
			rows[j] = new TableRow(this);
			rows[j].setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT, 
					TableRow.LayoutParams.FILL_PARENT)
					);
		}
		j = 0;
			for (int i = 0; i < 5000; i++) {
				buttons[i] = new ImageButton(this);
				Random rnd = new Random(2*(i+j));
				if (3*(i+j)%7 < 3) {
					rnd = new Random(3*(i+j));
				}
				else if (3*(i+j)%7 == 3 || 3*(i+j)%7 == 4) {
					rnd = new Random(4*(i+j));
				}
				else if (3*(i+j)%7 == 6 || 3*(i+j)%7 == 5) {
					rnd = new Random(5*(i+j));
				}
				int rand = rnd.nextInt(7) + 1;
				if (rand-1 == 1 || rand-1 == 6) {
					countOfrightBefore[i+1] = countOfrightBefore[i] + 1;
				}
				else countOfrightBefore[i+1] = countOfrightBefore[i];
				buttons[i].setBackgroundResource(pictures[rand-1]);
				buttons[i].setLayoutParams(new TableRow.LayoutParams(
						TableRow.LayoutParams.FILL_PARENT, 
						TableRow.LayoutParams.FILL_PARENT, (float) 1.0)
						);
				buttons[i].setId(i);
				buttons[i].setTag((int)(rand-1));
				buttons[i].setOnClickListener(this);
				rows[j].addView(buttons[i]);
				if ((i+1)%10 == 0 && i!=0) {
					tl.addView(rows[j], new TableLayout.LayoutParams(
							TableLayout.LayoutParams.FILL_PARENT, 
							TableLayout.LayoutParams.FILL_PARENT)
							);
					j++;
				}
			}
		
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		dbHelper = new DBHelper(this);
		moves = (TextView)findViewById(R.id.moves);
		countdownDisplay = 
				(TextView) findViewById(R.id.time_display_box);
		new CountDownTimer(300000, 1000) {
			int i = 0;
		     public void onTick(long millisUntilFinished) {
		    	 countdownDisplay.setText("" 
		    			 + (millisUntilFinished / 1000)/60 
		    			 + ":" + (millisUntilFinished / 1000)%60);
		    	 if ((millisUntilFinished / 1000) % 60 == 0 
		    			 && (millisUntilFinished / 1000) != 300) {
		    		 resultsForTimeR[i] = n;
		    		 resultsForTimeW[i] = m;
		    		 i++;
		    	 }
		     }
		     public void onFinish() {
		    	 resultsForTimeR[i] = n;
	    		 resultsForTimeW[i] = m;
		    	 countdownDisplay.setText("done!");
		    	 SQLiteDatabase db = dbHelper.getWritableDatabase();
		    	 Cursor c;
		    	 String selection = "id = ?";
		    	 String[] selectionArgs = new String[] { ""+userID };
		         c = db.query("listOfUsers", null, selection, 
		        		 selectionArgs, null, null, null);
		         int nameColIndex = c.getColumnIndex("name");
		         int dateColIndex = c.getColumnIndex("date");
		         
		    	 ContentValues cv = new ContentValues();
		    	 if (c.moveToFirst()) {
		    		 Log.d(LOG_TAG, "yes! "+c.getString(nameColIndex));
		    	 } else {
		    		 Log.d(LOG_TAG, "nope! "+c.getString(nameColIndex));
		    	 }
		    	 cv.put("name", c.getString(nameColIndex));
		    	 cv.put("birthDate", c.getString(dateColIndex));
		    	 Calendar calendar = Calendar.getInstance();
		    	 int month = calendar.get(calendar.MONTH)+1;
		    	 String date = calendar.get(calendar.YEAR) + "/" 
		    			 + month + "/" 
		    			 + calendar.get(calendar.DAY_OF_MONTH);
		    	 cv.put("testDate", date);
		    	 cv.put("right1", resultsForTimeR[0]);
		    	 cv.put("wrong1", resultsForTimeW[0]);
		    	 cv.put("right2", resultsForTimeR[1]);
		    	 cv.put("wrong2", resultsForTimeW[1]);
		    	 cv.put("right3", resultsForTimeR[2]);
		    	 cv.put("wrong3", resultsForTimeW[2]);
		    	 cv.put("right4", resultsForTimeR[3]);
		    	 cv.put("wrong4", resultsForTimeW[3]);
		    	 cv.put("right5", resultsForTimeR[4]);
		    	 cv.put("wrong5", resultsForTimeW[4]);
		    	 db.insert("ResultsOfUsers", null, cv);
		    	 dbHelper.close();
		    	 setContentView(R.layout.finaltest);
		    	 TextView thanx_results = 
		    			 (TextView)findViewById(R.id.thanx_results);
		    	 thanx_results.setText("5 минут истекли. " 
		    			 +"Спасибо за выполнение теста!\n" 
		    			 +"Твой результат: "+n+" правильных ответов и "
		    			 +m+" неправильных ответов");
		     }
		}.start();                                
	}
	public void onClick(View v) {
		
		if (currentPosition >= v.getId()) return;
		currentPosition = v.getId();
		switch ((Integer)(buttons[v.getId()].getTag())) {
		case 0:
		case 2:
		case 3:
		case 4:
		case 5:
			if (Math.abs(countOfrightBefore[v.getId()] - n) > 0) {
				m = m + Math.abs(countOfrightBefore[v.getId()] - n) - surplus;
				surplus = Math.abs(countOfrightBefore[v.getId()] - n);
			}
			m++;
			moves.setText(""+n+" правильных и "+m+" неправильных");
			buttons[v.getId()].setBackgroundResource(
					pictures_check[(Integer)(buttons[v.getId()].getTag())]);
			break;
		case 1:
		case 6:
			if (Math.abs(countOfrightBefore[v.getId()] - n) > 0) {
				m = m + Math.abs(countOfrightBefore[v.getId()] - n) - surplus;
				surplus = Math.abs(countOfrightBefore[v.getId()] - n);
			}
			n++;
			moves.setText(""+n+" правильных и "+m+" неправильных");
			buttons[v.getId()].setBackgroundResource(
					pictures_check[(Integer)(buttons[v.getId()].getTag())]);
			break;
		default:
			break;
		}
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{}
	}
}