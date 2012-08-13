package com.soningbo.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.soningbo.core.dao.BaseDao;
import com.soningbo.core.dao.Condition;
import com.soningbo.core.dao.OrderBy;
import com.soningbo.core.dao.Updater;
import com.soningbo.core.page.Pagination;
import com.soningbo.core.service.BaseService;

@Transactional
public class BaseServiceImpl<E,PK extends Serializable> implements BaseService<E, PK>{

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private BaseDao<E,PK> dao;
	
	public void setDao(BaseDao<E,PK> dao) {
		this.dao = dao;
	}

	protected BaseDao<E,PK> getDao() {
		return this.dao;
	}
	
	@Transactional(readOnly = true)
	public E findById(PK id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public E load(PK id) {
		return dao.load(id);
	}

	@Transactional(readOnly = true)
	public List<E> findAll() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Pagination<E> findAll(int pageNo, int pageSize, OrderBy... orderBys) {
		return dao.findAll(pageNo, pageSize, orderBys) ;
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, boolean anyWhere, Condition[] conds,
			String... exclude) {
		return dao.findByEgList(eg, anyWhere, conds, exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, boolean anyWhere, String... exclude) {
		return this.findByEgList(eg, anyWhere, null, exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, Condition[] conds, String... exclude) {
		return this.findByEgList(eg, false, conds, exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude) {
		return dao.findByEgList(eg, anyWhere, conds, firstResult, maxResult,
				exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, boolean anyWhere, int firstResult,
			int maxResult, String... exclude) {
		return this.findByEgList(eg, anyWhere, null, firstResult, maxResult,
				exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, Condition[] conds, int firstResult,
			int maxResult, String... exclude) {
		return this.findByEgList(eg, false, conds, firstResult, maxResult,
				exclude);
	}

	@Transactional(readOnly = true)
	public List<E> findByEgList(E eg, String... exclude) {
		return this.findByEgList(eg, false, null, exclude);
	}

	
	@Transactional(readOnly = true)
	public Pagination<E> findByEg(E eg, boolean anyWhere, Condition[] conds,
			int pageNo, int pageSize, String... exclude) {
		return dao.findByEg(eg, anyWhere, conds, pageNo, pageSize, exclude);
	}

	@Transactional(readOnly = true)
	public Pagination<E> findByEg(E eg, boolean anyWhere, int pageNo,
			int pageSize, String... exclude) {
		return this.findByEg(eg, anyWhere, null, pageNo, pageSize, exclude);
	}

	@Transactional(readOnly = true)
	public Pagination<E> findByEg(E eg, Condition[] conds, int pageNo,
			int pageSize, String... exclude) {
		return this.findByEg(eg, false, conds, pageNo, pageSize, exclude);
	}

	@Transactional(readOnly = true)
	public Pagination<E> findByEg(E eg, int pageNo, int pageSize,
			String... exclude) {
		return this.findByEg(eg, false, null, pageNo, pageSize, exclude);
	}

	public Object updateByUpdater(Updater updater) {
		return dao.updateByUpdater(updater);
	}

	public Object updateDefault(Object entity) {
		return updateByUpdater(Updater.create(entity));
	}

	public E save(E entity) {
		return dao.save(entity);
	}

	public Object update(Object o) {
		return getDao().update(o);
	}

	public Object saveOrUpdate(Object o) {
		return getDao().saveOrUpdate(o);
	}

	public void delete(Object o) {
		getDao().delete(o);
		
	}

	public E deleteById(PK id) {
		if (id == null) {
			return null;
		}
		return dao.deleteById(id);
	}

	public List<E> deleteById(PK[] ids) {
		List<E> dts = new ArrayList<E>();
		E del = null;
		if (ids != null && ids.length > 0) {
			for (PK id : ids) {
				del = deleteById(id);
				if (del != null) {
					dts.add(del);
				}
			}
		}
		return dts;
	}

	public E saveAndRefresh(E entity) {
		this.save(entity);
		getDao().refresh(entity);
		return entity;
	}

}
