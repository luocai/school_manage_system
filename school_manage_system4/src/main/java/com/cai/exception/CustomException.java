package com.cai.exception;

public class CustomException extends Exception{

	private String messege;

	public CustomException(String messege) {
		super();
		this.messege = messege;
	}

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
	
}
