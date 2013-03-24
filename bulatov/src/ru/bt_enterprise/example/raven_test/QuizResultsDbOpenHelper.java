package ru.bt_enterprise.example.raven_test;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizResultsDbOpenHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "ravenquiz";

	public static final String TABLE_NAME = "results";
	public static final String USERID = "userid";
	public static final String RESULT = "result";
	private static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ " ( _id integer primary key autoincrement, " + USERID + " integer, " + RESULT + " real)";

	public QuizResultsDbOpenHelper(Context context) {
		super(context, DB_NAME, null,DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
