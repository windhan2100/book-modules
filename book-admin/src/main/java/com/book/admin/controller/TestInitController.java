package com.book.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/testinit")
public class TestInitController implements InitializingBean{
	
	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ConcurrentHashMap<String, JSONObject> cacheData = new ConcurrentHashMap<String, JSONObject>();
	private static final ScheduledExecutorService EXEC_TEST1 = Executors.newScheduledThreadPool(1);
	private static Logger logger = LoggerFactory.getLogger(TestInitController.class);
	
	/**
	 		scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnitunit)
         	创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；
         	也就是将在 initialDelay 后开始执行，
         	然后在initialDelay+period 后执行，
         	接着在 initialDelay + 2 * period 后执行，依此类推
         	
         	
         	
         	不管任务执行耗时是否大于间隔时间，scheduleAtFixedRate和scheduleWithFixedDelay都不会导致同一个任务并发地被执行。
         	唯一不同的是scheduleWithFixedDelay是当前一个任务结束的时刻，开始结算间隔时间，
         	如0秒开始执行第一次任务，任务耗时5秒，任务间隔时间3秒，那么第二次任务执行的时间是在第8秒开始。
	 */
	
	public TestInitController() {
		logger.info("----------------------- init !!");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.info(" ====== test InitializingBean !" + format.format(new Date()));
		
		/**
		 * 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；<br/>
         * 也就是将在 initialDelay 后开始执行，然后在initialDelay+period 后执行，<br/>
         * 接着在 initialDelay + 2 * period 后执行，依此类推。
         * 
         * 如果程序时间大于间隔时间,那么每次执行完后，立即执行下一次！
		 */
/*		EXEC_TEST1.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("scheduleAtFixedRate ====== 可以调用一些方法初始化一些数据！begin:" + format.format(new Date()));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("scheduleAtFixedRate ====== 可以调用一些方法初始化一些数据！end:" + format.format(new Date()));
				System.err.println("");
			}
		}, 10, 5000, TimeUnit.MILLISECONDS); */
		
		
		/**
		 * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，
		 * 在每一次执行终止和下一次执行开始之间都存在给定的延迟。
		 * 
		 *  scheduleWithFixedDelay是当前一个任务结束的时刻，开始结算间隔时间，
         	如0秒开始执行第一次任务，任务耗时5秒，任务间隔时间3秒，那么第二次任务执行的时间是在第8秒开始。
		 */
/*		EXEC_TEST1.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("scheduleWithFixedDelay ====== 可以调用一些方法初始化一些数据！begin:" + format.format(new Date()));
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("scheduleWithFixedDelay ====== 可以调用一些方法初始化一些数据！end:" + format.format(new Date()));
				System.err.println("");
			}
		}, 10, 5000, TimeUnit.MILLISECONDS)*/;
		
		/**
		 * 创建并执行在给定延迟后的一次性操作
		 */
		EXEC_TEST1.schedule(new Runnable() {
			
			@Override
			public void run() {
				logger.info("---------- test ----------");
				initData();
			}
		}, 5000, TimeUnit.MILLISECONDS);
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "JSON!";
	}
	
	private void initData() {
		logger.info(" ===== 可以把数据输入缓存   cacheData 中！");
	}
	
	

}
