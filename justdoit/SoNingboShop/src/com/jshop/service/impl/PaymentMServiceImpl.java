package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.PaymentMDao;
import com.jshop.dao.impl.PaymentMDaoImpl;
import com.jshop.entity.PaymentM;
import com.jshop.service.PaymentMService;
@Service("paymentMServiceImpl")
@Scope("prototype")
public class PaymentMServiceImpl implements PaymentMService {
	@Resource(name="paymentMDaoImpl")
	private PaymentMDaoImpl paymentMDaoImpl;


	public PaymentMDaoImpl getPaymentMDaoImpl() {
		return paymentMDaoImpl;
	}

	public void setPaymentMDaoImpl(PaymentMDaoImpl paymentMDaoImpl) {
		this.paymentMDaoImpl = paymentMDaoImpl;
	}

	public int openPayment(String[] list) {
		return this.getPaymentMDaoImpl().openPayment(list);
	}

	public int updatePayment(PaymentM pm) {
		return this.getPaymentMDaoImpl().updatePayment(pm);
	}

	public int addPayment(PaymentM pm) {
		return this.getPaymentMDaoImpl().addPayment(pm);
	}

	public int countfindAllPayment() {
		return this.getPaymentMDaoImpl().countfindAllPayment();
	}

	public List<PaymentM> findAllPayment(int currentPage, int lineSize) {
		return this.getPaymentMDaoImpl().findAllPayment(currentPage, lineSize);
	}

	public PaymentM findPaymentbyId(String paymentid) {
		return this.getPaymentMDaoImpl().findPaymentbyId(paymentid);
	}

	public int closePayment(String[] list) {
		return this.getPaymentMDaoImpl().closePayment(list);
	}

	public List<PaymentM> findAllPaymentWithoutPage() {
		return this.getPaymentMDaoImpl().findAllPaymentWithoutPage();
	}
}
