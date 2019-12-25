package org.multithreading.basics;

import java.math.BigInteger;

public class ThreadTermination {

	public static void main(String[] args) {

		terminateWithInterrupt();
		terminateAsDaemon();

	}

	private static void terminateAsDaemon() {
		
		PowerCalculator p1 = new PowerCalculator(BigInteger.valueOf(1001), BigInteger.valueOf(200));
		Thread t1 = new Thread(p1);
		t1.setDaemon(true);
		t1.start();
		for (int i = 10; i > 0; i--) {
			System.out.println("Count down:" + i);
		}

	}

	private static void terminateWithInterrupt() {

		checkIfInterrupted();
		interruptedImplicitly();

	}

	/**
	 * This method illustrates that Thread.sleep()
	 * will internally check if thread was interrupted. 
	 * Hence even if we simply call interrupt, the thread
	 *  is terminated.
	 */
	private static void interruptedImplicitly() {
		Thread t = new Thread(new LazyCounter());
		t.start();
		t.interrupt();
	}

	/**
	 * This method illustrates that even if thread is interrupted we have to check
	 * explicitly if thread was interrupted. This thread is interrupted if it is not
	 * able to calculate base^pow in count down of 10.
	 */
	private static void checkIfInterrupted() {

		PowerCalculator p1 = new PowerCalculator(BigInteger.valueOf(1000000), BigInteger.valueOf(20));
		Thread t1 = new Thread(p1);
		t1.start();
		for (int i = 10; i > 0; i--) {
			System.out.println("Count down:" + i);
		}
		t1.interrupt();
	}

}

class PowerCalculator implements Runnable {

	private BigInteger base;
	private BigInteger pow;

	private BigInteger result = BigInteger.ONE;

	public PowerCalculator(BigInteger base, BigInteger pow) {
		this.base = base;
		this.pow = pow;
	}

	@Override
	public void run() {
		for (BigInteger i = BigInteger.ZERO; i.compareTo(pow) != 0; i = i.add(BigInteger.ONE)) {
			if (Thread.interrupted()) {
				System.out.println("Interrupted as it is consuming lot of time!");
			} else {
				result = result.multiply(base);
			}
		}
		System.out.println(base + "to the power of " + pow + "=" + result);
		System.out.println();
	}

}

class LazyCounter implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println();
			System.out.println("Hello this is lazy counter");
			for(int i=0;i<20;i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread is interrupted!");
		}
		
	}

}