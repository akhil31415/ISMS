package com.skag.backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalesOrder {
	private int id;
	private String date;
	private int quantity;
	private int prodId;
	private int custId;

	/*
	 * Sales order : : 1 Purchase : : 0
	 */
	private int salePurchase;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public int addOrder() {
		try {
			java.sql.Date dd = new Date(format.parse(date).getTime());
			String orderInsert = "INSERT INTO ORDER (orderDate, quantity, salesOrder, ProductID, CustomerID) VALUES ("
					+ "#" + dd + "#" + "," + quantity + "," + salePurchase + "," + prodId + "," + custId + ")";
			return new DBConnect().updtable(orderInsert);
		} catch (ParseException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public int modOrder() {
		String orderUpd = "UPDATE ORDER SET ";
		try {
			if (prodId != 0 && quantity != 0 && date != null) {
				orderUpd = orderUpd + "orderDate = #" + new Date(format.parse(date).getTime()) + "# , ProductID = " + prodId
						+ ", quantity = " + quantity;
			} else if (prodId != 0 && quantity != 0) {
				orderUpd = orderUpd + " ProductID = " + prodId + ", quantity = " + quantity;
			} else if (prodId != 0 && date != null) {
				orderUpd = orderUpd + "orderDate = #" + new Date(format.parse(date).getTime()) + "# , ProductID = " + prodId;
			} else if (quantity != 0 && date != null) {
				orderUpd = orderUpd + "orderDate = #" + new Date(format.parse(date).getTime()) + "# , quantity = "
						+ quantity;
			} else if (prodId != 0) {
				orderUpd = orderUpd + " ProductID = " + prodId;
			} else if (quantity != 0) {
				orderUpd = orderUpd + " quantity = " + quantity;
			} else if (date != null) {
				orderUpd = orderUpd + "orderDate = #" + new Date(format.parse(date).getTime()) + "#";
			}

			if (salePurchase == 1 && custId != 0) {
				orderUpd = orderUpd + (orderUpd.length() > 17 ? ", " : "") + "CustomerID =" + custId;
			}

			orderUpd = orderUpd + " WHERE ID=" + getId();
			return new DBConnect().updtable(orderUpd);
		} catch (ParseException e) {
			e.printStackTrace();
			return 1;
		}
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
				order.setDate(rs.getDate(2).toString());
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
