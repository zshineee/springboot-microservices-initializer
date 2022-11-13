package com.github.zshine.common.response;

public enum RspCode {

    //接口返回正确
    ok(1),
    //接口返回异常
    fail(-1);

    private final Integer code;

    RspCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
