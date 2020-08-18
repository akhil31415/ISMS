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

import com.skag.backend.Product;

class ProductFrame extends JFrame implements ActionListener {

	Container container = getContentPane();
	JLabel prodctIdlabel = new JLabel("Product ID");
	JLabel productNameLabel = new JLabel("Product Name");
	JLabel productPriceLabel = new JLabel("Product Price");

	JTextField prodctIdField = new JTextField();
	JTextField productNameTextField = new JTextField();
	JTextField productPriceField = new JTextField();

	JRadioButton insertButton = new JRadioButton("INSERT", true);
	JRadioButton editButton = new JRadioButton("EDIT", false);
	JRadioButton deleteButton = new JRadioButton("DELETE", false);

	JButton confirmButton = new JButton("CONFIRM");
	JButton returnButton = new JButton("To Dashboard");

	ProductFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		insertButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		confirmButton.addActionListener(this);
		returnButton.addActionListener(this);
		prodctIdField.setEditable(false);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
		prodctIdlabel.setBounds(50, 80, 100, 30);
		productNameLabel.setBounds(50, 150, 100, 30);
		productPriceLabel.setBounds(50, 220, 100, 30);
		prodctIdField.setBounds(150, 80, 150, 30);
		productNameTextField.setBounds(150, 150, 150, 30);
		productPriceField.setBounds(150, 220, 150, 30);

		insertButton.setBounds(50, 350, 100, 30);
		editButton.setBounds(200, 350, 100, 30);
		deleteButton.setBounds(350, 350, 100, 30);
		confirmButton.setBounds(80, 450, 150, 30);
		returnButton.setBounds(250, 450, 150, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(prodctIdlabel);
		container.add(productNameLabel);
		container.add(productPriceLabel);
		container.add(prodctIdField);
		container.add(productNameTextField);
		container.add(productPriceField);
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
			prodctIdField.setEditable(false);
			productNameTextField.setEditable(true);
			productPriceField.setEditable(true);
			prodctIdField.setText(null);
		} else if ("EDIT".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			deleteButton.setSelected(false);
			prodctIdField.setEditable(true);
			productNameTextField.setEditable(true);
			productPriceField.setEditable(true);
			prodctIdField.setText(null);
		} else if ("DELETE".equals(e.getActionCommand())) {
			insertButton.setSelected(false);
			editButton.setSelected(false);
			prodctIdField.setEditable(true);
			productNameTextField.setEditable(false);
			productPriceField.setEditable(false);
			productNameTextField.setText(null);
			productPriceField.setText(null);
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
				Product prod = new Product();
				if (insertButton.isSelected()) {
					if ((productNameTextField.getText() == null || "".equals(productNameTextField.getText().trim()))
							|| (productPriceField.getText() == null || "".equals(productPriceField.getText().trim()))) {
						JOptionPane.showMessageDialog(this, "Please enter both details to add !!");
						return;
					} else {
						prod.setName(productNameTextField.getText());
						prod.setPrice(Float.parseFloat(productPriceField.getText()));
					}
					int ret = prod.addProd();
					if (ret == 1) {
						JOptionPane.showMessageDialog(this, "Product Added!");
						wipeFrame();
					} else
						JOptionPane.showMessageDialog(this, "Error in Product Addition!");
				} else if (editButton.isSelected()) {

					if (prodctIdField.getText() != null) {
						prod.setId(Integer.parseInt(prodctIdField.getText()));
						if ((productNameTextField.getText() == null || "".equals(productNameTextField.getText().trim()))
								&& (productPriceField.getText() == null
										|| "".equals(productPriceField.getText().trim()))) {
							JOptionPane.showMessageDialog(this, "Please enter details to modify !!");
							return;
						} else if ((productNameTextField.getText() != null
								|| !"".equals(productNameTextField.getText().trim()))
								&& (productPriceField.getText() == null
										|| "".equals(productPriceField.getText().trim()))) {
							prod.setName(productNameTextField.getText());
						} else if ((productNameTextField.getText() == null
								|| "".equals(productNameTextField.getText().trim()))
								&& (productPriceField.getText() != null
										|| !"".equals(productPriceField.getText().trim()))) {
							float tmpPrice = Float.parseFloat(productPriceField.getText());
							if (tmpPrice < 1)
								throw new NumberFormatException();
							else
								prod.setPrice(tmpPrice);
						} else {
							prod.setName(productNameTextField.getText());
							float tmpPrice = Float.parseFloat(productPriceField.getText());
							if (tmpPrice < 1)
								throw new NumberFormatException();
							else
								prod.setPrice(tmpPrice);
						}
						int ret = prod.modProd();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Product details Modified!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Product detail Modification!");
					} else
						JOptionPane.showMessageDialog(this, "Please enter a valid produt id !!");
				} else if (deleteButton.isSelected()) {
					if (prodctIdField.getText() != null) {
						prod.setId((Integer.parseInt(prodctIdField.getText())));
						int ret = prod.delProd();
						if (ret == 1) {
							JOptionPane.showMessageDialog(this, "Product Deleted!");
							wipeFrame();
						} else
							JOptionPane.showMessageDialog(this, "Error in Product Deletion!");
					} else
						JOptionPane.showMessageDialog(this, "Please enter a valid produt id !!");
				} else
					JOptionPane.showMessageDialog(this, "Please select one of the options!!");
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Please enter a valid numerical value greater than zero!! ");
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "SYSTEM ERROR!!");
				e1.printStackTrace();
			}
		}
	}

	private void wipeFrame() {
		prodctIdField.setText(null);
		productNameTextField.setText(null);
		productPriceField.setText(null);
	}
}

public class ProductUI {
	public static void main(String[] a) {
		ProductFrame frame = new ProductFrame();
		frame.setTitle("Product Insert");
		frame.setVisible(true);
		frame.setBounds(10, 10, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

	}

}