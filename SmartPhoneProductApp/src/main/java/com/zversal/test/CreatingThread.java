package com.zversal.test;

public class CreatingThread implements Runnable {

	public static void main(String args[]) {
		System.out.println("Inside : " + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
