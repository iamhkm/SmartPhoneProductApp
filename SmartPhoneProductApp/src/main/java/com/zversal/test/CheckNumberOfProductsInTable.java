package com.zversal.test;

import static com.zversal.application.MainApp.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class CheckNumberOfProductsInTable {
	private static int i = 0;

	public static void main(String args[]) {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			try (Connection con = dbconnection.connection();
					PreparedStatement ps = con.prepareStatement("select count(*) from products");
					ResultSet rs = ps.executeQuery();) {
				rs.next();
				int count = rs.getInt(1);
				System.out.println(count);
				i++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}, 0, 2, TimeUnit.SECONDS);
		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) {
		 * e.printStackTrace(); } scheduledExecutorService.shutdown();
		 */

		scheduledExecutorService.scheduleAtFixedRate(() -> {
			System.out.println("count=" + i);
			if (i >= 30) {
				System.exit(0);
			}
		}, 10, 10, TimeUnit.SECONDS);

		Runtime r = Runtime.getRuntime();
		r.addShutdownHook(new Thread(() -> {
			System.out.println("shutdown executed");
			scheduledExecutorService.shutdown();
		}));
	}
}
