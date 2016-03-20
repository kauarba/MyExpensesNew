package com.example.myexpenses;

public class Record {
	private String date;
	private String item;
	private int amount;
	private String paymentMode;
	
	Record(String date, String item, int amount, String mode){
		this.date = date;
		this.item = item;
		this.amount = amount;
		this.paymentMode = mode;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setMode(String mode){
		this.paymentMode = mode;
	}
	
	public String getMode(){
		return this.paymentMode;
	}
	
	public void setItem(String item){
		this.item = item;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getItem(){
		return this.item;
	}
	
	public int getAmount(){
		return this.amount;
	}
}
