package com.zdh.frame.shiro.service.service.impl;

import com.zdh.frame.shiro.common.service.impl.BaseServiceImpl;
import com.zdh.frame.shiro.service.domain.admin.ModuleDomain;
import com.zdh.frame.shiro.service.mapper.IModuleMapper;
import com.zdh.frame.shiro.service.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 14:42
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleDomain> implements IModuleService {
    @Autowired
    private IModuleMapper moduleMapper;
}
