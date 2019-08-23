package com.zdh.frame.shiro.service.mapper;

import com.zdh.frame.shiro.common.persistence.Mapper;
import com.zdh.frame.shiro.service.domain.admin.PermissionDomain;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 16:18
 */
public interface IPermissionMapper extends Mapper<PermissionDomain> {
    /**
     * mapper 文件需要写 sql
     * @author yupanpan
     * @return
     */
    @Override
    Long getMaxId();
}
