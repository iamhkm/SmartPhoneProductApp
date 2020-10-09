package com.zversal.test;

public class CreatingThread implements Runnable {

	public static void main(String args[]) {
		System.out.println("Inside : " + Thread.currentThread().getName());
		CreatingThread thread1 = new CreatingThread();
		CreatingThread thread2 = new CreatingThread();

		Thread thread = new Thread(thread1);
		thread.setName("thread one");
		thread.start();

		thread = new Thread(thread2);
		thread.setName("thread two");
		thread.start();

	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
