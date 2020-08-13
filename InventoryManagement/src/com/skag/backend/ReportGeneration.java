package com.skag.backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportGeneration {
	String[][] records = new String[100][5];

	public void process(int salesPurchase, String d1, String d2) {
		try {
			String selRec = "SELECT id, orderDate, p.Name, quantity, (p.price*quantity)\r\n"
					+ "FROM order o, product p\r\n" 
					+ "WHERE o.ProductID = p.ID\r\n" 
					+ "AND o.salesOrder = "+ salesPurchase ;
					//+ " AND o.orderDate BETWEEN #"+d1+"# AND #"+d2+ "#" ;

			ResultSet rs = new DBConnect().select(selRec);
			int i = 0;

			while (rs.next()) {
				records[i][0] = String.valueOf(rs.getInt(1));
				records[i][1] = String.valueOf(rs.getDate(2));
				records[i][2] = rs.getString(3);
				records[i][3] = String.valueOf(rs.getInt(4));
				records[i][4] = String.valueOf(rs.getFloat(5));
				i++;
			}

			sort(i);
			if (salesPurchase == 1)
				fileprint("SalesReport.txt", records);
			else
				fileprint("PurchaseReport.txt", records);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sort(int reclen) {

		try {
			for (int i = 0; i < reclen - 1; i++) {
				for (int j = 0; j < reclen - i - 1; j++) {
					Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(records[j][1]);
					Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(records[j + 1][1]);
					if (d1.compareTo(d2) > 0) {
						String[] tmp = records[j];
						records[j] = records[j + 1];
						records[j + 1] = tmp;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void fileprint(String filename, String[][] records) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

			bw.write(
					"============================================================================================================\n");
			bw.write("OrderID\t Date\t Product\t\t Quantity\t Cost\n");
			bw.write(
					"============================================================================================================\n");

			for (int i = 0; i < 100; i++) {
				if (records[i][0] == null)
					break;
				bw.write(records[i][0] + "\t " + records[i][1] + "\t " + records[i][2] + "\t\t " + records[i][3] + "\t "
						+ records[i][4]);
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Report generation over");
		}
	}
}
