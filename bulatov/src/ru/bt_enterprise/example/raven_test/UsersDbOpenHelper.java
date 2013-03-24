package ru.bt_enterprise.example.raven_test;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsersDbOpenHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "ravenquiz";

	public static final String TABLE_NAME = "users";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String SEX = "sex";
	public static final String AGE_M = "month";
	public static final String AGE_D = "day";
	public static final String AGE_Y = "year";
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( "
			+ ID + " integer primary key autoincrement, " + LOGIN + " TEXT, " + SEX + " TEXT, "
			+ AGE_Y + " integer, "+ AGE_M + " integer, "+ AGE_D + " integer)";

	public UsersDbOpenHelper(Context context) {
		super(context, DB_NAME, null,DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("dbStatus", "Updating userstations database");

	    if (oldVersion == 1 && newVersion == 2){
			String upgradeQuery0 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + SEX + " TEXT;";
			String upgradeQuery1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + AGE_Y + " integer;";
			String upgradeQuery2 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + AGE_M + " integer;";
			String upgradeQuery3 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + AGE_D + " integer;";
			db.execSQL(upgradeQuery0);
			db.execSQL(upgradeQuery1);
			db.execSQL(upgradeQuery2);
			db.execSQL(upgradeQuery3);
	    }
	}

}
