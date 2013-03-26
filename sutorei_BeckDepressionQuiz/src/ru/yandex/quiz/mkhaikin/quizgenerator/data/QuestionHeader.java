package ru.yandex.quiz.mkhaikin.quizgenerator.data;

public class QuestionHeader{
	
	public String question;
	public QuestionType type;
	public int numberOfAnswers;
	
	public QuestionHeader(){
		
	}
	public QuestionHeader(String q, QuestionType t, int an){
		question = q;
		type = t;
		numberOfAnswers = an;
	}
}