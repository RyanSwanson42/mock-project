package com.mock.restaurant.model;

public class SubmitResponse {

	private String message;
	private int table_num;
	private int success;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTable_num() {
		return table_num;
	}

	public void setTable_num(int table_num) {
		this.table_num = table_num;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	
}
