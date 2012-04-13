package com.salesmanager.catalog.common;

public class PageCount {
	

	//set either page count  (pageStartIndex) or current cursor (pageStartCount)
	private int pageStartIndex = 0;// page number -- pagination (0-1-2-3)
	private int pageStartCount = 0;// current count item
	
	
	private int pageCriteriaIndex = 0;// for criteria
	private int listingCount = 0;// total number of items
	private int realCount = 0;// total number in the current page
	private int size = 20;// default
	private int firstItem = 1;
	private int lastItem = 0;

	public int getPageStartIndex() {
		return pageStartIndex;
	}

	public void setPageStartIndex(int pageStartIndex) {
		this.pageStartIndex = pageStartIndex;
	}

	public int getListingCount() {
		return listingCount;
	}

	public void setListingCount(int listingCount) {
		this.listingCount = listingCount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(int firstItem) {
		this.firstItem = firstItem;
	}

	public int getLastItem() {
		return lastItem;
	}

	public void setLastItem(int lastItem) {
		this.lastItem = lastItem;
	}

	public void processStartNumber() {

		//determine if we use page cont or cursor
		
		if(this.getPageStartCount()>0) {
		
			int start = this.getPageStartCount();
			this.setPageCriteriaIndex(start);
			
			
		} else {
		
			int start = this.getPageStartIndex();
			if (this.getPageStartIndex() == 0) {
				start = 0;
			} else {
				start = start * this.getSize();
			}
			this.setPageCriteriaIndex(start);
			
		}
	}

	public void setPageElements() {

		if (getListingCount() == 0) {
			if (this.getListingCount() == 0) {
				firstItem = 0;
			}
			this.setFirstItem(firstItem);

			this.setLastItem(listingCount);
		} else {
			if (this.getPageStartIndex() == 0) {
				this.setFirstItem(firstItem);
			} else {

				this.setFirstItem(this.getPageCriteriaIndex() + 1);

			}
			this.setLastItem(this.getPageCriteriaIndex() + this.getRealCount());

		}
	}

	public int getRealCount() {
		return realCount;
	}

	public void setRealCount(int realCount) {
		this.realCount = realCount;
	}

	public int getPageCriteriaIndex() {
		return pageCriteriaIndex;
	}

	public void setPageCriteriaIndex(int pageCriteriaIndex) {
		this.pageCriteriaIndex = pageCriteriaIndex;
	}

	public int getPageStartCount() {
		return pageStartCount;
	}

	public void setPageStartCount(int pageStartCount) {
		this.pageStartCount = pageStartCount;
	}


}
