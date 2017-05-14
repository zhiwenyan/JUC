package VolatileTest;

class CompareAndSwap {
	private int value;

	// 获取值
	public synchronized int getValue() {
		return value;
	}

	// 比较
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue) {
			this.value = newValue;
		}
		return oldValue;
	}

	// 设置
	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
}

public class TestCompareAndSwap {
	public static void main(String[] args) {
		CompareAndSwap compareAndSwap = new CompareAndSwap();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int exceptedValue = compareAndSwap.getValue();
					System.out.println(exceptedValue);
					boolean b = compareAndSwap.compareAndSet(exceptedValue, (int)(Math.random() * 101));
					System.out.println(b);
				}
			}).start();
		}
	}
}
