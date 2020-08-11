package com.skag.backend;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	private String name;
	private String address;
	private int contact;
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContact() {
		return this.contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public int addCustomer() {
		String custInsert = "INSERT INTO CUSTOMER (Name, Address, Contactno) VALUES (" + "\'" + name + "\'" + "," + "\'"
				+ address + "\'" + "," + contact + ")";
		return new DBConnect().updtable(custInsert);

	}

	public int modCustomer() {
		String custUpd = "UPDATE CUSTOMER SET Name= " + "\'" + name + "\'" + ",  Address= " + "\'" + address + "\'"
				+ ", Contactno= " + contact + " WHERE ID=" + getId();
		return new DBConnect().updtable(custUpd);
	}

	public int delCustomer() {
		String custDel = "DELETE FROM CUSTOMER WHERE ID=" + id;
		return new DBConnect().updtable(custDel);
	}

	public static Customer getCustomer(int id) {

		Customer cust = new Customer();
		String custSel = "SELECT * FROM CUSTOMER WHERE ID=" + id;
		ResultSet rs = new DBConnect().select(custSel);
		try {
			if (rs.next()) {
				cust.setId(rs.getInt(1));
				cust.setName(rs.getString(2));
				cust.setAddress(rs.getString(3));
				cust.setContact(rs.getInt(4));
				
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

}
