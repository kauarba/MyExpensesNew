package com.example.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EnableLock extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enable_lock);
		
		TextView enable = (TextView)findViewById(R.id.enableLock);
		
		
		enable.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				MySQLiteHelper db = DataBase.globalDB;
				
				db.enablePassword();
				Toast.makeText(EnableLock.this, "Password Enabled", Toast.LENGTH_SHORT).show();
				finish();
				
			}
		});
		
		TextView reset = (TextView)findViewById(R.id.resetPassword);
		
		
		
		reset.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EnableLock.this, ResetPassword.class);
				startActivity(intent);
				finish();
			}
		});
		
		TextView done = (TextView)findViewById(R.id.Done);
		
		done.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
