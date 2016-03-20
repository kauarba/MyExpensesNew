package com.example.myexpenses;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class MyModeListener implements View.OnClickListener{
	
	 TextView textView;
	 MyModeListener(TextView textView){
		 this.textView = textView;
	 }
	 
	 public void onClick(View v) {
		 if(CommonView.selectedMode!=null){
			 CommonView.selectedMode.setBackgroundResource(R.drawable.my_border);
     	}
     	
     	CommonView.selectedMode = this.textView;
     	CommonView.selectedMode.setBackgroundResource(R.drawable.green_button);
	 }
}
