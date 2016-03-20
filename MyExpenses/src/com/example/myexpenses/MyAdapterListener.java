package com.example.myexpenses;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyAdapterListener implements AdapterView.OnItemClickListener {
	
	private List<String> displayList;
	TableLayout tableLayout;
	Activity a;
	TextView totalExpense;
	public MyAdapterListener(List<String> displayList, TableLayout tableLayout, Activity a, TextView totalExpense){
		this.displayList = displayList;
		this.tableLayout = tableLayout;
		this.a = a;
		this.totalExpense = totalExpense;
	}
	public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
			
			TextView txt = (TextView) view.findViewById(R.id.textRow);
			
			if(CommonView.selectedGridItem != null){
				//CommonView.selectedGridItem.setBackgroundColor(Color.YELLOW);	
				CommonView.selectedGridItem.setBackgroundResource(R.drawable.red_button);
			}
			
			CommonView.selectedGridItem = txt;
			//CommonView.selectedGridItem.setBackgroundColor(Color.GREEN);
			CommonView.selectedGridItem.setBackgroundResource(R.drawable.green_button);
			
			String tableName = displayList.get(position).toString() + "_" + CommonView.yearDetail.getText().toString();
			MySQLiteHelper db = DataBase.globalDB;
			
			View rootView = this.a.getWindow().getDecorView().findViewById(android.R.id.content);
			TextView all = (TextView) rootView.findViewById(R.id.allDetail);
			TextView credit = (TextView) rootView.findViewById(R.id.creditDetail);
			TextView debit = (TextView) rootView.findViewById(R.id.debitDetail);
			TextView cash = (TextView) rootView.findViewById(R.id.cashDetail);
			TextView net = (TextView) rootView.findViewById(R.id.netDetail);
			
			List<Record> records = db.getAllRecords(tableName);
			
			//TableLayout tableHeader = (TableLayout)a.findViewById(R.id.tableHeader);
			
			if(records == null){
				tableLayout.removeAllViews();
				tableLayout.setBackground(null);
				
				//tableHeader.removeAllViews();
				//tableHeader.setBackground(null);
				
				TableRow errRow =  new TableRow(this.a);
		        TextView errCol = new TextView(this.a);
		        errCol.setTextColor(Color.RED);
		        errCol.setText("Sorry!! There is no record for " + displayList.get(position).toString());
		        errCol.setTypeface(null, Typeface.BOLD);
		        
		        errRow.addView(errCol);
		        tableLayout.addView(errRow);
		        
		        this.totalExpense.setText("");
		        this.totalExpense.setBackground(null);
		        
		        TextView export = (TextView)a.findViewById(R.id.export);
		        export.setText("");
		        export.setBackground(null);
		        
		        all.setText("");
		        all.setBackground(null);
		        credit.setText("");
		        credit.setBackground(null);
		        debit.setText("");
		        debit.setBackground(null);
		        cash.setText("");
		        cash.setBackground(null);
		        net.setText("");
		        net.setBackground(null);
		        
		        return;
			}
			
			TextView export = (TextView)a.findViewById(R.id.export);
			export.setBackgroundResource(R.drawable.my_border);
	        export.setTextColor(Color.WHITE);
	        export.setText("Export");
	        export.setTypeface(null, Typeface.BOLD);
			
			all.setBackgroundResource(R.drawable.green_button);
	        all.setTextColor(Color.WHITE);
	        all.setText("All");
	        all.setTypeface(null, Typeface.BOLD);
	        
	        CommonView.detailMode = all;
	        
	        all.setOnClickListener(new MyModeClickListener(this.a,tableName, this.tableLayout,this.totalExpense));
	        
	        credit.setBackgroundResource(R.drawable.blue_button);
	        credit.setTextColor(Color.WHITE);
	        credit.setText("credit card");
	        credit.setTypeface(null, Typeface.BOLD);
	        credit.setOnClickListener(new MyModeClickListener(this.a,tableName, this.tableLayout,this.totalExpense));
	        
	        
	        debit.setBackgroundResource(R.drawable.blue_button);
	        debit.setTextColor(Color.WHITE);
	        debit.setText("debit card");
	        debit.setTypeface(null, Typeface.BOLD);
	        debit.setOnClickListener(new MyModeClickListener(this.a,tableName, this.tableLayout,this.totalExpense));
	        
	        cash.setBackgroundResource(R.drawable.blue_button);
	        cash.setTextColor(Color.WHITE);
	        cash.setText("cash");
	        cash.setTypeface(null, Typeface.BOLD);
	        cash.setOnClickListener(new MyModeClickListener(this.a,tableName, this.tableLayout,this.totalExpense));
	        
	        net.setBackgroundResource(R.drawable.blue_button);
	        net.setTextColor(Color.WHITE);
	        net.setText("net banking");
	        net.setTypeface(null, Typeface.BOLD);
	        net.setOnClickListener(new MyModeClickListener(this.a,tableName, this.tableLayout,this.totalExpense));
			
			ExpenseLimit ex = db.getExpenseLimit(txt.getText().toString(),"year_" + CommonView.yearDetail.getText().toString());
			
			this.totalExpense.setText("Total Expense  " + ex.getExpense());
			this.totalExpense.setTypeface(null, Typeface.BOLD);
			this.totalExpense.setTextColor(Color.WHITE);
			this.totalExpense.setBackgroundResource(R.drawable.red_button);
			
			tableLayout.setBackgroundResource(R.drawable.my_border);
			//tableHeader.setBackgroundResource(R.drawable.my_border);
			
			
			tableLayout.setStretchAllColumns(true);
			tableLayout.bringToFront();
			tableLayout.removeAllViews();
			
			/*tableHeader.setStretchAllColumns(true);
			tableHeader.bringToFront();
			tableHeader.removeAllViews();*/
			
			TableRow tr =  new TableRow(this.a);
	        TextView c1 = new TextView(this.a);
	        c1.setText("Date");
	        c1.setTypeface(null, Typeface.BOLD);
	        c1.setBackgroundResource(R.drawable.red_button);
	        c1.setTextColor(Color.WHITE);
	        
	        
	        TextView c2 = new TextView(this.a);
	        c2.setText("Item");
	        c2.setTypeface(null, Typeface.BOLD);
	        c2.setBackgroundResource(R.drawable.red_button);
	        c2.setTextColor(Color.WHITE);
	        
	        TextView c3 = new TextView(this.a);
	        c3.setText("Amount");
	        c3.setTypeface(null, Typeface.BOLD);
	        c3.setBackgroundResource(R.drawable.red_button);
	        c3.setTextColor(Color.WHITE);
	         
	        tr.addView(c1);
	        tr.addView(c2);
	        tr.addView(c3);
	        
	        TableLayout.LayoutParams tableRowParams=
	        		  new TableLayout.LayoutParams
	        		  (TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
	        
	        
	        tableRowParams.setMargins(3,2,0,10);
	        tr.setLayoutParams(tableRowParams);
	      
	        tableLayout.addView(tr);
	        //tableHeader.addView(tr);
			
		    for(int i = 0; i < records.size(); i++){
		        TableRow row =  new TableRow(this.a);
		        TextView col1 = new TextView(this.a);
		        col1.setText(records.get(i).getDate());
		        col1.setTypeface(null, Typeface.BOLD);
		        col1.setBackgroundResource(R.drawable.black_button);
		        col1.setTextColor(Color.WHITE);
		        col1.setPadding(3, 3, 3, 3);
		        
		        TextView col2 = new TextView(this.a);
		        col2.setText(records.get(i).getItem());
		        col2.setTypeface(null, Typeface.BOLD);
		        col2.setBackgroundResource(R.drawable.black_button);
		        col2.setTextColor(Color.WHITE);
		        col2.setPadding(3, 3, 3, 3);
		        
		        TextView col3 = new TextView(this.a);
		        col3.setText(""+ records.get(i).getAmount());
		        col3.setTypeface(null, Typeface.BOLD);
		        col3.setBackgroundResource(R.drawable.black_button);
		        col3.setTextColor(Color.WHITE);
		        col3.setPadding(3, 3, 3, 3);
		        
		        row.addView(col1);
		        row.addView(col2);
		        row.addView(col3);
		        
		        tableRowParams.setMargins(3,3,0,3);
		        row.setLayoutParams(tableRowParams);
		        
		        tableLayout.addView(row);
		    }
	}
}
