package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.InvoicetempleteTDaoImpl;
import com.jshop.entity.InvoicetempleteT;
import com.jshop.service.InvoicetempleteTService;
@Service("invoicetempleteTServiceImpl")
@Scope("prototype")
public class InvoicetempleteTServiceImpl implements InvoicetempleteTService {
	@Resource(name="invoicetempleteTDaoImpl")
	private InvoicetempleteTDaoImpl invoicetempleteTDaoImpl;

	
	public InvoicetempleteTDaoImpl getInvoicetempleteTDaoImpl() {
		return invoicetempleteTDaoImpl;
	}

	public void setInvoicetempleteTDaoImpl(InvoicetempleteTDaoImpl invoicetempleteTDaoImpl) {
		this.invoicetempleteTDaoImpl = invoicetempleteTDaoImpl;
	}

	public int delInvoiceTemplete(String[] list) {
		return this.getInvoicetempleteTDaoImpl().delInvoiceTemplete(list);
	}

	public int updateInvoicetempleteT(InvoicetempleteT it) {
		return this.getInvoicetempleteTDaoImpl().updateInvoicetempleteT(it);
	}

	public int addInvoiceTemplete(InvoicetempleteT it) {
		return this.getInvoicetempleteTDaoImpl().addInvoiceTemplete(it);
	}

	public int countfindAllInvoicetempleteT() {
		return this.getInvoicetempleteTDaoImpl().countfindAllInvoicetempleteT();
	}

	public List<InvoicetempleteT> findAllInvoicetempleteT(int currentPage, int lineSize) {
		return this.getInvoicetempleteTDaoImpl().findAllInvoicetempleteT(currentPage, lineSize);
	}

	public InvoicetempleteT findInvoicetempleteById(String invoicetempleteid) {
		return this.getInvoicetempleteTDaoImpl().findInvoicetempleteById(invoicetempleteid);
	}

	public InvoicetempleteT findInvoicetempleteByState(String state) {
		return this.getInvoicetempleteTDaoImpl().findInvoicetempleteByState(state);
	}
}
