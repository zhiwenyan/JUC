package VolatileTest;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage2 {

	// 仓库最大存储量
	private final int MAX_SIZE = 100;
	// 仓库存储的载体
	private LinkedList<Object> list = new LinkedList<Object>();
	private Lock lock = new ReentrantLock();
	private Condition full=lock.newCondition();
	private Condition empty=lock.newCondition();

	// 生产产品
	public void produce(String producer) {
		lock.lock();
		// 如果仓库已满
		while (list.size() == MAX_SIZE) {
			System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
			try {
				// 由于条件不满足，生产阻塞
				full.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			// 生产产品
			list.add(new Object());

			System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());

			empty.signal();
		}
	}

	// 消费产品
	public void consume(String consumer) {
		lock.lock();
		// 如果仓库存储量不足
		while (list.size() == 0) {
			System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
			try {
				// 由于条件不满足，消费阻塞
				empty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			list.remove();
			System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());
			full.notify();
		}
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}

	public static void main(String[] args) {
	}
}
