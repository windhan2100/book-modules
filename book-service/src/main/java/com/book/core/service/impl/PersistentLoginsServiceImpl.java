package com.book.core.service.impl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.PersistentLoginsMapper;
import com.book.core.model.PersistentLogins;
import com.book.core.model.PersistentLoginsExample;
import com.book.core.service.PersistentLoginsService;

@Service("persistentLoginsService")
public class PersistentLoginsServiceImpl implements PersistentLoginsService{
	
	private static Logger logger = LoggerFactory.getLogger(PersistentLoginsServiceImpl.class);
	
	@Autowired
	private PersistentLoginsMapper persistentLoginsMapper;

	@Override
	public PersistentLogins getObjByUUID(String uuid) {
		logger.debug(" ====== uuid:{} ",uuid);
		if (Strings.isBlank(uuid)) {
			return null;
		}
		PersistentLoginsExample pLoginsExample = new PersistentLoginsExample();
		PersistentLoginsExample.Criteria pLCriteria = pLoginsExample.createCriteria();
		pLCriteria.andSeriesEqualTo(uuid);
		
		PersistentLogins persistentLogins = null;
		List<PersistentLogins> listpLogins = persistentLoginsMapper.selectByExample(pLoginsExample);
		if (listpLogins != null && listpLogins.size() > 0) {
			persistentLogins = listpLogins.get(0);
		}
		
		return persistentLogins;
	}

	@Override
	public int updateByObj(PersistentLogins persistentLogins) {
		PersistentLoginsExample pLoginsExample = new PersistentLoginsExample();
		PersistentLoginsExample.Criteria pLCriteria = pLoginsExample.createCriteria();
		pLCriteria.andIdEqualTo(persistentLogins.getId());
		return persistentLoginsMapper.updateByExample(persistentLogins, pLoginsExample);
	}

	@Override
	public int delObjById(int id) {
		return persistentLoginsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delObjByUserName(String userName) {
		PersistentLoginsExample pLoginsExample = new PersistentLoginsExample();
		PersistentLoginsExample.Criteria pLCriteria = pLoginsExample.createCriteria();
		pLCriteria.andUsernameEqualTo(userName);
		
		return persistentLoginsMapper.deleteByExample(pLoginsExample);
	}

}
