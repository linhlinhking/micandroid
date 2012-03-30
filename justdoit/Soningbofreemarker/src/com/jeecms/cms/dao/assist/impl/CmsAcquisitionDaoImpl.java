package com.jeecms.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.assist.CmsAcquisitionDao;
import com.jeecms.cms.entity.assist.CmsAcquisition;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class CmsAcquisitionDaoImpl extends
		HibernateBaseDao<CmsAcquisition, Integer> implements CmsAcquisitionDao {
	@SuppressWarnings("unchecked")
	public List<CmsAcquisition> getList(Integer siteId) {
		Finder f = Finder.create("from CmsAcquisition bean");
		if (siteId != null) {
			f.append(" where bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		f.append(" order by bean.id asc");
		return find(f);
	}

	public CmsAcquisition findById(Integer id) {
		CmsAcquisition entity = get(id);
		return entity;
	}

	public CmsAcquisition save(CmsAcquisition bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsAcquisition deleteById(Integer id) {
		CmsAcquisition entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsAcquisition> getEntityClass() {
		return CmsAcquisition.class;
	}
}