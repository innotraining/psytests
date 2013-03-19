package com.mistdale.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends Activity {

	public final static boolean DEBUG_QUESTIONS = false;
	public static boolean individual_app = true;
	public static boolean numerate_questions = false;
	public final static String EXTRA_ANSWERS = "com.mistdale.test.ANSWERS";
	private Button button_results;
	private Button button_about;
	public static String current_user;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       /* System.out.println("helloworld");
        Log.i("date to delete: ", "helloworls");
        Log.d("date to delete: ", "helloworls");
        Log.e("date to delete: ", "helloworls");
        Log.v("date to delete: ", "helloworls");
        Log.w("date to delete: ", "helloworls");
        Log.wtf("date to delete: ", "helloworls");
        */
        
        Intent intent = getIntent();
        current_user = intent.getStringExtra("user");
        TextView greeting = (TextView) findViewById(R.id.greetingTextView);
        greeting.setText("Здравствуйте, " + current_user);
        
        
        button_results = (Button) findViewById(R.id.button_results);
        button_about = (Button) findViewById(R.id.button_about);
        
        if (!individual_app) {
        	button_results.setVisibility(View.GONE);
        	button_about.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void showAbout(View view) {
    	// Log.e("TestingActivity", "hi!"); WTF IT DOESN'T WORK?!
    	//Toast.makeText(getApplicationContext(), "hi!", Toast.LENGTH_LONG).show();
    	
    	
    	Intent intent = new Intent(this, AboutActivity.class);
    	startActivity(intent);
    }
    
    public void startTest(View view) {
    	Intent intent = new Intent(this, StartTestActivity.class);
    	startActivity(intent);
    }
    
    public void exitApp(View view) {
    	// 
    	// android.os.Process.killProcess(android.os.Process.myPid());
    	finish();
    }
    
    public void resultsPage(View view) {
    	Intent intent = new Intent(this, ResultsMenuActivity.class);
    	//intent.putExtra("caller", "main");
    	startActivity(intent);
    }
    
    public void changeMode(View view) {
    	individual_app = !individual_app;
    	if (!individual_app) {
        	button_results.setVisibility(View.GONE);
        	button_about.setVisibility(View.GONE);
        } else {
        	button_results.setVisibility(View.VISIBLE);
        	button_about.setVisibility(View.VISIBLE);
        }
    }
}
