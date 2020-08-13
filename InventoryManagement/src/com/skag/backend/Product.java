package com.skag.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	private String name;
	private float price;
	private int id;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int addProd() {
		String prodInsert = "INSERT INTO PRODUCT (Name, price) VALUES (" + "\'" + name + "\'" + "," + price + ")";
		return new DBConnect().updtable(prodInsert);

	}

	public int modProd() {

		String prodUpd = "UPDATE PRODUCT SET ";
		if ((name != null && !"".equals(name)) && price != 0)
			prodUpd = prodUpd + "Name= " + "\'" + name + "\'" + " , price= " + price;
		else if (name != null && !"".equals(name))
			prodUpd = prodUpd + "Name= " + "\'" + name + "\'";
		else if (price != 0)
			prodUpd = prodUpd + "price= " + price;

		prodUpd = prodUpd + " WHERE ID=" + id;
		return new DBConnect().updtable(prodUpd);
	}

	public int delProd() {
		String prodDel = "DELETE FROM PRODUCT WHERE ID=" + id;
		return new DBConnect().updtable(prodDel);
	}

	public static Product getProd(int id) {

		Product prod = new Product();
		String prodsel = "SELECT * FROM CUSTOMER WHERE ID=" + id;
		ResultSet rs = new DBConnect().select(prodsel);
		try {
			if (rs.next()) {
				prod.setId(rs.getInt(1));
				prod.setName(rs.getString(2));
				prod.setPrice(rs.getFloat(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}
}
