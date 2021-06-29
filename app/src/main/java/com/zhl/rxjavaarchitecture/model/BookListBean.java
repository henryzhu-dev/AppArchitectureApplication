package com.zhl.rxjavaarchitecture.model;

import java.util.List;

/**
 * author : zhuhl
 * date   : 2021/6/24
 * desc   :
 */
public class BookListBean {

    public int pageNum;
    private int pageSize;
    private int total;

    public List<BookBean> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BookBean> getList() {
        return list;
    }

    public void setList(List<BookBean> list) {
        this.list = list;
    }
}
