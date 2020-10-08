package com.zversal.test;

import static com.zversal.application.MainApp.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class CheckNumberOfProductsInTable extends Thread {
	public void run() {
		for (;;) {
			try (Connection con = dbconnection.connection();
					PreparedStatement ps = con.prepareStatement("select count(*) from products");
					ResultSet rs = ps.executeQuery();) {
				rs.next();
				int count = rs.getInt(1);
				System.out.println(count);
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String args[]) {
		CheckNumberOfProductsInTable t1 = new CheckNumberOfProductsInTable();
		t1.start();
	}
}
