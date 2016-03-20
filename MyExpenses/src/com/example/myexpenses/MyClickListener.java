package com.example.myexpenses;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyClickListener implements View.OnClickListener{
	TextView v;
	String button;
	String month;
	String tableName;
	MySQLiteHelper db;
	EditText amount;
	TextView expenses;
	String year;
	String dateStr;
	Activity a;
	MyClickListener(TextView v, String button, String month, String year, String dateStr,String tableName, MySQLiteHelper db, EditText amount,TextView expenses){
		this.v = v;
		this.button = button;
		this.month = month;
		this.tableName = tableName;
		this.db = db;
		this.amount = amount;
		this.expenses = expenses;
		this.year = year;
		this.dateStr = dateStr;
	}
	
	MyClickListener(Activity a,TextView v, String button, String month, String year, String dateStr,String tableName, MySQLiteHelper db, EditText amount,TextView expenses){
		this.v = v;
		this.button = button;
		this.month = month.substring(0,3);
		this.tableName = tableName;
		this.db = db;
		this.amount = amount;
		this.expenses = expenses;
		this.year = year;
		this.dateStr = dateStr;
		this.a = a;
	}
	
	MyClickListener(String item){
		this.button = item;
	}
	 public void onClick(View v) {
		 
		 if(this.button.equals("addAmount")){
			 String item = "other";
			 String mode = "other";
			 
			 if(amount.getText().toString().equals("")){
				 Toast.makeText(this.a, "enter an Amount", Toast.LENGTH_SHORT).show();
				 return;
			 }
			 
			 if(CommonView.selectedMode == null){
				 Toast.makeText(this.a, "chose a Payment Mode", Toast.LENGTH_SHORT).show();
				 return;
			 }
			 
			 ExpenseLimit ex = db.getExpenseLimit(month, tableName);
			 
			 int amountValue = Integer.parseInt(amount.getText().toString());
			 amount.setText("");
			 
			 ex.setExpense(amountValue);
			 db.addExpenseLimit(ex, tableName);
			 expenses.setText("Expenses		" + db.getExpenseLimit(month, tableName).getExpense());
			 
			 if(CommonView.selectedView != null){
				 CommonView.selectedView.setBackgroundResource(R.drawable.red_button);
				 //item = CommonView.selectedView.getText().toString();
				 item = CommonView.currentItem;
				 CommonView.selectedView = null;
				 CommonView.currentItem = null;
			 }
			 
			 if(CommonView.selectedMode != null){
				 CommonView.selectedMode.setTextColor(Color.WHITE);
				 CommonView.selectedMode.setBackgroundResource(R.drawable.my_border);
				 mode = CommonView.selectedMode.getText().toString();
				 CommonView.selectedMode = null;
			 }
			 
			 Record record = new Record(this.dateStr,item,amountValue,mode);
			 
			 db.addRecord(record, month + "_" + year);
			 
		 }
		 
		 
     }
}
