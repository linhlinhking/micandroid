package com.salesmanager.catalog.product;

/**
 * Entity used for Ajax request. This is a simplification of 
 * com.salesmanager.core.entity.catalog.Product
 * @author Carl Samson
 *
 */
public class Product {
	
	private long productId;
	
	private String price;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductAttribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(ProductAttribute[] attributes) {
		this.attributes = attributes;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	private int quantity;
	
	private ProductAttribute[] attributes;
	

}
