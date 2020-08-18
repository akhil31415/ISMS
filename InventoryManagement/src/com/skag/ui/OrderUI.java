package com.skag.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.skag.backend.SalesOrder;

class OrderFrame extends JFrame implements ActionListener {

	Container container = getContentPane();

	JRadioButton salesButton = new JRadioButton("Sales Order", true);
	JRadioButton purchaseButton = new JRadioButton("Purchase Order", false);

	JLabel orderIdlabel = new JLabel("Order ID");
	JLabel productIdlabel = new JLabel("Product ID");
	JLabel customerIdlabel = new JLabel("Customer ID");
	JLabel dateLabel = new JLabel("Date");
	JLabel quantityLabel = new JLabel("Quantity");
	// JLabel priceLabel = new JLabel("Price");

	JTextField orderIDField = new JTextField();
	JTextField productIDField = new JTextField();
	JTextField customerIDTextField = new JTextField();
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	JFormattedTextField dateField = new JFormattedTextField(format);
	JTextField quantityField = new JTextField();
	// JTextField priceField = new JTextField();

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

		// priceLabel.setBounds(50, 300, 100, 30);
		// priceField.setBounds(150, 300, 100, 30);

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
		// container.add(priceLabel);

		container.add(orderIDField);
		container.add(productIDField);
		container.add(customerIDTextField);
		container.add(dateField);
		container.add(quantityField);
		// container.add(priceField);

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
			orderIDField.setText(null);
			if (salesButton.isSelected())
				customerIDTextField.setEditable(true);
			dateField.setEditable(true);
			quantityField.setEditable(true);
			// priceField.setEditable(true);
		} else if ("EDIT".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			deleteButton.setSelected(false);
			orderIDField.setEditable(true);
			productIDField.setEditable(true);
			if (salesButton.isSelected())
				customerIDTextField.setEditable(true);
			dateField.setEditable(true);
			quantityField.setEditable(true);
			// priceField.setEditable(true);
		} else if ("DELETE".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			editButton.setSelected(false);
			orderIDField.setEditable(true);
			productIDField.setEditable(false);
			customerIDTextField.setEditable(false);
			dateField.setEditable(false);
			quantityField.setEditable(false);
			productIDField.setText(null);
			customerIDTextField.setText(null);
			dateField.setText(null);
			quantityField.setText(null);
			// priceField.setEditable(false);
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
			int type = -1;
			if (salesButton.isSelected())
				type = 1;
			else if (purchaseButton.isSelected())
				type = 0;
			if (insertButton.isSelected()) {
				if (valAdd(dateField.getText(), productIDField.getText(), quantityField.getText(),
						customerIDTextField.getText()) != 1) {
					try {
						SalesOrder so = new SalesOrder();
						so.setDate(dateField.getText());
						so.setProdId(Integer.parseInt(productIDField.getText()));
						so.setQuantity(Integer.parseInt(quantityField.getText()));
						if (salesButton.isSelected())
							so.setCustId(Integer.parseInt(customerIDTextField.getText()));
						so.setSalePurchase(type);
						int ret = so.addOrder();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Order Added!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Order Addition!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Error in Order Addition!");
						e1.printStackTrace();
					}
				}
			} else if (editButton.isSelected()) {
				if (valMod(orderIDField.getText(), dateField.getText(), productIDField.getText(),
						quantityField.getText(), customerIDTextField.getText()) != 1) {
					try {
						SalesOrder so = new SalesOrder();
						if (!"".equals(orderIDField.getText()))
							so.setId(Integer.parseInt(orderIDField.getText()));
						if (!"".equals(dateField.getText()))
							so.setDate(dateField.getText());
						if (!"".equals(productIDField.getText()))
							so.setProdId(Integer.parseInt(productIDField.getText()));
						if (!"".equals(quantityField.getText()))
							so.setQuantity(Integer.parseInt(quantityField.getText()));
						if (salesButton.isSelected())
							so.setCustId(Integer.parseInt(customerIDTextField.getText()));
						so.setSalePurchase(type);
						int ret = so.modOrder();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Order Modified!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Order Modification!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Error in Order Modification!");
						e1.printStackTrace();
					}
				}
			} else if (deleteButton.isSelected()) {
				if (valDel(orderIDField.getText()) != 1) {
					try {
						SalesOrder so = new SalesOrder();
						so.setId(Integer.parseInt(orderIDField.getText()));
						int ret = so.delOrder();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Order Deleted!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Order Deletion!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Error in Order  Deletion!");
						e1.printStackTrace();
					}
				}
			} else
				JOptionPane.showMessageDialog(this, "Please select one of the options!!");
		}
	}

	private int valAdd(String orderDate, String productId, String quant, String customerId) {
		// validate the input parameters
		int retVal = 0;
		try {
			if (orderDate == null || "".equals(orderDate) || productId == null || "".equals(productId) || quant == null
					|| "".equals(quant)) {
				retVal = 1;
				JOptionPane.showMessageDialog(this, "Please enter all the neccessary details !! ");
				return retVal;
			}
			format.parse(orderDate);
			int prodid = Integer.parseInt(productId);
			int noOfItems = Integer.parseInt(quant);
			if (prodid < 1 || noOfItems < 1) {
				retVal = 1;
				JOptionPane.showMessageDialog(this, "Please enter a valid numerical value greater than zero !! ");
				return retVal;
			}

			if (salesButton.isSelected()) {
				int custid = Integer.parseInt(customerId);
				if (custid < 1) {
					retVal = 1;
					JOptionPane.showMessageDialog(this,
							"Please enter a valid numerical value for customer ID field !! ");
					return retVal;
				}
			}
		} catch (NumberFormatException e) {
			retVal = 1;
			JOptionPane.showMessageDialog(this, "Please enter a valid numerical value greater than zero!! ");
			e.printStackTrace();
		} catch (ParseException e) {
			retVal = 1;
			JOptionPane.showMessageDialog(this, "Please enter a valid Date value with format 'MM-dd-YYYY'!! ");
			e.printStackTrace();
		}
		return retVal;
	}

	private int valMod(String orderID, String orderDate, String productId, String quant, String customerId) {
		int retVal = 0;
		try {
			if ((orderID == null || "".equals(orderID)) && (orderDate == null || "".equals(orderDate))
					&& (productId == null || "".equals(productId)) && (quant == null || "".equals(quant))
					&& (customerId == null || "".equals(customerId) && salesButton.isSelected())) {
				retVal = 1;
				JOptionPane.showMessageDialog(this, "Please enter all the neccessary details !! ");
				return retVal;
			}

			int id = Integer.parseInt(orderID);
			if (id < 1) {
				retVal = 1;
				JOptionPane.showMessageDialog(this,
						"Please enter a valid numerical value greater than zero for order ID !! ");
				return retVal;
			}
			if (orderDate != null && !"".equals(orderDate)) {
				format.parse(orderDate);
			}
			if (productId != null && !"".equals(productId.trim())) {
				int prodid = Integer.parseInt(productId);
				if (prodid < 1) {
					retVal = 1;
					JOptionPane.showMessageDialog(this,
							"Please enter a valid numerical value greater than zero for prod ID !! ");
					return retVal;
				}
			}
			if (quant != null && !"".equals(quant.trim())) {
				int noOfItems = Integer.parseInt(productId);
				if (noOfItems < 1) {
					retVal = 1;
					JOptionPane.showMessageDialog(this,
							"Please enter a valid numerical value greater than zero for qunatity !! ");
					return retVal;
				}
			}
			if (customerId != null && !"".equals(customerId.trim()) && salesButton.isSelected()) {
				int custid = Integer.parseInt(customerId);
				if (custid < 1) {
					retVal = 1;
					JOptionPane.showMessageDialog(this,
							"Please enter a valid numerical value greater than zero for Customer ID !! ");
					return retVal;
				}
			}
		} catch (NumberFormatException e) {
			retVal = 1;
			JOptionPane.showMessageDialog(this, "Please enter a valid numerical value greater than zero !! ");
			e.printStackTrace();
		} catch (ParseException e) {
			retVal = 1;
			JOptionPane.showMessageDialog(this, "Please enter a valid Date value with format 'MM-dd-YYYY'!! ");
			e.printStackTrace();
		}
		return retVal;
	}

	private int valDel(String orderID) {
		int retVal = 0;
		try {
			if (orderID == null || "".equals(orderID)) {
				retVal = 1;
				JOptionPane.showMessageDialog(this, "Please enter all the neccessary details !! ");
				return retVal;
			}
			int id = Integer.parseInt(orderID);
			if (id < 1) {
				retVal = 1;
				JOptionPane.showMessageDialog(this,
						"Please enter a valid numerical value greater than zero for ID field !! ");
			}
		} catch (NumberFormatException e) {
			retVal = 1;
			JOptionPane.showMessageDialog(this, "Please enter a valid numerical value greater than zero !! ");
			e.printStackTrace();
		}
		return retVal;
	}

	private void wipeFrame() {
		orderIDField.setText(null);
		dateField.setText(null);
		productIDField.setText(null);
		quantityField.setText(null);
		customerIDTextField.setText(null);
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