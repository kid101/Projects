package com.test.main;

import java.util.concurrent.PriorityBlockingQueue;

public class ConncurentSol {

	public PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(10, null);
	int limit = 10;

	public void produce() throws InterruptedException {
		int value = 1;
		while (true) {
			value++;
			queue.add(value % limit);
			System.out.println("ELement added to queue: " + value % limit);
			Thread.sleep(1000);
		}
	}

	public void consume() throws InterruptedException {
		while (true) {
			System.out.println("ELement removed from queue: " + queue.poll());
			Thread.sleep(1000);
		}
	}
}
