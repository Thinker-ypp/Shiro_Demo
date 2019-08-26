package com.zdh.frame.shiro.common.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>
 * model 额外类
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.26 10:10
 */
public abstract class Query {

    /**
     * 当前页码
     */
    @JSONField(serialize = false)
    private Integer pageIndex = 1;
    /**
     * 页面大小，默认20
     */
    @JSONField(serialize = false)
    private Integer pageSize = 20;
    /**
     * 行偏移
     */
    @JSONField(serialize = false)
    private Integer offset = 0;
    /**
     * 获取最大数量
     */
    @JSONField(serialize = false)
    private Integer limit;
    /**
     * 排序字段
     */
    @JSONField(serialize = false)
    private String orders;
    /**
     * 是否倒序，默认是
     */
    @JSONField(serialize = false)
    private Boolean isDesc = true;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (this.getPageIndex() - 1) * (this.getPageSize());
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        if (limit > 500) {
            limit = 500;
        }
        this.limit = limit;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public Boolean getDesc() {
        return isDesc;
    }

    public void setDesc(Boolean desc) {
        isDesc = desc;
    }
}
