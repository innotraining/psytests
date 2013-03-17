package com.example.kettellstest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.DateFormat;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserAccountsManager";
 
    // UserAccounts table name
    private static final String TABLE_USER_ACCOUNTS = "user_accounts";
    private static final String TABLE_LOGIN_BIRTH_DATE = "login_age";
 
    // UserAccounts Table Columns names
    private static final String KEY_LOGIN = "login";
    private static final String DATE = "date";
    private static final String SCORE = "score";
    private static final String BIRTH_DATE = "birth_date";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_USER_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_USER_ACCOUNTS + "("
                  			+  KEY_LOGIN + " TEXT PRIMARY KEY," + DATE + " TEXT," + SCORE + " INTEGER" + ")";
    	String CREATE_LOGIN_AGE_TABLE = "CREATE TABLE " + TABLE_LOGIN_BIRTH_DATE + "("
    						+  KEY_LOGIN + " TEXT PRIMARY KEY," + BIRTH_DATE + " TEXT)";
        db.execSQL(CREATE_USER_ACCOUNTS_TABLE);	
        db.execSQL(CREATE_LOGIN_AGE_TABLE);	
    }
 
    // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN_BIRTH_DATE);
        // Create tables again
        onCreate(db);
    }
    
    // Adding new LOGIN_BIRTH_DATE node
    public void addLoginBirthDate(String login, Date birth_date) {
    	SQLiteDatabase db = this.getWritableDatabase();
   	 
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, login);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        values.put(DATE, dateFormat.format(birth_date).toString());

        // Inserting Row
        db.insert(TABLE_LOGIN_BIRTH_DATE, null, values);
        db.close(); // Closing database connection
    }
    // Getting login birth_date
    public Date getBirthDate(String login) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
  
        Cursor cursor = db.query(true, TABLE_LOGIN_BIRTH_DATE, new String[] {BIRTH_DATE}, KEY_LOGIN + "=\"" + login + "\"", null, null, null, KEY_LOGIN, null);

        Date date = null;
        if (cursor != null && cursor.moveToFirst()) {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            date = dateFormat.parse(cursor.getString(0));
        }
        
        db.close(); // Closing database connection
        return date;
    }
    
    // Adding new node
    public void addNode(String login, Attempt attempt) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, login);
        values.put(DATE, attempt.getStringDate());
        values.put(SCORE, attempt.getScore());

        // Inserting Row
        db.insert(TABLE_USER_ACCOUNTS, null, values);
        db.close(); // Closing database connection
    }
     
    public boolean userExists(String login) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.query(true, TABLE_USER_ACCOUNTS, new String[] {KEY_LOGIN}, KEY_LOGIN + "=\"" + login + "\"", null, null, null, KEY_LOGIN, null);
    	boolean result = (cursor != null && cursor.moveToFirst());
    	db.close(); // Closing database connection
        return result;
    }
    
    // Getting ALL Logins
    public List<String> getAllLogins() {
        SQLiteDatabase db = this.getReadableDatabase();
    
        Cursor cursor = db.query(true, TABLE_USER_ACCOUNTS, new String[] {KEY_LOGIN}, null, null, null, null, KEY_LOGIN, null);

        List<String> logins = new ArrayList<String>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
            	// Adding login to list
                logins.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        
        db.close(); // Closing database connection
        // return all logins
        return logins;
    }
     
    public List<String> getAllUserAttempts(String login) throws ParseException {
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	Cursor cursor = db.query(TABLE_USER_ACCOUNTS, new String[] {DATE, SCORE}, KEY_LOGIN + "=\"" + login + "\"", null, null, null, DATE);
        
        List<String> attempts = new ArrayList<String>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
            	// Adding attempts to list
            	attempts.add(cursor.getString(0) + " score: " + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        
        db.close(); // Closing database connection
    	return attempts;
    	
    }
}
