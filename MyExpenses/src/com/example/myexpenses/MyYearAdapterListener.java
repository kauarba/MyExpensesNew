package com.example.myexpenses;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class MyYearAdapterListener implements AdapterView.OnItemClickListener{
	
	Activity a;
	public MyYearAdapterListener(Activity a){
		this.a = a;
	}
	public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
			
			TextView txt = (TextView) view.findViewById(R.id.yearRow);	
			CommonView.yearDetail.setText(txt.getText().toString());
			
			a.finish();
	}
}
