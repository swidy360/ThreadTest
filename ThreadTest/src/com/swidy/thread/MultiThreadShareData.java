package com.swidy.thread;

/** 多个线程之间共享数据的方式
 * 
 * 设计4个线程，其中两个线程每次对j增加1,另外两个线程每次对j减1.
 * @author Administrator
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		final ShareData1 data1 = new ShareData1();
		
		for(int i=0; i<2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0; i<10; i++) {
						data1.increment();
					} 
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0; i<10; i++) {
						data1.decrement();
					}
				}
			}).start();
		}
		
	}
}


class ShareData1 {
	int j = 0;
	
	public synchronized void increment() {
		j++;
		System.out.println("Thread+:" + Thread.currentThread().getName() + " j:" + j);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void decrement() {
		j--;
		System.out.println("Thread-:" + Thread.currentThread().getName() + " j:" + j);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
