package com.example.quizbook;

import java.util.ArrayList;
import java.util.List;

public abstract class Engine implements IEngine {

  protected int currentQuestionNumber;
	protected ArrayList<String> questionDescriptions;
	protected ArrayList<ArrayList<String>> answersOnQusetions;
	protected String answerType;
	protected ArrayList<ArrayList<Integer>> userAnswersOnQuestions;
	
	@Override
	public void refresh() {
		currentQuestionNumber = 1;
		userAnswersOnQuestions.clear();
		for (int i = 0; i < questionDescriptions.size(); i++) {
			userAnswersOnQuestions.add(null);
		}
	}
	
	@Override
	public boolean nextQuestion() {
		currentQuestionNumber++;
		return (currentQuestionNumber <= questionDescriptions.size());
	}
	
	@Override
	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}

	@Override
	public int getQuestionsCount() {
		return questionDescriptions.size();
	}

	@Override
	public String getQuestionDescription(int qestionNumber) {
		// qestionNumber - 1 because array's enumeration starts with 0
		return "Вопрос " + qestionNumber + " из " + questionDescriptions.size() + "\n" + questionDescriptions.get(qestionNumber - 1);
	}

	@Override
	public List<String> getAnswersOnQuestion(int questionNumber) {
		return answersOnQusetions.get(questionNumber);
	}

	@Override
	public String getAnswerType() {
		return answerType;
	}

	@Override
	public void setAnswersOfUser(int questionNumber, ArrayList<Integer> answerNumbers) {
		// qestionNumber - 1 because array's enumeration starts with 0
		userAnswersOnQuestions.set(questionNumber - 1, answerNumbers);
	}

	// depends on realization
	@Override
	public abstract Object getConclusion();

}
