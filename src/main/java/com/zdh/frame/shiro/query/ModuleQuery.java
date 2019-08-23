package com.zdh.frame.shiro.query;

import com.zdh.frame.shiro.common.persistence.criteria.QueryCriteria;
import com.zdh.frame.shiro.common.query.Query;
import com.zdh.frame.shiro.service.domain.admin.ModuleDomain;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 17:21
 */
public class ModuleQuery extends Query {

    private List<Long> idList;

    public ModuleQuery(List<Long> idList,boolean desc,String orderBy){
        super();
        this.idList = idList;
        this.setDesc(desc);
        this.setOrderBy(orderBy);
    }
    @Override
    public QueryCriteria toCriteria() {
        QueryCriteria queryCriteria = new QueryCriteria(ModuleDomain.class);
        Example.Criteria criteria = queryCriteria.createCriteria();
        if(valid(idList)){
            criteria.andIn("id",idList);
        }
        return queryCriteria;
    }

    public List<Long> getIdList() {
        return idList;
    }
}
