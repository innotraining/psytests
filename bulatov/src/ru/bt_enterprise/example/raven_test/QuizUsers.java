package ru.bt_enterprise.example.raven_test;

import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class QuizUsers {
	public static final String CURRENT_USER_PREFERENCES_NAME = "CurrentUser"; 
	public static final String CURRENT_USER_PREFERENCES_FIELD_NAME = "CurrentUserID";
	
	private Context appContext;
	
	public QuizUsers(Context appContext) {
		this.appContext = appContext;
	}

	public void recreate(){

		/*
		String TABLE_NAME = "users";
		String ID = "id";
		String LOGIN = "login";
		String SEX = "sex";
		String AGE_M = "month";
		String AGE_D = "day";
		String AGE_Y = "year";
		String CREATE_TABLE = "create table " + TABLE_NAME + " ( "
				+ ID + " integer primary key autoincrement, " + LOGIN + " TEXT, " + SEX + " TEXT, "
				+ AGE_Y + " integer, "+ AGE_M + " integer, "+ AGE_D + " integer)";

		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Log.i("1", db.toString());
*/
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
//		db.execSQL("DROP_TABLE(users)");
//		db.execSQL(CREATE_TABLE);
        db.close();
	}

	
	public Map<Integer, String> getUsers() {
		Map<Integer, String> result = new TreeMap<Integer, String>();
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        
        Cursor cursor = db.query(UsersDbOpenHelper.TABLE_NAME, new String[] {UsersDbOpenHelper.ID, UsersDbOpenHelper.LOGIN}, null, null, null, null, null);
        
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
        	result.put(cursor.getInt(0), cursor.getString(1));
        	cursor.moveToNext();
        }

        cursor.close();
        
        db.close();
        
        return result;
	}
	
	public boolean existsUser(String login) {
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        Cursor cursor = db.query(UsersDbOpenHelper.TABLE_NAME, new String[] {UsersDbOpenHelper.ID}, UsersDbOpenHelper.LOGIN + " = '" + login + "'", null, null, null, null);

        Cursor dbCursor = db.query(UsersDbOpenHelper.TABLE_NAME, null, null, null, null, null, null); 
        String[] columNnames = dbCursor.getColumnNames();
        for(int i=0; i< columNnames .length; i++){
        	Log.w("dbStatus", columNnames[i] );
        }
        boolean result = cursor.getCount() > 0;
        
        cursor.close();
        
        db.close();
        
        return result;
	}
	
	//+ ID + " integer primary key autoincrement, " + LOGIN + " TEXT, " + SEX + "TEXT, "
	//+ AGE_Y + "integer, "+ AGE_M + "integer, "+ AGE_D + "integer, )";

	public int getUserDataInt(String login, int index) {
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        Cursor cursor = db.query(UsersDbOpenHelper.TABLE_NAME, new String[] {
        		UsersDbOpenHelper.ID, UsersDbOpenHelper.LOGIN, UsersDbOpenHelper.SEX, 
        		UsersDbOpenHelper.AGE_Y, UsersDbOpenHelper.AGE_M, UsersDbOpenHelper.AGE_D
        		}, UsersDbOpenHelper.LOGIN + " = '" + login + "'", null, null, null, null);
        		

//        Cursor cursor = db.query(UsersDbOpenHelper.TABLE_NAME, new String[] {UsersDbOpenHelper.ID, UsersDbOpenHelper.LOGIN}, UsersDbOpenHelper.LOGIN + " = '" + login + "'", null, null, null, null);
        
        cursor.moveToFirst();
        int result = cursor.getInt(index);
        
        cursor.close();
        
        db.close();
        
        return result;
	}

	public int getUserId(String login) {
        return getUserDataInt(login,0);
	}
	public int getUserDay(String login) {
        return getUserDataInt(login,5);
	}
	public int getUserMonth(String login) {
        return getUserDataInt(login,4);
	}
	public int getUserYear(String login) {
        return getUserDataInt(login,3);
	}
	
	public long addUser(String login, String sex, int year, int month, int day) throws IllegalArgumentException {
		if(existsUser(login)) {
			throw new IllegalArgumentException("User exists!");
		}
		
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

//        db.delete(UsersDbOpenHelper.TABLE_NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(UsersDbOpenHelper.LOGIN, login);
        cv.put(UsersDbOpenHelper.SEX, sex);
        cv.put(UsersDbOpenHelper.AGE_Y, year);
        cv.put(UsersDbOpenHelper.AGE_M, month);
        cv.put(UsersDbOpenHelper.AGE_D, day);
        
    	
        long id = db.insert(UsersDbOpenHelper.TABLE_NAME, null, cv);
        
        db.close();
        
        return id;
	}

	public void deleteUser(String login) throws IllegalArgumentException {
		if(!existsUser(login)) {
			throw new IllegalArgumentException("User doesn't exist!");
		}

		int id = getUserId(login);
		UsersDbOpenHelper dbOpenHelper = new UsersDbOpenHelper(appContext);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        
        db.delete(UsersDbOpenHelper.TABLE_NAME, UsersDbOpenHelper.ID+"="+id, null);
                
        db.close();
	}

	public void logIn(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
