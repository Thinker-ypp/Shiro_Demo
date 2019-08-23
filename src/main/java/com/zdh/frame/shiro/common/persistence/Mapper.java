package com.zdh.frame.shiro.common.persistence;

import com.zdh.frame.shiro.common.mybatis.CommonMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *      通用Mapper基类
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 15:57
 */
@Component
public interface Mapper<T> extends CommonMapper<T> {

    /**
     * 获取最大Id
     *
     * @author yupanpan
     */
    Long getMaxId();
}
