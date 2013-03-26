package com.vk.hpotter.gottshaldtquiz;

import java.util.LinkedList;
import java.util.Queue;

public class GottshaldtQuiz {
	private Queue<Integer> questions;
	private Queue<Integer> answers;

	@SuppressWarnings("serial")
	public GottshaldtQuiz() {
		questions = new LinkedList<Integer>() {
			{
				add(R.drawable.quiz1);
				add(R.drawable.quiz2);
				add(R.drawable.quiz3);
				add(R.drawable.quiz4);
				add(R.drawable.quiz5);
				add(R.drawable.quiz6);
				add(R.drawable.quiz7);
				add(R.drawable.quiz8);
				add(R.drawable.quiz9);
				add(R.drawable.quiz10);
				add(R.drawable.quiz11);
				add(R.drawable.quiz12);
				add(R.drawable.quiz13);
				add(R.drawable.quiz14);
				add(R.drawable.quiz15);
				add(R.drawable.quiz16);
				add(R.drawable.quiz17);
				add(R.drawable.quiz18);
				add(R.drawable.quiz19);
				add(R.drawable.quiz20);
				add(R.drawable.quiz21);
				add(R.drawable.quiz22);
				add(R.drawable.quiz23);
				add(R.drawable.quiz24);
				add(R.drawable.quiz25);
				add(R.drawable.quiz26);
				add(R.drawable.quiz27);
				add(R.drawable.quiz28);
				add(R.drawable.quiz29);
				add(R.drawable.quiz30);

			}
		};

		answers = new LinkedList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
				add(4);
				add(3);
				add(3);
				add(1);
				add(3);
				add(5);
				add(5);
				add(2);
				add(1);
				add(1);
				add(3);
				add(2);
				add(5);
				add(1);
				add(5);
				add(2);
				add(3);
				add(4);
				add(2);
				add(4);
				add(1);
				add(5);
				add(2);
				add(1);
				add(5);
				add(3);
				add(2);
			}
		};
	}

	public int getNext() {
		return questions.poll();
	}

	public boolean isEmpty() {
		return questions.isEmpty();
	}

	public boolean checkNextAnswer(Integer answer) {
		return answer == answers.poll();
	}

	public int remaining() {
		return questions.size();
	}
	
	public int getDescription(double I) {
		if (I < 2.5) {
			return R.string.dependent_result_description;
		} else {
			return R.string.not_dependent_result_description;
		}
	}
}
