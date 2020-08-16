package com.skag.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

class DashboardFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
	JButton custButton = new JButton("Customer");
	JButton prodButton = new JButton("Product");
	JButton orderButton = new JButton("Order");
	JButton reportButton = new JButton("Report");

	DashboardFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		custButton.addActionListener(this);
		prodButton.addActionListener(this);
		orderButton.addActionListener(this);
		reportButton.addActionListener(this);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
		custButton.setBounds(50, 150, 100, 30);
		prodButton.setBounds(50, 200, 100, 30);
		orderButton.setBounds(50, 250, 100, 30);
		reportButton.setBounds(50, 300, 100, 30);

	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(custButton);
		container.add(prodButton);
		container.add(orderButton);
		container.add(reportButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Customer".equals(e.getActionCommand())) {
			this.dispose();
			CustomerFrame c = new CustomerFrame();
			c.setTitle("Customer");
			c.setVisible(true);
			c.setBounds(10, 10, 600, 700);
			c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			c.setResizable(false);
		} else if ("Product".equals(e.getActionCommand())) {
			this.dispose();
			ProductFrame p = new ProductFrame();
			p.setTitle("Product");
			p.setVisible(true);
			p.setBounds(10, 10, 600, 700);
			p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			p.setResizable(false);
		}else if ("Order".equals(e.getActionCommand())) {
			this.dispose();
			OrderFrame o = new OrderFrame();
			o.setTitle("Order");
			o.setVisible(true);
			o.setBounds(10, 10, 600, 700);
			o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			o.setResizable(false);
		} else {
			this.dispose();
			ReportFrame p = new ReportFrame();
			p.setTitle("Report");
			p.setVisible(true);
			p.setBounds(10, 10, 600, 700);
			p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			p.setResizable(false);
		}
	}
}