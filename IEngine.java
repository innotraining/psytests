package com.example.quizbook;

import java.util.ArrayList;
import java.util.List;

public interface IEngine {
  
	public void refresh();
	public int getCurrentQuestionNumber();
	public boolean nextQuestion();
	public int getQuestionsCount();
	public String getQuestionDescription(int qestionNumber);
	public List<String> getAnswersOnQuestion(int questionNumber);
	/**
	 * @return 
	 * "YN" for yes-no answers<br>
	 * "1M" for one from many answers<br>
	 * "MM" for many from many answers<br>
	 */
	public String getAnswerType();
	public void setAnswersOfUser(int questionNumber, ArrayList<Integer> answerNumbers);
	/**
	 *@return result of user answers depending on an test engine with type TestNameConclusion
	 */
	public Object getConclusion(); 
	
}
