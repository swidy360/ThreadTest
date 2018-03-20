package com.swidy.thread;

public class TraditionalThreadSynchronized {
	
	

	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
		
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
					outputer.output1("chengjianfeng");
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
					outputer.output3("weilifang");
				}
			}
			
		}).start();
	}
	
	static class Outputer{
		
		private void output1(String name) {
			int len = name.length();
			synchronized(Outputer.class) {
				for(int i=0; i<len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
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
