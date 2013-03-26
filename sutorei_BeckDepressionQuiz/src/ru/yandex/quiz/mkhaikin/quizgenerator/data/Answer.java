package ru.yandex.quiz.mkhaikin.quizgenerator.data;

public class Answer {
	public String answer;
	int statID;
	int statValue;
	public Answer(String _text, int _ID, int _val){
		answer = _text;
		statID = _ID;
		statValue = _val;
	}
}
