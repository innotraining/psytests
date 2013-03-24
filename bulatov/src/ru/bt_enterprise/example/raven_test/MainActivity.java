package ru.bt_enterprise.example.raven_test;

import android.content.Intent;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView t1;
	ArrayList<ImageButton> elements = new ArrayList<ImageButton>();
	ArrayList<Integer> answers;
	int mode = 0;
	int level = 1;

	int incorrect_guesses = 0;
	int correct_guesses = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1 = (TextView) findViewById(R.id.textView1);

		answers = new ArrayList<Integer>();
		for (int i=0; i<6; i++) answers.add(i);
		
		drawAnswers();

	}

	
	public void drawAnswers(){

		java.util.Collections.shuffle(answers);


//		t1.setText("drawing..." + permutation[0] + ", " + permutation[1] + ", " + permutation[2] + ", " + permutation[3] + ", " + permutation[4] + ", " + permutation[5]);

		String str_mode = "";
		if (mode==0) str_mode = "a";
		if (mode==1) str_mode = "b";
		if (mode==2) str_mode = "c";
		if (mode==3) str_mode = "d";
		if (mode==4) str_mode = "e";
		String test_level = str_mode + level;
		
//		String test_level = "b1";

		int idPattern = getResources().getIdentifier("imageView1", "id", getPackageName());
		ImageView iv = (ImageView)findViewById(idPattern);
//		int idImageBG = getResources().getIdentifier("drawable/a2_background", "id", getPackageName());
		int idImageBG = getResources().getIdentifier("drawable/" + test_level + "_background", "id", getPackageName());
		iv.setImageResource(idImageBG);


		for(int i=1; i<=6; i++){
			int idButton = getResources().getIdentifier("imageButton" + i, "id", getPackageName());
			ImageButton ib = (ImageButton)findViewById(idButton);
			int idImage = getResources().getIdentifier("drawable/" + test_level + "_answer_w" + answers.get(i-1), "id", getPackageName());

			ib.setImageResource(idImage);
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onAnswerCick(View v){

		int ButtonNumber = 	Integer.parseInt(v.getTag().toString());


		if (answers.get(ButtonNumber)==0){
			t1.setText("YOU AREN'T COMPLETELY HOPELESS"); 
			correct_guesses++;
		}
		else {
			t1.setText("YOU ARE WORTHLESS LOSER");
			incorrect_guesses++;
		}

//		t1.setText( "BN = " + ButtonNumber + "perm[i] == " + permutation[ButtonNumber]);

		mode = (mode + 1);
		if (mode == 5){
			mode = 0;
			level = level + 1;
		}

		if ((level == 2)&&(mode == 1)) show_results();
		else drawAnswers();
		
	}


	private void show_results() {

		level = 1;
		mode = 0;
		drawAnswers();

        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
//      getIntent().getStringExtra("name", "Безымян Безымяныч");
        intent.putExtra("correct", correct_guesses);
        intent.putExtra("incorrect", incorrect_guesses);
        startActivity(intent);
		
//		t1.setText("WINNER!");
		//finish();
	}
	
}
