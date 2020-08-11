package com.skag.ui;

import javax.swing.JFrame;

import com.skag.ui.ReportFrame;

public class TestUI {

	public static void main(String[] args) {
	
		ReportFrame frame = new ReportFrame();
		frame.setTitle("Report");
		frame.setVisible(true);
		frame.setBounds(10, 10, 370, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

}
