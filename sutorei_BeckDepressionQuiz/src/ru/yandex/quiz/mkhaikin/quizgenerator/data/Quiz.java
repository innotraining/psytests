package ru.yandex.quiz.mkhaikin.quizgenerator.data;

import java.util.ArrayList;

public class Quiz {
	public ArrayList<Question> questions;
	public StatContainer container;
	
	public Quiz(){
		questions = new ArrayList<Question>();
		container = new StatContainer();
	}
	public Quiz(ArrayList<Question> q, StatContainer c){
		questions = q;
		container = c;
	}
}
