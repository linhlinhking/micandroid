package com.jshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.ProductSpecificationsTDaoImpl;
import com.jshop.entity.ProductSpecificationsT;
import com.jshop.service.ProductSpecificationsTService;

@Service("productSpecificationsTServiceImpl")
@Scope("prototype")
public class ProductSpecificationsTServiceImpl implements ProductSpecificationsTService {
	@Resource(name = "productSpecificationsTDaoImpl")
	private ProductSpecificationsTDaoImpl productSpecificationsTDaoImpl;

	public ProductSpecificationsTDaoImpl getProductSpecificationsTDaoImpl() {
		return productSpecificationsTDaoImpl;
	}

	public void setProductSpecificationsTDaoImpl(ProductSpecificationsTDaoImpl productSpecificationsTDaoImpl) {
		this.productSpecificationsTDaoImpl = productSpecificationsTDaoImpl;
	}

	public void addProductSpecification(ProductSpecificationsT pst) {
		this.getProductSpecificationsTDaoImpl().addProductSpecification(pst);

	}

	public int countfindAllProductSpecificationsT() {
		return this.getProductSpecificationsTDaoImpl().countfindAllProductSpecificationsT();
	}

	public int delProductSpecification(String[] list) {
		return this.getProductSpecificationsTDaoImpl().delProductSpecification(list);
	}

	public List<ProductSpecificationsT> findAllProductSpecificationsT(int currentPage, int lineSize) {
		return this.getProductSpecificationsTDaoImpl().findAllProductSpecificationsT(currentPage, lineSize);
	}

	public ProductSpecificationsT findProductSpecificationsTByspecificationsid(String specificationsid) {
		return this.getProductSpecificationsTDaoImpl().findProductSpecificationsTByspecificationsid(specificationsid);
	}

	public int updateProductSpecification(ProductSpecificationsT pst) {
		return this.getProductSpecificationsTDaoImpl().updateProductSpecification(pst);
	}

	public List<ProductSpecificationsT> findAllProductSpecificationsTWithoutPage() {
		return this.getProductSpecificationsTDaoImpl().findAllProductSpecificationsTWithoutPage();
	}

	public List<ProductSpecificationsT> sortAllProductSpecificationsT(int currentPage, int lineSize, String queryString) {

		return this.getProductSpecificationsTDaoImpl().sortAllProductSpecificationsT(currentPage, lineSize, queryString);
	}
}
