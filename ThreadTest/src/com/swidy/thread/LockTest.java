package com.swidy.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock对象线程互斥加锁
 * @author Administrator
 *
 */
public class LockTest {
	
	public static void main(String[] args) {
		new LockTest().init();
		
	}
	
	private void init() {
		final Outputer outputer = new Outputer();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output1("smartfeng");
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output1("stupidfang");
				}
			}
			
		}).start();
	}
	
	static class Outputer{
		
		Lock lock = new ReentrantLock();
		
		private void output1(String name) {
			int len = name.length();
			lock.lock();
			try {
				for(int i=0; i<len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} catch (Exception e) {
				
			} finally {
				lock.unlock();
			}
		}
		
		private synchronized void output2(String name) {
			int len = name.length();
			for(int i=0; i<len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		private static synchronized void output3(String name) {
			int len = name.length();
			for(int i=0; i<len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
	}
}
