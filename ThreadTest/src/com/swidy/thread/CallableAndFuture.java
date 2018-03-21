package com.swidy.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Callable和Future
 * @author Administrator
 *
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = 
		threadPool.submit(new Callable<String>(){

			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}
			
		});
		
		System.out.println("等待结果");
		try {
			System.out.println("拿到结果：" + future.get()); //阻塞的：等子线程执行完才能拿到结果 
//			System.out.println("拿到结果：" + future.get(1, TimeUnit.SECONDS)); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch(TimeoutException e) {
			e.printStackTrace();
		}*/
		threadPool.shutdown();
		
		
		
		
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completitionService = new ExecutorCompletionService<Integer>(threadPool2) ;
		
		for(int i=0; i<10; i++) {
			final int task = i;
			completitionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return task;
				}
				
			});
		}
		
		for(int i=0; i<10; i++) {
			try {
				System.out.println(completitionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		threadPool2.shutdown();
	}
}
