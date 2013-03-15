package com.congnitive.test.mmpitest.domainObjects;

public interface PsychologyTextedTest {
	/**
	 * @param ans
	 * @return true, if it's needed to stop test and true else Sets answer on
	 *         the num's question. 0 is no, 1 is don't know and 2 is yes.
	 */
	public boolean setAnswer(int num, int ans);

	/**
	 * @param num
	 *            is nonnegative number
	 * @return description of the num's question
	 */
	public String getQuestion(int num);

	/**
	 * 
	 * @return list of the pairs of names of abilities and level of these
	 *         abilities
	 */
	public QuizResult getResults();

	public int getLength();
}
