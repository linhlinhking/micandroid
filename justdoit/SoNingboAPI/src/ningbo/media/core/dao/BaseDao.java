package ningbo.media.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E, PK extends Serializable> {

	/**
	 * According to PK(id) query object(E)
	 * 
	 * @param id
	 * 
	 * @return E Entity Object
	 * 
	 */
	public E get(PK id);

	/**
	 * According to PK(id) query object(E)
	 * 
	 * @param id
	 * 
	 * @return E Entity Object
	 * 
	 */
	public E load(PK id);

	/**
	 * According to the array of id get the array of object list
	 * 
	 * @param ids
	 * @return collection of entity objects.
	 * 
	 */
	public List<E> get(PK[] ids);

	/**
	 * According to the property of name and the property of value get entity
	 * object.
	 * 
	 * @param propertyName
	 * @param value
	 * 
	 * @return E entity.
	 */
	public E get(String propertyName, Object value);

	
	public List<E> getList(String propertyName, Object value);

	
	public List<E> getAll();

	public Long getTotalCount();

	public boolean isExist(String propertyName, Object value);

	public PK save(E entity);

	public void update(E entity);

	public void delete(E entity);

	public void delete(PK id);

	public void delete(PK[] ids);

	public void flush();

	public void clear();

	public void evict(Object object);

	public Object findUnique(String hql, Object... values);

	public List<E> findByHql(String hql, Object... values);
	
	public List<E> findByHql(String hql, boolean isLike,Integer limit,Object... values);

	public List<String> findAllObject(String hql, Object... values);

}
