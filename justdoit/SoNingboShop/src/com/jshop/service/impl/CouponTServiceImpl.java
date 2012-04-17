package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.CouponTDaoImpl;
import com.jshop.entity.CouponT;
import com.jshop.service.CouponTService;
@Service("couponTServiceImpl")
@Scope("prototype")
public class CouponTServiceImpl implements CouponTService {
	@Resource(name="couponTDaoImpl")
	private CouponTDaoImpl couponTDaoImpl;

	public CouponTDaoImpl getCouponTDaoImpl() {
		return couponTDaoImpl;
	}

	public void setCouponTDaoImpl(CouponTDaoImpl couponTDaoImpl) {
		this.couponTDaoImpl = couponTDaoImpl;
	}

	public int countfindAllCoupon() {
		return this.getCouponTDaoImpl().countfindAllCoupon();
	}

	public List<CouponT> findAllCoupon(int currentPage, int lineSize) {
		return this.getCouponTDaoImpl().findAllCoupon(currentPage, lineSize);
	}

	public void addCouponT(CouponT ct) {
		this.getCouponTDaoImpl().addCouponT(ct);
	}
}
