package com.skag.backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGeneration {
	String [][] records = new String[100][5];
	public void process(int salesPurchase, java.util.Date startDate, java.util.Date endDate) {
		
		String selRec ="SELECT id, orderDate, p.Name, quantity, (p.price*quantity)\r\n" + 
				"FROM order o, product p\r\n" + 
				"WHERE o.ProductID = p.ID\r\n" + 
				"AND o.salesOrder = "+salesPurchase;
		
		ResultSet rs = new DBConnect().select(selRec);
		try {
			int i=0;
			while (rs.next()) {
				records[i][0] = String.valueOf(rs.getInt(1));
				records[i][1] = String.valueOf(rs.getDate(2));
				records[i][2] = rs.getString(3);
				records[i][3] = String.valueOf(rs.getInt(4));
				records[i][4] = String.valueOf(rs.getFloat(5));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sort(records);
		if (salesPurchase==1)			
			fileprint("SalesReport.txt", records);
		else
			fileprint("PurchaseReport.txt", records);
	}
	private void sort(Object records) {
		
		
	}
	private void fileprint(String filename, String[][] records) {
		
        try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			
			bw.write("============================================================================================================\n");
			bw.write("OrderID\t Date\t Product\t\t Quantity\t Cost\n");
			bw.write("============================================================================================================\n");
			
			for(int i=0; i<100; i++ ) {
				if (records[i][0]==null)
					break;
				bw.write(records[i][0]+"\t "+records[i][1]+"\t "+records[i][2]+"\t\t "+records[i][3]+"\t "+records[i][4]);
				bw.newLine();
			}		

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Report generation over");
		}
	}
}
