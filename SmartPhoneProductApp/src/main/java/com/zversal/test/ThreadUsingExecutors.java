package com.zversal.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadUsingExecutors {
	public static void main(String args[]) {
		
		/*
		System.out.println("Inside : " + Thread.currentThread().getName());

		System.out.println("Creating Executor Service...");
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		System.out.println("Creating a Runnable...");
		Runnable runnable = () -> {
			System.out.println("Inside : " + Thread.currentThread().getName());
		};

		System.out.println("Submit the task specified by the runnable to the executor service.");
		executorService.submit(runnable);
		executorService.shutdown();
		*/
		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
          System.out.println("Executing Task At " + System.nanoTime());
        };
        
        System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0,2, TimeUnit.SECONDS);
	}
}
