package com.example.myexpenses;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

public class MyDialogYear extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.year);
		
		GridView gridView = (GridView) findViewById(R.id.gridviewYear);
		
		MySQLiteHelper db = DataBase.globalDB;
		
		List<String> yearList = db.getAllYears("yearsTable");
		
		MyYearAdapter myAdapt = new MyYearAdapter(this, yearList);
        gridView.setAdapter(myAdapt);
        
        gridView.setOnItemClickListener(new MyYearAdapterListener(this));
	}
	
	
}
