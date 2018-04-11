package com.book.admin.test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


public class TestPAndC {
	
	public static void main(String[] args) throws Exception {
		//建立缓冲区
		BlockingDeque<PCData> queue = new LinkedBlockingDeque<PCData>(10);
		
		//建立生产者
		Product p1 = new Product(queue);
		Product p2 = new Product(queue);
		Product p3 = new Product(queue);

		//建立消费者
		Consumer c1 = new Consumer(queue);
		Consumer c2= new Consumer(queue);
		Consumer c3 = new Consumer(queue);

		//建立线程池
		ExecutorService service = Executors.newCachedThreadPool();

		//运行生产者
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		
		//运行消费者
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
		
		Thread.sleep(10 * 1000);
		
		//停止生产者
		p1.stop();
		p2.stop();
		p3.stop();
		Thread.sleep(3000);
		service.shutdown();
	}

}
