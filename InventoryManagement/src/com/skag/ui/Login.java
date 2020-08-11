package com.skag.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginFrame extends JFrame implements ActionListener {

	Container container = getContentPane();
	JLabel userLabel = new JLabel("USERNAME");
	JLabel passwordLabel = new JLabel("PASSWORD");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("LOGIN");

	LoginFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		loginButton.addActionListener(this);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
		userLabel.setBounds(50, 150, 100, 30);
		passwordLabel.setBounds(50, 220, 100, 30);
		userTextField.setBounds(150, 150, 150, 30);
		passwordField.setBounds(150, 220, 150, 30);
		loginButton.setBounds(50, 300, 100, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(loginButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (("USER".equals(userTextField.getText()) || "SYS".equals(userTextField.getText()))
				&& userTextField.getText().equals(passwordField.getText())) {
			this.dispose();
			DashboardFrame d = new DashboardFrame();
			d.setTitle("Dashboard");
			d.setVisible(true);
			d.setBounds(10, 10, 370, 600);
			d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			d.setResizable(false);
		} else
			JOptionPane.showMessageDialog(this, "Invalid Credentials!");
	}
}

public class Login {
	public static void main(String[] a) {
		LoginFrame frame = new LoginFrame();
		frame.setTitle("Login");
		frame.setVisible(true);
		frame.setBounds(10, 10, 370, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}