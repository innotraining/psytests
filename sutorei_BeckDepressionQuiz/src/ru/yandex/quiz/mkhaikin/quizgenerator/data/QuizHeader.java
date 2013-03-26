package ru.yandex.quiz.mkhaikin.quizgenerator.data;

public class QuizHeader {
	public int numberOfStats;
	public int numberOfQuestions;
	QuizHeader(){
		
	}
	QuizHeader (int n, int m){
		numberOfStats = n;
		numberOfQuestions = m;
	}
}