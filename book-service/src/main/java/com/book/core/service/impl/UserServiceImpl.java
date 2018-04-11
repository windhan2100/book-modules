package com.book.core.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.core.mapper.AdminRightMapper;
import com.book.core.mapper.PersistentLoginsMapper;
import com.book.core.mapper.UserMapper;
import com.book.core.model.AdminRight;
import com.book.core.model.AdminRightExample;
import com.book.core.model.PersistentLogins;
import com.book.core.model.PersistentLoginsExample;
import com.book.core.model.User;
import com.book.core.model.UserExample;
import com.book.core.service.PersistentLoginsService;
import com.book.core.service.UserService;
import com.book.core.utils.Constants;
import com.book.core.utils.CookieUtil;
import com.book.core.utils.EncryptionUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PersistentLoginsMapper persistentLoginsMapper;
	@Autowired
	private AdminRightMapper adminRightMapper;
	@Autowired
	private PersistentLoginsService persistentLoginsService;

	@Override
	public User adminlogin(User user, boolean remember,
			HttpServletResponse response) {
		if (user == null || StringUtils.isBlank(user.getName()) 
				|| StringUtils.isBlank(user.getPassword())) {
			return null;
		}
		
		//根据用户名和密码查找用户
		UserExample userExample = new UserExample();
		UserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andNameEqualTo(user.getName());
		userCriteria.andPasswordEqualTo(user.getPassword());
		User resultUser = null;
		List<User> listUser = userMapper.selectByExample(userExample);
		if (listUser != null && listUser.size() > 0) {
			resultUser = listUser.get(0);
		}
		
		
		//如果remember为true,则保存cookie的值，下次自动登录
		if (resultUser != null && remember == true) {
			//有效期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);//一个月
			Date validTime = calendar.getTime();
			//精确到分的时间戳
			String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
					+ calendar.get(Calendar.MINUTE);
			
			//sha256加密用户信息
			String userInfoBySha256 = EncryptionUtil.sha256Hex(resultUser.getName() + "_" + resultUser.getPassword() + "_" + timeString + "_" + Constants.salt);
			// UUID值
			String uuidString = UUID.randomUUID().toString();
			// Cookie值
			String cookieValue = EncryptionUtil.base64Encode(resultUser.getName() + ":" + uuidString);
			
			// 在数据库中保存自动登录记录（如果已有该用户的记录则更新记录）
			PersistentLoginsExample pLoginsExample = new PersistentLoginsExample();
			PersistentLoginsExample.Criteria pLCriteria = pLoginsExample.createCriteria();
			pLCriteria.andUsernameEqualTo(resultUser.getName());
			PersistentLogins persistentLogins = null;
			List<PersistentLogins> listpLogins = persistentLoginsMapper.selectByExample(pLoginsExample);
			if (listpLogins != null && listpLogins.size() > 0) {
				persistentLogins = listpLogins.get(0);
			}
			if (persistentLogins == null) {
				persistentLogins = new PersistentLogins();
				persistentLogins.setUsername(resultUser.getName());
				persistentLogins.setSeries(uuidString);
				persistentLogins.setToken(userInfoBySha256);
				persistentLogins.setValidTime(validTime);
				persistentLoginsMapper.insertSelective(persistentLogins);
			} else {
				persistentLogins.setSeries(uuidString);
				persistentLogins.setToken(userInfoBySha256);
				persistentLogins.setValidTime(validTime);
				persistentLogins.setUpdateTime(new Date());
				persistentLoginsMapper.updateByPrimaryKeySelective(persistentLogins);// .updateByExample(persistentLogins, pLoginsExample2);
			}
			//保存cookie
			CookieUtil.addCookie(response, Constants.RememberMe_Admin,cookieValue,null);
		}
		return resultUser;
	}

	@Override
	public void adminLogout(HttpServletRequest request,
			HttpServletResponse response) {
		//从session中获取用户详情
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute(Constants.ADMIN_SESSION_USER_KEY);
		}
		
		if (user != null) {
			PersistentLoginsExample pLoginsExample = new PersistentLoginsExample();
			PersistentLoginsExample.Criteria pLCriteria = pLoginsExample.createCriteria();
			pLCriteria.andUsernameEqualTo(user.getName());
			int result = persistentLoginsMapper.deleteByExample(pLoginsExample);
			logger.info(" ====== logout delete from db result:{},userName:{}",result,user.getName());
		}
		
		//清除session和用于自动登录的cookie
		if (session != null) {
			session.removeAttribute(Constants.ADMIN_SESSION_USER_KEY);
		}
		CookieUtil.delCookie(request, response,Constants.RememberMe_Admin);
	}

	@Override
	public User getUserByName(String name) {
		if (Strings.isBlank(name)) {
			return null;
		}
		
		UserExample userExample = new UserExample();
		UserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andNameEqualTo(name);
		User resultUser = null;
		List<User> listUser = userMapper.selectByExample(userExample);
		if (listUser != null && listUser.size() > 0) {
			resultUser = listUser.get(0);
		}
		return resultUser;
	}

	@Override
	public int add(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public int delByName(String name) {
		UserExample userExample = new UserExample();
		UserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andNameEqualTo(name);
		return userMapper.deleteByExample(userExample);
	}

	@Transactional
	@Override
	public int updateUserById(User user,String userName) {
		if (StringUtils.isNotBlank(user.getName())) {
			AdminRight adminRight = new AdminRight();
			adminRight.setName(user.getName());
			
			AdminRightExample adminRightExample = new AdminRightExample();
			AdminRightExample.Criteria criteria = adminRightExample.createCriteria();
			criteria.andNameEqualTo(userName);
			adminRightMapper.updateByExampleSelective(adminRight, adminRightExample);
			
			persistentLoginsService.delObjByUserName(userName);
		}
		
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
