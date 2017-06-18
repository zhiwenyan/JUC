package VolatileTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket implements Runnable {
	private int tick = 50;
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			 synchronized (this) { //同步代码块
			if (tick > 0) {
			//	lock.lock();
				try {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --tick);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
				//	lock.unlock();
				}
			}
		}
		 }
	}
}

public class TestLock {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "1号窗口").start();
		new Thread(ticket, "2号窗口").start();
		new Thread(ticket, "3号窗口").start();

	}

}
