package org.multithreading.basics;

/**
 * @author suhas_partha This is a test class do demonstrate different ways of
 *         thread creation and show case that the order of execution in
 *         multi-threaded application is unpredictable.
 *
 */
public class ThreadCreation {

	public static void main(String[] args) {

		System.out.println("This is main thread");

		ImplementRunnable ir = new ImplementRunnable();
		Thread t = new Thread(ir);
		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ExtendsThread et = new ExtendsThread();
		et.start();

		System.out.println("This is main thread");

	}

}

/**
 * @author suhas_partha Creating thread by implementing Runnable interface
 *
 */
class ImplementRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
				System.out.println("This is a thread created implementing Runnable Interface");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

/**
 * @author suhas_partha Creating thread by extending Thread class
 *
 */
class ExtendsThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(1000);
				System.out.println("This is a thread created by extending Thread class");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
