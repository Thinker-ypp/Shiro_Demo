package com.zdh.frame.shiro.common.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.29 18:01
 */
public class PermissionModel implements Serializable {

    private static final long serialVersionUID = 4512595624002047729L;

    /**
     * 权限id
     */
    @Id
    private Long id;

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型
     */
    private String type;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 资源父ID
     */
    private Long parentId;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 是否开启 0:关闭 1:开启
     */
    private Integer available;
    /**
     * 模块ID
     */
    private Long moduleId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
