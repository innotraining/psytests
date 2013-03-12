package com.example.rusalovtest;

import java.util.Vector;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Screen_3 extends Activity {
	
	private int numberOfQuestion = 0;
	private Button yesButton;
	private Button noButton;
	private Button mainMenuButton;
	private TextView currentQuestion;
	private TextView currentCount;
	private Vector<Integer> answers;
	private String[] questions;
	private DbOpenHelper dbOpenHelper;
	private String userName;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_3);
		userName = getIntent().getStringExtra("userName");
		answers = new Vector<Integer>();
		Resources res = getResources();
	    questions = res.getStringArray(R.array.Questions);
	    currentQuestion = (TextView) findViewById(R.id.textQuestion);
	    currentQuestion.setText(questions[numberOfQuestion]);
	    currentCount = (TextView) findViewById(R.id.counter);
	    currentCount.setText((Integer)(numberOfQuestion + 1) + "/" + "105");
	    numberOfQuestion++;
	    	
	    addListenerOnButton();
	}
	public void addListenerOnButton(){
		mainMenuButton = (Button) findViewById(R.id.mainMenu);
		yesButton = (Button) findViewById(R.id.yes);
	    noButton = (Button) findViewById(R.id.no);
	    currentQuestion = (TextView) findViewById(R.id.textQuestion);
	    currentCount = (TextView) findViewById(R.id.counter);
	    yesButton.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					
					
					answers.addElement(1);
					if(numberOfQuestion <= 104){
						currentQuestion.setText(questions[numberOfQuestion]);
						currentCount.setText((Integer)(numberOfQuestion + 1) + "/" + "105");
						numberOfQuestion++;
					}else{
						switch (v.getId()) 
						{
						    case R.id.yes:
						    	Screen_3.this.onDestroy();
						        processing();        
						        break;
						    default:
						        break;
					    }
					}
				}
				
	    	});
	    	
	    	noButton.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					
					
					answers.addElement(0);
					if(numberOfQuestion <= 104){
						currentQuestion.setText(questions[numberOfQuestion]);
						currentCount.setText((Integer)(numberOfQuestion + 1) + "/" + "105");
						numberOfQuestion++;
					}else{
						switch (v.getId()) 
						{
						    case R.id.no:
						    	Screen_3.this.onDestroy();
						        processing();
						        break;
						    default:
						        break;
					    }
					}
				}
				
	    	});
	    	
	    	mainMenuButton.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Screen_3.this, Screen_0.class);
			        startActivity(intent);
			        finish();
				}
				
	    	});
	    	
	      	
	    	
	    }
		
		public void processing(){
			int energy = answers.get(3) + answers.get(7) + answers.get(14)
					+ answers.get(21) + answers.get(41) + answers.get(49)
					+ answers.get(57) + answers.get(63) + answers.get(95)
					+ 3 - answers.get(26) - answers.get(82) - answers.get(102);
			
			int socialEnergy = answers.get(10) + answers.get(29) + answers.get(56)
					+ answers.get(61) + answers.get(66) + answers.get(77)
					 + answers.get(85) + 5 - answers.get(2) - answers.get(33)
					  - answers.get(73) - answers.get(89) - answers.get(104);
			
			int plasticity = answers.get(19) + answers.get(24) + answers.get(34)
					 + answers.get(37) + answers.get(46) + answers.get(65)
					  + answers.get(70) + answers.get(75) + answers.get(100)
					   + answers.get(103) + 2 - answers.get(53) - answers.get(58);
			
			int socialPlasticity = answers.get(1) + answers.get(8) + answers.get(17)
					 + answers.get(25) + answers.get(44) + answers.get(67)
					  + answers.get(84) + answers.get(98) + 4 - answers.get(30)
					   - answers.get(80) - answers.get(86) - answers.get(92);
			
			int pace = answers.get(0) + answers.get(12) + answers.get(18)
					 + answers.get(32) + answers.get(45) + answers.get(48)
					  + answers.get(54) + answers.get(76) + 4 - answers.get(28)
					   - answers.get(42) - answers.get(69) - answers.get(93);
			
			int socialPace = answers.get(23) + answers.get(36) + answers.get(38)
					 + answers.get(50) + answers.get(71) + answers.get(91)
					  + 6 - answers.get(4) - answers.get(9) - answers.get(15)
					   - answers.get(55) - answers.get(95) - answers.get(101);
			
			int emotionality = answers.get(13) + answers.get(16) + answers.get(27)
					 + answers.get(39) + answers.get(59) + answers.get(60)
					  + answers.get(68) + answers.get(78) + answers.get(87)
					   + answers.get(90) + answers.get(94) + answers.get(96);
			
			int socialEmotionality = answers.get(5) + answers.get(6) + answers.get(20)
					 + answers.get(35) + answers.get(40) + answers.get(47)
					  + answers.get(52) + answers.get(62) + answers.get(74)
					   + answers.get(79) + answers.get(83) + answers.get(99);
			
			int K = answers.get(31) + answers.get(51) + answers.get(88)
					 + 6 - answers.get(11) - answers.get(22) - answers.get(43)
					  - answers.get(64) - answers.get(72) - answers.get(81);
			
	
			
			dbOpenHelper = new DbOpenHelper(Screen_3.this);
			SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		    ContentValues cv = new ContentValues();
		    
		    cv.put(DbOpenHelper.USER, userName);
		    cv.put("energy", energy);
		    cv.put("socialEnergy", socialEnergy);
		    cv.put("plasticity", plasticity);
		    cv.put("socialPlasticity", socialPlasticity);
		    cv.put("pace", pace);
		    cv.put("socialPace", socialPace);
		    cv.put("emotionality", emotionality);
		    cv.put("socialEmotionality", socialEmotionality);
		    cv.put("k", K);
		    
		    db.insert(DbOpenHelper.TABLE_NAME_RESULT, null, cv );
		    db.close();
		    
			Intent intent = new Intent(Screen_3.this, Screen_4.class);
			intent.putExtra("userName", userName);
			startActivity(intent);
			finish();
			
		}
		
		
		
	}
