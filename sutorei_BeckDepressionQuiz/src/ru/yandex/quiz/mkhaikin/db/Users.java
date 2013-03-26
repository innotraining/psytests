package ru.yandex.quiz.mkhaikin.db;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Date;



import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Users {
	public static final String CURRENT_USER_PREFERENCES_NAME = "CurrentUser";
	public static final String CURRENT_USER_PREFERENCES_FIELD_NAME = "CurrentUserID";

	private Context appContext;
	private SharedPreferences settings;
	private SQLiteOpenHelper quizDBOpenHelper;

	public Users(Context appContext) {
		this.appContext = appContext;
		settings = this.appContext.getSharedPreferences(
				CURRENT_USER_PREFERENCES_NAME, 0);
		quizDBOpenHelper = new QuizDBOpenHelper(appContext);
	}



	public Map<Integer, String> getUsers() {
		Map<Integer, String> result = new TreeMap<Integer, String>();
		SQLiteDatabase db = quizDBOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(QuizDBOpenHelper.UsersTable.TABLE_NAME, new String[] {
				QuizDBOpenHelper.UsersTable.ID, QuizDBOpenHelper.UsersTable.LOGIN }, null, null,
				null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.put(cursor.getInt(0), cursor.getString(1));
			cursor.moveToNext();
		}

		cursor.close();

		db.close();

		return result;
	}

	public SortedMap<Date, Integer> getUserResults(long id) {
		SortedMap<Date, Integer> result = new TreeMap<Date, Integer>();
		SQLiteDatabase db = quizDBOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(QuizDBOpenHelper.ResultsTable.TABLE_NAME, new String[] {
				QuizDBOpenHelper.ResultsTable.DATE, QuizDBOpenHelper.ResultsTable.RESULT }, QuizDBOpenHelper.ResultsTable.ID
				+ " = '" + id + "'", null,
				null, null, null);
	
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.put(new Date(cursor.getLong(0)), cursor.getInt(1));
			cursor.moveToNext();
		}

		cursor.close();

		db.close();

		return result;
	}
	
	public long saveUserResults(int result) {
		SQLiteDatabase db = quizDBOpenHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(QuizDBOpenHelper.ResultsTable.RESULT, result);
		cv.put(QuizDBOpenHelper.ResultsTable.DATE, (new Date().getTime()));
		cv.put(QuizDBOpenHelper.ResultsTable.ID, getCurrentUserId());

		long i = db.insert(QuizDBOpenHelper.ResultsTable.TABLE_NAME, null, cv);

		db.close();
		
		return i; 
	}
	public boolean existsUser(String login) {
		SQLiteDatabase db = quizDBOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(QuizDBOpenHelper.UsersTable.TABLE_NAME,
				new String[] { QuizDBOpenHelper.UsersTable.ID }, QuizDBOpenHelper.UsersTable.LOGIN
						+ " = '" + login + "'", null, null, null, null);

		boolean result = cursor.getCount() > 0;

		cursor.close();

		db.close();

		return result;
	}

	public int getUserId(String login) {
		SQLiteDatabase db = quizDBOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(QuizDBOpenHelper.UsersTable.TABLE_NAME, new String[] {
				QuizDBOpenHelper.UsersTable.ID, QuizDBOpenHelper.UsersTable.LOGIN },
				QuizDBOpenHelper.UsersTable.LOGIN + " = '" + login + "'", null, null,
				null, null);

		cursor.moveToFirst();
		int result = cursor.getInt(0);

		cursor.close();

		db.close();

		return result;
	}

	public long addUser(String login) throws IllegalArgumentException {
		if (existsUser(login)) {
			throw new IllegalArgumentException("User exists!");
		}

		SQLiteDatabase db = quizDBOpenHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(QuizDBOpenHelper.UsersTable.LOGIN, login);

		long id = db.insert(QuizDBOpenHelper.UsersTable.TABLE_NAME, null, cv);

		db.close();

		return id;
	}

	public void deleteUser(long id) {
		SQLiteDatabase db = quizDBOpenHelper.getWritableDatabase();
		this.logOff();
		db.delete(QuizDBOpenHelper.UsersTable.TABLE_NAME, QuizDBOpenHelper.UsersTable.ID + " = " + id, null);
		db.delete(QuizDBOpenHelper.ResultsTable.TABLE_NAME, QuizDBOpenHelper.ResultsTable.ID + " = " + id, null);

		db.close();
	}

	public void logIn(long id) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(CURRENT_USER_PREFERENCES_FIELD_NAME, id);
		editor.commit();
	}

	public void logOff() {
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}
	
	public long getCurrentUserId() {
		return settings.getLong(CURRENT_USER_PREFERENCES_FIELD_NAME, 0);
	}
}