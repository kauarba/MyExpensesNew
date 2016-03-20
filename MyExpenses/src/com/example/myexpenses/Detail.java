package com.example.myexpenses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Detail extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		GridView gridView = (GridView)findViewById(R.id.gridview);
		
		List<String> monthList = new LinkedList<String>();
		
		monthList.add("Jan");
		monthList.add("Feb");
		monthList.add("Mar");
		monthList.add("Apr");
		
		monthList.add("May");
		monthList.add("Jun");
		monthList.add("Jul");
		monthList.add("Aug");
		
		monthList.add("Sep");
		monthList.add("Oct");
		monthList.add("Nov");
		monthList.add("Dec");
		
		TextView year = (TextView)findViewById(R.id.year);
		year.setTypeface(null, Typeface.BOLD);
		year.setTextColor(Color.WHITE);
		
		Calendar cal = Calendar.getInstance();
		
		year.setText("" + cal.get(Calendar.YEAR));
		
		CommonView.yearDetail = year;
		
		MyAdapter myAdapt = new MyAdapter(this, monthList);
        gridView.setAdapter(myAdapt);
        
        
        
        TableLayout table = (TableLayout)findViewById(R.id.tableId);
        TextView txt = (TextView)findViewById(R.id.totalExpense);
        
       
        MyAdapterListener myAdapterListener = new MyAdapterListener(monthList,table,Detail.this,txt);
        
        TextView all = (TextView)findViewById(R.id.allDetail);
        TextView credit = (TextView)findViewById(R.id.creditDetail);
        TextView debit = (TextView)findViewById(R.id.debitDetail);
        TextView cash = (TextView)findViewById(R.id.cashDetail);
        TextView net = (TextView)findViewById(R.id.netDetail);
        
        year.setBackgroundResource(R.drawable.black_button);
        
        year.setOnClickListener(new YearClickListener(this,table,txt,all,credit,debit,cash,net));
        
        gridView.setOnItemClickListener(myAdapterListener);
        
        TextView export = (TextView)findViewById(R.id.export);
        export.setTextColor(Color.WHITE);
        export.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	File root = Environment.getExternalStorageDirectory();
            	Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_TEXT,"Hi,\n\nPFA expense details.\n\nRegards,\nMyExpenses");	
            	email.setType("text/plain");
            	File attachment = null;
            	String fileName = "expense details.csv";
            	  if (root.canWrite()) {
            	    attachment = new File(root, fileName);
            	  }
            	FileWriter fw = null;
            	try{
            		fw = new FileWriter(attachment);
            		
            		
            		String mode = CommonView.detailMode.getText().toString();
            		email.putExtra(Intent.EXTRA_SUBJECT,mode + " expense details of " + CommonView.selectedGridItem.getText().toString() + " " + CommonView.yearDetail.getText().toString());
        	     	
        	     	List<Record> records = new LinkedList<Record>();
        	     	
        	     	MySQLiteHelper db = DataBase.globalDB;
        	     	
        	     	String tableName = CommonView.selectedGridItem.getText().toString() + "_" + CommonView.yearDetail.getText().toString();
        	   
        	     	if(mode.equals("All"))
        	     		records = db.getAllRecords(tableName);
        	     	else
        	     		records = db.getAllRecords(tableName,mode);
        	     	
        	     	
        	     	if(records != null){
        	            fw.write("Date,Item,Amount\n");
        	            for(int i = 0; i < records.size(); i++){
        	            	fw.write(records.get(i).getDate() + "," + records.get(i).getItem() + "," + records.get(i).getAmount() +"\n");
        	            }
        	     	}
     
            		fw.flush();
            		fw.close();
            	}
            	catch(IOException ex){
            		
            	}
            	
            	email.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
            	startActivity(Intent.createChooser(email, "Send Email"));
            }
        });
        
        
		
	}
}
