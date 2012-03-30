package com.jeecms.cms.dao.assist.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.cms.dao.assist.CmsVoteRecordDao;
import com.jeecms.cms.entity.assist.CmsVoteRecord;

@Repository
public class CmsVoteRecordDaoImpl extends
		HibernateBaseDao<CmsVoteRecord, Integer> implements CmsVoteRecordDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public CmsVoteRecord findById(Integer id) {
		CmsVoteRecord entity = get(id);
		return entity;
	}

	public CmsVoteRecord save(CmsVoteRecord bean) {
		getSession().save(bean);
		return bean;
	}

	public int deleteByTopic(Integer topicId) {
		String hql = "delete from CmsVoteItem bean"
				+ " where bean.topic.id=:topicId";
		return getSession().createQuery(hql).setParameter("topicId", topicId)
				.executeUpdate();
	}

	public CmsVoteRecord deleteById(Integer id) {
		CmsVoteRecord entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsVoteRecord> getEntityClass() {
		return CmsVoteRecord.class;
	}
}