package com.congnitive.test.mmpitest.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import biz.source_code.base64Coder.Base64Coder;

import com.congnitive.test.mmpitest.domainObjects.QuizResult;
import com.congnitive.test.mmpitest.domainObjects.User;

public class SQLiteDatabaseImpl implements DataBase {
	private class QuizDataBase extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "quizDatabase.db";
		private static final int DATABASE_VERSION = 9;
		public static final String TABLE_USER_NAME = "userTable";
		public static final String USER_ID = "userId";
		public static final String USER_NAME = "userName";
		public static final String USER_DESC = "userDescribtion";

		public static final String TABLE_QUIZ_NAME = "quizTable";
		public static final String QUIZ_USER_ID = "quizUserId";
		public static final String QUIZ_ID = "quizId";
		public static final String QUIZ_DESC = "quizDescribtion";
		public static final String QUIZ_DATE = "quizDate";

		private static final String SQL_CREATE_USER_ENTRIES = "CREATE TABLE "
				+ TABLE_USER_NAME + " (" + USER_ID + " TEXT PRIMARY KEY , "
				+ USER_NAME + " TEXT, " + USER_DESC + " TEXT)";
		private static final String SQL_DELETE_USER_ENTRIES = "DROP TABLE IF EXISTS "
				+ TABLE_USER_NAME;
		private static final String SQL_CREATE_QUIZ_ENTRIES = "CREATE TABLE "
				+ TABLE_QUIZ_NAME + " (" + QUIZ_ID + " TEXT PRIMARY KEY , "
				+ QUIZ_USER_ID + " TEXT , " + QUIZ_DATE + " TEXT," + QUIZ_DESC
				+ " TEXT)";
		private static final String SQL_DELETE_QUIZ_ENTRIES = "DROP TABLE IF EXISTS "
				+ TABLE_QUIZ_NAME;

		public QuizDataBase(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(android.database.sqlite.SQLiteDatabase db) {
			// db.execSQL(SQL_DELETE_USER_ENTRIES);
			// db.execSQL(SQL_DELETE_QUIZ_ENTRIES);
			db.execSQL(SQL_CREATE_USER_ENTRIES);
			db.execSQL(SQL_CREATE_QUIZ_ENTRIES);
		}

		@Override
		public void onUpgrade(android.database.sqlite.SQLiteDatabase db,
				int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_USER_ENTRIES);
			db.execSQL(SQL_DELETE_QUIZ_ENTRIES);
			onCreate(db);

		}

	}

	private Object deserializeObject(String s) {
		byte[] data = Base64Coder.decode(s);
		Object result = null;

		try {
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream(data));
			result = ois.readObject();
			ois.close();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String serialaizeObject(Serializable o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64Coder.encode(baos.toByteArray()));
	}

	@Override
	public UUID saveUser(Context context, User usr)
			throws IllegalArgumentException {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb;
		sqdb = sqh.getReadableDatabase();
		String query = "SELECT " + QuizDataBase.USER_ID + " FROM "
				+ QuizDataBase.TABLE_USER_NAME + " WHERE "
				+ QuizDataBase.USER_NAME + " = " + "'" + usr.getName() + "'";
		Cursor cursor = sqdb.rawQuery(query, null);
		if (cursor.getCount() == 0) {
			sqdb = sqh.getWritableDatabase();
			UUID uuid = UUID.randomUUID();
			String insertQuery = "INSERT INTO " + QuizDataBase.TABLE_USER_NAME
					+ " (" + QuizDataBase.USER_ID + " , "
					+ QuizDataBase.USER_NAME + " , " + QuizDataBase.USER_DESC
					+ ") VALUES (" + "'" + uuid + "'" + " , " + "'"
					+ usr.getName() + "'" + ", '" + serialaizeObject(usr)
					+ "')";
			sqdb.execSQL(insertQuery);
			sqdb.close();
			sqh.close();
			return uuid;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public User getUserById(Context context, UUID id) {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		String query = "SELECT " + QuizDataBase.USER_DESC + " FROM "
				+ QuizDataBase.TABLE_USER_NAME + " WHERE "
				+ QuizDataBase.USER_ID + " = " + "'" + id + "'";
		Cursor cursor = sqdb.rawQuery(query, null);
		User user = null;
		while (cursor.moveToNext()) {
			user = (User) deserializeObject(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.USER_DESC)));
		}
		cursor.close();
		sqdb.close();
		sqh.close();
		return user;
	}

	@Override
	public UUID getIdByUser(Context context, User usr) {

		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		String query = "SELECT " + QuizDataBase.USER_ID + " FROM "
				+ QuizDataBase.TABLE_USER_NAME + " WHERE "
				+ QuizDataBase.USER_DESC + " = " + "'" + serialaizeObject(usr)
				+ "'";
		Cursor cursor = sqdb.rawQuery(query, null);
		UUID userId = new UUID(0, 0);
		while (cursor.moveToNext()) {
			userId = UUID.fromString(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.USER_ID)));
		}
		cursor.close();
		sqdb.close();
		sqh.close();
		return userId;
	}

	@Override
	public void removeUser(Context context, UUID userId) {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		sqdb.delete(QuizDataBase.TABLE_USER_NAME, QuizDataBase.USER_ID + "="
				+ "'" + userId + "'", null);
		sqdb.delete(QuizDataBase.TABLE_QUIZ_NAME, QuizDataBase.QUIZ_USER_ID
				+ "=" + "'" + userId + "'", null);
		sqdb.close();
		sqh.close();
	}

	@Override
	public User[] getAllUsers(Context context) {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		String query = "SELECT " + QuizDataBase.USER_DESC + " FROM "
				+ QuizDataBase.TABLE_USER_NAME;
		Cursor cursor = sqdb.rawQuery(query, null);
		List<User> userList = new ArrayList<User>();
		while (cursor.moveToNext()) {
			userList.add((User) deserializeObject(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.USER_DESC))));
		}
		cursor.close();
		sqdb.close();
		sqh.close();
		return userList.toArray(new User[userList.size()]);
	}

	@Override
	public UUID addTestToUser(Context context, UUID userId,
			QuizResult testResults, Date date) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",
				Locale.US);
		try {
			date = dateFormatter.parse(dateFormatter.format(date));
		} catch (ParseException e) {
		}
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getWritableDatabase();
		UUID quizId = UUID.randomUUID();
		String insertQuery = "INSERT INTO " + QuizDataBase.TABLE_QUIZ_NAME
				+ " (" + QuizDataBase.QUIZ_ID + "," + QuizDataBase.QUIZ_USER_ID
				+ "," + QuizDataBase.QUIZ_DATE + "," + QuizDataBase.QUIZ_DESC
				+ ") VALUES (" + "'" + quizId + "'" + " , '" + userId + "', '"
				+ serialaizeObject(date) + "', '"
				+ serialaizeObject(testResults) + "')";
		sqdb.execSQL(insertQuery);
		sqdb.close();
		sqh.close();
		return quizId;
	}

	@Override
	public Map<Date, List<QuizResult>> getAllTestsOfUser(Context context,
			UUID userId) {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		String query = "SELECT * FROM " + QuizDataBase.TABLE_QUIZ_NAME
				+ " WHERE " + QuizDataBase.QUIZ_USER_ID + " = '" + userId + "'";
		Cursor cursor = sqdb.rawQuery(query, null);
		Map<Date, List<QuizResult>> result = new TreeMap<Date, List<QuizResult>>();
		QuizResult quizResult;
		Date date;
		while (cursor.moveToNext()) {
			quizResult = (QuizResult) deserializeObject(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.QUIZ_DESC)));
			date = (Date) deserializeObject(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.QUIZ_DATE)));
			if (!result.containsKey(date)) {
				result.put(date, new ArrayList<QuizResult>());
			}
			result.get(date).add(quizResult);
		}
		cursor.close();
		sqdb.close();
		sqh.close();
		if (result.isEmpty())
			return null;
		else
			return result;
	}

	@Override
	public QuizResult getTestResultById(Context context, UUID testId) {
		QuizDataBase sqh = new QuizDataBase(context);
		SQLiteDatabase sqdb = sqh.getReadableDatabase();
		String query = "SELECT " + QuizDataBase.QUIZ_DESC + " FROM "
				+ QuizDataBase.TABLE_QUIZ_NAME + " WHERE "
				+ QuizDataBase.QUIZ_ID + " = " + "'" + testId + "'";
		Cursor cursor = sqdb.rawQuery(query, null);
		QuizResult quizResult = null;
		while (cursor.moveToNext()) {
			quizResult = (QuizResult) deserializeObject(cursor.getString(cursor
					.getColumnIndex(QuizDataBase.USER_DESC)));
		}
		cursor.close();
		sqdb.close();
		sqh.close();
		return quizResult;
	}

	@Override
	public Date getTestDate(Context context, UUID userId, UUID testId) {
		// TODO Auto-generated method stub
		return null;
	}

}
