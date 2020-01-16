package com.woniuxy.entities;

import java.util.Date;

public class Order {

	private String orders_id;
	private int cus_id;
	private float orders_price;
	private String order_status;
	private Date orders_time;
	private String orders_remark;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(String orders_id, int cus_id, float orders_price, String order_status, Date orders_time,
			String orders_remark) {
		super();
		this.orders_id = orders_id;
		this.cus_id = cus_id;
		this.orders_price = orders_price;
		this.order_status = order_status;
		this.orders_time = orders_time;
		this.orders_remark = orders_remark;
	}
	public Order(int cus_id, float orders_price, String orders_remark) {
		super();
		this.cus_id = cus_id;
		this.orders_price = orders_price;
		this.orders_remark = orders_remark;
	}
	public String getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public float getOrders_price() {
		return orders_price;
	}
	public void setOrders_price(float orders_price) {
		this.orders_price = orders_price;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public Date getOrders_time() {
		return orders_time;
	}
	public void setOrders_time(Date orders_time) {
		this.orders_time = orders_time;
	}
	public String getOrders_remark() {
		return orders_remark;
	}
	public void setOrders_remark(String orders_remark) {
		this.orders_remark = orders_remark;
	}
	@Override
	public String toString() {
		return "Order [orders_id=" + orders_id + ", cus_id=" + cus_id + ", orders_price=" + orders_price
				+ ", order_status=" + order_status + ", orders_time=" + orders_time + ", orders_remark=" + orders_remark
				+ "]";
	}
	
	

}
