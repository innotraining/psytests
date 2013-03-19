package com.example.szondi;

import java.util.Calendar;

import com.example.szondi.ActivityReg.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityTest extends Activity implements OnClickListener {
	private static final int MILLIS_PER_SECOND = 1000;
	private static final int SECONDS_TO_COUNTDOWN = 30;
	private TextView     countdownDisplay;
	private CountDownTimer timer;
	DBHelper dbHelper;
	final String LOG_TAG = "myLogs";
	ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6,
	imageButton7, imageButton8, imageButton9, imageButton10, imageButton11, imageButton12, imageButton13, 
	imageButton14, imageButton15, imageButton16, imageButton17, imageButton18, imageButton19, imageButton20/*,
	imageButton, imageButton, imageButton, imageButton, imageButton, imageButton, imageButton*/;
	TextView moves;
	int n = 0;
	int m = 0;
	int userID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		Intent intent = getIntent();
		userID = intent.getIntExtra("userID", -1);
		
		dbHelper = new DBHelper(this);
		
		moves = (TextView)findViewById(R.id.moves);
		imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
		imageButton3 = (ImageButton)findViewById(R.id.imageButton3);
		imageButton4 = (ImageButton)findViewById(R.id.imageButton4);
		imageButton5 = (ImageButton)findViewById(R.id.imageButton5);
		imageButton6 = (ImageButton)findViewById(R.id.imageButton6);
		imageButton7 = (ImageButton)findViewById(R.id.imageButton7);
		imageButton8 = (ImageButton)findViewById(R.id.imageButton8);
		imageButton9 = (ImageButton)findViewById(R.id.imageButton9);
		imageButton10 = (ImageButton)findViewById(R.id.imageButton10);
		imageButton11 = (ImageButton)findViewById(R.id.imageButton11);
		imageButton12 = (ImageButton)findViewById(R.id.imageButton12);
		imageButton13 = (ImageButton)findViewById(R.id.imageButton13);
		imageButton14 = (ImageButton)findViewById(R.id.imageButton14);
		imageButton15 = (ImageButton)findViewById(R.id.imageButton15);
		imageButton16 = (ImageButton)findViewById(R.id.imageButton16);
		imageButton17 = (ImageButton)findViewById(R.id.imageButton17);
		imageButton18 = (ImageButton)findViewById(R.id.imageButton18);
		imageButton19 = (ImageButton)findViewById(R.id.imageButton19);
		imageButton20 = (ImageButton)findViewById(R.id.imageButton20);
		imageButton1.setOnClickListener(this);
		imageButton2.setOnClickListener(this);
		imageButton3.setOnClickListener(this);
		imageButton4.setOnClickListener(this);
		imageButton5.setOnClickListener(this);
		imageButton6.setOnClickListener(this);
		imageButton7.setOnClickListener(this);
		imageButton8.setOnClickListener(this);
		imageButton9.setOnClickListener(this);
		imageButton10.setOnClickListener(this);
		imageButton11.setOnClickListener(this);
		imageButton12.setOnClickListener(this);
		imageButton13.setOnClickListener(this);
		imageButton14.setOnClickListener(this);
		imageButton15.setOnClickListener(this);
		imageButton16.setOnClickListener(this);
		imageButton17.setOnClickListener(this);
		imageButton18.setOnClickListener(this);
		imageButton19.setOnClickListener(this);
		imageButton20.setOnClickListener(this);
		
		countdownDisplay = (TextView) findViewById(R.id.time_display_box);
		new CountDownTimer(30000, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 countdownDisplay.setText("" + (millisUntilFinished / 1000)/60 + ":" + (millisUntilFinished / 1000)%60);
		    	 if ((millisUntilFinished / 1000) % 60 == 0 && (millisUntilFinished / 1000) != 300) {
		    	/*	 SQLiteDatabase db = dbHelper.getWritableDatabase();
		    		 
		    		 dbHelper.close();*/
		    	 }
		     }
		     public void onFinish() {
		    	 countdownDisplay.setText("done!");
		    	 SQLiteDatabase db = dbHelper.getWritableDatabase();
		    	 Cursor c;
		    	 String selection = "id = ?";
		    	 String[] selectionArgs = new String[] { ""+userID };
		         c = db.query("listOfUsers", null, selection, selectionArgs, null, null, null);
		      //   int idColIndex = c.getColumnIndex("id");
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
		    	 String date = calendar.get(calendar.YEAR) + "/" 
		    			 + calendar.get(calendar.MONTH) + "/" 
		    			 + calendar.get(calendar.DAY_OF_MONTH);
		    	 cv.put("testDate", date);
		    	 cv.put("right", n);
		    	 cv.put("wrong", m);
		    	 db.insert("ResultsOfUsers", null, cv);
		    	 dbHelper.close();
		    	 setContentView(R.layout.finaltest);
		    	 TextView thanx_results = (TextView)findViewById(R.id.thanx_results);
		    	 thanx_results.setText("5 минут истекли. Спасибо за выполнение теста!\n" +
		    	 		"Твой результат: "+n+" правильных ответов и "+m+" неправильных ответов");
		    //	 Intent intentReg = new Intent(this, ActivityResults.class);
			//     startActivity(intentReg);
		     }
		}.start();
	}
	public void onClick(View v) {
		switch (v.getId()) {
	    case R.id.imageButton1:
	    	imageButton1.setImageResource(R.drawable.triangle_check);
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton2:
	    	imageButton2.setImageResource(R.drawable.rectangle_check);
	    	if (n < 1) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton3:
	    	imageButton3.setImageResource(R.drawable.flag_check);
	    	if (n < 1) m++;
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton4:
	    	imageButton4.setImageResource(R.drawable.circle_check);
	    	if (n < 2) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton5:
	    	imageButton5.setImageResource(R.drawable.star_check);
	    	if (n < 2) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton6:
	    	imageButton6.setImageResource(R.drawable.semicircle_check);
	    	if (n < 2) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton7:
	    	imageButton7.setImageResource(R.drawable.circle_check);
	    	if (n < 2) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton8:
	    	imageButton8.setImageResource(R.drawable.triangle_check);
	    	if (n < 2) m++;
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton9:
	    	imageButton9.setImageResource(R.drawable.flag_check);
	    	if (n < 3) m++;
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton10:
	    	imageButton10.setImageResource(R.drawable.semicircle_check);
	    	if (n < 4) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton11:
	    	imageButton11.setImageResource(R.drawable.star_check);
	    	if (n < 4) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton12:
	    	imageButton12.setImageResource(R.drawable.square_check);
	    	if (n < 4) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton13:
	    	imageButton13.setImageResource(R.drawable.semicircle_check);
	    	if (n < 4) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton14:
	    	imageButton14.setImageResource(R.drawable.circle_check);
	    	if (n < 4) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton15:
	    	imageButton15.setImageResource(R.drawable.flag_check);
	    	if (n < 4) m++;
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton16:
	    	imageButton16.setImageResource(R.drawable.circle_check);
	    	if (n < 5) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton17:
	    	imageButton17.setImageResource(R.drawable.star_check);
	    	if (n < 5) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton18:
	    	imageButton18.setImageResource(R.drawable.rectangle_check);
	    	if (n < 5) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton19:
	    	imageButton19.setImageResource(R.drawable.square_check);
	    	if (n < 5) m++;
	    	m++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    case R.id.imageButton20:
	    	imageButton20.setImageResource(R.drawable.flag_check);
	    	if (n < 5) m++;
	    	n++;
	    	moves.setText(n + "правильных и " + m + "неправильных");
	    	break;
	    default:
	    	break;
		}
	}
	
	class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context) {
		      // конструктор суперкласса
		      super(context, "myDB", null, 1);
		    }
		public void onCreate(SQLiteDatabase db) {
		      Log.d(LOG_TAG, "--- onCreate database ---");
		      // создаем таблицу с полями
		      db.execSQL("create table listOfUsers ("
			          + "id integer primary key autoincrement," 
			          + "name text,"
			          + "date text" + ");");
			      db.execSQL("create table ResultsOfUsers ("
				          + "id integer primary key autoincrement," 
				          + "name text,"
				          + "birthDate text,"
				          + "testDate text,"
				          + "right integer," + "wrong integer" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}
}
