package com.congnitive.test.mmpitest.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import android.content.res.Resources;

import com.congnitive.test.mmpitest.R;
import com.congnitive.test.mmpitest.domainObjects.PsychologyTextedTest;
import com.congnitive.test.mmpitest.domainObjects.QuizResult;

public class MMPITest implements PsychologyTextedTest {

	public final static int CRITICAL_LIE_LEVEL = 30;

	private class MMPIAnswerHandler {
		protected int value = 0;
		protected Set<Integer> upQuestions = new HashSet<Integer>();
		protected Set<Integer> downQuestions = new HashSet<Integer>();
		protected String name;

		public MMPIAnswerHandler(String name, Resources res, int upNumsId,
				int downNumsId, int descrId) {
			this.name = name;
			handlers.add(this);
			try {
				Scanner scanner = new Scanner(res.openRawResource(downNumsId));
				while (scanner.hasNext()) {
					int num = Integer.parseInt(scanner.next());
					downQuestions.add(num - 1);
					answerHandlers.get(num - 1).add(this);
				}
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Scanner scanner = new Scanner(res.openRawResource(upNumsId));
				while (scanner.hasNext()) {
					int num = Integer.parseInt(scanner.next());
					upQuestions.add(num - 1);
					answerHandlers.get(num - 1).add(this);
				}
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String getDescribtion() {
			return null;
		}

		public String getName() {
			return name;

		}

		public int getTPoints() {
			int res = 50 + value * 10;
			return Math.min(Math.max(res, 0), 100);
		}

		public void vote(int result, int num) {
			if (upQuestions.contains(num))
				value += (result - 1);
			if (downQuestions.contains(num))
				value -= (result - 1);
		}
	}

	private class LieHandler extends MMPIAnswerHandler {
		public LieHandler(Resources res) {
			super("Lie", res, 0, R.raw.lie_nums, 0);
		}

		public int getLieLevel() {
			int res = 50 + value * 10;
			return Math.min(Math.max(res, 0), 100);
		}
	}

	private Vector<String> questions = new Vector<String>();
	private Vector<MMPIAnswerHandler> handlers = new Vector<MMPIAnswerHandler>();
	private Vector<List<MMPIAnswerHandler>> answerHandlers = new Vector<List<MMPIAnswerHandler>>();
	private LieHandler lieHandler;

	public MMPITest(boolean isMale, Resources res) {
		try {
			int questionsId;
			if (isMale) {
				questionsId = R.raw.male_mmpi;
			} else {
				questionsId = R.raw.female_mmpi;
			}
			Scanner scanner = new Scanner(res.openRawResource(questionsId));
			while (scanner.hasNext()) {
				questions.add(scanner.nextLine());
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		answerHandlers.setSize(questions.size());
		for (int i = 0; i < questions.size(); i++) {
			answerHandlers.set(i, new ArrayList<MMPITest.MMPIAnswerHandler>());
		}
		lieHandler = new LieHandler(res);
		new MMPIAnswerHandler("Correction", res, R.raw.correction_up_nums,
				R.raw.correction_down_nums, 0);
		new MMPIAnswerHandler("Anxiety", res, R.raw.anxiety_up_nums,
				R.raw.anxiety_down_nums, 0);
		if (isMale) {
			new MMPIAnswerHandler("FeminityMale", res,
					R.raw.femininity_up_nums_male,
					R.raw.femininity_down_nums_male, 0);
		} else {
			new MMPIAnswerHandler("feminityFemale", res,
					R.raw.femininity_up_nums_female,
					R.raw.femininity_down_nums_female, 0);
		}
		new MMPIAnswerHandler("Hysteria", res, R.raw.hysteria_up_nums,
				R.raw.hysteria_down_nums, 0);
		new MMPIAnswerHandler("Impulsivity", res, R.raw.impulsiveness_up_nums,
				R.raw.impulsiveness_down_nums, 0);
		new MMPIAnswerHandler("Individuality", res,
				R.raw.individualistic_up_nums, R.raw.individualistic_down_nums,
				0);
		new MMPIAnswerHandler("Introversity", res, R.raw.introversion_up_nums,
				R.raw.introversion_down_nums, 0);
		new MMPIAnswerHandler("Optimistic", res, R.raw.optimistic_up_nums,
				R.raw.optimistic_down_nums, 0);
		new MMPIAnswerHandler("PverControl", res, R.raw.overcontrol_up_nums,
				R.raw.overcontrol_down_nums, 0);
		new MMPIAnswerHandler("Pessimistic", res,
				R.raw.pessimistically_up_nums, R.raw.pessimistically_down_nums,
				0);
		new MMPIAnswerHandler("Reliability", res, R.raw.reliability_up_nums,
				R.raw.reliability_down_nums, 0);
		new MMPIAnswerHandler("Stiff", res, R.raw.stiff_up_nums,
				R.raw.stiff_down_nums, 0);
	}

	@Override
	public boolean setAnswer(int num, int ans) {
		for (int i = 0; i < answerHandlers.get(num).size(); i++) {
			answerHandlers.get(num).get(i).vote(ans, num);
		}
		return (lieHandler.getLieLevel() <= MMPITest.CRITICAL_LIE_LEVEL);
	}

	@Override
	public String getQuestion(int num) {
		return questions.get(num);
	}

	@Override
	public QuizResult getResults() {
		QuizResult result = new QuizResult();
		for (int i = 0; i < handlers.size(); i++) {
			result.addSkill(result.new QuizEnty(handlers.get(i).getName(),
					handlers.get(i).getDescribtion(), handlers.get(i)
							.getTPoints()));
		}
		return result;
	}

	@Override
	public int getLength() {
		return 100;
	}

}
