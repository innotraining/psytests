package ru.yandex.quiz.mkhaikin.quizgenerator.data;

import java.util.ArrayList;

public class Question {
	public QuestionHeader header;
	public ArrayList<Answer> answers;
	
	public Question(QuestionHeader h, ArrayList<Answer> a) {
		header = new QuestionHeader();
		header = h;
		answers = a;
	}
	public Question(String q, QuestionType t, int an, ArrayList<Answer> a){
		header = new QuestionHeader();
		header.question = q;
		header.type = t;
		header.numberOfAnswers = an;
		answers = a;
	}
}
