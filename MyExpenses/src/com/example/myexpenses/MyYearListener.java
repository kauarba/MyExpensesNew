package com.example.myexpenses;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class MyYearListener implements AdapterView.OnItemClickListener{

	public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
			
			TextView txt = (TextView) view.findViewById(R.id.textRow);
			
			if(CommonView.selectedView != null){
				//CommonView.selectedView.setBackgroundColor(Color.YELLOW);	
				CommonView.selectedView.setBackgroundResource(R.drawable.red_button);
			}
			
			CommonView.selectedView = txt;
			//CommonView.selectedView.setBackgroundColor(Color.GREEN);
			CommonView.selectedView.setBackgroundResource(R.drawable.green_button);
}
}