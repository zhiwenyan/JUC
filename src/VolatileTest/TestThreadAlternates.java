package VolatileTest;

class Thread1 extends Thread {
	private String name;

	public Thread1(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.name);
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.notifyAll();
		}
	}
}

class Thread2 extends Thread {
	private String name;

	public Thread2(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.name);
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.notifyAll();
		}
	}
}

class Thread3 extends Thread {
	private String name;

	public Thread3(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.name);
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.notifyAll();
		}

	}
}

public class TestThreadAlternates {

	public static void main(String[] args) {
		Thread1 thread1 = new Thread1("A");
		thread1.start();
		Thread2 thread2 = new Thread2("B");
		thread2.start();
		Thread3 thread3 = new Thread3("C");
		thread3.start();

	}

}
