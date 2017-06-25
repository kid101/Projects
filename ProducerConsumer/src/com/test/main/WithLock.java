package com.test.main;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WithLock {
	// these locks can be worked across multiple methods
	// also it can acquire multiple locks on the same object and increase the
	// hold count. every unlock decrease the hold count and the lock becomes
	// free when the hold count is zero
	// As a rule of thumb, you want the lock-object to have the same static-ness
	// than the operated-on value. So if you manipulate non-static values only,
	// you'll want a non-static lock object. If you manipulate static values
	// only, you'll want a static lock object.

	ReentrantLock lock = new ReentrantLock(true);
	LinkedList<Integer> linkedList = new LinkedList<>();
	Condition isEmpty = lock.newCondition();
	Condition isFull = lock.newCondition();
	int limit = 10;
	volatile int interruptCounter = 0;

	public void produce() {
		System.out.println("WithLock.produce() Name: " + Thread.currentThread().getName());
		int value = 1;
		while (true) {
			try {
				// if the thread is waiting for the thread to get the lock and
				// is interrupted it wont get the lock then.
				lock.lockInterruptibly();
				if (limit == linkedList.size()) {
					System.out.println("acquiring lock in produce");
					isEmpty.await(3000, TimeUnit.MILLISECONDS);
				}
				linkedList.add(value % limit);
				System.out.println("value added to list: " + value % limit);
				value++;
				isFull.signal();
				System.out.println("notifiedy lock in produce");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("I was interupted Producer");
				interruptCounter++;
				System.out.println("interruptCounter value :" + interruptCounter);
			} finally {
				lock.unlock();
				System.out.println("Finally Unlocked Producuer");
			}
			
		}
	}

	public void consume() {
		System.out.println("WithLock.consume() Name: " + Thread.currentThread().getName());
		while (true) {
			try {
				lock.lockInterruptibly();
				// no use as poll doesn't throw an exception if the queue is
				// empty
				if (linkedList.size() == 0) {
					System.out.println("acquiring lock in consume");
					isFull.await(3000, TimeUnit.MILLISECONDS);
					if (interruptCounter > 2) {
						break;
					}
				}
				System.out.println("removing element from queue: " + linkedList.poll());
				isEmpty.signal();
				System.out.println("notifiedy lock in consume");
				Thread.sleep(1000);
				if (interruptCounter != 0) {
					interruptCounter++;
				}
			} catch (InterruptedException e) {
				System.out.println("I was Interupted Consumer");
			} finally {
				lock.unlock();
				System.out.println("Finally Unlocked Consumer");
			}
		}
		System.out.println("Ending things now: Consume");
	}
}
