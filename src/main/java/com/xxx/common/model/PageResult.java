package com.xxx.common.model;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description
 * @Author ZJC
 * @Date 2019/4/16 15:26
 */
public class PageResult {

    private int total;

    private List<?> data;

    public PageResult(Page page, List<?> data){

        this.total = page.getTotal();
        this.data = data;
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
}
