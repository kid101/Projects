package com.test.main;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	/*
	 * If no permit is available then the current thread becomes disabled for
	 * thread scheduling purposes and lies dormant until one of two things
	 * happens: 
	 * Some other thread invokes the release() method for this
	 * semaphore and the current thread is next to be assigned a permit; or
	 * Some other thread interrupts the current thread.
	 * 
	 * as a result I've to call release first to make a permit available and the acquire to get the lock
	 * 
	 * 
	 * Mutex is a mutually exclusive sepmaphore which has only one permit. one one thread can execute the code at a single time. 
	 */
	Semaphore semaphore = new Semaphore(0);

	public void test() {
		try {
			System.out.println("Acquiring lock");
			semaphore.acquire();
			// I some other threads try to execute if wont be able to as the lock is acquired and have to wait for it to finish
			System.out.println("acquired");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting....");
	}

	public void relaseLock() {
		semaphore.release();
		System.out.println("released");
		System.out.println("premits count is " + semaphore.availablePermits());
	}
}
