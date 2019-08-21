package com.zdh.frame.shiro.common.mybatis.mapper;

import com.zdh.frame.shiro.common.mybatis.provider.CriteriaProvider;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *     通用Mapper接口,Condition查询
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 16:04
 */
public interface SelectByCriteriaMapper<T> {

    /**
     * 根据Condition条件进行查询
     *
     * @param condition
     * @return
     */
    @SelectProvider(type = CriteriaProvider.class, method = "dynamicSQL")
    @ResultType(value = List.class)
    List<Map<String, Object>> selectByCriteria(Object condition);
}
