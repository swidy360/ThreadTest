package com.swidy.thread;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author Administrator
 *
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) {
//		ExecutorService threadPool =  Executors.newFixedThreadPool(3); //创建固定大小线程池
//		ExecutorService threadPool =  Executors.newCachedThreadPool(); //创建缓存线程池
		ExecutorService threadPool =  Executors.newSingleThreadExecutor(); //创建单一线程池，死掉后重新创建一个线程池
		for(int i=0; i<5; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for(int j=0; j<10; j++) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " is looper of " + j + " for task of " +  task);
					}
				}
			});
		} 
		System.out.println("all of 10 tasks have committed!");
		threadPool.shutdown();
		
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("bombing");
			}
			
		}, 10, 2, TimeUnit.SECONDS); 
		
	}
}
