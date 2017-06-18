package VolatileTest;

public class TestABC1 {
	private int flag = 1;

	private synchronized void loopA() {
		if (flag != 1) {
			System.out.print("A");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = 2;
		notifyAll();
	}

	private synchronized void loopB() {
		if (flag != 2) {
			System.out.print("B");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		flag = 3;
		notifyAll();
	}

	private synchronized void loopC() {
		if (flag != 3) {
			System.out.print("C");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = 1;
		notifyAll();
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
