package com.zversal.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingCallableAndFuture {

	private static Callable<String> callable = new Callable<String>() {
		@Override
		public String call() throws Exception {
			// Perform some computation
			System.out.println("Entered Callable");
			Thread.sleep(10000);
			return "Hello from Callable";
		}

	};

	public static void main(String args[]) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<String> future = executorService.submit(callable);

		while (!future.isDone()) {
			System.out.println("Task is still not done...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Task completed! Retrieving the result");
		String result = "";
		try {
			result = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		executorService.shutdown();
	}
}
