package com.jshop.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.OrderTDaoImpl;
import com.jshop.entity.OrderT;
import com.jshop.service.OrderTService;
@Service("orderTServiceImpl")
@Scope("prototype")
public class OrderTServiceImpl implements OrderTService {
	@Resource(name="orderTDaoImpl")
	private OrderTDaoImpl orderTDaoImpl;

	public OrderTDaoImpl getOrderTDaoImpl() {
		return orderTDaoImpl;
	}

	public void setOrderTDaoImpl(OrderTDaoImpl orderTDaoImpl) {
		this.orderTDaoImpl = orderTDaoImpl;
	}

	public int addOrder(OrderT o) {
		return this.getOrderTDaoImpl().addOrder(o);
	}

	public int countfindAllOrderByorderstate(String userid, String orderstate, String paystate, String shippingstate) {
		return this.getOrderTDaoImpl().countfindAllOrderByorderstate(userid, orderstate, paystate, shippingstate);
	}

	public List<OrderT> findAllOrderByorderstate(int currentPage, int lineSize, String userid, String orderstate, String paystate, String shippingstate) {
		return this.getOrderTDaoImpl().findAllOrderByorderstate(currentPage, lineSize, userid, orderstate, paystate, shippingstate);
	}

	public int delOrderByorderid(String userid, String orderid, String orderstate) {
		return this.getOrderTDaoImpl().delOrderByorderid(userid, orderid, orderstate);
	}

	public List<OrderT> findAllOrderByorderstateForOn(int currentPage, int lineSize, String userid, String orderstate, String paystate, String shippingstate) {
		return this.getOrderTDaoImpl().findAllOrderByorderstateForOn(currentPage, lineSize, userid, orderstate, paystate, shippingstate);
	}

	public int countfindAllOrderByorderstateForOn(String userid, String orderstate, String paystate, String shippingstate) {
		return this.getOrderTDaoImpl().countfindAllOrderByorderstateForOn(userid, orderstate, paystate, shippingstate);
	}

	public OrderT findOrderDetailByorderid(String orderid) {
		return this.getOrderTDaoImpl().findOrderDetailByorderid(orderid);
	}

	public int updateOrder(OrderT o) {
		return this.getOrderTDaoImpl().updateOrder(o);
	}

	public int updateOrderPaystateByorderid(String orderid, String paystate) {
		return this.getOrderTDaoImpl().updateOrderPaystateByorderid(orderid, paystate);
	}

	public List<OrderT> findAllOrderT(int currentPage, int lineSize) {
		return this.getOrderTDaoImpl().findAllOrderT(currentPage, lineSize);
	}

	public int countfindAllOrderT() {
		return this.getOrderTDaoImpl().countfindAllOrderT();
	}

	public int countfindOrderbyOrderid(String orderid) {
		return this.getOrderTDaoImpl().countfindOrderbyOrderid(orderid);
	}

	public List<OrderT> findOrderByOrderid(int currentPage, int lineSize, String orderid) {
		return this.getOrderTDaoImpl().findOrderByOrderid(currentPage, lineSize, orderid);
	}

	public int countfindOrderByShippingUsername(String shippingusername) {
		return this.getOrderTDaoImpl().countfindOrderByShippingUsername(shippingusername);
	}

	public List<OrderT> findOrderByShippingUsername(int currentPage, int lineSize, String shippingusername) {
		return this.getOrderTDaoImpl().findOrderByShippingUsername(currentPage, lineSize, shippingusername);
	}

	public int updateOrderPayShippingState(String orderid, String orderstate, String paystate, String shippingstate) {
		return this.getOrderTDaoImpl().updateOrderPayShippingState(orderid, orderstate, paystate, shippingstate);
	}

	public int countfindAllTobeShippedOrders(String shippingstate) {
		return this.getOrderTDaoImpl().countfindAllTobeShippedOrders(shippingstate);
	}

	public List<OrderT> findAllTobeShippedOrders(int currentPage, int lineSize, String shippingstate) {
		return this.getOrderTDaoImpl().findAllTobeShippedOrders(currentPage, lineSize, shippingstate);
	}

	public int updateExpressnumberByOrderId(String orderid, String expressnumber) {
		return this.getOrderTDaoImpl().updateExpressnumberByOrderId(orderid, expressnumber);
	}

	public int updateInvoicenumberByOrderId(String orderid, String invoicenumber, Date deliverytime) {
		return this.getOrderTDaoImpl().updateInvoicenumberByOrderId(orderid, invoicenumber, deliverytime);
	}

	public int updateInvoiceByOrderId(String orderid, String invoice) {
		return this.getOrderTDaoImpl().updateInvoiceByOrderId(orderid, invoice);
	}

	public int updateOrderShippingstateByorderid(String orderid, String shippingstate) {
		return this.getOrderTDaoImpl().updateOrderShippingstateByorderid(orderid, shippingstate);
	}

	public int updateOrderStateByorderid(String orderid, String orderstate) {
		return this.getOrderTDaoImpl().updateOrderStateByorderid(orderid, orderstate);
	}

	public List<OrderT> sortAllOrderT(int currentPage, int lineSize, String queryString) {

		return this.getOrderTDaoImpl().sortAllOrderT(currentPage, lineSize, queryString);
	}

	public List<OrderT> findAllhaveshippedOrder(int currentPage, int lineSize, String shippingstate) {

		return this.getOrderTDaoImpl().findAllhaveshippedOrder(currentPage, lineSize, shippingstate);
	}

	public int countAllhaveshippedOrder(String shippingstate) {

		return this.getOrderTDaoImpl().countAllhaveshippedOrder(shippingstate);
	}

	public int countAllreturnOrder(String orderstate) {
		return this.getOrderTDaoImpl().countAllreturnOrder(orderstate);
	}

	public List<OrderT> findAllreturnOrder(int currentPage, int lineSize, String orderstate) {
		return this.getOrderTDaoImpl().findAllreturnOrder(currentPage, lineSize, orderstate);
	}
}
