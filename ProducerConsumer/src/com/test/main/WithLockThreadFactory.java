package com.test.main;

import java.util.concurrent.ThreadFactory;

public class WithLockThreadFactory implements ThreadFactory {
	private int counter;

	public WithLockThreadFactory() {
		this.counter = 1;
	}

	@Override
	public Thread newThread(Runnable r) {
		//Thread t = new Thread(r, "WithLockThreadFactoryThread " + counter);
		// with Thread group you can set the priority of the threads in a group all at once.It may or may not change the priority of the previous threads but the new threads that will be added to this 
		//group will have the priority set as per the group
		Thread t = new Thread(new ThreadGroup("WithLockGroup"), r, "WithLockThreadFactoryThread " + counter);
		counter++;
		return t;
	}

}