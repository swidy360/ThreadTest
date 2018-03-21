package com.swidy.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition线程同步通信技术
 * @author Administrator
 *
 */
public class ConditionCommunication {
	
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
		private boolean bShouldSub = true;
		private Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		public void sub(int i) {
			lock.lock();
			try {
				while(!bShouldSub) {
					try {
						condition.await();  //类比this.wati();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				for(int j=1;j<=3;j++) {
					System.out.println("sub thread i:" + i + " j:" + j);
				}
				bShouldSub = false;
				condition.signal(); //类比this.nofity();
			} finally{
				lock.unlock();
			}
		}
		
		public void main(int i) {
			lock.lock();
			try {
				while(bShouldSub) {
					try {
						condition.await(); //类比this.wati();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for(int j=1;j<=5;j++) {
					System.out.println("main thread i:" + i + " j:" + j);
				}
				bShouldSub = true;
				condition.signal(); //类比this.nofity();
			} finally{
				lock.unlock();
			}
		}
		
	}

}

