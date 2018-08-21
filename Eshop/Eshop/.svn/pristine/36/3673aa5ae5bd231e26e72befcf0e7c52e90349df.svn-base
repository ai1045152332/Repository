package com.shop.util.bean;

public class PagingBean {
	
    private int totalCount;

    private int pageCount;

    private int currentPage;

    private String prefixUrl;

    private int countPerPage;
    //true的时候是& 否则是？
  	private boolean isAnd;
    
    public PagingBean(int currentPage, int totalCount, int countPerPage) {
    	this.countPerPage = countPerPage;
    	pageCount = (totalCount - 1) / countPerPage + 1;
    	
        if (currentPage >= pageCount) {
        	currentPage = pageCount - 1;
        }
        if (currentPage < 0) {
        	currentPage = 0;
        }

    	this.totalCount = totalCount;
    	this.currentPage = currentPage; 
    }
    
    public PagingBean() {
    	
    }
    
    public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
     * @return Returns the prefixUrl.
     */
    public String getPrefixUrl() {
        return prefixUrl;
    }
    /**
     * @param prefixUrl The prefixUrl to set.
     */
    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }
    /**
     * @return Returns the totalCount.
     */
    public int getTotalCount() {
        return totalCount;
    }
    /**
     * @param totalCount The totalCount to set.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	

	@Override
	public String toString() {
		return "PagingBean [totalCount=" + totalCount + ", totalPageCount="
				+ pageCount + ", currentPage=" + currentPage
				+ ", prefixUrl=" + prefixUrl + ", countPerPage=" + countPerPage
				+ ", isAnd=" + isAnd + "]";
	}

	public boolean isAnd() {
		return isAnd;
	}

	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}
    
}
