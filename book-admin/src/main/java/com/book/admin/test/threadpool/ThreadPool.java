package com.book.admin.test.threadpool;

import java.util.List;
import java.util.Vector;


/**
 * 线程池的基本功能就是进行线程的复用！
 * @author liweihan
 *
 */
public class ThreadPool {

	private static ThreadPool instance = null;
	
	//空闲线程队列
	private List<PThread> idleThreads;
	//已有的线程总数
	private int threadCounter;
	private boolean isShutDown = false;
	
	private ThreadPool() {
		this.idleThreads = new Vector(5);
		threadCounter = 0;
	}
	
	public int getCreatedThreadsCount() {
		return threadCounter;
	}
	
	//获取线程池的实例
	public synchronized static ThreadPool getInstance() {
		if (instance == null) {
			instance = new ThreadPool();
		}
		return instance;
	}
	
	//将线程放入池中
	protected synchronized void repool(PThread repoolingThread) {
		if (!isShutDown) {
			idleThreads.add(repoolingThread);
		} else {
			repoolingThread.shutDown();
		}
	}
	
	//关闭池中所有的线程
	public synchronized void shutDown() {
		isShutDown = true;
		for(int threadIndex = 0; threadIndex < idleThreads.size();threadIndex++) {
			PThread idlePThread =  (PThread)idleThreads.get(threadIndex);
			idlePThread.shutDown();
		}
	}
	
	//执行任务
	public synchronized void start(Runnable target) {
		PThread thread = null;
		//如果有空闲线程,则直接使用
		if (idleThreads.size() > 0) {
			int lastIndex =idleThreads.size() - 1;
			thread =  (PThread)idleThreads.get(lastIndex);
			idleThreads.remove(lastIndex);
			//立即执行这个任务
			thread.setTarget(thread);
		} else {
			//若是没有空闲线程
			threadCounter++;
			//创建新线程
			thread = new PThread(target, "PThread #" + threadCounter, this);
			//启动这个线程
			thread.start();
		}
	}
}
