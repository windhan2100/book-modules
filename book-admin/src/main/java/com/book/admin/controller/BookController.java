package com.book.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.book.core.model.City;
import com.book.core.model.Orders;
import com.book.core.model.OrdersExample;
import com.book.core.model.PayType;
import com.book.core.model.Shop;
import com.book.core.model.Type;
import com.book.core.model.User;
import com.book.core.service.CityService;
import com.book.core.service.OrdersService;
import com.book.core.service.PayTypeService;
import com.book.core.service.ShopService;
import com.book.core.service.TypeService;
import com.book.core.utils.Constants;
import com.book.core.utils.DateUtil;
import com.book.core.utils.NumberUtil;

/**
 * 北京预约相关的controller
 * @author liweihan
 *
 */
@Controller
@RequestMapping("/book")
public class BookController extends BaseController implements InitializingBean{

	private static Logger logger = LoggerFactory.getLogger(BookController.class);
	
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
	 * 预约查询
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{cityurl}", method = {RequestMethod.POST,RequestMethod.GET})
	public String index(@PathVariable(value = "cityurl") String cityurl,HttpServletRequest request, HttpServletResponse response) {
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
		request.setAttribute("shop", shopTemp);
		
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
		request.setAttribute("type", typeTemp);
		
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
		request.setAttribute("payType", payTypeTemp);
		
		//查询
		Integer shopId = NumberUtil.getInt(request.getParameter("param_shop"), 0);
		String bookTime = request.getParameter("param_book_time");
		String phone = request.getParameter("param_phone");
		
		OrdersExample example = new OrdersExample();
		OrdersExample.Criteria criteria = example.createCriteria();
		
		//加入城市限制
		criteria.andCityIdEqualTo(cityId);
		
		if (StringUtils.isNotBlank(phone)) {
			//选择手机号精确查询时,不对分店和日期进行处理
			criteria.andPhoneEqualTo(phone);
		} else {
			//不适用手机号查询时,可以使用分店和日期查询,不选择日期是当天
			
			//分店选择
			if (shopId != 0) {
				criteria.andShopIdEqualTo(shopId);
			}
			//时间选择
			if (StringUtils.isBlank(bookTime)) {
				bookTime = DateUtil.date2String(new Date()); //当天
			}
			Date startTime = DateUtil.getDate(bookTime,0,1);
			Date endTime = DateUtil.getDate(bookTime, 1, -1);
			logger.info(" ====== beginTime：{},endTime:{}",startTime,endTime);
			criteria.andBookTimeBetween(startTime, endTime);
			example.setOrderByClause(" book_time ASC");
		}
		
		List<Orders> list = ordersService.getAll(example);
		request.setAttribute("records", list);
		request.setAttribute("bookTime", bookTime);
		request.setAttribute("shopId", shopId);
		request.setAttribute("phone", phone);
		request.setAttribute("cityName", cityName);
		
		return getReturnStr(request, "book");
	}
	
	/**
	 * 删除订单
	 * @param id
	 * 			订单Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	public Map<String, Object> delBook(
			@RequestParam("id") Long id,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "没有登录!");
			return map;
		}
		
		int result = ordersService.delById(id);
		if (result == 1) {
			map.put("code", 0);
			map.put("msg", "删除成功!");
			return map;
		} else {
			map.put("code", 2);
			map.put("msg", "删除失败,稍后再试!");
			return map;
		}
	}
	
	/**
	 * 根据ID订单
	 * @param id
	 * 			菜单ID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail.json",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> detailBook(
			@RequestParam("id") Long id,
			HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		Orders orders = ordersService.getOrdersById(id);
		if (orders != null) {
			map.put("code", 0);
			map.put("msg", orders);
		} else {
			map.put("code", 1);
			map.put("msg", "该订单不存在!稍后再试!");
		}
		return map;
	}
	
	
	/**
	 * 增加或修改订单
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/addorupdate",method = RequestMethod.POST)
	public Map<String, Object> addOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		long id = NumberUtil.getLong(request.getParameter("id"), 0L);
		String userName = request.getParameter("username");
		String phone = request.getParameter("phone");
		Integer shopId = NumberUtil.getInt(request.getParameter("shop"), 0);
		String bookTime = request.getParameter("bookTime");
		Integer typeId = NumberUtil.getInt(request.getParameter("type"), 0);
		Integer payTypeId = NumberUtil.getInt(request.getParameter("payType"), 0);
		BigDecimal payPrice = NumberUtil.getBigDecimal(request.getParameter("payPrice"), new BigDecimal(0));
		String backUp = request.getParameter("backUp");//备注信息:地址等
		String payCode = request.getParameter("payCode");
		int status =  NumberUtil.getInt(request.getParameter("status"), -1);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isBlank(phone)) {
			map.put("code", 1);
			map.put("msg", "手机号码不能为空");
			return map;
		}		
		if(shopId == 0) {
			map.put("code", 2);
			map.put("msg", "分店选择错误!");
			return map;
		}
		if (StringUtils.isBlank(bookTime)) {
			map.put("code", 3);
			map.put("msg", "预约时间不能为空!");
			return map;
		}
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 4);
			map.put("msg", "没有登录!");
			return map;
		}
		
		Date bookAndTime = DateUtil.parseDateWithoutSecond(bookTime);
		
		//支付方式
		List<PayType> payTypeList = new ArrayList<PayType>();
		if (tempCache.get("payType") != null) {
			payTypeList = (List<PayType>) tempCache.get("payType");
		}
		PayType payType = new PayType();
		for(PayType p : payTypeList) {
			if (p.getId() == payTypeId) {
				payType = p;
			}
		}
		//分店
		List<Shop> shopList = new ArrayList<Shop>();
		if (tempCache.get("shop") != null) {
			shopList = (List<Shop>) tempCache.get("shop");
		}
		Shop shop = new Shop();
		for(Shop p : shopList) {
			if (p.getId() == shopId) {
				shop = p;
			}
		}
		
		//城市处理
		List<City> cityList = new ArrayList<City>();
		if (tempCache.get("city") != null) {
			cityList = (List<City>) tempCache.get("city");
		}
		City city = new City();
		for(City c : cityList) {
			if (c.getId() == shop.getCityId()) {
				city = c;
			}
		}
		
		//套餐
		List<Type> typeList = new ArrayList<Type>();
		if (tempCache.get("type") != null) {
			typeList = (List<Type>) tempCache.get("type");
		}
		Type type = new Type();
		for(Type t : typeList) {
			if (t.getId() == typeId) {
				type = t;
			}
		}
		
		if (id == 0L) {
			//查询一下该时间段或该手机号有没有被预约过
			OrdersExample example = new OrdersExample();
			example.or().andPhoneEqualTo(phone.trim()).andShopIdEqualTo(shopId).andBookTimeGreaterThan(new Date()).andStatusNotEqualTo(2);
			example.or().andBookTimeEqualTo(bookAndTime).andShopIdEqualTo(shopId).andBookTimeGreaterThan(new Date()).andStatusNotEqualTo(2);
			List<Orders> list = ordersService.getAll(example);
			if (list != null && list.size() > 0) {
				Orders orders = list.get(0);
				if (orders.getBookTime().equals(bookAndTime)) {
					map.put("code", 7);
					map.put("msg", "该时间已经被预约!");
					return map;
				}
				if (orders.getPhone().equals(phone.trim())) {
					map.put("code", 8);
					map.put("msg", "该手机号已经被预约!");
					return map;
				}
				map.put("code", 6);
				map.put("msg", "该时间或该手机号已经被预约!");
				return map;
			}
			
			//添加
			Orders order = new Orders();
			order.setBackUp(backUp);
			order.setBookTime(bookAndTime);
			order.setCityId(shop.getCityId());
			order.setCityName(city.getCityName());
			order.setCreateTime(new Date());
			order.setPayCode(payCode);
			order.setPayId(payTypeId);
			order.setPayName(StringUtils.isNotBlank(payType.getPayName()) ? payType.getPayName() : "");
			order.setPayPrice(payPrice);
			order.setPhone(phone);
			order.setShopId(shopId);
			order.setShopName(shop.getName());
			order.setBackUp(backUp);
			order.setStatus(status);
			order.setUsername(userName);
			order.setTypeId(typeId);
			order.setTypeName(StringUtils.isNotBlank(type.getTypeName()) ? type.getTypeName() : "");
			order.setUpdateUser(user.getName());
			
			int result = ordersService.add(order);
			if (result == 1) {
				map.put("code", 0);
				map.put("msg", "OK!");
				return map;
			} else {
				map.put("code", 5);
				map.put("msg", "预约失败,请稍后再试!");
				return map;
			}
			
		} else {
			//修改
			Orders order = ordersService.getOrdersById(id);
			if (isMobile(request)) {
				//移动端
				order.setBookTime(DateUtil.parseDateWithoutSecond(bookTime));
				order.setCityId(shop.getCityId());
				order.setCityName(city.getCityName());
				order.setPhone(phone);
				order.setShopId(shopId);
				order.setShopName(shop.getName());
				order.setStatus(status);
				order.setUpdateUser(user.getName());
				order.setUpdateTime(new Date());
			} else {
				//PC端
				order.setBackUp(backUp);
				order.setBookTime(DateUtil.parseDateWithoutSecond(bookTime));
				order.setCityId(shop.getCityId());
				order.setCityName(city.getCityName());
				order.setPayCode(payCode);
				order.setPayId(payTypeId);
				order.setPayName(StringUtils.isNotBlank(payType.getPayName()) ? payType.getPayName() : "");
				order.setPayPrice(payPrice);
				order.setPhone(phone);
				order.setShopId(shopId);
				order.setShopName(shop.getName());
				order.setBackUp(backUp);
				order.setStatus(status);
				order.setUsername(userName);
				order.setTypeId(typeId);
				order.setTypeName(StringUtils.isNotBlank(type.getTypeName()) ? type.getTypeName() : "");
				order.setUpdateUser(user.getName());
				order.setUpdateTime(new Date());
			}
			int result = ordersService.updateById(order);
			if (result == 1) {
				map.put("code", 0);
				map.put("msg", "OK!");
				return map;
			} else {
				map.put("code", 5);
				map.put("msg", "修改预约,请稍后再试!");
				return map;
			}
			
		}
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
	}

	public void afterPropertiesSet() throws Exception {
		this.init();
		logger.warn(" ====== {} init OK!",this.getClass().getSimpleName());
	}
	
}
