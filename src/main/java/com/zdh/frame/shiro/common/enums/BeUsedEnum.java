package com.zdh.frame.shiro.common.enums;

/**
 * <p></p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.21 14:41
 */
public enum BeUsedEnum {
    /**
     * 是否可用
     */
    NOT_USED(0,"不可用"),
    BE_USED(1,"可用");

    private Integer code;
    private String desc;

    BeUsedEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
