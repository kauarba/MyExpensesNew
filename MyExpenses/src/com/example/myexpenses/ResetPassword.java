package com.example.myexpenses;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ResetPassword extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		
		TextView saved = (TextView)findViewById(R.id.newPassSaved);
		TextView canceld = (TextView)findViewById(R.id.newPassCanceld);
		
		saved.setBackgroundResource(R.drawable.green_button);
		canceld.setBackgroundResource(R.drawable.green_button);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		saved.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				MySQLiteHelper db = DataBase.globalDB;
				
				PasswordRecord record = db.getPasswordRecord();
				
				EditText oldPass = (EditText)findViewById(R.id.oldPassEdit);
				EditText newPass = (EditText)findViewById(R.id.newPassEdit);
				
				if(oldPass.getText().toString().equals(record.getPassword())){
					db.changePassword(newPass.getText().toString());
					Toast.makeText(ResetPassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
					finish();
				}
				else{
					TextView error = (TextView)findViewById(R.id.errorTxt);	
					error.setText("Old Password does not match !!");
					error.setTextColor(Color.WHITE);
				}
			}
		});
		
		canceld.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}

}
