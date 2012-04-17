package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.ProductTDao;
import com.jshop.dao.impl.ProductTDaoImpl;
import com.jshop.entity.ProductT;
@Service("productTServiceImpl")
@Scope("prototype")
public class ProductTServiceImpl implements ProductTDao {
	@Resource(name = "productTDaoImpl")
	private ProductTDaoImpl productTDaoImpl;
	
	public ProductTDaoImpl getProductTDaoImpl() {
		return productTDaoImpl;
	}

	public void setProductTDaoImpl(ProductTDaoImpl productTDaoImpl) {
		this.productTDaoImpl = productTDaoImpl;
	}

	public int addProductT(ProductT pt) {
		return this.getProductTDaoImpl().addProductT(pt);
	}

	public int countfindAllProductT(String creatorid) {
		return this.getProductTDaoImpl().countfindAllProductT(creatorid);
	}

	public List<ProductT> findAllProductT(int currentPage, int lineSize, String creatorid) {
		return this.getProductTDaoImpl().findAllProductT(currentPage, lineSize, creatorid);
	}

	public List<ProductT> findAllProductTByGoodsid(String creatorid, String goodsid) {
		return this.getProductTDaoImpl().findAllProductTByGoodsid(creatorid, goodsid);
	}

	public int updateProductT(ProductT pt) {
		return this.getProductTDaoImpl().updateProductT(pt);
	}

	public List<ProductT> findProductTByproductid(String creatorid, String productid) {
		return this.getProductTDaoImpl().findProductTByproductid(creatorid, productid);
	}

	public int delProductTBygoodsid(String goodsid, String creatorid) {
		return this.getProductTDaoImpl().delProductTBygoodsid(goodsid, creatorid);
	}

	public List<ProductT> sortAllProductT(int currentPage, int lineSize, String creatorid, String queryString) {

		return this.getProductTDaoImpl().sortAllProductT(currentPage, lineSize, creatorid, queryString);
	}
}
