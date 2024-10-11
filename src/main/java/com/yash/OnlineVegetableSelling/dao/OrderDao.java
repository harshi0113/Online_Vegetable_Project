package com.yash.OnlineVegetableSelling.dao;

import java.util.List;

import com.yash.OnlineVegetableSelling.domain.Order;

public interface OrderDao {
	public void addOrder(Order order);
	public void cancelOrder(int orderId);
	public void updateOrder(Order order);
	public Order getOrderById(int orderId);
	public List<Order> getAllOrder();
	

}
