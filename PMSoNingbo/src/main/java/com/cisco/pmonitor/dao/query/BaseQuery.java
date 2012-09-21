package com.cisco.pmonitor.dao.query;

import java.io.Serializable;

public class BaseQuery implements Serializable {

	private static final long serialVersionUID = -1644929904100754164L;

	private int id;
	private String sort = "id"; // order by what
	private String order = "desc";// desc/asc
	private int page = 0;
	private int rows = 10;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page - 1;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}
