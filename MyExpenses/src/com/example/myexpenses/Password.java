package com.example.myexpenses;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class Password extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_main);
		
		TextView saved = (TextView)findViewById(R.id.saved);
		TextView canceld = (TextView)findViewById(R.id.canceld);
		
		saved.setBackgroundResource(R.drawable.green_button);
		canceld.setBackgroundResource(R.drawable.green_button);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		saved.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				MySQLiteHelper db = DataBase.globalDB;
				
				EditText password = (EditText)findViewById(R.id.passEdit);
				//EditText emailId = (EditText)findViewById(R.id.emailEdit);
				
				PasswordRecord record = new PasswordRecord(1,"", password.getText().toString());
				db.addPassword(record);
				finish();
			}
		});
		
		canceld.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
