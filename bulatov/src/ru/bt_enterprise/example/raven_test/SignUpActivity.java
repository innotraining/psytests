package ru.bt_enterprise.example.raven_test;


import java.util.Calendar;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUpActivity extends Activity implements OnDateSetListener {
	private RadioButton btMale;
	private RadioButton btFemale;
	private EditText userNameView;
	private Time tBDay;
	private boolean time_set = false;
	private String userName;
	private String sex;
	private int day;
	private int month;
	private int year;
	
	private SharedPreferences settings;
	private QuizUsers users;

	private Button btDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		btDate = (Button) findViewById(R.id.button1);
		btMale = (RadioButton) findViewById(R.id.NewUserRadioButtonMale);
		btFemale = (RadioButton) findViewById(R.id.NewUserRadioButtonFemale);
		userNameView = (EditText) findViewById(R.id.NewUserUserName);
		
//		userId = getIntent().getIntExtra("userId", -1);
		settings = getSharedPreferences(
				QuizUsers.CURRENT_USER_PREFERENCES_NAME, 0);
		users = new QuizUsers(SignUpActivity.this);

	}
	public void OnSignUpExitClick(View v){
		finish();
	}
	public void setBDayDate(View v){
		tBDay = new Time();
		DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

	@Override
    public void onDateSet(DatePicker view, int year_, int month_, int day_) {
		time_set = true;

		month = month_ + 1;
		day = day_;
		year = year_;
		Toast toast = Toast.makeText(getApplicationContext(), 
				"Date : " + month + "/" + day + "/" + year, 
				   Toast.LENGTH_SHORT); 
		toast.show(); 
    }
	
	public void onNewUserRadioButtonClick(View v){
		boolean isMale = (v.getId() == btMale.getId());
		btMale.setChecked(isMale);
		btFemale.setChecked(!isMale);
		if (isMale) sex = "m"; else sex = "f";
	}

	public void onButtonContinueClick(View v){
		userName = userNameView.getText().toString();
		boolean all_is_ok = time_set;
		all_is_ok = all_is_ok && btMale.isChecked() || btFemale.isChecked();
		all_is_ok = all_is_ok && !userName.equals("");
		if (all_is_ok) {
				add_new_user();	
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), 
					   "Введите имя и пол!", Toast.LENGTH_SHORT); 
			toast.show(); 
		}
	}
	private void add_new_user(){
		long id = -1;
		try {
			id = users.addUser(userName, sex, year, month, day);
		} catch (IllegalArgumentException e) {
			Toast toast = Toast.makeText(getApplicationContext(), 
					   "Такой пользователь уже существует!", Toast.LENGTH_SHORT); 
			toast.show();
		}
		Toast toast1 = Toast.makeText(getApplicationContext(), 
				   "add_new_user!" + id, Toast.LENGTH_SHORT); 
		toast1.show();

		if(id > 0) {
			users.logIn(id);
			Intent quizActivity = new Intent(SignUpActivity.this, TutorialActivity.class);
			startActivity(quizActivity);
			finish();
		}
	}

		
}