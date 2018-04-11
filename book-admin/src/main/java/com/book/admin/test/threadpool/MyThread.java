package com.book.admin.test.threadpool;

public class MyThread implements Runnable{

	private String name;
	public MyThread() {
		
	}
	public MyThread(String name) {
		this.name = name;
	}
	public void run() {
		try {
			//使用sleep方法代替一个具体的功能的执行
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
