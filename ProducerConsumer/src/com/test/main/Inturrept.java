package com.test.main;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inturrept implements Runnable {
	Lock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			System.out.println("Trying to get lock ,Thread name is: " + Thread.currentThread().getName());
			lock.lockInterruptibly();
			System.out.println("Running");
			long now = System.currentTimeMillis();
			Date d = new Date(Calendar.getInstance().getTimeInMillis() + 5000);
			while (now < d.getTime()) {
				now = System.currentTimeMillis();
			}
			System.out.println("Stopped Waiting");
		} catch (InterruptedException e) {
			System.out.println("I was inturrepted, Thread name is: " + Thread.currentThread().getName());
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
