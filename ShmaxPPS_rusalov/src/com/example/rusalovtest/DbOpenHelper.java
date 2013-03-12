package com.example.rusalovtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "mydatabase.db";
	
	public static final String TABLE_NAME_USER = "users";
	public static final String TABLE_NAME_RESULT = "results";
	public static final String USER= "user";
	
	private static final String CREATE_TABLE_USER ="CREATE TABLE "+TABLE_NAME_USER+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
	        +USER+" TEXT UNIQUE);";
	private static final String CREATE_TABLE_RESULT ="CREATE TABLE "+TABLE_NAME_RESULT+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
	        +USER+" TEXT," + "energy INTEGER, socialEnergy INTEGER, plasticity INTEGER, socialPlasticity INTEGER, pace INTEGER," +
	        		"  socialPace INTEGER, emotionality INTEGER, socialEmotionality INTEGER, k INTEGER);";
	
	public DbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE_USER);
		sqLiteDatabase.execSQL(CREATE_TABLE_RESULT);
	}	
	
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		Log.w(DbOpenHelper.class.getName(), 
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RESULT);
		onCreate(sqLiteDatabase);
	}
}
