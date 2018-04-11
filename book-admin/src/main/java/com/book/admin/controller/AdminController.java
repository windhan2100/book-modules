package com.book.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.book.core.model.AdminFunctions;
import com.book.core.model.AdminRight;
import com.book.core.model.User;
import com.book.core.service.AdminFunctionsService;
import com.book.core.service.AdminRightService;
import com.book.core.service.UserService;
import com.book.core.utils.Constants;

/**
 * 对用户权限以及菜单管理的Admin
 * @author liweihan
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminRightService adminRightService;
	@Autowired
	private AdminFunctionsService adminFunctionsService;
	@Autowired
	private UserService userService;

	/**
	 * 来到菜单管理首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/menus")
	public String menus(HttpServletRequest request, HttpServletResponse response) {
		List<AdminFunctions> menus = adminFunctionsService.getAll(); 
		request.setAttribute("records", menus);
		return getReturnStr(request, "menus");
	}
	
	/**
	 * 添加菜单
	 * @param name
	 * 			菜单名称
	 * @param url
	 * 			菜单URL	
	 * @param sort
	 * 			菜单位置
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menu/addOrUpdate",method = RequestMethod.POST)
	public Map<String, Object> addMenu(
			@RequestParam("name") String name,
			@RequestParam("url") String url,
			@RequestParam("sort") Integer sort,
			@RequestParam(value = "id",required=false,defaultValue="0") Integer id,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(name)) {
			map.put("code", 1);
			map.put("msg", "菜单名称不能为空！");
			return map;
		}
		
		if (StringUtils.isBlank(url)) {
			map.put("code", 2);
			map.put("msg", "菜单URL不能为空！");
			return map;
		}
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 3);
			map.put("msg", "没有登录!");
			return map;
		}
		
		AdminFunctions adminFunctions = new AdminFunctions();
		adminFunctions.setName(name);
		adminFunctions.setUrl(url);
		adminFunctions.setSort(sort);
		adminFunctions.setCreatorName(user.getName());
		int result = 0;
		
		if (id == 0) {
			 result = adminFunctionsService.addFunction(adminFunctions);
		} else {
			adminFunctions.setId(id);
			adminFunctions.setCreateTime(new Date());
			result = adminFunctionsService.updateObj(adminFunctions);
		}
		
		Object[] params = {name,url,sort,user.getName(),id};
		logger.info(" ====== name:{},url:{},sort:{},creator:{},id:{}",params);
		
		if (result == 1) {
			map.put("code", 0);
			map.put("msg", "OK");
			return map;
		} else {
			map.put("code", 4);
			map.put("msg", "一会儿再试!");
			return map;
		}
	}
	
	/**
	 * 根据ID获取菜单详情
	 * @param id
	 * 			菜单ID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menu/detail.json",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> detailMenu(
			@RequestParam("id") Integer id,
			HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		AdminFunctions adminFunctions = adminFunctionsService.getObjById(id);
		if (adminFunctions != null) {
			map.put("code", 0);
			map.put("msg", adminFunctions);
		} else {
			map.put("code", 1);
			map.put("msg", "该菜单不存在!稍后再试!");
		}
		return map;
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * 			菜单Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menu/del",method = RequestMethod.POST)
	public Map<String, Object> delMenu(
			@RequestParam("id") Integer id,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "没有登录!");
			return map;
		}
		
		int result = adminFunctionsService.delFunction(id);
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
	 * 去用户权限管理首页
	 * @param request
	 * 			HttpServletRequest
	 * @param response
	 * 			HttpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/users")
	public String users(HttpServletRequest request, HttpServletResponse response) {
		//用户
		List<AdminRight> adminRights = adminRightService.getAll();
		request.setAttribute("users", adminRights);
		
		//所有的菜单
		List<AdminFunctions> menus = adminFunctionsService.getAll(); 
		request.setAttribute("menus", menus);
		
		return getReturnStr(request, "users");
	}
	
	/**
	 * 添加新用户
	 * @param name
	 * 			用户中文名字
	 * @param email
	 * 			用户登录账号
	 * @param isAdmin
	 * 			是否超级管理员1是，0不是
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/add",method = RequestMethod.POST)
	public Map<String, Object> addUser(
			@RequestParam("name") String name,
			@RequestParam("email") String email
			,@RequestParam("isAdmin") Integer isAdmin,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (StringUtils.isBlank(name)) {
			map.put("code", 1);
			map.put("msg", "姓名不能为空");
			return map;
		}
		
		if (StringUtils.isBlank(email)) {
			map.put("code", 2);
			map.put("msg", "登录账号不能为空");
			return map;
		}
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 3);
			map.put("msg", "重新登录!");
			return map;
		}
		
		AdminRight adminRight = new AdminRight();
		adminRight.setChineseName(name);
		adminRight.setName(email);
		adminRight.setCreatorName(user.getName());
		adminRight.setIsAdmin(isAdmin);
		
		//检查一下登录账号是否重复
		User user2 = userService.getUserByName(email);
		if (user2 != null) {
			map.put("code", 5);
			map.put("msg", "登录账号重复!");
			return map;
		}

	
		try {
			int result = adminRightService.add(adminRight);
			if (result != 1) {
				map.put("code", 4);
				map.put("msg", "添加用户失败,请稍后再试!");
				return map;
			}
		} catch (Exception e) {
			logger.error(" ===== 添加新用户失败!name:{},email:{},isAdmin:{}",name,email,isAdmin,e);
			map.put("code", 4);
			map.put("msg", "添加用户失败,请稍后再试!");
			return map;
		}
		
		map.put("code", 0);
		map.put("msg", "OK!");
		return map;
	}
	
	
	/**
	 * 删除用户对象
	 * @param id
	 * 			AdminRightId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/del",method = RequestMethod.POST)
	public Map<String,Object> delUser(
			@RequestParam("id") Integer id,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "请重新登录!");
			return map;
		}
		
		try {
			adminRightService.delById(id);
		} catch (Exception e) {
			logger.error(" ====== 删除用户失败：稍后再试!AdminRightId:{}",id);
			map.put("code", 2);
			map.put("msg", "删除失败,稍后再试!");
			return map;
		}
		
		map.put("code", 0);
		map.put("msg", "删除成功!");
		return map;
	}
	
	/**
	 * 更新用户的权限
	 * @param id
	 * 			adminRight的ID
	 * @param rights
	 * 			用户权限,AdminFunctions的ID,多个以逗号分开
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/modify",method = RequestMethod.POST)
	public Map<String, Object> modifyUser(
			@RequestParam("id") Integer id,
			@RequestParam("rights") String rights,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "请重新登录!");
			return map;
		}
		
		AdminRight adminRight = new AdminRight();
		adminRight.setId(id);
		adminRight.setRights(rights);
		adminRight.setCreatorName(user.getName());
		adminRight.setCreateTime(new Date());
		
		int result = adminRightService.updateObjById(adminRight);
		if (result < 1) {
			logger.error(" ====== modifyAdminRight error, id:{},rights:{},",id,rights);
			map.put("code", 2);
			map.put("msg", "修改失败,请稍后再试!");
			return map;
		}
		
		map.put("code", 0);
		map.put("msg", "OK");
		return map;
	}
	
	/**
	 * 修改用户基本信息
	 * @param request
	 * 			HttpServletRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/update.json" ,method = RequestMethod.POST)
	public Map<String, Object> updateUserInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "请重新登录!");
			return map;
		}
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		User userParams = new User();
		if (StringUtils.isNotBlank(name)) {
			userParams.setName(name);
		}
		if (StringUtils.isNotBlank(password)) {
			userParams.setPassword(password);
		}
		if (StringUtils.isNotBlank(email)) {
			userParams.setEmail(email);
		}
		if (StringUtils.isNotBlank(phone)) {
			userParams.setPhone(phone);
		}
		userParams.setUpdateTime(new Date());
		userParams.setId(user.getId());
		
		int result = userService.updateUserById(userParams,user.getName());
		
		if (result < 1) {
			map.put("code", 2);
			map.put("msg", "修改失败,请稍后再试!");
			return map;
		}
		
		User userResult = userService.getUserById(user.getId());
		if (userResult == null) {
			map.put("code", 3);
			map.put("msg", "修改失败,您可能被删除!");
			return map;
		} else {
			WebUtils.setSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY, userResult);
		}
		
		map.put("code", 0);
		map.put("msg", "Ok");
		return map;
	}
	
	/**
	 * 用户查看自己信息时需要输入密码
	 * @param password
	 * 			用户密码
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/validate.json",method = RequestMethod.POST)
	public Map<String, Object> validate(
			@RequestParam("password") String password ,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String,Object>();
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user == null) {
			map.put("code", 1);
			map.put("msg", "请重新登录!");
			return map;
		}
		
		if (!password.trim().equals(user.getPassword())) {
			map.put("code", 2);
			map.put("msg", "密码错误!");
			return map;
		} 
		
		map.put("code", 0);
		map.put("msg", user);
		return map;
	}
	
	/**
	 * 我的信息页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/myinfo",method = {RequestMethod.GET,RequestMethod.POST})
	public String myinfo(HttpServletRequest request,HttpServletResponse response) {
		return getReturnStr(request, "myinfo");
	}
}
