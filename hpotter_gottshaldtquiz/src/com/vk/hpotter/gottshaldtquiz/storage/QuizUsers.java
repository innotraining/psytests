package com.vk.hpotter.gottshaldtquiz.storage;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizUsers {
	public static final String CURRENT_USER_PREFERENCES_NAME = "CurrentUser";
	public static final String CURRENT_USER_PREFERENCES_FIELD_NAME = "CurrentUserID";

	private Context appContext;
	private SharedPreferences settings;
	private SQLiteOpenHelper dbOpenHelper;

	// private SimpleDateFormat formatter;

	public QuizUsers(Context appContext) {
		this.appContext = appContext;
		settings = this.appContext.getSharedPreferences(
				CURRENT_USER_PREFERENCES_NAME, 0);
		dbOpenHelper = new UsersDbOpenHelper(appContext);
		// formatter = new SimpleDateFormat(DATE_FORMATTER_STRING);
	}

	public Map<Integer, String> getUsers() {
		Map<Integer, String> result = new TreeMap<Integer, String>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(UsersDbOpenHelper.UsersTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.UsersTable.ID,
						UsersDbOpenHelper.UsersTable.LOGIN }, null, null, null,
				null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.put(cursor.getInt(0), cursor.getString(1));
			cursor.moveToNext();
		}

		cursor.close();

		db.close();

		return result;
	}

	public SortedMap<Date, Double> getUserResults(long id) {
		SortedMap<Date, Double> result = new TreeMap<Date, Double>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.ResultsTable.DATE,
						UsersDbOpenHelper.ResultsTable.RESULT },
				UsersDbOpenHelper.ResultsTable.USERID + " = '" + id + "'",
				null, null, null, null);

		// Cursor cursor = db.query(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
		// new String[] {
		// UsersDbOpenHelper.ResultsTable.DATE,
		// UsersDbOpenHelper.ResultsTable.RESULT }, null, null,
		// null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			result.put(new Date(cursor.getLong(0)), cursor.getDouble(1));
			cursor.moveToNext();
		}

		cursor.close();

		db.close();

		return result;
	}

	public long saveUserResult(Double result) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(UsersDbOpenHelper.ResultsTable.RESULT, result);
		// try {
		// cv.put(UsersDbOpenHelper.ResultsTable.DATE,
		// formatter.parse(formatter.format(new Date())).getTime());
		cv.put(UsersDbOpenHelper.ResultsTable.DATE, (new Date().getTime()));
		cv.put(UsersDbOpenHelper.ResultsTable.USERID, getCurrentUserId());
		// } catch (ParseException e) {
		// //Nothing to do here
		// }

		long i = db.insert(UsersDbOpenHelper.ResultsTable.TABLE_NAME, null, cv);

		db.close();

		return i;
	}

	public boolean existsUser(String login) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(UsersDbOpenHelper.UsersTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.UsersTable.ID },
				UsersDbOpenHelper.UsersTable.LOGIN + " = '" + login + "'",
				null, null, null, null);

		boolean result = cursor.getCount() > 0;

		cursor.close();

		db.close();

		return result;
	}

	public int getUserId(String login) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(UsersDbOpenHelper.UsersTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.UsersTable.ID,
						UsersDbOpenHelper.UsersTable.LOGIN },
				UsersDbOpenHelper.UsersTable.LOGIN + " = '" + login + "'",
				null, null, null, null);

		cursor.moveToFirst();
		int result = cursor.getInt(0);

		cursor.close();

		db.close();

		return result;
	}

	public int getUserResultIdByDate(Date date) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		Cursor cursor = db.query(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.ResultsTable.ID },
				UsersDbOpenHelper.ResultsTable.DATE + " = '" + date.getTime() + "'",
				null, null, null, null);

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

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(UsersDbOpenHelper.UsersTable.LOGIN, login);

		long id = db.insert(UsersDbOpenHelper.UsersTable.TABLE_NAME, null, cv);

		db.close();

		return id;
	}

	public void deleteUser(long id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		db.delete(UsersDbOpenHelper.UsersTable.TABLE_NAME,
				UsersDbOpenHelper.UsersTable.ID + " = " + id, null);
		db.delete(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
				UsersDbOpenHelper.ResultsTable.USERID + " = " + id, null);

		db.close();
	}
	
	public void deleteUserResult(long id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		
		
		Cursor cursor = db.query(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
				new String[] { UsersDbOpenHelper.ResultsTable.ID, UsersDbOpenHelper.ResultsTable.USERID },
//				UsersDbOpenHelper.ResultsTable.ID + " = " + id + "",
				null,
				null, null, null, null);

		cursor.moveToFirst();
		System.err.println(cursor.getCount());
		
		db.delete(UsersDbOpenHelper.ResultsTable.TABLE_NAME,
				UsersDbOpenHelper.ResultsTable.ID + " = " + id, null);

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
