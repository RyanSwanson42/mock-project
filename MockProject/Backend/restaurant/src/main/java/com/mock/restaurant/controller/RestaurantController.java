package com.mock.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mock.restaurant.model.Menu;
import com.mock.restaurant.model.MenuResponse;
import com.mock.restaurant.model.OrderRequest;
import com.mock.restaurant.model.OrderResponse;
import com.mock.restaurant.model.SubmitResponse;
import com.mock.restaurant.service.RestaurantService;

@RestController
@RequestMapping("/restaurantController")
public class RestaurantController {
	

	@Autowired
	RestaurantService service;
	
	
	// API to retrieve the menu
	@GetMapping(value = "/menu")
	public ResponseEntity<MenuResponse> getMenu() {
		MenuResponse res = new MenuResponse();
		HttpStatus status;
		try {
			status = HttpStatus.OK;
			List<Menu> menu = new ArrayList<>();
			menu = service.getMenu();
			res.setMenu(menu);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return new ResponseEntity<>(res, status);
	}
	
	
	// API to enter an order into the DB
	@PostMapping(value = "/order")
	public ResponseEntity<SubmitResponse> order(@RequestBody OrderRequest request) {
		SubmitResponse res = new SubmitResponse();
		HttpStatus status;
		try {
			status = HttpStatus.OK;
			res = service.submitOrder(request);
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

		return new ResponseEntity<>(res, status);
	}
	
	// API to retrieve items ordered
	@GetMapping(value = "/checkout/{tableNum}")
	public ResponseEntity<List<OrderResponse>> checkout(@PathVariable(name = "tableNum") int tableNum) {
		List<OrderResponse> res = new ArrayList<OrderResponse>();
		HttpStatus status;
		try {
			res = service.checkout(tableNum);
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
		return new ResponseEntity<>(res, status);
	}
	
	
	
	
	

}
