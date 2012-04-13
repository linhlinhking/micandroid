package com.salesmanager.core.entity.catalog;

import java.io.Serializable;
import java.util.Date;

/**
 * Object used in JSON requests
 * @author Carl Samson
 *
 */
public class ReviewVO implements Serializable {
	
	private long reviewId;
	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(Integer reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Integer reviewRating;
	private String customerName;
	private String productName;
	private Date dateAdded;
	
	private String description;

}
