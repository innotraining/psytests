package com.example.eysencktest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper{

	  private static final int DB_VERSION = 1;
	  private static final String DB_NAME = "test";
	  public static final String TABLE_NAME = "users";
	  public static final String LOGIN = "login";
	  public static final String  DATE = "date";
	  public static final String  FALSEM = "falseM";
	  public static final String  INEXTROM = "introextroM";
	  public static final String  STABLEM = "stableM";
	  public static final String FIX ="fix"; 
	  private static final String COLUMN_ID = "_id";
	  
	  private static final String CREATE_TABLE =  " create table " + TABLE_NAME + " ("
			  + COLUMN_ID + " integer primary key autoincrement, "
		      + LOGIN + " TEXT, "
			  + DATE + " TEXT, "
		      + FALSEM +" INTEGER, "
			  + INEXTROM + " INTEGER, "
		      + STABLEM + " INTEGER, "
		      + FIX + " TEXT ); ";
	  
		  
	  public DbOpenHelper(Context context) {
	    super(context, DB_NAME, null,DB_VERSION);
	  }

	
	  public void onCreate(SQLiteDatabase sqLiteDatabase) {
	     sqLiteDatabase.execSQL(CREATE_TABLE);
	  }

	  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		  //sqLiteDatabase.execSQL(CREATE_TABLE);
	  }
}

	