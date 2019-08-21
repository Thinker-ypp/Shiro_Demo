package com.zdh.frame.shiro.common.enums;

/**
 * <p>
 *     状态 枚举
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:36
 */
public enum EnableDisableEnum {

    /**
     * 状态
     */
    ENABLE("ENABLE","启用"),
    DISABLE("DISABLE","禁用");

    private String code;
    private String desc;

    EnableDisableEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
