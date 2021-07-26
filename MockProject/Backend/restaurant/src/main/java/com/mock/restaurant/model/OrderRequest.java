package com.mock.restaurant.model;

import java.util.List;

public class OrderRequest {
	
	private List<Menu> menu;

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
}
