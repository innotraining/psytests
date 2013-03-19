package com.vk.hpotter.gottshaldtquiz;

import java.util.LinkedList;
import java.util.Queue;

public class GottshaldtQuiz {
	private Queue<Integer> questions;
	private Queue<String> answers;

	@SuppressWarnings("serial")
	public GottshaldtQuiz() {
		questions = new LinkedList<Integer>() {
			{
				add(R.drawable.test1);
				add(R.drawable.test2);
				add(R.drawable.test3);
				add(R.drawable.test4);
				add(R.drawable.test5);
				add(R.drawable.test6);
				add(R.drawable.test7);
				add(R.drawable.test8);
				add(R.drawable.test9);
				add(R.drawable.test10);
			}
		};

		answers = new LinkedList<String>() {
			{
				add("А");
				add("Б");
				add("В");
				add("Г");
				add("В");
				add("В");
				add("А");
				add("В");
				add("Д");
				add("Д");
				add("Б");
				add("А");
				add("А");
				add("В");
				add("Б");
				add("Д");
				add("А");
				add("Д");
				add("Б");
				add("В");
				add("Г");
				add("Б");
				add("Г");
				add("А");
				add("Д");
				add("Б");
				add("А");
				add("Д");
				add("В");
				add("Б");
			}
		};
	}

	public int getNext() {
		return questions.poll();
	}

	public boolean isEmpty() {
		return questions.isEmpty();
	}

	public boolean checkNextAnswer(String answer) {
		return answer.equalsIgnoreCase(answers.poll());
	}

	public int getDescription(double I) {
		if (I < 2.5) {
			return R.string.negative_test_result_description;
		} else {
			return R.string.positive_test_result_description;
		}
	}
}
