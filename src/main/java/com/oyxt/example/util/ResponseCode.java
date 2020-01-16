package com.oyxt.example.util;

/**
 *
 * @author 20190712713
 * @date 2019/12/16 19:35
 */
public enum  ResponseCode {
    /**
     * 默认成功响应
     */
    success("200", "success"),
    /**
     * 默认失败响应
     */
    fail("500", "fail");

    public String code;
    public String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getEnumByCode(String code){
        if (null == code || "".equals(code)) {
            return null;
        }
        ResponseCode responseCode;

        for (int i = 0; i < ResponseCode.values().length; i++) {
            responseCode = ResponseCode.values()[i];
            if (responseCode.code.equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
