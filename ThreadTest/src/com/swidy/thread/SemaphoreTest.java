package com.swidy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore可以维护当前访问自身的线程个数，并提供同步机制。
 * 使用semaphore可以控制同时访问资源的线程个数。
 * 例如：厕所只有3个坑，有10个人在等待。
 * 备注：单个信号量的semaphore对象可以实现互斥锁的功能，类似synchronize和lock的功能
 * @author Administrator
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore sp = new Semaphore(3);//还有优先级的构造函数
		for(int i=0;i<10;i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" +
							(3-sp.availablePermits() + "个并发"));
					try {
						Thread.sleep((long) (Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"即将离开");	
					sp.release();
					System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" +
							(3-sp.availablePermits() + "个并发"));
					
				};
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
