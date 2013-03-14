 package com.example.kettellstest;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Attempt {	
	private int score;
	private Date date;
	
	Attempt(int score) {
		this.score = score;
		// get current date
		this.date = new Date();
	}
	Attempt(int score, String sDate) throws ParseException {
		this.score = score;
		this.date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH).parse(sDate);
	}
	
	public int getScore() {
		return score;
	}
	public String getStringDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

}

