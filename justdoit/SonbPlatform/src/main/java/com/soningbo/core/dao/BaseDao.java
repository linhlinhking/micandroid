package com.soningbo.core.dao;

import java.io.Serializable;
import java.util.List;

import com.soningbo.core.page.Pagination;

public interface BaseDao<E,PK extends Serializable> {
	/**
	 * 通过ID查找对象
	 * 
	 * @param id
	 *            记录的ID
	 * @param lock
	 *            是否锁定对象
	 * @return 实体对象
	 */
	public E load(PK id, boolean lock);
	

	public E get(PK id);

	/**
	 * 通过ID查找对象,不锁定对象
	 * 
	 * @param id
	 *            记录的ID
	 * @return 实体对象
	 */
	public E load(PK id);

	/**
	 * 查找所有对象
	 * 
	 * @return 对象列表
	 */
	public List<E> findAll();

	public List<E> findAll(OrderBy... orders);

	public Pagination<E> findAll(int pageNo, int pageSize, OrderBy... orders);

	/**
	 * 通过示例对象查找对象列表
	 * 
	 * @param eg
	 *            示例对象
	 * @param anyWhere
	 *            是否模糊查询，默认false。
	 * @param conds
	 *            排序和is null的字段。分别为OrderBy和String。
	 * @param exclude
	 *            需要排除的属性
	 * @return 对象列表
	 */
	public List<E> findByEgList(E eg, boolean anyWhere, Condition[] conds,
			String... exclude);

	public List<E> findByEgList(E eg, boolean anyWhere, Condition[] conds,
			int firstResult, int maxResult, String... exclude);

	public Pagination<E> findByEg(E exampleInstance, boolean anyWhere,
			Condition[] conds, int pageNo, int pageSize, String... exclude);

	/**
	 * 按属性查找对象列表.
	 */
	public List<E> findByProperty(String property, Object value);

	/**
	 * 按属性查找唯一对象.
	 */
	public E findUniqueByProperty(String property, Object value);

	/**
	 * 按属性查找对象的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value);

	/**
	 * 根据Updater更新对象
	 * 
	 * @param updater
	 * @return 持久化对象
	 */
	public Object updateByUpdater(Updater updater);

	public Object updateDefault(Object entity);

	/**
	 * 保存对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public E save(E entity);

	/**
	 * 更新对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public Object update(Object entity);

	/**
	 * 保存或更新对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	public Object saveOrUpdate(Object entity);

	/**
	 * 保存或更新对象拷贝
	 * 
	 * @param entity
	 * @return 已更新的持久化对象
	 */
	public Object merge(Object entity);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void delete(Object entity);

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 *            记录ID
	 */
	public E deleteById(PK id);

	/**
	 * 刷新对象
	 * 
	 * @param entity
	 */
	public void refresh(Object entity);

	/**
	 * 获得实体Class
	 * 
	 * @return
	 */
	public Class<E> getPersistentClass();

	/**
	 * 创建实体类的对象
	 * 
	 * @return
	 */
	public E createNewEntiey();
}
