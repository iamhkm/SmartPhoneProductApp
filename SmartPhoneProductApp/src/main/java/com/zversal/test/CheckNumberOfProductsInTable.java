package com.zversal.test;

import static com.zversal.application.MainApp.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class CheckNumberOfProductsInTable implements Runnable {

	public static void main(String args[]) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = new CheckNumberOfProductsInTable();
		System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
		scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledExecutorService.shutdown();
	}

	@Override
	public void run() {
		try (Connection con = dbconnection.connection();
				PreparedStatement ps = con.prepareStatement("select count(*) from products");
				ResultSet rs = ps.executeQuery();) {
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
