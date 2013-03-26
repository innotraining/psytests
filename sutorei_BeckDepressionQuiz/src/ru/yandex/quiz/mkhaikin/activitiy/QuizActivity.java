package ru.yandex.quiz.mkhaikin.activitiy;

import java.util.ArrayList;

import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.db.Users;
import ru.yandex.quiz.mkhaikin.quizgenerator.QuizGenerator;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.Quiz;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class QuizActivity extends Activity {
	
	private ArrayList<Integer> statistics;
	private ArrayList<CheckBox> cb;
	private TextView questionText;
	private Button nextButton;
	private int questionNumber;
	private Quiz q;
	private Users users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quiz);
		
		users = new Users(QuizActivity.this);
		
		QuizGenerator tg = new QuizGenerator();
		q = tg.generateQuiz(getApplicationContext());
		statistics = new ArrayList<Integer>();
		statistics.add(0);
		cb = new ArrayList<CheckBox>();
		cb.add((CheckBox)findViewById(R.id.checkBox1));
		cb.add((CheckBox)findViewById(R.id.checkBox2));
		cb.add((CheckBox)findViewById(R.id.checkBox3));
		cb.add((CheckBox)findViewById(R.id.checkBox4));
		questionText = (TextView)findViewById(R.id.textView1);
		nextButton = (Button)findViewById(R.id.nextButton);
		questionNumber = 1;
		questionText.setText(q.questions.get(questionNumber - 1).header.question);
		for (int i = 0; i < cb.size(); ++i){
			cb.get(i).setText(q.questions.get(questionNumber - 1).answers.get(i).answer);
		}
		nextButton.setText("Продолжить");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	
	public void onClickNext(View view){
		for (int i = 0; i < cb.size(); ++i){
			if (cb.get(i).isChecked()){
				statistics.set(0, statistics.get(0) + i);
			}
			cb.get(i).setChecked(false);
		}
		questionNumber++;
		questionText.setText(q.questions.get(questionNumber-1).header.question);
		for (int i = 0; i < cb.size(); ++i){
			cb.get(i).setText(q.questions.get(questionNumber-1).answers.get(i).answer);
		}
		if (questionNumber == q.questions.size()){
			nextButton.setText("Завершить тест");
			nextButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for (int i = 0; i < cb.size(); ++i){
						if (cb.get(i).isChecked()){
							statistics.set(0, statistics.get(0) + i);
						}
					}
					Intent k = new Intent(QuizActivity.this, StatisticsActivity.class);
					users.saveUserResults(statistics.get(0));
					startActivity(k);
					finish();
				}
			});
		}
	}
	public void onClickCancelQuiz(View view){
		Intent i = new Intent(QuizActivity.this, MainMenuActivity.class);
		startActivity(i);
		finish();
	}
}
