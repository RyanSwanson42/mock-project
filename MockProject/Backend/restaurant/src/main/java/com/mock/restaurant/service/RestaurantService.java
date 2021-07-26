package com.mock.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mock.restaurant.model.Menu;
import com.mock.restaurant.model.OrderRequest;
import com.mock.restaurant.model.OrderResponse;
import com.mock.restaurant.model.SubmitResponse;

@Service
public interface RestaurantService {
	
	public List<Menu> getMenu() throws RuntimeException;
	
	public SubmitResponse submitOrder(OrderRequest request) throws RuntimeException;
	
	public List<OrderResponse> checkout(int tableNum) throws RuntimeException;

}
