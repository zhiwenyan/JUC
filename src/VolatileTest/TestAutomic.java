package VolatileTest;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicDemo implements Runnable{
	//private int serialNumber=0;
	private AtomicInteger atomicInteger=new AtomicInteger();
	
	
	public int getSerialNumber() {
		return atomicInteger.getAndIncrement();  //自增加1
	}
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getSerialNumber());
	}
}

public class TestAutomic {
	public static void main(String[] args) {
		AtomicDemo atomicDemo=new AtomicDemo();
		for (int i = 0; i < 10; i++) {
			new Thread(atomicDemo).start();	
		}
	}

}
