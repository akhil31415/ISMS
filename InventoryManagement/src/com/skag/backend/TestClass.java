package com.skag.backend;

public class TestClass {

	public static void main(String[] args) {

		/*
		 * Customer c= new Customer(); c.setName("Sushant"); c.setAddress("Mumbai");
		 * c.setContact(123456); int ret=c.addCustomer(); if (ret==1)
		 * System.out.println("Customer Added"); else
		 * System.out.println("Error in customer addition. Returned "+ret);
		 */

		/*
		 * Customer c= new Customer(); c.setId(2); c.setName("Khulge");
		 * c.setAddress("Goregaon"); c.setContact(12345679); int ret=c.modifyCustomer();
		 * if (ret==1) System.out.println("Customer updated"); else
		 * System.out.println("Error in customer addition. Returned "+ret);
		 */

		/*
		 * Customer c= new Customer(); c.setId(3); int ret=c.delCustomer(); if (ret==1)
		 * System.out.println("Customer deleted"); else
		 * System.out.println("Error in customer addition. Returned "+ret);
		 */

		/*
		 * Customer c=Customer.getCustomer(2); if (c != null)
		 * System.out.println("Customer details : \n ID: "+c.getId()
		 * +"\n name: "+c.getName() +"\n address: "+c.getAddress()
		 * +"\n Number: "+c.getContact() ); else
		 * System.out.println("Error in customer addition. Returned ");
		 */

/*		Product p = new Product();
		p.setName("Green April");
		p.setPrice(400);
		int ret = p.addProd();
		if (ret == 1)
			System.out.println("Product added");
		else
			System.out.println("Error in Product addition. Returned " + ret);
		
		
		p.setId(2);
		p.setName("White Forrest");
		p.setPrice(326.39f);
		ret = p.modProd();
		if (ret == 1)
			System.out.println("Product updated");
		else
			System.out.println("Error in Product update. Returned " + ret);	*/
		
		ReportGeneration rg= new ReportGeneration();
		rg.process(0, null, null);
	}

}
