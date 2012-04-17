package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.BrandTDaoImpl;
import com.jshop.entity.BrandT;
import com.jshop.service.BrandTService;
@Service("brandTServiceImpl")
@Scope("prototype")
public class BrandTServiceImpl implements BrandTService {
	@Resource(name="brandTDaoImpl")
	private BrandTDaoImpl brandTDaoImpl;


	public BrandTDaoImpl getBrandTDaoImpl() {
		return brandTDaoImpl;
	}

	public void setBrandTDaoImpl(BrandTDaoImpl brandTDaoImpl) {
		this.brandTDaoImpl = brandTDaoImpl;
	}

	public int delBrandt(String[] list, String creatorid) {
		return this.getBrandTDaoImpl().delBrandt(list, creatorid);
	}

	public void updateBrandt(BrandT bt) {
		this.getBrandTDaoImpl().updateBrandt(bt);
	}

	public int addBrandt(BrandT bt) {
		return this.getBrandTDaoImpl().addBrandt(bt);
	}

	public int countfindAllBrandt(String creatorid) {
		return this.getBrandTDaoImpl().countfindAllBrandt(creatorid);
	}

	public List<BrandT> findAllBrandt(int currentPage, int lineSize, String creatorid) {
		return this.getBrandTDaoImpl().findAllBrandt(currentPage, lineSize, creatorid);
	}

	public BrandT findBrandById(String brandid) {
		return this.getBrandTDaoImpl().findBrandById(brandid);
	}

	public List<BrandT> findAllBrandtjson(String creatorid) {
		return this.getBrandTDaoImpl().findAllBrandtjson(creatorid);
	}

	public List<BrandT> sortAllBrandt(int currentPage, int lineSize, String creatorid, String queryString) {

		return this.getBrandTDaoImpl().sortAllBrandt(currentPage, lineSize, creatorid, queryString);
	}
}
