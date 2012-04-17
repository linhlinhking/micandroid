package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.DeliverAddressTDaoImpl;
import com.jshop.entity.DeliverAddressT;
import com.jshop.service.DeliverAddressTService;
@Service("deliverAddressTServiceImpl")
@Scope("prototype")
public class DeliverAddressTServiceImpl implements DeliverAddressTService {
	@Resource(name="deliverAddressTDaoImpl")
	private DeliverAddressTDaoImpl deliverAddressTDaoImpl;
	

	public DeliverAddressTDaoImpl getDeliverAddressTDaoImpl() {
		return deliverAddressTDaoImpl;
	}

	public void setDeliverAddressTDaoImpl(DeliverAddressTDaoImpl deliverAddressTDaoImpl) {
		this.deliverAddressTDaoImpl = deliverAddressTDaoImpl;
	}

	public int delDeliverAddress(String[] list) {
		return this.getDeliverAddressTDaoImpl().delDeliverAddress(list);
	}

	public int updateDeliverAddress(DeliverAddressT d) {
		return this.getDeliverAddressTDaoImpl().updateDeliverAddress(d);
	}

	public int addDeliverAddress(DeliverAddressT d) {
		return this.getDeliverAddressTDaoImpl().addDeliverAddress(d);
	}

	public List<DeliverAddressT> findAllDeliverAddress() {
		return this.getDeliverAddressTDaoImpl().findAllDeliverAddress();
	}

	public List<DeliverAddressT> findDeliverAddressByuserid(String userid) {
		return this.getDeliverAddressTDaoImpl().findDeliverAddressByuserid(userid);
	}

	public DeliverAddressT findDeliverAddressById(String addressid) {
		return this.getDeliverAddressTDaoImpl().findDeliverAddressById(addressid);
	}
}
