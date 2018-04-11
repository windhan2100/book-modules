package com.book.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.core.model.City;
import com.book.core.model.Orders;
import com.book.core.model.OrdersExample;
import com.book.core.model.PayType;
import com.book.core.model.Shop;
import com.book.core.model.StatusVo;
import com.book.core.model.Type;
import com.book.core.service.CityService;
import com.book.core.service.OrdersService;
import com.book.core.service.PayTypeService;
import com.book.core.service.ShopService;
import com.book.core.service.TypeService;
import com.book.core.utils.DateUtil;
import com.book.core.utils.Page;

/**
 * 
 * @author liweihan
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController implements InitializingBean {
	
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private CityService cityService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private PayTypeService payTypeService;
	@Autowired
	private OrdersService ordersService;
	
	private ConcurrentHashMap<String,Object> tempCache = new ConcurrentHashMap<String,Object>();//临时缓存城市、 分店、支付方式等
	
	/**
	 * 查询订单
	 * @param request
	 * 			HttpServletRequest
	 * @param response
	 * 			HttpServletResponse
	 * @param cityurl
	 * 			城市url,分城市查询,便于权限控制
	 * @param shopId
	 * 			分店ID,默认为0,查询所有
	 * @param typeId
	 * 			套餐ID,默认为0,查询所有
	 * @param payId
	 * 			支付渠道ID,默认为0,查询所有
	 * @param status
	 * 			订单状态,默认为-1,查询所有
	 * @param startDate
	 * 			查询开始日期,默认为每月1号(包括当天)
	 * @param endDate
	 * 			查询结束日期,默认为今天(包括当天)
	 * @param phone 
	 * 			手机号[默认使用手机号查询时,不使用其他的查询条件]
	 * @param pageNo
	 * 			页数,不传默认为第一页
	 * @param pageSize
	 * 			每页显示条数,不传默认显示每页10条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{cityurl}", method = {RequestMethod.POST,RequestMethod.GET})
	public String index(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "cityurl") String cityurl,
			@RequestParam(value = "shopId",required = false ,defaultValue = "0") Integer shopId,
			@RequestParam(value = "typeId",required = false ,defaultValue = "0") Integer typeId,
			@RequestParam(value = "payId",required = false ,defaultValue = "0") Integer payId,
			@RequestParam(value = "statusId",required = false,defaultValue = "-1") Integer statusId,
			@RequestParam(value = "startDate",required = false) String startDate,
			@RequestParam(value = "endDate",required = false ) String endDate,
			@RequestParam(value = "phone",required = false ) String phone,
			@RequestParam(value = "pageNo",required = false ,defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize) {
		
		//查找城市-
		List<City> cityList = (List<City>)tempCache.get("city");
		if (cityList == null || cityList.size() == 0) {
			request.setAttribute("error", "超级管理员还没有添加城市!");
			return getReturnStr(request, "main");
		}
		Integer cityId = 0;
		String cityName = "";
		for(City c : cityList) {
			if (c.getCityUrl().equals(cityurl)) {
				cityId = c.getId();
				cityName = c.getCityName();
				break;
			}
		}
		if (cityId == 0) {
			request.setAttribute("error", "超级管理员还没有添加该城市!");
			return getReturnStr(request, "main");
		}
		
		//查找分店
		List<Shop> shopList = (List<Shop>) tempCache.get("shop");
		List<Shop> shopTemp = new ArrayList<Shop>();
		for(Shop shop : shopList) {
			if (shop.getCityId() == cityId) {
				shopTemp.add(shop);
			}
		}
		if (shopTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加该城市的分店!");
			return getReturnStr(request, "main");
		}
		
		//查找套餐
		List<Type> typeList = (List<Type>) tempCache.get("type");
		List<Type> typeTemp = new ArrayList<Type>();
		for(Type type : typeList) {
			if (type.getCityId() == cityId) {
				typeTemp.add(type);
			}
		}
		if (typeTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加套餐类型!");
			return getReturnStr(request, "main");
		}
		
		//查找支付类型
		List<PayType> payTypeList = (List<PayType>) tempCache.get("payType");
		List<PayType> payTypeTemp = new ArrayList<PayType>();
		for(PayType payType : payTypeList) {
			if (payType.getCityId() == cityId) {
				payTypeTemp.add(payType);
			}
		}
		if (payTypeTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加支付类型!");
			return getReturnStr(request, "main");
		}
		
		//条件控制
		OrdersExample example = new OrdersExample();
		OrdersExample.Criteria criteria = example.createCriteria();
		
		//加入城市限制
		criteria.andCityIdEqualTo(cityId);
		
		if (StringUtils.isNotBlank(phone)) {
			//选择手机号精确查询时,不对分店和日期进行处理
			criteria.andPhoneEqualTo(phone);
		} else {
			//不使用手机号查询时,可以使用分店、套餐、支付类型等查询,日期不选择时，默认为当月的开始与当前日期
			//分店选择
			if (shopId != 0) {
				criteria.andShopIdEqualTo(shopId);
			}
			//套餐选择
			if (typeId != 0) {
				criteria.andTypeIdEqualTo(typeId);
			}
			//支付类型
			if (payId != 0) {
				criteria.andPayIdEqualTo(payId);
			}
			//订单状态
			if (statusId != -1) {
				criteria.andStatusEqualTo(statusId);
			}
			//开始时间和结束时间
			if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
				startDate = DateUtil.date2String(DateUtil.getFirstDayOfMonth(new Date())); //当天
				endDate = DateUtil.date2String(new Date());//当天
			}
			Date startTime = DateUtil.getDate(startDate,0,1);
			Date endTime = DateUtil.getDate(endDate, 1, -1);
			
			logger.info(" ====== beginTime：{},endTime:{}",startTime,endTime);
			criteria.andBookTimeBetween(startTime, endTime);
		}
		
		//总的个数
		long totalCount = ordersService.count(example);
		List<Orders> list = null;
		Page<Orders> page = new Page<Orders>(totalCount, pageNo, pageSize);
		example.setOrderByClause(" book_time ASC");
		example.setStartRow(page.getStartIndex());
		example.setPageSize(pageSize);
		if (totalCount > 0) {
			//分页 
			logger.info(" ====== totalCount:{},currentPage:{},pageSize:{},startIndex:{},totalPages:{}",
					page.getTotalCount(),page.getCurrPage(),page.getPageSize(),page.getStartIndex(),page.getTotalPages());
			
			//传递回页面
		    list = ordersService.getAll(example);
		}
		
		//集合结果
		request.setAttribute("records", list);
		request.setAttribute("shop", shopTemp);
		request.setAttribute("type", typeTemp);
		request.setAttribute("payType", payTypeTemp);
		request.setAttribute("status", tempCache.get("status"));
		request.setAttribute("pageSizeList", tempCache.get("pageSizeList"));
		
		//参数
		request.setAttribute("shopId", shopId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("payId", payId);
		request.setAttribute("statusId", statusId);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("phone", phone);
		request.setAttribute("cityName", cityName);
		
		//分页
		request.setAttribute("page", page);
		
		return getReturnStr(request, "query");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/statistics/{cityurl}", method = {RequestMethod.POST,RequestMethod.GET})
	public String statistics(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "cityurl") String cityurl,
			@RequestParam(value = "shopId",required = false) Integer[] shopIds,
			@RequestParam(value = "typeId",required = false) Integer[] typeIds,
			@RequestParam(value = "payId",required = false) Integer[] payIds,
			@RequestParam(value = "statusId",required = false) Integer[] statusIds,
			@RequestParam(value = "startDate",required = false) String startDate,
			@RequestParam(value = "endDate",required = false ) String endDate,
			@RequestParam(value = "pageNo",required = false ,defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize) {
		
		logger.info(" ====== shopIds:{},typeIds:{},payIds:{},statusIds:{}",shopIds,typeIds,payIds,statusIds);
		
		//查找城市-
		List<City> cityList = (List<City>)tempCache.get("city");
		if (cityList == null || cityList.size() == 0) {
			request.setAttribute("error", "超级管理员还没有添加城市!");
			return getReturnStr(request, "main");
		}
		Integer cityId = 0;
		String cityName = "";
		for(City c : cityList) {
			if (c.getCityUrl().equals(cityurl)) {
				cityId = c.getId();
				cityName = c.getCityName();
				break;
			}
		}
		if (cityId == 0) {
			request.setAttribute("error", "超级管理员还没有添加该城市!");
			return getReturnStr(request, "main");
		}
		
		//查找分店
		List<Shop> shopList = (List<Shop>) tempCache.get("shop");
		List<Shop> shopTemp = new ArrayList<Shop>();
		for(Shop shop : shopList) {
			if (shop.getCityId() == cityId) {
				shopTemp.add(shop);
			}
		}
		if (shopTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加该城市的分店!");
			return getReturnStr(request, "main");
		}
		
		//查找套餐
		List<Type> typeList = (List<Type>) tempCache.get("type");
		List<Type> typeTemp = new ArrayList<Type>();
		for(Type type : typeList) {
			if (type.getCityId() == cityId) {
				typeTemp.add(type);
			}
		}
		if (typeTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加套餐类型!");
			return getReturnStr(request, "main");
		}
		//加上其他(不选套餐的情况)查询
		Type type = new Type();
		type.setCityId(cityId);
		type.setId(0);
		type.setTypeName("其他");
		typeTemp.add(type);
		
		//查找支付类型
		List<PayType> payTypeList = (List<PayType>) tempCache.get("payType");
		List<PayType> payTypeTemp = new ArrayList<PayType>();
		for(PayType payType : payTypeList) {
			if (payType.getCityId() == cityId) {
				payTypeTemp.add(payType);
			}
		}
		if (payTypeTemp.size() <= 0) {
			request.setAttribute("error", "超级管理员还没有添加支付类型!");
			return getReturnStr(request, "main");
		}
		//加上其他(不选支付方式的情况)查询
		PayType payType = new PayType();
		payType.setCityId(cityId);
		payType.setId(0);
		payType.setPayName("其他");
		payTypeTemp.add(payType);
		
		//默认查找所有的分店
		/*if (shopIds == null) {
			shopIds = new Integer[shopTemp.size()];
			for (int i = 0; i < shopTemp.size(); i++) {
				shopIds[i] = shopTemp.get(i).getId();
			}
		}*/
		//默认查找所有的套餐
		/*if (typeIds == null) {
			typeIds = new Integer[typeTemp.size()];
			for (int i = 0; i < typeTemp.size(); i++) {
				typeIds[i] = typeTemp.get(i).getId();
			}
		}*/
		//默认查找所有的支付方式
		/*if (payIds == null) {
			payIds = new Integer[payTypeTemp.size()];
			for (int i = 0; i < payTypeTemp.size(); i++) {
				payIds[i] = payTypeTemp.get(i).getId();
			}
		}*/
		
		List<StatusVo> statusTemp = (List<StatusVo>) tempCache.get("status");
		//默认查找所有的订单状态
		/*if (statusIds == null) {
			statusIds = new Integer[statusTemp.size()];
			for (int i = 0; i < statusTemp.size(); i++) {
				statusIds[i] = statusTemp.get(i).getId();
			}
		}*/
		
		
		//开始时间和结束时间[默认查当月第一天到今天]
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			startDate = DateUtil.date2String(DateUtil.getFirstDayOfMonth(new Date())); //当天
			endDate = DateUtil.date2String(new Date());//当天
		}
		Date startTime = DateUtil.getDate(startDate,0,1);
		Date endTime = DateUtil.getDate(endDate, 1, -1);
		logger.info(" ====== beginTime：{},endTime:{}",startTime,endTime);
		
		//查询条件
		OrdersExample example = new OrdersExample();
		OrdersExample.Criteria criteria = example.createCriteria();
		//加入城市限制
		criteria.andCityIdEqualTo(cityId);
		//加入时间控制
		criteria.andBookTimeBetween(startTime, endTime);
		//加入分店控制
		if (shopIds != null) {
			criteria.andShopIdIn(Arrays.asList(shopIds));
		}
		//加入套餐控制
		if (typeIds != null) {
			criteria.andTypeIdIn(Arrays.asList(typeIds));
		}
		//加入支付方式控制
		if (payIds != null) {
			criteria.andPayIdIn(Arrays.asList(payIds));
		}
		//加入订单状态控制
		if (statusIds != null) {
			criteria.andStatusIn(Arrays.asList(statusIds));
		}
		
		//总的个数
		long totalCount = ordersService.count(example);
		//实际支付的价格总和
		BigDecimal totalPrice = ordersService.sumPrice(example);
		
		List<Orders> list = null;
		Page<Orders> page = new Page<Orders>(totalCount, pageNo, pageSize);
		example.setOrderByClause(" book_time ASC");
		example.setStartRow(page.getStartIndex());
		example.setPageSize(pageSize);
		if (totalCount > 0) {
			//分页 
			logger.info(" ====== totalCount:{},currentPage:{},pageSize:{},startIndex:{},totalPages:{}",
					page.getTotalCount(),page.getCurrPage(),page.getPageSize(),page.getStartIndex(),page.getTotalPages());
			
			//传递回页面
		    list = ordersService.getAll(example);
		}
		
		logger.info("  ======  totalCount:{},totalPrice:{}",totalCount,totalPrice);
		request.setAttribute("totalPrice", totalPrice != null ? totalPrice : "0.00");
		request.setAttribute("records", list);
		request.setAttribute("shop", shopTemp);
		request.setAttribute("type", typeTemp);
		request.setAttribute("payType", payTypeTemp);
		request.setAttribute("status", statusTemp);
		request.setAttribute("pageSizeList", tempCache.get("pageSizeList"));
		
		//参数
		request.setAttribute("shopIds", shopIds);
		request.setAttribute("typeIds", typeIds);
		request.setAttribute("payIds", payIds);
		request.setAttribute("statusIds", statusIds);
		request.setAttribute("cityName", cityName);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		request.setAttribute("page", page);
		return getReturnStr(request, "statis");
	}

	/**
	 * 初始化相关值
	 */
	protected void init() {
		//查找城市
		List<City> cityList = cityService.getAll();
		if (cityList != null) {
			tempCache.put("city", cityList);
		}
		
		//查找分店
		List<Shop> shopList = shopService.getAll();
		if (shopList != null) {
			tempCache.put("shop", shopList);
		}
		
		//查找套餐
		List<Type> typeList = typeService.getAll();
		if (typeList != null) {
			tempCache.put("type", typeList);
		}
		
		//查找支付方式
		List<PayType> payTypeList = payTypeService.getAll();
		if (payTypeList != null) {
			tempCache.put("payType", payTypeList);
		}
		
		//订单状态此处写死了
		List<StatusVo> statusList = new ArrayList<StatusVo>();
		StatusVo s1 = new StatusVo();
		s1.setId(0);
		s1.setStatusName("完成");
		statusList.add(s1);
		
		StatusVo s2 = new StatusVo();
		s2.setId(1);
		s2.setStatusName("预约");
		statusList.add(s2);
		
		StatusVo s3 = new StatusVo();
		s3.setId(2);
		s3.setStatusName("取消");
		statusList.add(s3);
	
		tempCache.put("status", statusList);
		
		//初始化所有可以选的每页显示条数
		int[] pageSizeArray = new int[]{10,20,30,50};
		tempCache.put("pageSizeList", pageSizeArray);
	}

	public void afterPropertiesSet() throws Exception {
		this.init();
		logger.warn(" ====== {} init OK!",this.getClass().getSimpleName());
	}

}
