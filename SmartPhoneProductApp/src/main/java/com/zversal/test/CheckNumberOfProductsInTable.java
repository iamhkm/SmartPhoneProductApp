package com.zversal.test;

import static com.zversal.application.MainApp.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class CheckNumberOfProductsInTable extends Thread {
	
	volatile boolean finished = false;

	  public void stopMe()
	  {
	    finished = true;
	  }
	
	public void run() {
		while (!finished)
	    {
		for (;;) {
			if(!finished){
			try (Connection con = dbconnection.connection();
					PreparedStatement ps = con.prepareStatement("select count(*) from products");
					ResultSet rs = ps.executeQuery();) {
				rs.next();
				int count = rs.getInt(1);
				System.out.println(count);
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
			else{
				break;
			}
		}
	    }
	}

	public static void main(String args[]) {
		CheckNumberOfProductsInTable t1 = new CheckNumberOfProductsInTable();
		t1.start();
		try {
			CheckNumberOfProductsInTable.sleep(10000);
			t1.stopMe();
			System.out.println(t1.getName()+"stopped");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
