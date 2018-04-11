package com.book.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.AdminFunctionsMapper;
import com.book.core.model.AdminFunctions;
import com.book.core.model.AdminFunctionsExample;
import com.book.core.service.AdminFunctionsService;

@Service
public class AdminFunctionsServiceImpl implements AdminFunctionsService{

	@Autowired
	private AdminFunctionsMapper adminFunctionsMapper;
	
	@Override
	public List<AdminFunctions> getAll() {
		AdminFunctionsExample adminFunctionsExample = new AdminFunctionsExample();
//		AdminFunctionsExample.Criteria criteria = adminFunctionsExample.createCriteria();
//		criteria.andStateEqualTo(0); //有效的
		adminFunctionsExample.setOrderByClause("sort ASC");
		
		return adminFunctionsMapper.selectByExample(adminFunctionsExample);
	}

	@Override
	public List<AdminFunctions> getObjByIds(List<Integer> listIds) {
		AdminFunctionsExample adminFunctionsExample = new AdminFunctionsExample();
		AdminFunctionsExample.Criteria criteria = adminFunctionsExample.createCriteria();
		adminFunctionsExample.setOrderByClause("sort ASC");
//		criteria.andStateEqualTo(0); //有效的
		if (listIds != null && listIds.size() > 0) {
			criteria.andIdIn(listIds);
		}
		return adminFunctionsMapper.selectByExample(adminFunctionsExample);
	}

	@Override
	public int addFunction(AdminFunctions adminFunctions) {
		return adminFunctionsMapper.insertSelective(adminFunctions);
	}

	@Override
	public int delFunction(Integer id) {
		return adminFunctionsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdminFunctions getObjById(Integer id) {
		return adminFunctionsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateObj(AdminFunctions adminFunctions) {
		return adminFunctionsMapper.updateByPrimaryKeySelective(adminFunctions);
	}


}
