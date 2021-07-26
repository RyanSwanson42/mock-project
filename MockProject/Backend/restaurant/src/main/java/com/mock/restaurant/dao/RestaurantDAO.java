package com.mock.restaurant.dao;

import java.util.List;

import com.mock.restaurant.model.Menu;
import com.mock.restaurant.model.OrderRequest;
import com.mock.restaurant.model.OrderResponse;
import com.mock.restaurant.model.SubmitResponse;

public interface RestaurantDAO {
	
	public List<Menu> getMenu();
	
	public Integer submitOrder_1(double totalPrice) throws RuntimeException;
	
	public SubmitResponse submitOrder_2(OrderRequest request, int table_num) throws RuntimeException;
	
	public List<OrderResponse> getOrder(int table_num) throws RuntimeException;
	


}
