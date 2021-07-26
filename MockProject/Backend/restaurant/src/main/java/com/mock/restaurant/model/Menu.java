package com.mock.restaurant.model;

//item_id INT PRIMARY KEY,
//item_name VARCHAR(20) NOT NULL,
//item_price DOUBLE NOT NULL,
//item_desc VARCHAR(250) NOT NULL
public class Menu {
	private int item_id;
	private String item_name;
	private double item_price;
	private String item_desc;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
}
