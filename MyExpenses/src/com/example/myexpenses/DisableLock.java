package com.example.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisableLock extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disable_lock);
		
		TextView disable = (TextView)findViewById(R.id.disableLock);
		
		disable.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				MySQLiteHelper db = DataBase.globalDB;
				
				db.disablePassword();
				Toast.makeText(DisableLock.this, "Password Disabled", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		TextView reset = (TextView)findViewById(R.id.resetPassword);
		
		reset.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DisableLock.this, ResetPassword.class);
				startActivity(intent);
				finish();
			}
		});
		
		TextView done = (TextView)findViewById(R.id.done);
		
		done.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
