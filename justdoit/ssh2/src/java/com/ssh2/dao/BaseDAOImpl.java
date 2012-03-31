package com.ssh2.dao;

import com.ssh2.DAOException;
import com.ssh2.dao.BaseDAO;
import com.ssh2.utils.PaginationSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author JeccyZhao
 *
 * @param <T>
 */
public abstract class BaseDAOImpl<T> extends HibernateDaoSupport implements BaseDAO<T> {
	
	private final static Logger logger = Logger.getLogger(BaseDAOImpl.class);
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDAOImpl(){
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(T domain) throws DAOException{
		saveOrUpdate(domain, true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#merge(java.lang.Object)
	 */
	public void merge(T domain) throws DAOException{
		merge(domain, true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#merge(java.lang.Object, boolean)
	 */
	public void merge(T domain, boolean update) throws DAOException{
		if(update){
			if(domain != null){
				getHibernateTemplate().merge(domain);
			}
		}else{
			logger.info("fake saveOrUpdate to clear the caches");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(T domain, boolean update) throws DAOException {
		if(update){
			if(domain != null){
				getHibernateTemplate().saveOrUpdate(domain);
			}
		}else{
			logger.info("fake saveOrUpdate to clear the caches");
		}
	}
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(PaginationSupport<T> domain, boolean update) throws DAOException {
		if(!update){
			logger.info("fake saveOrUpdate to clear the caches");
		}
	}
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(List<T> domain, boolean update) throws DAOException {
		if(!update){
			logger.info("fake saveOrUpdate to clear the caches");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#saveOrUpdateAll(java.util.List)
	 */
	public void saveOrUpdateAll(List<T> domain) throws DAOException {
		if(domain != null && domain.size() > 0){
			getHibernateTemplate().saveOrUpdateAll(domain);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#batchSaveOrUpdate(java.util.List)
	 */
	public void batchSaveOrUpdate(List<T> models) throws DAOException {
		if(models != null && models.size() > 0){
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Transaction tx = null;
			tx = session.beginTransaction();
			for(int i = 0, size = models.size(); i < size; i++){
				session.save(models.get(i));
				if(i % 25 == 0){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#remove(java.lang.Object)
	 */
	public void remove(T domain) throws DAOException{
		if(domain != null){
			getHibernateTemplate().delete(domain);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#get(java.io.Serializable)
	 */
	public T get(Serializable id) throws DAOException{
		return id != null ? (T)getHibernateTemplate().get(entityClass, id) : null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mary.dao.common.BaseDAO#findListByNativeSQL(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List findListByNativeSQL(String nativeSql) throws DAOException{
		return getSession().createSQLQuery(nativeSql).list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mary.dao.common.BaseDAO#findListByNativeSQL(java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List findListByNativeSQL(String nativeSql,Class claz) throws DAOException{
		return getSession().createSQLQuery(nativeSql).addEntity(claz).list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#findListByNativeSQL(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List findListByNativeSQL(String nativeSql,Map<String, Class> entities) throws DAOException{
		SQLQuery query = getSession().createSQLQuery(nativeSql);
		for(Map.Entry<String, Class> entry : entities.entrySet()){
			query.addEntity(entry.getKey(), entry.getValue());
		}
		return query.list();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.mary.dao.common.BaseDAO#getListByHSQL(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListByHSQL(String hsql) throws DAOException{
		return (List<T>)getHibernateTemplate().find(hsql);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mary.dao.common.BaseDAO#findListByNamedQuery(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListByNamedQuery(String query) throws DAOException{
		return (List<T>)getHibernateTemplate().findByNamedQuery(query);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mary.dao.common.BaseDAO#execUpdateByNativeSQL(java.lang.String)
	 */
	public int execUpdateByNativeSQL(String nativeSql) throws DAOException{
		return getSession().createSQLQuery(nativeSql).executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ims.mary.dao.BaseDAO#execUpdateByHSQL(java.lang.String)
	 */
	public int execUpdateByHSQL(String hsql) throws DAOException{
		return getSession().createSQLQuery(hsql).executeUpdate();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#getListByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings("unchecked")
	public List getListByCriteria(DetachedCriteria criteria) throws DAOException{
		return (List<T>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#getListByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List getListByCriteria(
			DetachedCriteria criteria,int firstResult,int maxResults) throws DAOException{
		return (List<T>)getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.mali.dao.common.BaseDAO#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@SuppressWarnings("unchecked")
	public PaginationSupport<T> findPageByCriteria(
			final DetachedCriteria criteria,
			final int pageSize,
			final int startIndex) throws DAOException{
		return (PaginationSupport<T>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria execCriteria = criteria.getExecutableCriteria(session);
				int rowCount = ((Long)execCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				execCriteria.setProjection(null);
				execCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				execCriteria.setFirstResult(startIndex);
				if(pageSize > 0){
					execCriteria.setMaxResults(pageSize);	
				}else{
					execCriteria.setMaxResults(rowCount);
				}
				List<T> items = execCriteria.list();
				return rowCount > 0 ? new PaginationSupport<T>(items, rowCount, startIndex, 
						pageSize >0 ? pageSize : rowCount) : null;
			}
		});
	}

	public static Logger getLogger() {
		return logger;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByPage(int start, int pageSize, String hql,
			String orderBy, String orderType){
		List<T> result = null;
		
		if (orderBy != null && orderType != null) {
			hql = hql + " order by " + orderBy + " " + orderType;
		}else{
			hql = hql + " order by id desc" ;
		}
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		result = query.list();
		return result;
	}

	
	public int getCount(String hql){
		String h = "select count(*) " + hql;
		Integer count = new Integer(getHibernateTemplate().iterate(h).next().toString());
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findByHQL(String hql){
		return this.getHibernateTemplate().find(hql);
	}
	
	
	@SuppressWarnings("unchecked")
	public T uniqueResult(String hql){
		Session session = getHibernateTemplate().getSessionFactory()
		.getCurrentSession();
		Query query=session.createQuery(hql);
		return (T)query.uniqueResult();
	}
}
