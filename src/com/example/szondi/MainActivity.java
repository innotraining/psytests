package com.example.szondi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
public class  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}*/
public class MainActivity extends Activity implements OnClickListener {

	Button btnRegistration;
	Button btnLogin;

	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    btnRegistration = (Button) findViewById(R.id.btnRegistration);
	    btnLogin = (Button) findViewById(R.id.btnLogin);
	    btnRegistration.setOnClickListener(this);
	    btnLogin.setOnClickListener(this);
	  }

	  @Override
	  public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.btnRegistration:
	    	Intent intentReg = new Intent(this, ActivityReg.class);
	        startActivity(intentReg);
	        break;
	    case R.id.btnLogin:
	    	Intent intentLog = new Intent(this, ActivityLog.class);
	        startActivity(intentLog);
	        break;
	    default:
	      break;
	    }
	  }
	  
	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
		  // Inflate the menu; this adds items to the action bar if it is present.
		  getMenuInflater().inflate(R.menu.main, menu);
	  return true;
	  }
	    
}