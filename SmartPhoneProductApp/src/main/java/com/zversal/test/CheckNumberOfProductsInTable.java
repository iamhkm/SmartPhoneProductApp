package com.zversal.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class CheckNumberOfProductsInTable {
	public static void main(String args[]) {
		final Runtime r = Runtime.getRuntime();
		final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleAtFixedRate(new DatabaseRowCountTest(), 2, 2, TimeUnit.SECONDS);
		NumberOfDatabaseHits hitRunnable = new NumberOfDatabaseHits();
		Thread t = new Thread(hitRunnable);
		t.start();

		// NumberOfDatabaseHits hitRunnable2 = new NumberOfDatabaseHits();
		// Thread t2 = new Thread(hitRunnable2);
		// t2.start();

		r.addShutdownHook(new Thread() {
			@Override
			public void run() {
				scheduledExecutorService.shutdown();
				System.out.println("executor shut down");
				// NumberOfDatabaseHits.ConditionForGracefullKill = false;
				System.out.println("thread killed gracefully");
				System.out.println("shut down hook task completed..");
			}
		});
	}
}
