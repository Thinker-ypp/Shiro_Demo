package com.zdh.frame.shiro.common.enums;

/**
 * 公共组件模块错误信息
 *
 * @author yupanpan
 * @version 1.0.0
 * @since JKD 1.8
 */
public enum CommonErrors {

    // 40001XXXX
    DATE_PARSE_ERROR(400010103, "解析成日期对象发生错误");

    private int code;

    private String message;

    private CommonErrors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    }
