package com.skag.ui;

import javax.swing.*;

import com.skag.backend.Customer;
import com.skag.backend.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomerFrame extends JFrame implements ActionListener {

    Container container=getContentPane();
    JLabel customerNameLabel=new JLabel("Customer Name");
    JLabel customerAdressLabel=new JLabel("Adress");
    JLabel customerContactLabel=new JLabel("Contact Number");

    JTextField customerNameTextField=new JTextField();
    JTextField customerAdressField=new JTextField();
    JTextField customerContactField=new JTextField();

    JButton insertButton=new JButton("INSERT");
    JButton editButton=new JButton("EDIT");
    JButton deleteButton=new JButton("DELETE");
    
    CustomerFrame()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        insertButton.addActionListener(this);

    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       customerNameLabel.setBounds(50,150,100,30);
       customerAdressLabel.setBounds(50,220,100,30);
       customerContactLabel.setBounds(50,290,100,30);

       customerNameTextField.setBounds(150,150,150,30);
       customerAdressField.setBounds(150,220,150,30);
       customerContactField.setBounds(150,290,150,30);

       insertButton.setBounds(50,350,100,30);
       editButton.setBounds(200,350,100,30);
       deleteButton.setBounds(350,350,100,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(customerNameLabel);
       container.add(customerAdressLabel);
       container.add(customerContactLabel);
       container.add(customerNameTextField);
       container.add(customerAdressField);
       container.add(customerContactField);
       
       container.add(insertButton);
       container.add(editButton);
       container.add(deleteButton);
   }


    @Override
    public void actionPerformed(ActionEvent e) {
    	Customer cust= new Customer();
    	if("INSERT".equals(e.getActionCommand())) {    		
    		cust.setName(customerNameTextField.getText());
    		cust.setAddress(customerAdressField.getText());
    		cust.setContact(Integer.parseInt((customerContactField.getText())));
    		int ret = cust.addCustomer();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Customer Added!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Customer Addition!");
    		
    	} else if ("MODIFY".equals(e.getActionCommand())) {
    		cust.setName(customerNameTextField.getText());
    		cust.setAddress(customerAdressField.getText());
    		cust.setContact(Integer.parseInt(customerContactField.getText()));
    		int ret = cust.modCustomer();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Customer details Modified!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Customer detail Modification!");
    	}else {
/*    		cust.setId((customerNameTextField.getText()));
    		int ret = cust.delCustomer();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Customer Details Deleted!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Customer Details Deletion!");*/
    	}
    }
}

public class CustomerUI {
    public static void main(String[] a){
    	CustomerFrame frame=new CustomerFrame();
        frame.setTitle("Customer");
        frame.setVisible(true);
        frame.setBounds(10,10,500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}