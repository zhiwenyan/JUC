package VolatileTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ABC {
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void loopA() {
		lock.lock();
		try {
			if (number != 1) {

				condition1.await();
			}
				System.out.print(Thread.currentThread().getName());
			
			number = 2;
			condition2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void loopB() {

		lock.lock();
		try {
			if (number != 2) {

				condition2.await();
			}
				System.out.print(Thread.currentThread().getName());
			
			number = 3;
			condition3.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void loopC() {
		lock.lock();
		try {
			if (number != 3) {

				condition3.await();
			}
				System.out.print(Thread.currentThread().getName());
			
			number = 1;
			condition1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class TestABC {
	public static void main(String[] args) {
		ABC abc = new ABC();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc.loopA();
				}
			}
		}, "A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc.loopB();
				}
			}
		}, "B").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc.loopC();
				}
			}
		}, "C").start();
	}

}
