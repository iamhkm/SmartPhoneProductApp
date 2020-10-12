package com.zversal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckingExecutorService implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return Thread.currentThread().getName();
	}

	public static void main(String args[]) {
		//List<Future<String>> listFuture = new ArrayList<Future<String>>();
		Callable<String> ces = new CheckingExecutorService();
		Callable<String> ces2 = new CheckingExecutorService();
		Callable<String> ces3 = new CheckingExecutorService();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		/*
		try {
			listFuture = executorService.invokeAll(Arrays.asList(ces,ces2,ces3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Future<String> future: listFuture) {
            try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e);
			}
        }
        */
		try {
			System.out.println(executorService.invokeAny(Arrays.asList(ces,ces2,ces3)));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
