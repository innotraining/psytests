package com.example.tester;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "testerappdata";

	public static final String TABLE_NAME = "userdata";

	private static final String CREATE_TABLE = "create table "
			+ TABLE_NAME
			+ " ( _id integer primary key autoincrement, "
			+ "username text, hypertim integer default 0, cycloid integer default 0, labil integer default 0, as_nervous integer default 0, sensetive integer default 0, "
			+ "psyhantic integer default 0, shizoid integer default 0, epileptic integer default 0, isteroid integer default 0, unstable integer default 0, conform integer default 0)";

	public DbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}
}