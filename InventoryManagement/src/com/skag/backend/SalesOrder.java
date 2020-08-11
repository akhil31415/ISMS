package com.skag.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesOrder {
	private int id;
	private java.util.Date date;
	private int quantity;
	private int prodId;
	private int custId;
	
	/*	Sales order :  : 1
	 * 	Purchase 	:  : 0
	 * */
	private int salePurchase;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getSalePurchase() {
		return salePurchase;
	}
	public void setSalePurchase(int salePurchase) {
		this.salePurchase = salePurchase;
	}
	
	public int addOrder() {
		String orderInsert = "INSERT INTO ORDER (orderDate, quantity, salesOrder, ProductID, CustomerID) VALUES (" 
							+ "\'" + date + "\'" + "," 
							+ quantity + "," 
							+ salePurchase + "," 
							+ prodId + "," 
							+ custId + ")";
		return new DBConnect().updtable(orderInsert);

	}

	public int modOrder() {
		String orderUpd = "UPDATE ORDER SET orderDate= " 
						+ "\'" + date + "\'" + ",  price= " 
						+ quantity 
						+ " WHERE ID="
				+ getId();
		return new DBConnect().updtable(orderUpd);
	}

	public int delOrder() {
		String orderDel = "DELETE FROM ORDER WHERE ID=" + id;
		return new DBConnect().updtable(orderDel);
	}

	public static SalesOrder getOrder(int id) {

		SalesOrder order = new SalesOrder();
		String prodsel = "SELECT * FROM ORDER WHERE ID=" + id;
		ResultSet rs = new DBConnect().select(prodsel);
		try {
			if (rs.next()) {
				order.setId(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setQuantity(rs.getInt(3));
				order.setSalePurchase(rs.getInt(4));
				order.setProdId(rs.getInt(5));
				order.setCustId(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}	
}
