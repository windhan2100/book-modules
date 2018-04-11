package com.book.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.core.mapper.AdminRightMapper;
import com.book.core.model.AdminRight;
import com.book.core.model.AdminRightExample;
import com.book.core.model.User;
import com.book.core.service.AdminRightService;
import com.book.core.service.PersistentLoginsService;
import com.book.core.service.UserService;

@Service
public class AdminRightServiceImpl implements AdminRightService{
	
	private static Logger logger = LoggerFactory.getLogger(AdminRightServiceImpl.class);
	
	@Autowired
	private AdminRightMapper adminRightMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private PersistentLoginsService persistentLoginsService;

	@Override
	public AdminRight getObjByUserName(String userName) {
		AdminRightExample adminRightExample = new AdminRightExample();
		AdminRightExample.Criteria criteria = adminRightExample.createCriteria();
		criteria.andNameEqualTo(userName);
		
		AdminRight adminRight = null;
		List<AdminRight> list = adminRightMapper.selectByExample(adminRightExample);
		if (list != null && list.size() > 0) {
			adminRight = list.get(0);
		}
		
		return adminRight;
	}

	@Override
	public List<AdminRight> getAll() {
		AdminRightExample adminRightExample = new AdminRightExample();
		return adminRightMapper.selectByExample(adminRightExample);
	}

	@Transactional
	@Override
	public int add(AdminRight adminRight) throws Exception{
		int result1 = adminRightMapper.insertSelective(adminRight);
		
		User newUser = new User();
		newUser.setName(adminRight.getName());
		newUser.setPassword("000000");
		int result2 = userService.add(newUser);
		
		if (result1 == 1 && result2 == 1) {
			return 1;
		} else {
			throw new Exception("添加用户失败,需要回滚!");
		}
		
	}

	@Override
	public AdminRight getObiById(Integer id) {
		return adminRightMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delById(Integer id) {
		AdminRight adminRight = adminRightMapper.selectByPrimaryKey(id);
		if (adminRight != null) {
			String name = adminRight.getName();
			int result1 = userService.delByName(name);
			int result2 = adminRightMapper.deleteByPrimaryKey(id);
			int result3 = persistentLoginsService.delObjByUserName(name);
			
			logger.info(" ====== 删除用户的结果：resultUser:{},resultAdminRight:{},persistent_logins:{}",result1,result2,result3);
		}
		return 1;
	}

	@Override
	public int updateObjById(AdminRight adminRight) {
		return adminRightMapper.updateByPrimaryKeySelective(adminRight);
	}
}
