package com.example.myexpenses;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private TextView expenses;
	private String tableName;
	
	public void setExpense(TextView expenses){
		this.expenses = expenses;
	}
	
	public TextView getExpense(){
		return this.expenses;
	}
	
	List<String> getList(){
		
		List<String> itemList = new LinkedList<String>();
		
		itemList.add("Groccery");
		itemList.add("Shopping");
		itemList.add("Vegetable");
		itemList.add("Fruits");
		itemList.add("Food");
		
		itemList.add("Laundry");
		itemList.add("Kids");
		itemList.add("Medical");	
		
		itemList.add("Internet");
		itemList.add("Mobile");
		itemList.add("Rent");
		itemList.add("Electricity");
		
		itemList.add("Fuel");	
		itemList.add("Cylinder");
		
		itemList.add("Gifts");
		itemList.add("Holidays");
		itemList.add("Travel");
		itemList.add("Stationery");
		itemList.add("Movie");
		itemList.add("Restaurent");
		
		itemList.add("Bus");
		itemList.add("Taxi");
		itemList.add("Auto");
		itemList.add("Train");
		itemList.add("Flight");
		
		itemList.add("Other");		
		return itemList;
	}

	String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
	
	public void checkPassword(MySQLiteHelper db){
		
		PasswordRecord record = db.getPasswordRecord();
		
		if(record == null || (record.getIsPassSet() == 0)){ 
			Calendar cal = Calendar.getInstance();
	    	ExpenseLimit ex = db.getExpenseLimit(getMonthForInt(cal.get(Calendar.MONTH)), tableName);
	    	
	    	TextView expenses = this.getExpense();
			
			expenses = (TextView)findViewById(R.id.expenses);
			expenses.setTextColor(Color.WHITE);
			expenses.setTypeface(null, Typeface.BOLD);
			
			
			expenses.setText("Expenses		" + ex.getExpense());
			expenses.setBackgroundResource(R.drawable.red_button);
			
			this.setExpense(expenses);
			return;
		}
		
		if(record.getIsPassSet() == 1){
			Intent intent = new Intent(MainActivity.this, Login.class);
	        startActivityForResult(intent,1);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK && requestCode == 1) {
	    	
	    	Calendar cal = Calendar.getInstance();
	    	MySQLiteHelper db = DataBase.globalDB;
	    	ExpenseLimit ex = db.getExpenseLimit(getMonthForInt(cal.get(Calendar.MONTH)), tableName);
	    	
	    	TextView expenses = this.getExpense();
			
			expenses = (TextView)findViewById(R.id.expenses);
			expenses.setTextColor(Color.WHITE);
			expenses.setTypeface(null, Typeface.BOLD);
			expenses.setBackgroundResource(R.drawable.red_button);
			
			expenses.setText("Expenses		" + ex.getExpense());
			
			this.setExpense(expenses);
	    }
	    else if (resultCode == CommonView.EXIT_CODE && requestCode == 1) {
	    	finish();
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		dateFormat.format(date); 
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("EEE");
		String text = formatter.format(cal.getTime());
		
		TextView dateText = (TextView)findViewById(R.id.date);
		
		dateText.setText(dateFormat.format(date));
		dateText.setTextColor(Color.WHITE);
		dateText.setTypeface(null, Typeface.BOLD);
		
		//Typeface type = Typeface.createFromAsset(getAssets(),"fonts/DroidSansMono.ttf"); 
		
		TextView month = (TextView)findViewById(R.id.month);
		month.setTextColor(Color.WHITE);
		month.setTypeface(null, Typeface.BOLD);
		month.setText(getMonthForInt(cal.get(Calendar.MONTH)).substring(0, 3));
		
		TextView day = (TextView)findViewById(R.id.day);
		day.setTextColor(Color.WHITE);
		day.setTypeface(null, Typeface.BOLD);
		day.setText(text);
		
		TextView amountTxt = (TextView)findViewById(R.id.amountTxt);
		amountTxt.setTextColor(Color.RED);
		amountTxt.setTypeface(null, Typeface.BOLD);
		
		TextView mode = (TextView)findViewById(R.id.mode);
		mode.setTextColor(Color.LTGRAY);
		mode.setTypeface(null, Typeface.BOLD);
		
		TextView credit = (TextView)findViewById(R.id.credit);
		credit.setTextColor(Color.WHITE);
		credit.setTypeface(null, Typeface.BOLD);
		credit.setOnClickListener(new MyModeListener(credit));

		TextView debit = (TextView)findViewById(R.id.debit);
		debit.setTextColor(Color.WHITE);
		debit.setTypeface(null, Typeface.BOLD);
		debit.setOnClickListener(new MyModeListener(debit));
		
		TextView cash = (TextView)findViewById(R.id.cash);
		cash.setTextColor(Color.WHITE);
		cash.setTypeface(null, Typeface.BOLD);
		cash.setOnClickListener(new MyModeListener(cash));
		
		TextView net = (TextView)findViewById(R.id.net);
		net.setTextColor(Color.WHITE);
		net.setTypeface(null, Typeface.BOLD);
		net.setOnClickListener(new MyModeListener(net));
		
		TextView lock = (TextView)findViewById(R.id.lock);
		lock.setTextColor(Color.GRAY);
		lock.setTypeface(null, Typeface.BOLD);
		
		
		DataBase.globalDB = MySQLiteHelper.getInstance(this, "" + cal.get(Calendar.YEAR),getMonthForInt(cal.get(Calendar.MONTH)));
		MySQLiteHelper db = DataBase.globalDB;
		
		
		tableName = "year_" + cal.get(Calendar.YEAR);
		String year = "" + cal.get(Calendar.YEAR);
		CommonView.year = year;
		String dateStr = dateFormat.format(date);
		String monthName = getMonthForInt(cal.get(Calendar.MONTH));
		
		GridView gridView = (GridView)findViewById(R.id.gridviewMain);	
		MyAdapter myAdapt = new MyAdapter(this, getList());
        gridView.setAdapter(myAdapt);
        MyGridListener myGridListener = new MyGridListener();    
        gridView.setOnItemClickListener(myGridListener);
		
		
		TextView exit = (TextView)findViewById(R.id.exit);		
		exit.setTextColor(Color.WHITE);
		exit.setTypeface(null, Typeface.BOLD);
		
		exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	finish();
            }
        });
		
		EditText amount = (EditText)findViewById(R.id.amount);
		amount.setTypeface(null, Typeface.BOLD);
		amount.setTextColor(Color.BLACK);
		
		TextView expenses = this.getExpense();
		
		expenses = (TextView)findViewById(R.id.expenses);
		
		TextView addAmount = (TextView)findViewById(R.id.addAmount);
		addAmount.setTypeface(null, Typeface.BOLD);
		addAmount.setTextColor(Color.WHITE);
		addAmount.setOnClickListener(new MyClickListener(MainActivity.this,addAmount,"addAmount",monthName,year, dateStr,tableName,db,amount,expenses));
		
		
		TextView details = (TextView)findViewById(R.id.details);
		details.setTypeface(null, Typeface.BOLD);
		details.setTextColor(Color.WHITE);
		
		checkPassword(db);
		
		details.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Detail.class);
		        startActivity(intent);
				
			}
		});
		
		lock.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				MySQLiteHelper db = DataBase.globalDB;
				PasswordRecord record = db.getPasswordRecord();
				
				if(record == null){
					Intent intent = new Intent(MainActivity.this, Password.class);
					startActivity(intent);
				}
				else if(record.getIsPassSet() == 1){
					Intent intent = new Intent(MainActivity.this, DisableLock.class);
					startActivity(intent);
				}
				else if(record.getIsPassSet() == 0){
					Intent intent = new Intent(MainActivity.this, EnableLock.class);
					startActivity(intent);
				}
				
			}
		});
	}

	
}
