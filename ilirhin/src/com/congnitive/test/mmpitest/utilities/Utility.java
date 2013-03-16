package com.congnitive.test.mmpitest.utilities;

import android.content.res.Resources;

import com.congnitive.test.mmpitest.domainObjects.PsychologyTextedTest;

public class Utility {
	static private DataBase database = null;

	static public DataBase getDataBase() {
		return database;
	}

	static public void setDataBaseContext() {
		if (database == null) {
			database = new SQLiteDatabaseImpl();
		}
	}

	static public PsychologyTextedTest getTest(boolean gender, Resources res) {
		return new MMPITest(gender, res);
	}

	static public final String USER_ID_TAG = "Utility.userIdTag";
	public static final String QUIZ_RESULT_TAG = "Utility.quizResultTag";
}