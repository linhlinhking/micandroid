package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ElectronicMenuCartTDaoImpl;
import com.jshop.entity.ElectronicMenuCartT;
import com.jshop.service.ElectronicMenuCartTService;
@Service("electronicMenuCartTServiceImpl")
@Scope("prototype")
public class ElectronicMenuCartTServiceImpl implements ElectronicMenuCartTService {
	@Resource(name="electronicMenuCartTDaoImpl")
	private ElectronicMenuCartTDaoImpl electronicMenuCartTDaoImpl;
	
	public ElectronicMenuCartTDaoImpl getElectronicMenuCartTDaoImpl() {
		return electronicMenuCartTDaoImpl;
	}

	public void setElectronicMenuCartTDaoImpl(ElectronicMenuCartTDaoImpl electronicMenuCartTDaoImpl) {
		this.electronicMenuCartTDaoImpl = electronicMenuCartTDaoImpl;
	}

	public int addElectronicMenuCartT(ElectronicMenuCartT ec) {
		return this.getElectronicMenuCartTDaoImpl().addElectronicMenuCartT(ec);
	}

	public int countfindAllElectronicMenuCartT() {
		return this.getElectronicMenuCartTDaoImpl().countfindAllElectronicMenuCartT();
	}

	public int delElectronicMenuCartTGoods(String tableNumber, String tablestate, String goodsid, String state) {
		return this.getElectronicMenuCartTDaoImpl().delElectronicMenuCartTGoods(tableNumber, tablestate, goodsid, state);	
	}

	public List<ElectronicMenuCartT> findAllElectronicMenuCartT() {
		return this.getElectronicMenuCartTDaoImpl().findAllElectronicMenuCartT();
	}

	public List<ElectronicMenuCartT> findAllElectronicMenuCartTBytableNumber(String tableNumber, String tablestate) {
		return this.getElectronicMenuCartTDaoImpl().findAllElectronicMenuCartTBytableNumber(tableNumber, tablestate);
	}

	public List<ElectronicMenuCartT> findAllElectronicMenuCartTByusername(String username) {
		return this.getElectronicMenuCartTDaoImpl().findAllElectronicMenuCartTByusername(username);
	}

	public List<ElectronicMenuCartT> findElectronicMenuCartTByelectronicMenuOrderid(String electronicMenuOrderid, String state) {
		return this.getElectronicMenuCartTDaoImpl().findElectronicMenuCartTByelectronicMenuOrderid(electronicMenuOrderid, state);
	}

	public List<ElectronicMenuCartT> findGoodsInElectronicMenuCartTOrNot(String tableNumber, String tablestate, String goodsid, String state) {
		return this.getElectronicMenuCartTDaoImpl().findGoodsInElectronicMenuCartTOrNot(tableNumber, tablestate, goodsid, state);
	}

	public int updateElectronicMenuCartTelectronicMenuCartid(String electronicMenuCartid, String state, String tableNumber, String tablestate) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTelectronicMenuCartid(electronicMenuCartid, state, tableNumber, tablestate);
	}

	public int updateElectronicMenuCartTneedquantityBygoodsid(String tableNumber, String tablestate, String goodsid, int needquantity, String state) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTneedquantityBygoodsid(tableNumber, tablestate, goodsid, needquantity, state);
	}

	public int updateElectronicMenuCartTstate(String tableNumber, String tablestate, String goodsid, String state) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTstate(tableNumber, tablestate, goodsid, state);
	}

	public int updateElectronicMenuCartTstate(String tableNumber, String tablestate, String electronicMenuOrderid, String state, String electronicMenuCartid) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTstate(tableNumber, tablestate, electronicMenuOrderid, state, electronicMenuCartid);
	}

	public int updateElectronicMenuCartTsubtotal(String tableNumber, String tablestate, String goodsid, double subtotal) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTsubtotal(tableNumber, tablestate, goodsid, subtotal);
	}

	public int updateElectronicMenuCartTtablestate(String electronicMenuOrderid, String tablestate) {
		return this.getElectronicMenuCartTDaoImpl().updateElectronicMenuCartTtablestate(electronicMenuOrderid, tablestate);
	}

}
