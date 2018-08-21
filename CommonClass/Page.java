package com.honghe.recordhibernate.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chby on 2014/9/25.
 */
public class Page<T> {

    private int totalPageSize;

    private int currentPageSize;


    public Page(int currentPageSize, int pageSize) {
        this.currentPageSize = currentPageSize;
        this.pageSize = pageSize;
    }

    public Page(int currentPageSize) {
        this(currentPageSize, 10);
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalRecordCount) {
        this.totalPageSize = (totalRecordCount + this.pageSize - 1) / this.pageSize;
    }

    public int getFirstResult() {
        return (currentPageSize - 1) * pageSize;
    }


    public int getPageSize() {
        return pageSize;
    }


    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    private int pageSize;
    private List<T> result = new ArrayList<T>();

}
