package com.swidy.thread;

/** 多个线程之间共享数据的方式
 * 
 * 设计4个线程，其中两个线程每次对j增加1,另外两个线程每次对j减1.
 * @author Administrator
 *
 */
public class MutilThreadShareData1 {
	
	public static void main(String[] args) {
		SharedData1 data  = new SharedData1();
		MyRunnable1 runnable1 = new MyRunnable1(data);
		MyRunnable2 runnable2 = new MyRunnable2(data);
		for(int i=0; i<2; i++) {
			new Thread(runnable1).start();
			new Thread(runnable2).start();
		}
	}
	
	
}

class MyRunnable1 implements Runnable{
	
	public SharedData1 data = null;
	
	public MyRunnable1(SharedData1 data){
		this.data = data;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			data.increment();
		}
	}
}

class MyRunnable2 implements Runnable{
	
	public SharedData1 data = null;
	
	public MyRunnable2(SharedData1 data){
		this.data = data;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			data.decrement();
		}
	}
}

class SharedData1{
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
