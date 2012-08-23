package ningbo.media.core.service.impl;

import java.io.Serializable;
import java.util.List;

import ningbo.media.core.dao.BaseDao;
import ningbo.media.core.service.BaseService;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseServiceImpl<E, PK extends Serializable> implements
		BaseService<E, PK> {
	
	private BaseDao<E,PK> baseDao ;
	
	public BaseServiceImpl(BaseDao<E,PK> baseDao){
		this.baseDao = baseDao ;
		
	}

	public E get(PK id) {
		return baseDao.get(id);
	}

	public E load(PK id) {
		return baseDao.load(id);
	}

	public List<E> get(PK[] ids) {
		return baseDao.get(ids);
	}

	public E get(String propertyName, Object value) {
		return baseDao.get(propertyName, value);
	}

	public List<E> getList(String propertyName, Object value) {
		return baseDao.getList(propertyName, value);
	}

	public List<E> getAll() {
		return baseDao.getAll();
	}
	
	public E saveOrUpdate(E entity){
		return baseDao.saveOrUpdate(entity) ;
	}

	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	public boolean isExist(String propertyName, Object value) {
		return baseDao.isExist(propertyName, value);
	}

	public PK save(E entity) {
		return baseDao.save(entity);
	}

	
	public void update(E entity) {
		baseDao.update(entity) ;
	}

	public void delete(E entity) {
		baseDao.delete(entity) ;
	}

	public void delete(PK id) {
		baseDao.delete(id) ;
	}

	public void delete(PK[] ids) {
		baseDao.delete(ids) ;
	}

	public void flush() {
		baseDao.flush() ;
	}

	public void clear() {
		baseDao.clear() ;
	}

	public void evict(Object object) {
		baseDao.evict(object) ;
	}

	public Object findUnique(String hql, Object... values) {
		return baseDao.findUnique(hql, values);
	}

	public List<E> findByHql(String hql, Object... values) {
		return baseDao.findByHql(hql, values);
	}
	
	public List<E> findByHql(String hql, boolean isLike,Integer limit,Object... values){
		return baseDao.findByHql(hql, isLike,limit, values) ;
	}
	
	public List<String> findAllObject(String hql, Object... values){
		return baseDao.findAllObject(hql, values) ;
	}
	
	

}
