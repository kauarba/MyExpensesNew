package com.example.myexpenses;

public class PasswordRecord {
	
	private int isPassSet;
	private String emailId;
	private String password;
	
	public PasswordRecord(int isPassSet, String emailId, String password){
		
		this.isPassSet = isPassSet;
		this.emailId = emailId;
		this.password = password;
	}
	
	public int getIsPassSet(){
		return this.isPassSet;
	}
	
	public String getEmailId(){
		return this.emailId;
	}
	
	public String getPassword(){
		return this.password;
	}

}
