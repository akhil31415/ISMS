package com.skag.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class OrderFrame extends JFrame implements ActionListener {

	Container container = getContentPane();

	JRadioButton salesButton = new JRadioButton("Sales Order", true);
	JRadioButton purchaseButton = new JRadioButton("Purchase Order", false);

	JLabel orderIdlabel = new JLabel("Order ID");
	JLabel productIdlabel = new JLabel("Product ID");
	JLabel customerIdlabel = new JLabel("Customer ID");
	JLabel dateLabel = new JLabel("Date");
	JLabel quantityLabel = new JLabel("Quantity");
	JLabel priceLabel = new JLabel("Price");

	JTextField orderIDField = new JTextField();
	JTextField productIDField = new JTextField();
	JTextField customerIDTextField = new JTextField();
	DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
	JFormattedTextField dateField = new JFormattedTextField(format);
	JTextField quantityField = new JTextField();
	JTextField priceField = new JTextField();

	JRadioButton insertButton = new JRadioButton("INSERT", false);
	JRadioButton editButton = new JRadioButton("EDIT", false);
	JRadioButton deleteButton = new JRadioButton("DELETE", false);

	JButton confirmButton = new JButton("CONFIRM");
	JButton returnButton = new JButton("To Dashboard");

	OrderFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		salesButton.addActionListener(this);
		purchaseButton.addActionListener(this);
		confirmButton.addActionListener(this);
		returnButton.addActionListener(this);
		insertButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		salesButton.setBounds(50, 50, 100, 30);
		purchaseButton.setBounds(150, 50, 150, 30);

		orderIdlabel.setBounds(50, 100, 100, 30);
		orderIDField.setBounds(150, 100, 50, 30);

		dateLabel.setBounds(50, 150, 100, 30);
		dateField.setBounds(150, 150, 100, 30);

		productIdlabel.setBounds(50, 200, 100, 30);
		productIDField.setBounds(150, 200, 50, 30);

		quantityLabel.setBounds(50, 250, 100, 30);
		quantityField.setBounds(150, 250, 50, 30);

		priceLabel.setBounds(50, 300, 100, 30);
		priceField.setBounds(150, 300, 100, 30);

		customerIdlabel.setBounds(50, 350, 100, 30);
		customerIDTextField.setBounds(150, 350, 50, 30);

		insertButton.setBounds(80, 400, 100, 30);
		editButton.setBounds(180, 400, 100, 30);
		deleteButton.setBounds(280, 400, 100, 30);

		confirmButton.setBounds(80, 480, 150, 30);
		returnButton.setBounds(250, 480, 150, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(salesButton);
		container.add(purchaseButton);

		container.add(orderIdlabel);
		container.add(productIdlabel);
		container.add(customerIdlabel);
		container.add(dateLabel);
		container.add(quantityLabel);
		container.add(priceLabel);

		container.add(orderIDField);
		container.add(productIDField);
		container.add(customerIDTextField);
		container.add(dateField);
		container.add(quantityField);
		container.add(priceField);

		container.add(insertButton);
		container.add(editButton);
		container.add(deleteButton);

		container.add(confirmButton);
		container.add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("INSERT".equals(e.getActionCommand())) {
			editButton.setSelected(false);
			deleteButton.setSelected(false);
			orderIDField.setEditable(false);
			productIDField.setEditable(true);
			customerIDTextField.setEditable(true);
			dateField.setEditable(true);
			quantityField.setEditable(true);
			priceField.setEditable(true);
		} else if ("EDIT".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			deleteButton.setSelected(false);
			orderIDField.setEditable(true);
			productIDField.setEditable(true);
			customerIDTextField.setEditable(true);
			dateField.setEditable(true);
			quantityField.setEditable(true);
			priceField.setEditable(true);
		} else if ("DELETE".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			editButton.setSelected(false);
			orderIDField.setEditable(true);
			productIDField.setEditable(false);
			customerIDTextField.setEditable(false);
			dateField.setEditable(false);
			quantityField.setEditable(false);
			priceField.setEditable(false);
		} else if ("To Dashboard".equals(e.getActionCommand())) {
			this.dispose();
			DashboardFrame df = new DashboardFrame();
			df.setTitle("Dashboard");
			df.setVisible(true);
			df.setBounds(10, 10, 600, 700);
			df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			df.setResizable(false);
		} else if ("Sales Order".equals(e.getActionCommand())) {
			purchaseButton.setSelected(false);
			salesButton.setSelected(true);
		} else if ("Purchase Order".equals(e.getActionCommand())) {
			salesButton.setSelected(false);
			purchaseButton.setSelected(true);
		} else {
			// to confirm code
			int action = -1;
			if (salesButton.isSelected())
				action = 1;
			else if (purchaseButton.isSelected())
				action = 0;
				if (validateIp(orderIDField.getText(), dateField.getText(), productIDField.getText(), priceField.getText(), quantityField.getText(), customerIdlabel.getText())!=0) {
					//code after validation 
				}else 
					return;
			

		}
	}
	
	private int validateIp(String orderid, String orderDate, String productid, String price, String quant, String custid ) {
		//validate the input parameters
		
		return 0;
	}
}

public class OrderUI {
	public static void main(String[] a) {
		OrderFrame frame = new OrderFrame();
		frame.setTitle("Order");
		frame.setVisible(true);
		frame.setBounds(10, 10, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}