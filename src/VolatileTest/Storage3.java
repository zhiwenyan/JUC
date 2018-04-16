package VolatileTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage3 {

	// 仓库最大存储量
	private final int MAX_SIZE = 100;
	private BlockingQueue<Object> list = new LinkedBlockingQueue<>(MAX_SIZE);

	// 生产产品
	public void produce(String producer) {
		// 如果仓库已满
		if (list.size() == MAX_SIZE) {
			System.out.println("仓库已满，【" + producer + "】： 暂时不能执行生产任务!");
		}

		// 生产产品
		try {
			list.put(new Object());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("【" + producer + "】：生产了一个产品\t【现仓储量为】:" + list.size());
	}

	// 消费产品
	public void consume(String consumer) {
		// 如果仓库存储量不足
		if (list.size() == 0) {
			System.out.println("仓库已空，【" + consumer + "】： 暂时不能执行消费任务!");
		}
		try {
			list.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("【" + consumer + "】：消费了一个产品\t【现仓储量为】:" + list.size());

	}

	public BlockingQueue<Object> getList() {
		return list;
	}

	public void setList(BlockingQueue<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
}
