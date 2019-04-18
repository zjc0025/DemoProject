package com.xxx.common.model;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description
 * @Author ZJC
 * @Date 2019/4/16 15:26
 */
public class PageResult {

    private int pageIndex;

    private int pageSize;

    private int total;

    private List<?> data;

    public PageResult(Page page, List<?> data){
        this.pageSize = page.getPageSize();
        this.pageIndex = page.getPageIndex();
        this.total = page.getTotal();
        int dataSize = data.size();
        if(dataSize <= pageSize){
            this.data = data;
        }else {
            int startIndex = pageSize * pageIndex;
            int endIndex = (pageSize * pageIndex + pageSize - 1)>=dataSize?dataSize:(pageSize * pageIndex + pageSize);
            this.data = data.subList(startIndex, endIndex);
        }

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
