package com.zdh.frame.shiro.common.enums;

/**
 * <p>
 *      权限资源 状态 枚举
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.29 17:21
 */
public enum PermissionAvailableTypeEnum {

    /**
     *
     */
    ON(1,"启用"),
    OFF(0,"关闭")
    ;

    private Integer code;
    private String desc;

    PermissionAvailableTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
