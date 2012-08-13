package com.soningbo.core.dao;

import java.util.HashSet;
import java.util.Set;

public class Updater {

	private Object bean;

	private Set<String> includeProperties = new HashSet<String>();

	private Set<String> excludeProperties = new HashSet<String>();

	private UpdateMode mode = UpdateMode.MIDDLE;

	protected Updater(Object bean) {
		this.bean = bean;
	}

	/**
	 * 创建更新对象
	 * 
	 * @param bean
	 * @return
	 */
	public static Updater create(Object bean) {
		return new Updater(bean);
	}

	/**
	 * 创建更新对象
	 * 
	 * @param bean
	 * @param mode
	 * @return
	 */
	public static Updater create(Object bean, UpdateMode mode) {
		Updater updater = new Updater(bean);
		updater.setUpdateMode(mode);
		return updater;
	}

	/**
	 * 设置更新模式
	 * 
	 * @param mode
	 * @return
	 */
	public Updater setUpdateMode(UpdateMode mode) {
		this.mode = mode;
		return this;
	}
	
	/**
	 * 必须更新的字段
	 * 
	 * @param property
	 * @return
	 */
	public Updater include(String property) {
		includeProperties.add(property);
		return this;
	}

	/**
	 * 不更新的字段
	 * 
	 * @param property
	 * @return
	 */
	public Updater exclude(String property) {
		excludeProperties.add(property);
		return this;
	}
	
	/**
	 * 某一字段是否更新
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值。用于检查是否为NULL
	 * @return
	 */
	public boolean isUpdate(String name, Object value) {
		if (this.mode == UpdateMode.MAX) {
			return !excludeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIN) {
			return includeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIDDLE) {
			if (value != null) {
				return !excludeProperties.contains(name);
			} else {
				return includeProperties.contains(name);
			}
		} else {
			// never reach
		}
		return true;
	}

	public Object getBean() {
		return bean;
	}

	public Set<String> getIncludeProperties() {
		return includeProperties;
	}

	public Set<String> getExcludeProperties() {
		return excludeProperties;
	}

	public static enum UpdateMode {
		MAX, MIN, MIDDLE
	}

}
