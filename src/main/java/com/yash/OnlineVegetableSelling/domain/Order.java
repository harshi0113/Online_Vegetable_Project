package com.yash.OnlineVegetableSelling.domain;

import java.util.Date;

public class Order {

	private int orderId;

	private long userId;

	private Date orderDate;

	private int totalAmount;

	private boolean orderStatus = false;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, long userId, Date orderDate, int totalAmount, boolean orderStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", orderStatus=" + orderStatus + "]";
	}

}
