package com.example.myexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class YearClickListener implements View.OnClickListener{
	
	Activity a;
	TextView credit;
	TextView debit;
	TextView cash;
	TextView net;
	TextView all;
	TableLayout table;
	TextView totalexpense;
	
	public YearClickListener(Activity a,TableLayout table,TextView totalExpense, TextView all, 
				TextView credit, TextView debit, TextView cash, TextView net){
			
			this.cash = cash;
			this.net = net;
			this.credit = credit;
			this.debit = debit;
			this.table = table;
			this.totalexpense = totalExpense;
			this.all = all;
			this.a = a;
	}

	public void onClick(View v){
		credit.setText("");
		credit.setBackground(null);
		debit.setText("");
		debit.setBackground(null);
		cash.setText("");
		cash.setBackground(null);
		net.setText("");
		net.setBackground(null);
		all.setText("");
		all.setBackground(null);
		table.removeAllViews();
		table.setBackground(null);
		this.totalexpense.setText("");
		this.totalexpense.setBackground(null);
		
		TextView export = (TextView)a.findViewById(R.id.export);
	    export.setText("");
	    export.setBackground(null);
		
		if(CommonView.selectedGridItem != null){
			//CommonView.selectedGridItem.setBackgroundColor(Color.YELLOW);	
			CommonView.selectedGridItem.setBackgroundResource(R.drawable.red_button);
			CommonView.selectedGridItem = null;
		}
		
		Intent intent = new Intent(this.a, MyDialogYear.class);
        this.a.startActivity(intent);
        
	}
}
