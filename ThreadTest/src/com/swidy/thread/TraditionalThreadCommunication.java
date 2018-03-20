package com.swidy.thread;

public class TraditionalThreadCommunication {
	
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
}

class Business{
	private boolean bShouldSub = true;
	
	public synchronized void sub(int i) {
		while(!bShouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		for(int j=1;j<=3;j++) {
			System.out.println("sub thread i:" + i + " j:" + j);
		}
		bShouldSub = false;
		this.notify();
	}
	
	public synchronized void main(int i) {
		while(bShouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=1;j<=5;j++) {
			System.out.println("main thread i:" + i + " j:" + j);
		}
		bShouldSub = true;
		this.notify();
		
	}
	
}
