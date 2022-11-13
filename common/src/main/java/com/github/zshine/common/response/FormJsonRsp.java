package com.github.zshine.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormJsonRsp<T> extends BaseJsonRsp {

    private T data;


    public static <T> FormJsonRsp<T> ok(T data) {
        FormJsonRsp<T> formJsonRsp = new FormJsonRsp<>();
        formJsonRsp.errMsg = "";
        formJsonRsp.code = RspCode.ok.getCode();
        formJsonRsp.success = true;
        formJsonRsp.data = data;
        return formJsonRsp;
    }


    public static <T> FormJsonRsp<T> fail(String errMsg) {
        FormJsonRsp<T> formJsonRsp = new FormJsonRsp<>();
        formJsonRsp.errMsg = errMsg;
        formJsonRsp.code = RspCode.fail.getCode();
        formJsonRsp.success = false;
        return formJsonRsp;
    }
}