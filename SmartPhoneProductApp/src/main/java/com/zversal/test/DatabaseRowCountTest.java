package com.zversal.test;

import static com.zversal.application.MainApp.dbconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseRowCountTest implements Runnable {
	public static volatile int noOfDBHit;
	@Override
	public void run() {
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("select count(*) from products");
				ResultSet rs = ps.executeQuery();) {
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);
			noOfDBHit++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
