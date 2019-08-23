package com.zdh.frame.shiro.common.enums;

/**
 * <p>
 *      角色权限是否开启枚举
 * </p>
 *
 * @Author Yupanpan
 * @Since JDK 1.8
 * @Version 1.0.0
 * @Date 2019.08.23 15:35
 */
public enum RoleAvailableEnum {

    /**
     *
     */
    SHUT_DOWN(0, "关闭"),
    OPEN(1, "开启");

    private Integer code;
    private String desc;

    RoleAvailableEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取value值
     * @author : zhuxueke
     * @since : 2018/3/15 9:16
     */
    public static RoleAvailableEnum valueOf(int index){
        for (RoleAvailableEnum type:values()){
            if (type.getCode() == index) {
                return type;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
