package ru.yandex.quiz.mkhaikin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class QuizDBOpenHelper extends SQLiteOpenHelper {
	public static int DB_VERSION = 1;
	public static String DB_NAME = "beckdepressionquiz";
	
	public class UsersTable{
		public static final String TABLE_NAME = "users";
		public static final String ID = "id";
		public static final String LOGIN = "login";
		private static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME +
				 " ( " + ID + " integer primary key autoincrement, " + LOGIN + " TEXT)";
	}
	public class ResultsTable{
		public static final String TABLE_NAME = "results";
		public static final String ID = "id";
		public static final String DATE = "date";
		public static final String RESULT = "result";
		private static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME +
				 " ( key integer primary key autoincrement, " + ID + " integer, " + RESULT + " integer, " + DATE + " long)";
	}
	
	
	
	public QuizDBOpenHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(UsersTable.CREATE_TABLE_QUERY);
		sqLiteDatabase.execSQL(ResultsTable.CREATE_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
}
