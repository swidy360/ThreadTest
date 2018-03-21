package com.swidy.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统定时器技术
 * @author Administrator
 *
 */
public class TraditionalTimerTest {
	
	private static int count = 0;
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("bombing!");
			}
		}, 5000, 3000);
		
		class MyTimerTask extends TimerTask{

			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("1-bombing!");
				new Timer().schedule(new MyTimerTask(), 2000+2000*count);
			}
		}
		
		new Timer().schedule(new MyTimerTask() , 2000);
		
		while(true) {
			try {
				System.out.println(new Date().getSeconds());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
