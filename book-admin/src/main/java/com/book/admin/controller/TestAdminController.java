package com.book.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.core.mapper.TestOrderMapper;
import com.book.core.model.TestOrder;
import com.book.core.model.TestOrderExample;
import com.book.core.service.TestService;

@Controller
public class TestAdminController {

	@Autowired
	TestService testService;
	@Autowired
	TestOrderMapper testOrderMapper;
	
	private static Logger logger = LoggerFactory.getLogger(TestAdminController.class);
	
	@RequestMapping("/test")
	public String test() {
		logger.info(" ====== book-admin log4j2 test ... ");
		testService.testService();
		
		/**
		 * 查询所有的数量
		 */
/*		TestOrderExample example = new TestOrderExample();
		Long count = testOrderMapper.countByExample(example);
		logger.info(" ====== : test:数据库所有的数量:{}",count);*/
		
		/**
		 * 查询所有的对象
		 */
/*		TestOrderExample example = new TestOrderExample();
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数据库所有的数量:{}",list.size());*/
		
		/**
		 * 根据订单ID查询订单
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(829L);
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		System.out.println(list.get(0).getOrderNo());*/
		
		/**
		 * 根据订单号查询订单
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo("P201706121110537211003890");
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		System.out.println(list.get(0).getId());*/
		
		/**
		 * 获取所有支付宝和微信直接购买成功的账单
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		Date beginTime = DateUtils.getDate(-1, 1);
		Date endTime = DateUtils.getDate(0, -1);
		criteria1.andUpdateTimeBetween(beginTime, endTime).andStatusEqualTo(0); //between and 等同于 >= <= ,0代表成功的订单
		
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数量:{}",list.size());*/
		
		
		/**
		 * 根据商品ID和用户ID查找用户的“有效订单”
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		criteria1.andUserIdEqualTo(72114794747985920L)
		.andProductIdEqualTo(39L)
		.andStatusEqualTo(0);
		
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:name::{}",list.get(0).getProductName());*/
		
		/**
		 * 查询支付宝或者微信,支付成功的订单
		 * 查询条件1：a=? and (b=? or c=?) 不支持

			查询条件2：(a=? And b=?) or (a=? And c=?) 支持
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		criteria1.andStatusEqualTo(0).andPayWayEqualTo(1);
		
		TestOrderExample.Criteria criteria2 = example.createCriteria();
		criteria2.andStatusEqualTo(0).andPayWayEqualTo(2);
		
		example.or(criteria2);
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数量:{}",list.size());
		//SQL:WHERE ( status = ? and pay_way = ? ) or( status = ? and pay_way = ? ) 
*/		
/*		
		//第二种写法
		TestOrderExample example = new TestOrderExample();
		example.or().andStatusEqualTo(0).andPayWayEqualTo(1);
		example.or().andStatusEqualTo(0).andPayWayEqualTo(2);
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数量:{}",list.size());
		//SQL:( status = ? and pay_way = ? ) or( status = ? and pay_way = ? ) 
		
*/		
/*		//第三种写法
		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		
		criteria1.andStatusEqualTo(0).andPayWayIn(list);
		List<TestOrder> resultList = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数量:{}",resultList.size());
		// WHERE ( status = ? and pay_way in ( ? , ? ) ) 
*/		
		
		/**
		 * 查询所有有效的订单,并按更新时间逆序
		 */
/*		TestOrderExample example = new TestOrderExample();
		example.createCriteria().andStatusEqualTo(0);
		example.setOrderByClause("update_time DESC"); 
		//还可以多个排序: update_time DESC,id ASC
		
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		logger.info(" ====== : test:数量:{}",list.size());*/
		
		/**
		 * 更新
		 * 值不为null,
		 * 相当于更新所有：首先查出对象，其次，设置要更新的字段，最后更新
		 * update test_order set id = ?, create_time = ?, product_id = ?,
		 *  real_pay = ?, purchase_type = ?, pay_way = ?, nick_name = ?, 
		 *  open_id = ?, mobile = ?, status = ?, peak_id = ?, user_id = ?,
		 *  update_time = ?, product_name = ?, order_no = ?, plat = ?, drama_id = ?,
		 *  play_id = ?, live_date = ?, live_time = ?, period = ?, 
		 *  cover = ?, upgrade = ?, upgrade_order_id = ? WHERE ( id = ? ) 
		 */
		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		criteria1.andIdEqualTo(832L);
		
		//先查询
		List<TestOrder> list = testOrderMapper.selectByExample(example);
		TestOrder testOrder = list.get(0);
		testOrder.setProductName("测试测试");
		
		int result = testOrderMapper.updateByExample(testOrder, example);
		logger.info(" ====== : update:数量:{}",result);
		
		
		/**
		 * 更新：只更新部分字段，按条件更新值不为null的字段   
		 * update test_order SET nick_name = ?, product_name = ? WHERE ( id = ? ) 
		 * 
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
//		criteria1.andIdEqualTo(833L); //修改一条
		
		//修改多条
		Long[] ids = {835L,836L,837L};
		criteria1.andIdIn(Arrays.asList(ids));
		
		TestOrder testOrder = new TestOrder();
		testOrder.setProductName("测试测试");
		testOrder.setNickName("测试测试");
		
		int result = testOrderMapper.updateByExampleSelective(testOrder, example);
		logger.info(" ====== : update:数量:{}",result);*/
		
		/**
		 * 删除
		 */
/*		TestOrderExample example = new TestOrderExample();
		TestOrderExample.Criteria criteria1 = example.createCriteria();
		criteria1.andIdEqualTo(842L); //删除一条
		
		int result = testOrderMapper.deleteByExample(example);
		logger.info(" ====== : delete:数量:{}",result);*/
		
		/**
		 * 插入
		 */
		TestOrder order = new TestOrder();
		order.setPlat(Integer.valueOf(3));
		order.setUserId(Long.valueOf("1232131"));
		order.setProductId(Long.valueOf(2L));
		String orderNo =  null;//CommonUtils.createOrderNo("P", "1232131");
		order.setOrderNo(orderNo);
		order.setProductName("中华大测试");
		order.setRealPay(0.01d);
		//2017-05-23-add-因为已购买列表需要
		order.setCover("http://abc.gif"); //客户端要横图
		order.setDramaId(2L);
		order.setPlayId(100l);
		order.setLiveDate("20:00:00");
		order.setLiveTime("20:00:00");
		order.setPeriod("OVER");
		order.setMobile("1234353");
		order.setNickName("TOM");
		
//		int result = testOrderMapper.insertSelective(order);
		logger.info(" ====== : insert:数量:{}",result);
		
		return "test-admin";
	}
}
