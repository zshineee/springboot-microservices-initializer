package com.github.zshine.common.response;

import lombok.Data;

@Data
public class BaseJsonRsp {

    protected Boolean success;

    protected Integer code;

    protected String errMsg;


    public static BaseJsonRsp ok() {
        BaseJsonRsp baseJsonRsp = new BaseJsonRsp();
        baseJsonRsp.code = RspCode.ok.getCode();
        baseJsonRsp.success = true;
        return baseJsonRsp;
    }

}
