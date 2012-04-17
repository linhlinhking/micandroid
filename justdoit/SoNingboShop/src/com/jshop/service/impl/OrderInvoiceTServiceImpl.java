package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.OrderInvoiceTDaoImpl;
import com.jshop.entity.OrderInvoiceT;
import com.jshop.service.OrderInvoiceTService;
@Service("orderInvoiceTServiceImpl")
@Scope("prototype")
public class OrderInvoiceTServiceImpl implements OrderInvoiceTService {
	@Resource(name="orderInvoiceTDaoImpl")
	private OrderInvoiceTDaoImpl orderInvoiceTDaoImpl;

	
	public OrderInvoiceTDaoImpl getOrderInvoiceTDaoImpl() {
		return orderInvoiceTDaoImpl;
	}

	public void setOrderInvoiceTDaoImpl(OrderInvoiceTDaoImpl orderInvoiceTDaoImpl) {
		this.orderInvoiceTDaoImpl = orderInvoiceTDaoImpl;
	}

	public int delOrderInvoice(String[] list) {
		return this.getOrderInvoiceTDaoImpl().delOrderInvoice(list);
	}

	public int updateOrderInvoiceState(String[] orderinvoiceid, String state) {
		return this.getOrderInvoiceTDaoImpl().updateOrderInvoiceState(orderinvoiceid, state);
	}

	public int addOrderInvoice(OrderInvoiceT oi) {
		return this.getOrderInvoiceTDaoImpl().addOrderInvoice(oi);
	}

	public int countfindAllOrderIvoice() {
		return this.getOrderInvoiceTDaoImpl().countfindAllOrderIvoice();
	}

	public List<OrderInvoiceT> findAllOrderIvoice(int currentPage, int lineSize) {
		return this.getOrderInvoiceTDaoImpl().findAllOrderIvoice(currentPage, lineSize);
	}
}
