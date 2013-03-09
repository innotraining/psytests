package com.example.tester;

public class typeResolver {
	// int hypertim, int cycloid, int labil, int as_nervous, int sensetive, int psyhantic, int shizoid, int epileptic, int isteroid, int unstable, int conform
	public static String resolve(int[] values) {
		String[] types = {"Гипертимный", "Циклоидный", "Лабильный", "Астено-невротический ", "Сенсетивный", "Психастенический", "Шизоидный", "Эпилептоидный", "Истероидный", "Неустойчивый", "Конформный"};
		if (values[0] < 7) values[0] = 0;
		if (values[3] < 5) values[3] = 0;
		for (int i = 1; i < values.length; ++i) {
			if (i != 3) {
				if (values[i] < 6) values[i] = 0;
			}
		}
		int reachedCount = 0;
		int reachedNum = -1;
		for (int i = 0; i < values.length; ++i) {
			if (i == values.length - 1 && reachedCount != 0) {
				
			} else
			if (values[i] != 0) {
				reachedCount++;
				reachedNum = i;
			}
		}
		if (reachedCount == 0) {
			return "Неопределенный";
		}
		if (reachedCount == 1)
			return types[reachedNum];
		else {
			String result = "";
			for (int i = 0; i < values.length; ++i)
				if (values[i] > 0)
					result += (types[i] + "+");
			return result;
		}
	}
}
