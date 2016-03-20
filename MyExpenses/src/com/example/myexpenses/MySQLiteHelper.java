package com.example.myexpenses;

import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

 
public class MySQLiteHelper extends SQLiteOpenHelper{
 
	private static MySQLiteHelper single;
    private String month;
    private String year;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myFinaleDB";
    private static final String[] COLUMNS = {"month","maxLimit","expense"};
    private static final String[] COLUMNS_RECORDS = {"date","item","amount","mode"};
 
    
    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
    
    public static MySQLiteHelper getInstance(Context context,String year, String month){
    	if(single == null){
    		single = new MySQLiteHelper(context);
    		single.year = year;single.month = month;
    		return single;
    	}
    	
    	return single;
    }
 
    public boolean isTableExists(SQLiteDatabase mDatabase, String tableName) {
    	
        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                                cursor.close();
                return true;
            }
                        cursor.close();
        }
        return false;
    }
    
    public void createYearsTable(SQLiteDatabase db,String tableName){
    	String YEARS_TABLE = "CREATE TABLE " + tableName + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "year TEXT )";
    	
    	db.execSQL(YEARS_TABLE);
    }
    
    public void createPasswordTable(SQLiteDatabase db,String tableName){
    	String PASSWORD_TABLE = "CREATE TABLE " + tableName + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
    			"isPassSet INTEGER, " + 
    			"emailId TEXT, " + 
                "password TEXT )";
    	
    	db.execSQL(PASSWORD_TABLE);
    }
    
    
    public void createExpenseLimitTable(SQLiteDatabase db,String tableName){
    	
    	addYear(db,this.year,"yearsTable");
    	
    	String EXPENSE_TABLE = "CREATE TABLE year_" + tableName + " ( " +
                "month TEXT PRIMARY KEY, " + 
                "maxLimit INTEGER, "+
                "expense INTEGER )";
    	
    	 db.execSQL(EXPENSE_TABLE);
    }
    
 public void createRecordTable(SQLiteDatabase db, String tableName){
    	
    	String RECORD_TABLE = "CREATE TABLE " + tableName + " ( " +
                "date TEXT, " + 
                "item TEXT, "+
                "amount INTEGER, "+
                "mode TEXT )";
    	
    	 db.execSQL(RECORD_TABLE);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    	if(!isTableExists(db,"yearsTable")){
    		createYearsTable(db,"yearsTable");
    	}
    	
    	if(!isTableExists(db,"passwordTable")){
    		createPasswordTable(db,"passwordTable");
    	}
    
    	if(!isTableExists(db,"year_" + this.year)){
        	 createExpenseLimitTable(db,this.year);
        }
    	
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	this.onCreate(db);
    }
    
    public void addYear(SQLiteDatabase db, String record, String tableName){
       
        if(!isTableExists(db,tableName)){
        	createRecordTable(db,tableName);
        }
       
        ContentValues values = new ContentValues();
        values.put("year", record); 
        
        db.insert(tableName, 
                null, 
                values); 
 
    }
    
    public void addPassword(PasswordRecord record){
        
    	SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isPassSet", record.getIsPassSet()); 
        values.put("emailId", record.getEmailId());
        values.put("password", record.getPassword());
        
        db.insert("passwordTable", 
                null, 
                values); 
 
    }
    
    
    public void addRecord(Record record, String tableName){
       
        SQLiteDatabase db = this.getWritableDatabase();
 
        if(!isTableExists(db,tableName)){
        	createRecordTable(db,tableName);
        }
       
        ContentValues values = new ContentValues();
        values.put("date", record.getDate()); 
        values.put("item", record.getItem()); 
        values.put("amount", record.getAmount()); 
        values.put("mode", record.getMode()); 
        
        db.insert(tableName, 
                null, 
                values); 
 
        
        //db.close(); 
    }
    
public int addExpenseLimit(ExpenseLimit record, String tableName){
       
        
        SQLiteDatabase db = this.getWritableDatabase();
        
        Cursor cursor = 
                db.query(tableName, // a. table
                COLUMNS, // b. column names
                " month = ?", // c. selections 
                new String[] { String.valueOf(record.getMonth()) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put("maxLimit", record.getLimit()); 
            values.put("expense", record.getExpense());
            
            db.update(tableName, values, "month = '" + cursor.getString(0) + "'",null);
            
            //db.close();
            
            return 0;
            
        }
        
        return -1;
 
       
        
    }
    
    public ExpenseLimit getExpenseLimit(String month, String tableName){
    	
    	
    	month = month.substring(0, 3);
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	if(!isTableExists(db,"year_" + this.year)){
          	 createExpenseLimitTable(db,this.year);
          }
        
     
        Cursor cursor = 
                db.query(tableName, 
                COLUMNS, 
                " month = ?", 
                new String[] { String.valueOf(month) }, 
                null,
                null, 
                null, 
                null); 
     
        if (cursor.getCount()>0){
            cursor.moveToFirst();
     
            ExpenseLimit q = new ExpenseLimit(month);
            q.setLimit(cursor.getInt(1));
            q.setExpense(cursor.getInt(2));
            return q;
        }
        
        ExpenseLimit ex = new ExpenseLimit(month);
        
        ContentValues values = new ContentValues();
        values.put("month", ex.getMonth());
        values.put("maxLimit", ex.getLimit()); 
        values.put("expense", ex.getExpense());
        
        db.insert(tableName, 
                null, 
                values); 
 
        
        //db.close(); 
        return ex;
    }

    
    public List<Record> getAllRecords(String tableName, String mode) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
        
    	if(!isTableExists(db,tableName)){
         	return null;
         }
        
        Cursor cursor = 
                db.query(tableName, 
                COLUMNS_RECORDS, 
                " mode = ?", 
                new String[] { String.valueOf(mode) }, 
                null,
                null, 
                null, 
                null); 
        
        if(cursor.getCount() <= 0){
        	return null;
        }
    	
    	List<Record> records = new LinkedList<Record>();
    	
    	 Record record = null;
    	 
         if (cursor.moveToFirst()) {
             do {
                 record = new Record(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
                 records.add(record);
             } while (cursor.moveToNext());
         }
         
    	return records;
    }
    
 public void disablePassword() {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues values = new ContentValues();
        values.put("isPassSet", 0);
        
        db.update("passwordTable", values, "isPassSet = '1'",null);
        
        //db.close();
 }
 
 public void enablePassword() {
 	
 	SQLiteDatabase db = this.getWritableDatabase();
 	 
     ContentValues values = new ContentValues();
     values.put("isPassSet", 1);
     
     db.update("passwordTable", values, "isPassSet = '0'",null);
     
     //db.close();
}
 
 public void changePassword(String newPass) {
	 	
	 	SQLiteDatabase db = this.getWritableDatabase();
	 	 
	     ContentValues values = new ContentValues();
	     values.put("password", newPass);
	     
	     db.update("passwordTable", values, "id = '1'",null);
	     
	     //db.close();
	}
    
    
    public List<Record> getAllRecords(String tableName) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	 if(!isTableExists(db,tableName)){
         	return null;
         }
    	 
        List<Record> records = new LinkedList<Record>();
 
        String query = "SELECT  * FROM " + tableName;
 
        Cursor cursor = db.rawQuery(query, null);
 
        
        Record record = null;
        if (cursor.moveToFirst()) {
            do {
                record = new Record(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
                records.add(record);
            } while (cursor.moveToNext());
        }
 
        
        return records;
    }
    
public List<String> getAllYears(String tableName) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        List<String> records = new LinkedList<String>();
 
        String query = "SELECT * FROM " + tableName;
 
        Cursor cursor = db.rawQuery(query, null);
 
        if (cursor.moveToFirst()) {
            do {
                records.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
 
        
        return records;
    }

public PasswordRecord getPasswordRecord() {
	
	SQLiteDatabase db = this.getWritableDatabase();
	 
    List<String> records = new LinkedList<String>();

    String query = "SELECT * FROM passwordTable";

    Cursor cursor = db.rawQuery(query, null);
    
    PasswordRecord record = null;

    if (cursor.moveToFirst()) {
        record = new PasswordRecord(cursor.getInt(1),cursor.getString(2),cursor.getString(3));
    }

    
    return record;
}



    
   
 
}
