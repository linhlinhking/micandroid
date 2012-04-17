package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.VouchersTDaoImpl;
import com.jshop.entity.VouchersT;
import com.jshop.service.VouchersTService;
@Service("vouchersTServiceImpl")
@Scope("prototype")
public class VouchersTServiceImpl implements VouchersTService {
	@Resource(name="vouchersTDaoImpl")
	private VouchersTDaoImpl vouchersTDaoImpl;

	
	public VouchersTDaoImpl getVouchersTDaoImpl() {
		return vouchersTDaoImpl;
	}

	public void setVouchersTDaoImpl(VouchersTDaoImpl vouchersTDaoImpl) {
		this.vouchersTDaoImpl = vouchersTDaoImpl;
	}

	public int delVoucherst(String[] list) {
		return this.getVouchersTDaoImpl().delVoucherst(list);
	}

	public int updateVoucherst(VouchersT vt) {
		return this.getVouchersTDaoImpl().updateVoucherst(vt);
	}

	public int addVoucherst(VouchersT vt) {
		return this.getVouchersTDaoImpl().addVoucherst(vt);
	}

	public int countfindAllVoucherst() {
		return this.getVouchersTDaoImpl().countfindAllVoucherst();
	}

	public List<VouchersT> findAllVoucherst(int currentPage, int lineSize) {
		return this.getVouchersTDaoImpl().findAllVoucherst(currentPage, lineSize);
	}

	public List<VouchersT> findVoucherstByName(String vouchersname) {
		return this.getVouchersTDaoImpl().findVoucherstByName(vouchersname);
	}

	public VouchersT findVouchersForHonor(String vouchersname) {
		return this.getVouchersTDaoImpl().findVouchersForHonor(vouchersname);
	}

	public List<VouchersT> findUserVouchers(String userid, int currentPage, int lineSize) {
		return this.getVouchersTDaoImpl().findUserVouchers(userid, currentPage, lineSize);
	}

	public int countfindUserVouchers(String userid) {
		return this.getVouchersTDaoImpl().countfindUserVouchers(userid);
	}
}
