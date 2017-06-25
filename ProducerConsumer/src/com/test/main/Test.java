package com.test.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args){
		Test test = new Test();
		// test.startWork();
		//SemaphoreTest sTest= new SemaphoreTest();
		//test.concWork();
		test.LockWork();
		//sTest.relaseLock();
		//sTest.test();
		//to check if lock.intureptly actually throws and exception
		//test.inturreptWork();
		//Main group
		System.out.println("Main Thread group: "+Thread.currentThread().getThreadGroup().getName());
		//System group  is the parent of main group. it contains system level threads like finalizer,signal dispatcher,attach listener
		System.out.println("Main Thread group: "+Thread.currentThread().getThreadGroup().getParent());
	}
	
	public void inturreptWork(){
		Inturrept inturrept= new Inturrept();
		Thread t1 = new Thread(inturrept,"Thread 1");
		Thread t2 = new Thread(inturrept,"Thread 2");
		Thread t3 = new Thread(inturrept,"Thread 3");
		try{
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Finally");
		}
	}

	public void startWork() {
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void concWork() {
		ConncurentSol sol = new ConncurentSol();
		Thread t1 = new Thread(() -> {
			try {
				sol.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				sol.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void LockWork() {
		WithLock withLock = new WithLock();
		Thread t1 = new Thread(() -> {
			withLock.produce();
		});
		Thread t2 = new Thread(() -> {
			withLock.consume();
		});	
		ExecutorService service= Executors.newCachedThreadPool(new WithLockThreadFactory());
		Future<?> f1=service.submit(t1);
		Future<?> f2=service.submit(t2);
		//f1.cancel(true);
		try {
			System.out.println("-------------------------------------sleeping now-------------------------------------");
			Thread.sleep(3000);
			System.out.println("-------------------------------------Intereputing Producer-------------------------------------");
			f1.cancel(true);
			service.shutdown();
			Thread.sleep(1000);
			System.out.println("is Producer done: "+f1.isDone());
			service.awaitTermination(1, TimeUnit.DAYS);
			System.out.println("is Consumer done: "+f2.isDone());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending Program");
		
	}
}