package ru.yandex.quiz.mkhaikin.frontend;

import java.util.ArrayList;

import ru.yandex.quiz.mkhaikin.R;
import ru.yandex.quiz.mkhaikin.activitiy.QuizActivity;
import ru.yandex.quiz.mkhaikin.activitiy.StatisticsActivity;
import ru.yandex.quiz.mkhaikin.quizgenerator.*;
import ru.yandex.quiz.mkhaikin.quizgenerator.data.*;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.Intent;

public class QuizProcessor {
	private Quiz t;
	private int questionNumber;
	private ArrayList<Integer> statistics;
	private ArrayList<CheckBox> cb;
	private TextView questionText;
	private Button nextButton;
	private QuizActivity qa;
	public void processQuizUnit (QuizActivity _qa){
		
		//get layout elements
		QuizGenerator tg = new QuizGenerator();
		qa = _qa;
		t = tg.generateQuiz(qa.getApplicationContext());
		statistics = new ArrayList<Integer>();
		statistics.add(0);
		cb = new ArrayList<CheckBox>();
		cb.add((CheckBox)qa.findViewById(R.id.checkBox1));
		cb.add((CheckBox)qa.findViewById(R.id.checkBox2));
		cb.add((CheckBox)qa.findViewById(R.id.checkBox3));
		cb.add((CheckBox)qa.findViewById(R.id.checkBox4));
		questionText = (TextView)qa.findViewById(R.id.textView1);
		nextButton = (Button)qa.findViewById(R.id.nextButton);
		
		//set initial values
		
		questionNumber = 1;
		questionText.setText(t.questions.get(questionNumber).header.question);
		for (int i = 0; i < cb.size(); ++i){
			cb.get(i).setText(t.questions.get(questionNumber).answers.get(i).answer);
		}
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 0; i < cb.size(); ++i){
					if (cb.get(i).isChecked()){
						statistics.set(0, statistics.get(0) + i);
					}
				}
				questionNumber++;
				SetNextQuestion();
			}
		});
	}
	
	private void SetNextQuestion(){
		questionText.setText(t.questions.get(questionNumber).header.question);
		for (int i = 0; i < cb.size(); ++i){
			cb.get(i).setText(t.questions.get(questionNumber).answers.get(i).answer);
		}
		if (questionNumber == t.questions.size()){
			nextButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for (int i = 0; i < cb.size(); ++i){
						if (cb.get(i).isChecked()){
							statistics.set(0, statistics.get(0) + i);
						}
					}
					//TODO: write results to database
					Intent k = new Intent(qa, StatisticsActivity.class);
					qa.startActivity(k);
				}
			});
		}
	}
	
}
