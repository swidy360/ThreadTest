package com.swidy.thread;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用两个具有1个空间的队列来实现同步通知的功能
 * @author Administrator
 *
 */
public class BlockingQueueCommunication {
	
	public static void main(String[] args) {
		
		final Business business = new Business();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=2;i++) {
					business.sub(i);
				}
			}
		}).start();
	
		
		for(int i=1;i<=2;i++) {
			business.main(i);
		}
		
	}
	
	static class Business{

		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(1);
		
		//匿名构造方法，在任何构造方法之前，创造对象的时候会被调用
		{
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void sub(int i) {
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int j=1;j<=3;j++) {
				System.out.println("sub thread i:" + i + " j:" + j);
			}
			try {
				queue2.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		public void main(int i) {
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int j=1;j<=5;j++) {
				System.out.println("main thread i:" + i + " j:" + j);
			}
			try {
				queue1.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}


