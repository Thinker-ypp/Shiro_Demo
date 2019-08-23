package com.zdh.frame.shiro.common.query;

import com.zdh.frame.shiro.common.enums.IEnum;
import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *     查询对象基类
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 14:45
 */
public abstract class Query {
    /**
     * 当前页码
     */
    private Integer pageIndex = 1;
    /**
     *页面大小，默认20
     */
    private Integer pageSize = 20;
    /**
     *行偏移
     */
    private Integer offset = 0;
    /**
     *获取最大数量
     */
    private Integer limit;
    /**
     *排序字段
     */
    private String orderBy;
    /**
     *是否倒序，默认是
     */
    private Boolean isDesc = true;
    /**
     *通用查询条件
     */
    private QueryCriteria queryCriteria;

    /**
     * 获取当前页码
     *
     * @return
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置当前页码
     *
     * @param pageIndex 当前页码
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小
     *
     * @param pageSize 页面大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取查询排序字段
     *
     * @return
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置查询排序字段
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 是否倒排序
     *
     * @return
     */
    public Boolean getDesc() {
        return isDesc;
    }

    /**
     * 设置是否倒排序
     *
     * @param desc
     */
    public void setDesc(Boolean desc) {
        isDesc = desc;
    }


    public Integer getOffset() {
        return offset;
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

    /**
     * 验证Integer值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(Integer value) {
        return value != null;
    }

    /**
     * 验证Long值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(Long value) {
        return value != null;
    }

    /**
     * 验证Double值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(Double value) {
        return value != null;
    }

    /**
     * 验证String值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(String value) {
        return StringUtils.isNotBlank(value);
    }

    /**
     * 验证Date值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(Date value) {
        return value != null;
    }

    /**
     * 验证List<?>值是否有效
     *
     * @param
     * @return
     */
    protected boolean valid(List<?> list) {
        return list != null && list.size() > 0;
    }

    /**
     * 验证Object[]值是否有效
     *
     * @param
     * @return
     */
    protected boolean valid(Object[] objects) {
        return objects != null && objects.length > 0;
    }

    /**
     * 验证Boolean值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(Boolean value) {
        return value != null;
    }

    /**
     * 验证自定义枚举值是否有效
     *
     * @param value
     * @return
     */
    protected boolean valid(IEnum value) {
        return value != null;
    }

    /**
     * 把查询对象转换为Dao层所需的查询条件对象
     *
     * @return
     */
    public abstract QueryCriteria toCriteria();

    public QueryCriteria getQueryCriteria() {
        return queryCriteria;
    }
}
