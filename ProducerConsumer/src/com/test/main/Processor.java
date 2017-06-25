package com.test.main;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	public LinkedList<Integer> list = new LinkedList<Integer>();
	ReentrantLock lock=  new ReentrantLock(true);
	
	int capacity=4;

	public void producer() throws InterruptedException {
		System.out.println("Processor.produce()");
		int value=0;
		while (true) {
			synchronized (list) {
				if(list.size()==capacity){
					System.out.println("waiting for list to become empty");
				list.wait();
				System.out.println("list now has some empty space");
				}
				list.add(value%capacity);
				System.out.println("value added to list: "+ value%capacity);
				value++;
				list.notify();
				Thread.sleep(1000);
			}
			
		}
	}

	public void consumer() throws InterruptedException {
		System.out.println("Processor.consume()");
		while (true) {
			synchronized (list) {
				if (list.size()==0) {
					System.out.println("waiting for list to have some value");
					list.wait();
					System.out.println("wait ended");
				}
				System.out.println("value removed from list: "+list.removeFirst());
				list.notify();
				Thread.sleep(1000);
			}
		}
	}

}
