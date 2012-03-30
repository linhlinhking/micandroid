package com.jeecms.cms.manager.assist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.assist.CmsVoteRecordDao;
import com.jeecms.cms.entity.assist.CmsVoteRecord;
import com.jeecms.cms.manager.assist.CmsVoteRecordMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class CmsVoteRecordMngImpl implements CmsVoteRecordMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public CmsVoteRecord findById(Integer id) {
		CmsVoteRecord entity = dao.findById(id);
		return entity;
	}

	public CmsVoteRecord save(CmsVoteRecord bean) {
		dao.save(bean);
		return bean;
	}

	public CmsVoteRecord update(CmsVoteRecord bean) {
		Updater<CmsVoteRecord> updater = new Updater<CmsVoteRecord>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public int deleteByTopic(Integer topicId) {
		return dao.deleteByTopic(topicId);
	}

	public CmsVoteRecord deleteById(Integer id) {
		CmsVoteRecord bean = dao.deleteById(id);
		return bean;
	}

	public CmsVoteRecord[] deleteByIds(Integer[] ids) {
		CmsVoteRecord[] beans = new CmsVoteRecord[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private CmsVoteRecordDao dao;

	@Autowired
	public void setDao(CmsVoteRecordDao dao) {
		this.dao = dao;
	}
}