package com.skag.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.skag.backend.Product;

class ProductFrame extends JFrame implements ActionListener {

    Container container=getContentPane();
    JLabel prodctIdlabel=new JLabel("Product ID");
    JLabel productNameLabel=new JLabel("Product Name");
    JLabel productPriceLabel=new JLabel("Product Price");
    JTextField prodctIdField=new JTextField();
    JTextField productNameTextField=new JTextField();
    JTextField productPriceField=new JTextField();
    
    JButton insertButton=new JButton("INSERT");
    JButton editButton=new JButton("EDIT");
    JButton deleteButton=new JButton("DELETE");
    JButton returnButton=new JButton("To Dashboard");
    ProductFrame()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       productNameLabel.setBounds(50,150,100,30);
       productPriceLabel.setBounds(50,220,100,30);
       productNameTextField.setBounds(150,150,150,30);
       productPriceField.setBounds(150,220,150,30);
       insertButton.setBounds(50,300,100,30);
       editButton.setBounds(50,300,150,30);
       deleteButton.setBounds(50,300,200,30);
       returnButton.setBounds(100, 400, 150, 30);
   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(productNameLabel);
       container.add(productPriceLabel);
       container.add(productNameTextField);
       container.add(productPriceField);
       container.add(insertButton);
       container.add(editButton);
       container.add(deleteButton);
       container.add(returnButton);
   }


    @Override
    public void actionPerformed(ActionEvent e) {
    	Product prod= new Product();
    	if("INSERT".equals(e.getActionCommand())) {    		
    		prod.setName(productNameTextField.getText());
    		prod.setPrice(Float.parseFloat(productPriceField.getText()));
    		int ret = prod.addProd();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Product Added!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Product Addition!");
    		
    	} else if ("MODIFY".equals(e.getActionCommand())) {
    		prod.setName(productNameTextField.getText());
    		prod.setPrice(Float.parseFloat(productPriceField.getText()));
    		int ret = prod.modProd();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Product details Modified!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Product detail Modification!");
    	}else {
/*    		prod.setId((productNameTextField.getText()));
    		int ret = prod.delProd();
    		if (ret == 1)
    			JOptionPane.showMessageDialog(this, "Product Deleted!");
    		else 
    			JOptionPane.showMessageDialog(this, "Error in Product Deletion!");*/
    	}
    }
}

public class ProductUI {
    public static void main(String[] a){
    	ProductFrame frame=new ProductFrame();
        frame.setTitle("Product Insert");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}