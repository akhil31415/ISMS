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

import com.skag.backend.ReportGeneration;

public class ReportFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
	/*JLabel startDatelbl = new JLabel("Start Date: ");
	JLabel endDatelbl = new JLabel("End Date: ");

	DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
	JFormattedTextField startDateFld = new JFormattedTextField(format);
	JFormattedTextField endDateFld = new JFormattedTextField(format);*/

	JButton genButton = new JButton("GENERATE");
	JRadioButton salesButton = new JRadioButton("Sales Report", true);
	JRadioButton purchaseButton = new JRadioButton("Purchase Report", false);

	ReportFrame() {
		// Calling methods inside constructor.
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		genButton.addActionListener(this);
		salesButton.addActionListener(this);
		purchaseButton.addActionListener(this);
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		// Setting location and Size of each components using setBounds() method.
/*		startDatelbl.setBounds(50, 150, 100, 30);
		endDatelbl.setBounds(50, 220, 100, 30);

		startDateFld.setBounds(150, 150, 150, 30);
		endDateFld.setBounds(150, 220, 150, 30);*/
		
		salesButton.setBounds(50, 300, 100, 30);
		purchaseButton.setBounds(150, 300, 150, 30);
		genButton.setBounds(50, 350, 100, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
/*		container.add(startDatelbl);
		container.add(endDatelbl);
		container.add(startDateFld);
		container.add(endDateFld);*/
		
		container.add(salesButton);
		container.add(purchaseButton);
		container.add(genButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("Sales Report".equals(e.getActionCommand()))
			purchaseButton.setSelected(false);

		if("Purchase Report".equals(e.getActionCommand()))
			salesButton.setSelected(false);
		
		if ("GENERATE".equals(e.getActionCommand())){
			ReportGeneration rp = new ReportGeneration();
			//rp.process(salesButton.isSelected()?1:0, startDateFld.getText(), endDateFld.getText());
			rp.process(salesButton.isSelected()?1:0, null, null);
		}
	}
}
