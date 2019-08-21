package com.zdh.frame.shiro.service.domain.admin;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     模块对象 实体类
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 10:11
 */
@Table(name = "MODULE")
public class ModuleDomain implements Serializable {

    private static final long serialVersionUID = -1687607263778885918L;

    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块名称
     */
    private String description;

    /**
     * 模块名称
     */
    private String label;

    /**
     * 状态代码 ENABLE：启用 DISABLE：禁用
     */
    private String statusCode;

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 排序字段
     */
    private Long sorting;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getSorting() {
        return sorting;
    }

    public void setSorting(Long sorting) {
        this.sorting = sorting;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
