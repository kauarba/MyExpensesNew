package com.example.myexpenses;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity{
	
	public void onBackPressed() {
		setResult(CommonView.EXIT_CODE, new Intent());
		finish();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_password);
		
		TextView ok = (TextView)findViewById(R.id.ok);
		
		TextView exit = (TextView)findViewById(R.id.exit);
		
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        exit.setBackgroundResource(R.drawable.green_button);
        ok.setBackgroundResource(R.drawable.green_button);
        
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(CommonView.EXIT_CODE, new Intent());
				finish();
			}
		});
			
		ok.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				MySQLiteHelper db = DataBase.globalDB;
				
				EditText password = (EditText)findViewById(R.id.passwordEdit);
				PasswordRecord record = db.getPasswordRecord();
				
				if(password.getText().toString().equals(record.getPassword())){
					setResult(RESULT_OK, new Intent());
					finish();
				}
				else{
					TextView incorrectPass = (TextView)findViewById(R.id.incorrectPass);
					incorrectPass.setText("Incorrect Password !!");
					
				}
			}
		});
		
		
	}

}
