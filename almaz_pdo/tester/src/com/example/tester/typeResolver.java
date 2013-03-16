package com.example.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.animation.TypeEvaluator;
import android.util.Log;

public class typeResolver {
	// int hypertim, int cycloid, int labil, int as_nervous, int sensetive, int
	// psyhantic, int shizoid, int epileptic, int isteroid, int unstable, int
	// conform
	String types[] = null;
	int[] typesNumbers = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
	int[] typesWeights = new int[] { 4, 1, 2, 5, 3, 6, 7, 9, 8, 10, 0 };
	boolean[] typesState = null;
	int reachedCount = 0;
	int[] values = null;

	// HERE COME RULES OF FIGHT CLUB

	private void ruleZero() {
		// reaching of MDN
		if (values[0] < 7) {
			typesState[0] = false;
			values[0] = 0;
		} else
			values[0] -= 6;

		if (values[3] < 5) {
			typesState[3] = false;
			values[3] = 0;
		} else
			values[3] -= 4;
		for (int i = 1; i < values.length; ++i) {
			if (i != 3) {
				if (values[i] < 6) {
					typesState[i] = false;
					values[i] = 0;
				}
				values[i] -= 5;
			}
		}
	}

	private void ruleFour() {
		// checking for conform type

		for (int i = 0; i < typesState.length; ++i) {
			if (typesState[i])
				reachedCount++;
		}
		if (reachedCount > 1 && typesState[typesState.length - 1])
			typesState[typesState.length - 1] = false;
	}

	private void ruleFive() {
		if (reachedCount == 2) {
			int mixedType = 1;
			for (int i = 0; i < typesState.length; ++i)
				if (typesState[i])
					mixedType *= typesNumbers[i];
			List<Integer> stackableTypes = new ArrayList<Integer>(
					Arrays.asList(35, 55, 115, 145, 323, 391, 493, 437, 551,
							667, 6, 58, 46, 77, 91, 161, 143, 187, 221));
			if (stackableTypes.contains(mixedType)) {
				// KRUTO
			} else {
				// b or c
				int maxPoints = 0;
				int reachedCount = 0;
				for (int i = 0; i < values.length; ++i) {
					if (maxPoints > values[i]) {
						maxPoints = values[i];
						reachedCount = 1;
					} else if (maxPoints == values[i]) {
						reachedCount++;
					}
				}
				if (reachedCount == 1) {
					// b
					for (int i = 0; i < values[i]; ++i)
						if (values[i] != maxPoints)
							typesState[i] = false;
				} else {
					// c
					if (typesState[0]) {
						// гипертимный
						typesState[2] = false;
						typesState[4] = false;
					}
					if (typesState[3]) {
						// аснето..
						typesState[0] = false;
						typesState[1] = false;
					}
					if (typesState[4]) {
						// сенсетивный
						typesState[1] = false;
					}
					if (typesState[5]) {
						// психантический
						typesState[0] = false;
						typesState[1] = false;
						typesState[2] = false;
					}
					if (typesState[6]) {
						// шизоидный
						typesState[0] = false;
						typesState[1] = false;
						typesState[2] = false;
						typesState[3] = false;
					}
					if (typesState[7]) {
						// эпитептический
						typesState[0] = false;
						typesState[1] = false;
						typesState[2] = false;
						typesState[3] = false;
						typesState[4] = false;
						typesState[5] = false;
					}
					if (typesState[8]) {
						// истероидный
						typesState[1] = false;
						typesState[4] = false;
						typesState[5] = false;
					}
					if (typesState[9]) {
						// нестабильный
						typesState[1] = false;
						typesState[4] = false;
						typesState[5] = false;
					}
				}
			}
		}
	}

	private void ruleSix() {
		int maxPoint = 0;

		for (int i = 0; i < values.length; ++i) {
			maxPoint = Math.max(maxPoint, values[i]);
		}

		int lesserNum = 0;

		for (int i = 0; i < values.length; ++i) {
			if (maxPoint - values[i] < 4)
				lesserNum++;
		}

		if (lesserNum == values.length - 1) {
			for (int i = 0; i < values.length; ++i)
				if (values[i] != maxPoint)
					typesState[i] = false;
		}
	}

	private void ruleSeven() {
		int maxPoint = 0;
		int maxPointNum = -1;
		for (int i = 0; i < values.length; ++i) {
			if (maxPoint < values[i]) {
				maxPointNum = i;
				maxPoint = values[i];
			}
		}
		
		maxPoint = 0;
		int almostMax = -1;
		for (int i = 0; i < values.length; ++i) {
			if (maxPoint < values[i] && i != maxPointNum) {
				almostMax = i;
				maxPoint = values[i];
			}
		}

		for (int i = 0; i < values.length; ++i)
			if (i != maxPointNum && i != almostMax) {
				typesState[i] = false;
			}
	}

	public String resolve(int[] incomingValues) {
		types = new String[] { "Гипертимный", "Циклоидный", "Лабильный",
				"Астено-невротический ", "Сенсетивный", "Психастенический",
				"Шизоидный", "Эпилептоидный", "Истероидный", "Неустойчивый",
				"Конформный" };
		typesState = new boolean[types.length];
		for (int i = 0; i < typesState.length; ++i)
			typesState[i] = true;
		values = incomingValues;

		ruleZero();
		ruleFour();
		ruleFive();
		ruleSix();
		ruleSeven();

		String resultType = "";
		for (int i = 0; i < values.length; ++i)
			if (typesState[i]) {
				resultType += types[i] + " ";
			}
		return resultType;
	}
}
