package com.zversal.test;

public class NumberOfDatabaseHits implements Runnable {
	private int noOfCount=0;
	@Override
	public void run() {
		while(noOfCount<=20){
		System.out.println("DB hit by number of times=" + DatabaseRowCountTest.noOfDBHit);
		noOfCount++;
		try {
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}
