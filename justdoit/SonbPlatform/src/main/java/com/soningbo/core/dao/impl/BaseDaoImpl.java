package com.soningbo.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.soningbo.core.dao.BaseDao;
import com.soningbo.core.dao.OrderBy;
import com.soningbo.core.page.Finder;
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

	/**
	 * 按Criterion查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	@SuppressWarnings("unchecked")
	protected List<E> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	@SuppressWarnings("unchecked")
	protected Pagination<E> findByCriteria(Criteria crit, int pageNo,
			int pageSize, Projection projection, Order... orders) {
		int totalCount = ((Number) crit.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		Pagination<E> p = new Pagination<E>(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList<E>());
			return p;
		}
		crit.setProjection(projection);
		if (null == projection) {
			crit.setResultTransformer(Criteria.ROOT_ENTITY);
		}

		if (null != orders) {
			for (Order order : orders) {
				crit.addOrder(order);
			}
		}

		crit.setFirstResult(p.getFirstResult());
		crit.setMaxResults(p.getPageSize());
		p.setList(crit.list());

		return p;
	}

	public Pagination<E> findAll(int pageNo, int pageSize, OrderBy... orders) {
		Criteria crit = createCriteria();
		return findByCriteria(crit, pageNo, pageSize, null,
				OrderBy.asOrders(orders));
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll(OrderBy... orders) {
		Criteria crit = createCriteria();
		if (null != orders && orders.length > 0) {
			for (OrderBy order : orders) {
				crit.addOrder(order.getOrder());
			}
		}
		return crit.list();
	}

	public List<E> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByProperty(String property, Object value) {
		Assert.hasText(property);
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	@SuppressWarnings("unchecked")
	public E findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		return (E) createCriteria(Restrictions.eq(property, value))
				.uniqueResult();
	}

	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}
	
	
	@SuppressWarnings("unchecked")
	protected Pagination<E> find(Finder finder,int pageNo,int pageSize){
		int totalCount = countQueryResult(finder) ;
		Pagination<E> p = new Pagination<E>(pageNo,pageSize,totalCount) ;
		if(totalCount < 1){
			p.setList(new ArrayList<E>()) ;
			return p ;
		}
		Query query = getSession().createQuery(finder.getOrigHql()) ;
		finder.setParamsToQuery(query) ;
		query.setFirstResult(p.getFirstResult()) ;
		query.setMaxResults(p.getPageSize()) ;
		List<E> list = query.list() ;
		p.setList(list) ;
		return p ;
	}
	
	@SuppressWarnings("unchecked")
	protected List<E> find(Finder finder){
		Query query = getSession().createQuery(finder.getOrigHql()) ;
		finder.setParamsToQuery(query) ;
		query.setFirstResult(finder.getFirstResult()) ;
		if(finder.getMaxResults() > 0){
			query.setMaxResults(finder.getMaxResults()) ;
		}
		List<E> list = query.list() ;
		return list ;
	}
	
	protected int countQueryResult(Finder finder){
		Query query = getSession().createQuery(finder.getRowCountHql()) ;
		finder.setParamsToQuery(query) ;
		return ((Number)query.iterate().next()).intValue() ;
	}

	/**
	 * Hql query By List
	 * 
	 * @param hql
	 * 
	 * @param values
	 * 
	 */
	@SuppressWarnings("rawtypes")
	protected List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * Hql query by unique
	 * 
	 * @param hql
	 * @param values
	 * 
	 */
	protected Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * Help function.
	 * 
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query q = getSession().createQuery(queryString);
		if (null != values && values.length > 0) {
			for (int i = 0, j = values.length; i < j; i++) {
				q.setParameter(i, values[i]);
			}
		}
		return q;
	}

}
