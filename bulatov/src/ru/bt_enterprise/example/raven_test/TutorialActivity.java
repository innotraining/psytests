package ru.bt_enterprise.example.raven_test;

import android.content.Intent;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialActivity extends Activity {

	AlphaAnimation  blinkanimation;
	ImageView picture;
	TextView t1;
	ImageButton ib2;
	Button btOK;
	
	ArrayList<ImageButton> elements = new ArrayList<ImageButton>();
	ArrayList<Integer> answers;
	int mode = 0;
	int level = 1;
	int stage = 0;
	
	int incorrect_guesses = 0;
	int correct_guesses = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		t1 = (TextView) findViewById(R.id.textView1);

		btOK = (Button) findViewById(R.id.buttonOK);
		ib2 = (ImageButton)findViewById(R.id.imageButton2);
		hideButtons();


		t1.setText("Посмотри, видишь, из верхней части картинки вырезан кусочек");
		blinkanimation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
		blinkanimation.setDuration(300); // duration - half a second
		blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
		blinkanimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
		blinkanimation.setRepeatMode(Animation.REVERSE);

		picture = (ImageView)findViewById(R.id.imageView1);
		picture.startAnimation(blinkanimation);
		answers = new ArrayList<Integer>();
		for (int i=0; i<6; i++) answers.add(i);

		
	}

	public void OKClicked(View v){

		if (stage==0){
			drawAnswers();
			picture.setAnimation(null);
			t1.setText("Один из этих кусочков подходит. Для каждой картинки есть только один верный ответ. Выбери правильный кусочек картинки");
			for(int i=1; i<=6; i++){
				int idButton = getResources().getIdentifier("imageButton" + i, "id", getPackageName());
				ImageButton ib = (ImageButton)findViewById(idButton);
				ib.setVisibility(ImageButton.VISIBLE);
				ib.setAnimation(blinkanimation);
			}
			stage = 1;
//			drawAnswers();
			return;
		}

		if (stage==1){
			stopButtonsAnimation();
			stage = 2;
			t1.setText("");
			btOK.setVisibility(Button.GONE);
			return;
		}

		if (stage == 3){
			t1.setText("");
			btOK.setVisibility(Button.GONE);
			showButtons();
			stage = 4;
			next_level();
			return;
		}
	}
	
	public void drawAnswers(){

		if (stage < 3){
			answers.set(0, 1);
			answers.set(1, 0);
		} else java.util.Collections.shuffle(answers);

		

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

	private class MyAnimationListener implements AnimationListener{

	    @Override
	    public void onAnimationEnd(Animation animation) {
	        ib2.clearAnimation();
	        
//	        LayoutParams lp = new LayoutParams(imageView.getWidth(), imageView.getHeight());
//	        lp.setMargins(50, 100, 0, 0);
//	        ib2.setLayoutParams(lp);
	        picture.setImageResource(getResources().getIdentifier("drawable/a1_background_filled", "id", getPackageName()));
	        stage = 3;
	        btOK.setVisibility(Button.VISIBLE);
	        t1.setText("Найди подходящий кусочек");
	        hideButtons();
	    }

	    @Override
	    public void onAnimationRepeat(Animation animation) {
	    }

	    @Override
	    public void onAnimationStart(Animation animation) {
	    }

	}

	public void hideButtons(){
		for(int i=1; i<=6; i++){
			int idButton = getResources().getIdentifier("imageButton" + i, "id", getPackageName());
			ImageButton ib = (ImageButton)findViewById(idButton);
			ib.setVisibility(ImageButton.GONE);
		}
	}
	public void showButtons(){
		for(int i=1; i<=6; i++){
			int idButton = getResources().getIdentifier("imageButton" + i, "id", getPackageName());
			ImageButton ib = (ImageButton)findViewById(idButton);
			ib.setVisibility(ImageButton.VISIBLE);
		}
	}
	public void stopButtonsAnimation(){
		for(int i=1; i<=6; i++){
			int idButton = getResources().getIdentifier("imageButton" + i, "id", getPackageName());
			ImageButton ib = (ImageButton)findViewById(idButton);
			ib.setAnimation(null);
		}
	}

	public void onAnswerCick(View v){

		if (stage < 1) return;
		if (stage==1){
			stopButtonsAnimation();
			stage = 2;
			t1.setText("");
			btOK.setVisibility(Button.GONE);
		}
		
		int ButtonNumber = 	Integer.parseInt(v.getTag().toString());
		if (stage==2){
			if (ButtonNumber==1){
				t1.setText("Молодец!"); 
				TranslateAnimation animation = new TranslateAnimation(0, 60, 0, -250);
				animation.setDuration(1000);
				animation.setFillAfter(false);
				animation.setAnimationListener(new MyAnimationListener());
//				v.setBackgroundDrawable(null);
				v.startAnimation(animation);
			}
			else {
				t1.setText("Подумай еще. Выбери правильный кусочек.");
			}
			return;
		}

		if (answers.get(ButtonNumber)==0){
//			t1.setText("Молодец!"); 
			correct_guesses++;
		}
		else {
//			t1.setText("Подумай еще. Выбери правильный кусочек.");
			incorrect_guesses++;
		}

		next_level();
	}

	private void next_level(){
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

        Intent intent = new Intent(TutorialActivity.this, ResultsActivity.class);
//      getIntent().getStringExtra("name", "Безымян Безымяныч");
        intent.putExtra("correct", correct_guesses);
        intent.putExtra("incorrect", incorrect_guesses);
        startActivity(intent);
		
//		t1.setText("WINNER!");
		//finish();
	}
	
}
