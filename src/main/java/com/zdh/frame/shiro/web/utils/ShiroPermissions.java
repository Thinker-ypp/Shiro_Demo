package com.zdh.frame.shiro.web.utils;

import com.zdh.frame.shiro.common.enums.PermissionAvailableTypeEnum;
import com.zdh.frame.shiro.common.enums.PermissionTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 权限注解
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.29 17:16
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShiroPermissions {

    /**
     * 菜单名称
     *
     */
    String name() default "";

    /**
     * 菜单类型
     */
    PermissionTypeEnum type() default PermissionTypeEnum.MENU;

    /**
     * 父级权限字符串
     */
    String parentPermissions() default "";

    /**
     * 模块标签
     */
    String moduleLabel() default "";

    /**
     * 权限字符串
     */
    String permissions() default "";

    /**
     * 资源是否可用
     */
    PermissionAvailableTypeEnum available() default PermissionAvailableTypeEnum.ON;
}
