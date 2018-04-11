package com.book.admin.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			new Thread(new MyThread("testNoThreadPool"+Integer.toString(i))).start();
		}
		long t2 = System.currentTimeMillis();
		System.out.println(" ===== time1:" + (t2 - t1));
		
/*		for (int i = 0; i < 800; i++) {
			ThreadPool pool = ThreadPool.getInstance();
			Runnable target = new MyThread("testThreadPool"+Integer.toString(i));
			pool.start(target);
		}*/
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			exe.execute(new MyThread("testJDKThreadPool" + Integer.toString(i)));
		}
		long t3 = System.currentTimeMillis();
		System.out.println(" ====== time2 : " + (t3 - t2));
		
		System.err.println(" ----- 线程池应该设置的大小:" + Runtime.getRuntime().availableProcessors());
	}

}
