package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ShippingAddressTDaoImpl;
import com.jshop.entity.ShippingAddressT;
import com.jshop.service.ShippingAddressTService;
@Service("shippingAddressTServiceImpl")
@Scope("prototype")
public class ShippingAddressTServiceImpl implements ShippingAddressTService {
	@Resource(name = "shippingAddressTDaoImpl")
	private ShippingAddressTDaoImpl shippingAddressTDaoImpl;
	
	public ShippingAddressTDaoImpl getShippingAddressTDaoImpl() {
		return shippingAddressTDaoImpl;
	}

	public void setShippingAddressTDaoImpl(ShippingAddressTDaoImpl shippingAddressTDaoImpl) {
		this.shippingAddressTDaoImpl = shippingAddressTDaoImpl;
	}

	public int addShoppingAddress(ShippingAddressT s) {
		return this.getShippingAddressTDaoImpl().addShoppingAddress(s);
	}

	public List<ShippingAddressT> findShippingAddressByDeliveraddressidAndstate(String deliveraddressid, String state, String orderid) {
		return this.getShippingAddressTDaoImpl().findShippingAddressByDeliveraddressidAndstate(deliveraddressid, state, orderid);
	}

	public List<ShippingAddressT> findShippingAddressByIdAndState(String shippingaddressid, String state) {
		return this.getShippingAddressTDaoImpl().findShippingAddressByIdAndState(shippingaddressid, state);
	}

	public int updateShippingAddressByorderandstate(String orderid, String state) {
		return this.getShippingAddressTDaoImpl().updateShippingAddressByorderandstate(orderid, state);
	}

	public ShippingAddressT findShippingAddressByOrderid(String orderid, String state) {
		return this.getShippingAddressTDaoImpl().findShippingAddressByOrderid(orderid, state);
	}
}
