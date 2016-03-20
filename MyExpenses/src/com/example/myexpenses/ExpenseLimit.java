package com.example.myexpenses;

public class ExpenseLimit {
	private int limit;
	private int totalExpense;
	private String month;
	
	public ExpenseLimit(String month)
	{
		this.limit = 7000;
		this.totalExpense = 0;
		this.month = month;
	}
	public void setExpense(int expense){
		this.totalExpense += expense;
	}
	
	public void setLimit(int limit){
		this.limit = limit;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public int getExpense(){
		return this.totalExpense;
	}
	
	public int getLimit(){
		return this.limit;
	}
	
	public String getMonth(){
		return this.month;
	}
}
