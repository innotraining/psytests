package com.mistdale.test2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDbHelper extends SQLiteOpenHelper {
	 
	 public static final String dbName="users.db";
	 public static final int dbVersion = 6;
	 
	 //public static final String TABLE_USERS="Users";
	 //public static final String colId="UserID";
	 //public static final String COLUMN_USERS_NAME="UserName"; // PRIMARY KEY ID
	 
	 //public static final String TABLE_RESULTS="Results";
	 //public static final String colResultId="ResultID";
	 //public static final String COLUMN_RESULTS_DATE="ResultsDate"; // PRIMARY KEY ID
	 //public static final String COLUMN_RESULTS_PARAMS="ResultsParams";
	 //public static final String COLUMN_RESULTS_USER="ResultsUser";
	 
	public TestDbHelper(Context context) {
	  super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// CREATE TABLE Users (UserName TEXT NOT NULL PRIMARY KEY)
		db.execSQL("CREATE TABLE " + TestDbContract.TableUsers.TABLE_NAME + " (" + TestDbContract.TableUsers.COLUMN_NAME_USERNAME + " TEXT NOT NULL PRIMARY KEY)");
		// CREATE TABLE Results (ResultsDate INTEGER PRIMARY KEY , ResultsParams TEXT, ResultsUser TEXT, ResultsDateWithoutTime INTEGER)
		db.execSQL("CREATE TABLE " + TestDbContract.TableResults.TABLE_NAME + " (" + 
		TestDbContract.TableResults.COLUMN_NAME_DATE + " INTEGER PRIMARY KEY , " 
		+ TestDbContract.TableResults.COLUMN_NAME_PARAMS + " TEXT , " + TestDbContract.TableResults.COLUMN_NAME_USER + " TEXT , " + 
		 TestDbContract.TableResults.COLUMN_NAME_DATE_DAY + " INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + "users.db");
		
		db.execSQL("DROP TABLE IF EXISTS " + TestDbContract.TableUsers.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TestDbContract.TableResults.TABLE_NAME);
		
		onCreate(db);
	}
}