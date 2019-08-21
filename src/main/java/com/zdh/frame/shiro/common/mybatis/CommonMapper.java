package com.zdh.frame.shiro.common.mybatis;

import com.zdh.frame.shiro.common.mybatis.mapper.SelectByCriteriaMapper;
import com.zdh.frame.shiro.common.mybatis.mapper.criteria.SelectSliceByCriteriaMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 16:00
 */
public interface CommonMapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        RowBoundsMapper<T>,
        SelectByCriteriaMapper<T>,
        SelectSliceByCriteriaMapper<T>,
        Marker {
}
