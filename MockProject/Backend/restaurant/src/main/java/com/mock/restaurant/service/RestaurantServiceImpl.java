package com.mock.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.restaurant.dao.RestaurantDAO;
import com.mock.restaurant.model.Menu;
import com.mock.restaurant.model.OrderRequest;
import com.mock.restaurant.model.OrderResponse;
import com.mock.restaurant.model.SubmitResponse;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired RestaurantDAO dao;
	
	public List<Menu> getMenu() throws RuntimeException {
		List<Menu> menu = new ArrayList<>();
		try {
			menu = dao.getMenu();
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return menu;
	}
	
	public SubmitResponse submitOrder(OrderRequest request) throws RuntimeException {
		SubmitResponse res = new SubmitResponse();
		try {
			double totalPrice = 0;
			for(Menu item : request.getMenu()) {
				totalPrice += item.getItem_price();
			}	
			int table_num = dao.submitOrder_1(totalPrice);
			dao.submitOrder_2(request, table_num);
			
			res.setTable_num(table_num);
			res.setMessage("Success");
			res.setSuccess(1);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return res;
	}
	
	public List<OrderResponse> checkout(int tableNum) throws RuntimeException {
		List<OrderResponse> res = new ArrayList<OrderResponse>();
		try {
			res = dao.getOrder(tableNum);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return res;
	}
	
	

}
