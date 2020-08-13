package com.skag.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.skag.backend.Customer;

class CustomerFrame extends JFrame implements ActionListener {

	Container container = getContentPane();
	JLabel customerIdlabel = new JLabel("Customer ID");
	JLabel customerNameLabel = new JLabel("Customer Name");
	JLabel customerAdressLabel = new JLabel("Adress");
	JLabel customerContactLabel = new JLabel("Contact Number");

	JTextField customerIDField = new JTextField();
	JTextField customerNameTextField = new JTextField();
	JTextField customerAdressField = new JTextField();
	JTextField customerContactField = new JTextField();

	JRadioButton insertButton = new JRadioButton("INSERT", true);
	JRadioButton editButton = new JRadioButton("EDIT", false);
	JRadioButton deleteButton = new JRadioButton("DELETE", false);

	JButton confirmButton = new JButton("CONFIRM");
	JButton returnButton = new JButton("To Dashboard");

	CustomerFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		insertButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		confirmButton.addActionListener(this);
		returnButton.addActionListener(this);
		customerIDField.setEditable(false);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
		customerIdlabel.setBounds(50, 80, 100, 30);
		customerNameLabel.setBounds(50, 150, 100, 30);
		customerAdressLabel.setBounds(50, 220, 100, 30);
		customerContactLabel.setBounds(50, 290, 100, 30);

		customerIDField.setBounds(150, 80, 150, 30);
		customerNameTextField.setBounds(150, 150, 150, 30);
		customerAdressField.setBounds(150, 220, 150, 30);
		customerContactField.setBounds(150, 290, 150, 30);

		insertButton.setBounds(50, 350, 100, 30);
		editButton.setBounds(200, 350, 100, 30);
		deleteButton.setBounds(350, 350, 100, 30);
		confirmButton.setBounds(80, 450, 150, 30);
		returnButton.setBounds(250, 450, 150, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(customerIdlabel);
		container.add(customerNameLabel);
		container.add(customerAdressLabel);
		container.add(customerContactLabel);
		container.add(customerIDField);
		container.add(customerNameTextField);
		container.add(customerAdressField);
		container.add(customerContactField);

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
			customerIDField.setEditable(false);
			customerNameTextField.setEditable(true);
			customerAdressField.setEditable(true);
			customerContactField.setEditable(true);
		} else if ("EDIT".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			deleteButton.setSelected(false);
			customerIDField.setEditable(true);
			customerNameTextField.setEditable(true);
			customerAdressField.setEditable(true);
			customerContactField.setEditable(true);
		} else if ("DELETE".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			editButton.setSelected(false);
			customerIDField.setEditable(true);
			customerNameTextField.setEditable(false);
			customerAdressField.setEditable(false);
			customerContactField.setEditable(false);
		} else if ("To Dashboard".equals(e.getActionCommand())) {
			this.dispose();
			DashboardFrame df = new DashboardFrame();
			df.setTitle("Report");
			df.setVisible(true);
			df.setBounds(10, 10, 600, 700);
			df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			df.setResizable(false);
		} else {
			try {
				Customer cust = new Customer();
				if (insertButton.isSelected()) {
					cust.setName(customerNameTextField.getText());
					cust.setAddress(customerAdressField.getText());
					cust.setContact(Long.parseLong((customerContactField.getText())));
					//cust.setContact(new BigInteger(customerContactField.getText()));
					int ret = cust.addCustomer();
					if (ret == 1) {
						JOptionPane.showMessageDialog(this, "Customer Added!");
						wipeFrame();
					} else
						JOptionPane.showMessageDialog(this, "Error in Customer Addition!");
				} else if (editButton.isSelected()) {
					if (customerIDField.getText() != null) {
						cust.setId(Integer.parseInt(customerIDField.getText()));
						if ((customerNameTextField.getText() == null
								|| "".equals(customerNameTextField.getText().trim()))
								&& (customerContactField.getText() == null
										|| "".equals(customerContactField.getText().trim()))
								&& (customerAdressField.getText() == null
										|| "".equals(customerAdressField.getText().trim()))) {
							JOptionPane.showMessageDialog(this, "Please enter details to modify !!");
							return;
						} else {
							cust.setName(customerNameTextField.getText());
							cust.setAddress(customerAdressField.getText());
							if ("".equals(customerContactField.getText()))
									cust.setContact(0L);
							else 
								cust.setContact(Long.parseLong((customerContactField.getText())));
								//cust.setContact(new BigInteger(customerContactField.getText()));
						}
						int ret = cust.modCustomer();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Customer details Modified!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Customer detail Modification!");
					} else
						JOptionPane.showMessageDialog(this, "Please enter a valid Customer id !!");
				} else if (deleteButton.isSelected()) {
					if (customerIDField.getText() != null) {
						cust.setId((Integer.parseInt(customerIDField.getText())));
						int ret = cust.delCustomer();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Customer Deleted!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Customer Deletion!");
					} else
						JOptionPane.showMessageDialog(this, "Please enter a valid produt id !!");
				} else
					JOptionPane.showMessageDialog(this, "Please select one of the options!!");
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Please enter a valid numerical value !! ");
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "SYSTEM ERROR!!");
				e1.printStackTrace();
			}
		}
	}

	private void wipeFrame() {
		customerIDField.setText(null);
		customerNameTextField.setText(null);
		customerContactField.setText(null);
		customerAdressField.setText(null);
	}
}

public class CustomerUI {
	public static void main(String[] a) {
		CustomerFrame frame = new CustomerFrame();
		frame.setTitle("Customer");
		frame.setVisible(true);
		frame.setBounds(10, 10, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

	}

}