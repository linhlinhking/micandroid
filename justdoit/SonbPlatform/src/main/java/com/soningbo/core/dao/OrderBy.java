package com.soningbo.core.dao;

import org.hibernate.criterion.Order;


public class OrderBy extends Condition {

	private static final long serialVersionUID = 4286642347068377505L;

	private OrderType orderType;

	protected OrderBy(String field, OrderType orderType) {
		this.field = field;
		this.orderType = orderType;
	}

	public static OrderBy asc(String field) {
		return new OrderBy(field, OrderType.ASC);
	}

	public static OrderBy desc(String field) {
		return new OrderBy(field, OrderType.DESC);
	}

	public Order getOrder() {
		Order order = null;
		if (OrderType.ASC == orderType) {
			order = Order.asc(getField());
		} else {
			order = Order.desc(getField());
		}
		return order;
	}

	public static Order[] asOrders(OrderBy[] orderBys){
		if(null != orderBys && orderBys.length >0){
			Order[] orders = new Order[orderBys.length];
			for(int i=0,j = orderBys.length;i<j;i++){
				orders[i] = orderBys[i].getOrder() ;
			}
			return orders ;
		}else{
			return null ;
		}
	}

	public static enum OrderType {
		ASC, DESC
	}

}
