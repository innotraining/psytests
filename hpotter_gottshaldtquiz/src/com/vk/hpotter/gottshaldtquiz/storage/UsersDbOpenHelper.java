package com.vk.hpotter.gottshaldtquiz.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDbOpenHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "gottshaldtquiz";

	public class UsersTable {
		public static final String TABLE_NAME = "users";
		public static final String ID = "id";
		public static final String LOGIN = "login";
		private static final String CREATE_TABLE = "create table " + TABLE_NAME
				+ " ( " + ID + " integer primary key autoincrement, " + LOGIN + " TEXT)";
	}
	
	public class ResultsTable {
		public static final String TABLE_NAME = "results";
		public static final String ID = "id";
		public static final String USERID = "userid";
		public static final String RESULT = "result";
		public static final String DATE = "testdate";
		private static final String CREATE_TABLE = "create table " + TABLE_NAME
				+ " ( " + ID + " integer primary key autoincrement, " + USERID + " integer, " + RESULT + " real, " + DATE + " long)";
	}

	public UsersDbOpenHelper(Context context) {
		super(context, DB_NAME, null,DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(UsersTable.CREATE_TABLE);
		sqLiteDatabase.execSQL(ResultsTable.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
