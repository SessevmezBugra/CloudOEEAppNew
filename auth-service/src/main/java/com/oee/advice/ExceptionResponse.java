package com.oee.advice;

import java.util.Date;

public class ExceptionResponse {
	
	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ExceptionResponse(Date date, String message) {
		this.date = date;
		this.message = message;
	}

	private Date date;
	private String message;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}