package VolatileTest;

public class TestABC1 {
	private int flag = 1;


	private synchronized void loopA() {
		if (flag != 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("A");
		flag = 2;
		notify();
	}

	private synchronized void loopB() {
		if (flag != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.print("B");
		flag = 3;
		notify();
	}

	private synchronized void loopC() {
		if (flag != 3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("C");
		flag = 1;
		notify();
	}

	public static void main(String[] args) {
		TestABC1 abc1 = new TestABC1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc1.loopA();
				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc1.loopB();
				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					abc1.loopC();
				}

			}
		}).start();
	}

}
