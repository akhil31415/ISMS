package com.skag.ui;

import javax.swing.*;
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
    //JButton editButton=new JButton("EDIT");
    //JButton deleteButton=new JButton("DELETE");
    


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

       insertButton.setBounds(50,370,100,30);
       //editButton.setBounds(50,300,100,30);
       //deleteButton.setBounds(50,300,100,30);

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
       //container.add(editButton);
       //container.add(deleteButton);
   }


    @Override
    public void actionPerformed(ActionEvent e) {
    	System.out.println("Button pressed");
    }
}

public class CustomerUI {
    public static void main(String[] a){
    	CustomerFrame frame=new CustomerFrame();
        frame.setTitle("Customer Insert");
        frame.setVisible(true);
        frame.setBounds(10,10,600,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}