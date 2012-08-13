package com.soningbo.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.soningbo.core.dao.BaseDao;
import com.soningbo.core.page.Pagination;

@Repository
public abstract class BaseDaoImpl<E, PK extends Serializable> implements
		BaseDao<E, PK> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private Class<E> persistentClass;

	protected SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	public E save(E entity) {
		Assert.notNull(entity);
		getSession().save(entity);
		return entity;
	}

	public Object update(Object entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		return entity;
	}

	public Object saveOrUpdate(Object entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		return entity;

	}

	public Object merge(Object entity) {
		Assert.notNull(entity);
		return getSession().merge(entity);
	}

	public void delete(Object entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
	}

	public E deleteById(PK id) {
		Assert.notNull(id);
		E entity = load(id);
		getSession().delete(entity);
		return entity;
	}

	public E load(PK id) {
		Assert.notNull(id);
		return load(id, false);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public E load(PK id, boolean isLock) {
		Assert.notNull(id);
		E entity = null;
		if (isLock) {
			entity = (E) getSession().load(getPersistentClass(), id,
					LockMode.UPGRADE);
		} else {
			entity = (E) getSession().load(getPersistentClass(), id);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public E get(PK id) {
		Assert.notNull(id);
		return (E) getSession().get(getPersistentClass(), id);
	}

	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	protected List<E> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	@SuppressWarnings("unchecked")
	protected Pagination<E> findByCriteria(Criteria crit, int pageNo,
			int pageSize, Projection projection, Order... orders) {
		int totalCount = ((Number)crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		Pagination<E> p = new Pagination<E>(pageNo,pageSize,totalCount) ;
		if(totalCount < 1){
			p.setList(new ArrayList<E>()) ;
			return p ;
		}
		crit.setProjection(projection) ;
		if(null == projection){
			crit.setResultTransformer(Criteria.ROOT_ENTITY) ;
		}
		
		if(null != orders){
			for(Order order : orders){
				crit.addOrder(order) ;
			}
		}
		
		crit.setFirstResult(p.getFirstResult()) ;
		crit.setMaxResults(p.getPageSize()) ;
		p.setList(crit.list()) ;
		
		return p ;
	}
}
