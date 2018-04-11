package com.book.admin.test;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Product implements Runnable{
	
	private volatile boolean isRunning  = true;
	//内存缓存区
	private BlockingDeque<PCData> queue;
	//总数,原子操作
	private static AtomicInteger count = new AtomicInteger();
	
	private static final int SLEEPTIME = 1000;
	
	public Product(BlockingDeque<PCData> queue) {
		this.queue = queue;
	}

	public void run() {
		PCData data = null;
		Random r = new Random();
		System.out.println("start producer id=" + Thread.currentThread().getId());
		
		try {
			while (isRunning) {
				Thread.sleep(r.nextInt(SLEEPTIME));
				//构造任务数据
				data = new PCData(count.incrementAndGet());
				System.out.println(data + " is put into queue");
				//提交数据到缓存区
				if (!queue.offer(data,2,TimeUnit.SECONDS)) {
					System.err.println("failed to put data : " + data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop() {
		isRunning = false;
	}

}
