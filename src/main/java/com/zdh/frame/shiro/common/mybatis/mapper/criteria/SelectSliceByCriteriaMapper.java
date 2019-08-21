package com.zdh.frame.shiro.common.mybatis.mapper.criteria;

import com.zdh.frame.shiro.common.mybatis.provider.CriteriaProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 16:08
 */
public interface SelectSliceByCriteriaMapper<T> {
    /**
     * 根据Condition条件进行查询
     * @param condition
     * @return
     */
    @SelectProvider(type = CriteriaProvider.class, method = "dynamicSQL")
    List<T> selectSliceByCriteria(Object condition);
}
