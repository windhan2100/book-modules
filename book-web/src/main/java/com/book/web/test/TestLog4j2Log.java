package com.book.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


/**
 * log4j2测试
 * @author liweihan
 *
 */
public class TestLog4j2Log {
	
	/**
	 	1.
		<dependency>
		  <groupId>org.apache.logging.log4j</groupId>
		  <artifactId>log4j-api</artifactId>
		  <version>2.5</version>
		</dependency>
		<dependency>
		  <groupId>org.apache.logging.log4j</groupId>
		  <artifactId>log4j-core</artifactId>
		  <version>2.5</version>
		</dependency>
		
		如果是web环境可以加入：因为会提示：
		Log4j appears to be running in a Servlet environment,
		 but there's no log4j-web module available. 
		 If you want better web container support,
		 please add the log4j-web JAR to your web archive or server lib directory.
		<dependency>
		  <groupId>org.apache.logging.log4j</groupId>
		  <artifactId>log4j-web</artifactId>
		  <version>2.2</version>
		</dependency>
	 	
	 	2.
	 	log4j2与log4j1发生了很大的变化，不兼容。
	 	log4j1仅仅作为一个实际的日志框架，
	 	slf4j、commons-logging作为门面，统一各种日志框架的混乱格局，
	 	现在log4j2也想跳出来充当门面了，也想统一大家了!!
	 	
		3.log4j2分成2个部分：

			log4j-api： 作为日志接口层，用于统一底层日志系统
			log4j-core : 作为上述日志接口的实现，是一个实际的日志框架
			
		4.log4j 2.x版本不再支持像1.x中的.properties后缀的文件配置方式,
		  2.x版本配置文件后缀名只能为”.xml”,”.json”或者”.jsn”。
		  
		  log4j2默认会在classpath目录下寻找:
		  <1.>log4j2-test.json 或者log4j2-test.jsn的文件
		  <2.>log4j2-test.xml的文件.
		  <3.>log4j2.json 或者log4j2.jsn的文件.
		  <4.>log4j2.xml的文件.
		     
		      如果都没有找到，则会按默认配置输出，也就是输出到控制台
		
		5.和log4j1是不同的。此时Logger是log4j-api中定义的接口，而log4j1中的Logger则是类
		
		6.如果要log4j2与commons-logging集成，还需要引入jar包：
		<dependency>
		  <groupId>commons-logging</groupId>
		  <artifactId>commons-logging</artifactId>
		  <version>1.2</version>
		</dependency>	
		<dependency>
		  <groupId>org.apache.logging.log4j</groupId>
		  <artifactId>log4j-jcl</artifactId>
		  <version>2.2</version>
		</dependency>
		
			commons-logging
			log4j-api （log4j2的API包）
			log4j-core （log4j2的API实现包）
			log4j-jcl （log4j2与commons-logging的集成包）
			
		private static Log logger = LogFactory.getLog(TestLog4j2Log.class);
		使用commons-logging的Log接口和LogFactory来进行编写，看不到log4j2的影子。
		但是这时候含有上述几个jar包，log4j2就与commons-logging集成了起来。
		
		common-logging貌似不支持占位符！
		
		7.如果log4j2与slf4j集成，还需要引入jar包：
		<!-- log4j2与slf4j集成 -->
		<dependency>
		  <groupId>org.slf4j</groupId>
		  <artifactId>slf4j-api</artifactId>
		  <version>1.7.7</version>
		</dependency>
		<dependency>
		  <groupId>org.apache.logging.log4j</groupId>
		  <artifactId>log4j-slf4j-impl</artifactId>
		  <version>2.5</version>
		</dependency>
		
		
		log4j-slf4j-impl : 用于log4j2与slf4j集成
		获取logger的方式：
		private static Logger logger=LoggerFactory.getLogger(Log4j2Slf4jTest.class);
	 */
	
//	private static final Logger logger  = LogManager.getLogger(TestLog4j2Log.class);
//	private static Log logger = LogFactory.getLog(TestLog4j2Log.class);
	private static Logger logger = LoggerFactory.getLogger(TestLog4j2Log.class);
	
	public static void main(String[] args) {
		
		if (logger.isTraceEnabled()) {
			logger.trace(" ===== log4j2 trace meassge");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" ===== log4j2 debug message");
			logger.debug(" ------ test log4j2:{},{}","Hello ","HANCHo");
		}
		if (logger.isInfoEnabled()) {
			logger.info(" ===== log4j2 info message");
		}
		if (logger.isErrorEnabled()) {
			logger.error(" ===== log4j2 error message ");
		}
		
		//
		Object[] params = {"HAN",1,"CHAO"};
		logger.info(" ===== test:{}, 加班 {} 天, {}要回家了！",params);
	}

}
