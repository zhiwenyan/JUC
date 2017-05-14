package VolatileTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadPoolDemo implements Runnable {
	@Override
	public void run() {

	}
}

public class TestThreadPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});

	}

}
