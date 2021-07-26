package com.mock.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mock.restaurant.model.Menu;
import com.mock.restaurant.model.OrderRequest;
import com.mock.restaurant.model.OrderResponse;
import com.mock.restaurant.model.SubmitResponse;

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {
	@Autowired JdbcTemplate jdbcTemplate;
	
	
	public List<Menu> getMenu() throws RuntimeException {
		List<Menu> menuItems = new ArrayList<>();
		String sql = "SELECT * FROM menu";		
		try {
			menuItems = jdbcTemplate.query(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement preparedStatement) throws SQLException {

				}
				
			}, BeanPropertyRowMapper.newInstance(Menu.class));		
		} 
		catch (InvalidResultSetAccessException e) {
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e) {
		    throw new RuntimeException(e);
		}

		
		return menuItems;
	}
	
	public Integer submitOrder_1(double totalPrice) throws RuntimeException {

		String sql = "INSERT INTO orders (total_price) "
				+ "VALUES(?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {	
			jdbcTemplate.update(new PreparedStatementCreator() {	
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, new String[] { "table_num" });
					ps.setDouble(1, totalPrice);
					return ps;
				}		
			}, keyHolder);
		}
		catch (InvalidResultSetAccessException e) {
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e) {
		    throw new RuntimeException(e);
		}
		
		return Integer.valueOf(keyHolder.getKey().toString());
	}
	
	public SubmitResponse submitOrder_2(OrderRequest request, int table_num) throws RuntimeException {
		SubmitResponse res = new SubmitResponse();
		String sql = "INSERT INTO orders_menu_junction (table_num, item_id) "
				+ "VALUES(?,?);";
		try {
			List<Menu> orders = request.getMenu();		
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
		        @Override
		        public void setValues(PreparedStatement ps, int i)
		            throws SQLException {

		            Menu order = orders.get(i);
		            ps.setInt(1, table_num);
		            ps.setInt(2, order.getItem_id());


		        }

		        @Override
		        public int getBatchSize() {
		            return orders.size();
		        }
				
			});
		}
		catch (InvalidResultSetAccessException e) {
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e) {
		    throw new RuntimeException(e);
		}	
		return res;
	}
	
	public List<OrderResponse> getOrder(int table_num) throws RuntimeException {
		List<OrderResponse> resultSet = new ArrayList<OrderResponse>();
		String sql = "SELECT o.table_num, o.total_price, m.item_id, m.item_name, m.item_price"
				+ " FROM orders o, menu m, orders_menu_junction om "
				+ "WHERE o.table_num = ? and "
				+ "o.table_num = om.table_num and "
				+ "om.item_id = m.item_id";
		try {
			resultSet = jdbcTemplate.query(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setInt(1, table_num);
				}			
			}, BeanPropertyRowMapper.newInstance(OrderResponse.class));
		} 
		catch (InvalidResultSetAccessException e) {
		    throw new RuntimeException(e);
		} 
		catch (DataAccessException e) {
		    throw new RuntimeException(e);
		}			
		return resultSet;
	}


}
