package com.zdh.frame.shiro.common.enums;

/**
 * <p>
 *     权限类型枚举
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 11:19
 */
public enum PermissionTypeEnum {
    /**
     * 资源类型 【点击、菜单、按钮】
     */
    CLICK("click"),
    MENU("menu"),
    BUTTON("button");

    PermissionTypeEnum(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }
}
