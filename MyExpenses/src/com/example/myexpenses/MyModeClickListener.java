package com.example.myexpenses;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyModeClickListener implements View.OnClickListener{
	
		 String tableName;
		 TableLayout tableLayout;
		 TextView totalExpense;
		 Activity a;
		 
		 MyModeClickListener(Activity a,String tableName, TableLayout tableLayout, TextView totalExpense){
			 this.tableName = tableName;
			 this.tableLayout = tableLayout;
			 this.totalExpense = totalExpense;
			 this.a = a;
		 }
		 
		 public void onClick(View v) {
			 if(CommonView.detailMode!=null){
	     		//CommonView.detailMode.setTextColor(Color.LTGRAY);
				 CommonView.detailMode.setBackgroundResource(R.drawable.blue_button);
	     	}
	     	
	     	CommonView.detailMode = (TextView)v;
	     	//CommonView.detailMode.setTextColor(Color.RED);
	     	CommonView.detailMode.setBackgroundResource(R.drawable.green_button);
	     	
	     	String mode = CommonView.detailMode.getText().toString();
	     	
	     	List<Record> records = new LinkedList<Record>();
	     	
	     	MySQLiteHelper db = DataBase.globalDB;
	   
	     	if(mode.equals("All"))
	     		records = db.getAllRecords(this.tableName);
	     	else
	     		records = db.getAllRecords(this.tableName,mode);
	     	
	     	//TableLayout tableHeader = (TableLayout)a.findViewById(R.id.tableHeader);
	     	
	     	if(records == null){
				tableLayout.removeAllViews();
				//tableHeader.removeAllViews();
				
				TableRow errRow =  new TableRow(this.a);
		        TextView errCol = new TextView(this.a);
		        errCol.setTextColor(Color.RED);
		        errCol.setText("Sorry!! There is no record for " + mode);
		        errCol.setTypeface(null, Typeface.BOLD);
		        
		        TextView export = (TextView)a.findViewById(R.id.export);
		        export.setText("");
		        export.setBackground(null);
		        
		        this.totalExpense.setText("");
		        errRow.addView(errCol);
		        tableLayout.addView(errRow);
		        tableLayout.setBackground(null);
		        //tableHeader.setBackground(null);
		        this.totalExpense.setBackground(null);
		        return;
	     	}
	     	
	     	TextView export = (TextView)a.findViewById(R.id.export);
			export.setBackgroundResource(R.drawable.my_border);
	        export.setTextColor(Color.WHITE);
	        export.setText("Export");
	        export.setTypeface(null, Typeface.BOLD);
	     	
	     	tableLayout.setBackgroundResource(R.drawable.my_border);
	     	//tableHeader.setBackgroundResource(R.drawable.my_border);
	   
			tableLayout.setStretchAllColumns(true);
			tableLayout.bringToFront();
			tableLayout.removeAllViews();
			
			/*tableHeader.setStretchAllColumns(true);
			tableHeader.bringToFront();
			tableHeader.removeAllViews();*/
			
			/*LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1);*/
			
			
			TableRow tr =  new TableRow(this.a);
			
	        TextView c1 = new TextView(this.a);
	        c1.setTextColor(Color.WHITE);
	        c1.setText("Date");
	        c1.setTypeface(null, Typeface.BOLD);
	        c1.setBackgroundResource(R.drawable.red_button);
	        
	        
	      
	        TextView c2 = new TextView(this.a);
	        c2.setTextColor(Color.WHITE);
	        c2.setText("Item");
	        c2.setTypeface(null, Typeface.BOLD);
	        c2.setBackgroundResource(R.drawable.red_button);
	      
	        
	        TextView c3 = new TextView(this.a);
	        c3.setTextColor(Color.WHITE);
	        c3.setText("Amount");
	        c3.setTypeface(null, Typeface.BOLD);
	        c3.setBackgroundResource(R.drawable.red_button);
	        
	        
	        
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
	        
	        int totalAmount = 0;
			
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
		        
		        totalAmount += records.get(i).getAmount();
		        
		        row.addView(col1);
		        row.addView(col2);
		        row.addView(col3);
		        
		        tableRowParams.setMargins(3,3,0,3);
		        row.setLayoutParams(tableRowParams);
		        
		        tableLayout.addView(row);
		    }
		    
		    if(mode.equals("All"))
		    	this.totalExpense.setText("Total Expense  " + totalAmount);
		    else
		    	this.totalExpense.setText(mode + " Expense  " + totalAmount);
		   
		    this.totalExpense.setTextColor(Color.WHITE);
			this.totalExpense.setBackgroundResource(R.drawable.red_button);
	     	
		 }
	


}
