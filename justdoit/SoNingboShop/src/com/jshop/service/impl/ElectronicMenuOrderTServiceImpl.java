package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ElectronicMenuOrderTDaoImpl;
import com.jshop.entity.ElectronicMenuOrderT;
import com.jshop.service.ElectronicMenuOrderTService;
@Service("electronicMenuOrderTServiceImpl")
@Scope("prototype")
public class ElectronicMenuOrderTServiceImpl implements ElectronicMenuOrderTService {
	@Resource(name="electronicMenuOrderTDaoImpl")
	private ElectronicMenuOrderTDaoImpl electronicMenuOrderTDaoImpl;
	
	public ElectronicMenuOrderTDaoImpl getElectronicMenuOrderTDaoImpl() {
		return electronicMenuOrderTDaoImpl;
	}

	public void setElectronicMenuOrderTDaoImpl(ElectronicMenuOrderTDaoImpl electronicMenuOrderTDaoImpl) {
		this.electronicMenuOrderTDaoImpl = electronicMenuOrderTDaoImpl;
	}

	public int addElectronicMenuOrderT(ElectronicMenuOrderT eo) {
		return this.getElectronicMenuOrderTDaoImpl().addElectronicMenuOrderT(eo);
	}

	public int countfindAllElectronicMenuOrderT() {
		return this.getElectronicMenuOrderTDaoImpl().countfindAllElectronicMenuOrderT();
	}

	public int countsortAllElectronicMenuOrderTByshippingusername(String shippingusername) {
		return this.getElectronicMenuOrderTDaoImpl().countsortAllElectronicMenuOrderTByshippingusername(shippingusername);
	}

	public int countsortAllTobeShippedElectronicMenuOrderT(String shippingstate) {
		return this.getElectronicMenuOrderTDaoImpl().countsortAllTobeShippedElectronicMenuOrderT(shippingstate);
	}

	public List<ElectronicMenuOrderT> findAllElectronicMenuOrderTBytableNumberandstate(String tableNumber, String tablestate, String electronicorderstate) {
		return this.getElectronicMenuOrderTDaoImpl().findAllElectronicMenuOrderTBytableNumberandstate(tableNumber, tablestate, electronicorderstate);
	}

	public ElectronicMenuOrderT findElectronicMenuOrderTByelectronicMenuOrderid(String electronicMenuOrderid) {
		return this.getElectronicMenuOrderTDaoImpl().findElectronicMenuOrderTByelectronicMenuOrderid(electronicMenuOrderid);
	}

	public List<ElectronicMenuOrderT> sortAllElectronicMenuOrderT(int currentPage, int lineSize, String queryString) {
		return this.getElectronicMenuOrderTDaoImpl().sortAllElectronicMenuOrderT(currentPage, lineSize, queryString);
	}

	public List<ElectronicMenuOrderT> sortAllTobeShippedElectronicMenuOrderT(int currentPage, int lineSize, String shippingstate) {
		return this.getElectronicMenuOrderTDaoImpl().sortAllTobeShippedElectronicMenuOrderT(currentPage, lineSize, shippingstate);
	}

	public void updateElectronicMenuOrderT(ElectronicMenuOrderT eo) {
		this.getElectronicMenuOrderTDaoImpl().updateElectronicMenuOrderT(eo);
	}

	public int updateElectronicMenuOrderTelectronicorderstateByelectronicMenuOrderid(String electronicMenuOrderid, String electronicorderstate) {
		return this.getElectronicMenuOrderTDaoImpl().updateElectronicMenuOrderTelectronicorderstateByelectronicMenuOrderid(electronicMenuOrderid, electronicorderstate);
	}

	public int updateElectronicMenuOrderTpayshippingstate(String electronicMenuOrderid, String electronicorderstate, String paystate, String shippingstate) {
		return this.getElectronicMenuOrderTDaoImpl().updateElectronicMenuOrderTpayshippingstate(electronicMenuOrderid, electronicorderstate, paystate, shippingstate);
	}

	public int updateElectronicMenuOrderTpaystateByelectronicMenuOrderid(String electronicMenuOrderid, String paystate) {
		return this.getElectronicMenuOrderTDaoImpl().updateElectronicMenuOrderTpaystateByelectronicMenuOrderid(electronicMenuOrderid, paystate);
	}

	public int updateElectronicMenuOrderTshippingstateByelectronicMenuOrderid(String electronicMenuOrderid, String shippingstate) {
		return this.getElectronicMenuOrderTDaoImpl().updateElectronicMenuOrderTshippingstateByelectronicMenuOrderid(electronicMenuOrderid, shippingstate);
	}

	public int updateInvoiceByelectronicMenuOrderid(String electronicMenuOrderid, String invoice) {
		return this.getElectronicMenuOrderTDaoImpl().updateInvoiceByelectronicMenuOrderid(electronicMenuOrderid, invoice);
	}

	public int updateexpressnumberByelectronicMenuOrderid(String electronicMenuOrderid, String expressnumber) {
		return this.getElectronicMenuOrderTDaoImpl().updateexpressnumberByelectronicMenuOrderid(electronicMenuOrderid, expressnumber);
	}

}
