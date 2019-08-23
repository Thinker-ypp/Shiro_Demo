package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;
import tk.mybatis.mapper.entity.Example;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:59
 */
public class PermissionQuery extends Query {

    private Long id;
    /*权限类型*/
    private String type;

    /*模块ID*/
    private Long moduleId;

    private Long parentId;

    private String name;

    private String url;
    /**
     * 是否开启 0:关闭 1:开启
     */
    private Integer available;

    public PermissionQuery(){

    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(PermissionDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(id)){
            criteria.andEqualTo("id",id);
        }
        if(valid(parentId)){
            criteria.andEqualTo("parentId",parentId);
        }
        if(valid(name)){
            criteria.andLike("name","%" + name + "%");
        }
        if(valid(type)){
            criteria.andEqualTo("type",type);
        }
        if (valid(available)){
            criteria.andEqualTo("available",available);
        }
        if (valid(url)){
            criteria.andEqualTo("url",url);
        }

        return queryCriteria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
