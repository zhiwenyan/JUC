package VolatileTest;

/**
 * 
 * @author zhiwenyan Volatile 关键字
 *
 */
class ThreadDemo implements Runnable {
	private volatile boolean flag = false; // 共享数据

	@Override
	public void run() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = true;
		System.out.println("flag==" + isFlag());
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

public class VolatileTest {

	public static void main(String[] args) {

		ThreadDemo threadDemo = new ThreadDemo();
		Thread thread = new Thread(threadDemo);
		thread.start();
		while (true) {
				if (threadDemo.isFlag()) {
					System.out.println("-------");
					break;
				}		
		}
	}
}
