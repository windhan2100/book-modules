package com.book.admin.test;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * 消费者
 * @author liweihan
 *
 */
public class Consumer implements Runnable{
	//缓冲区
	private BlockingDeque<PCData> queue;
	private static final int SLEEPTIME = 1000;
	
	public Consumer(BlockingDeque<PCData> queue) {
		this.queue = queue;
	}

	public void run() {
		System.out.println("start Consumer id= " + Thread.currentThread().getId());
		Random r = new Random();
		
		try {
			while (true) {
				//提取任务
				PCData data = queue.take();
				if (null != data) {
					//计算平方
					int re = data.getData() * data.getData(); 
					System.out.println(MessageFormat.format("{0} * {1} = {2}",data.getData(),data.getData(),re));
					Thread.sleep(r.nextInt(SLEEPTIME));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	
}
