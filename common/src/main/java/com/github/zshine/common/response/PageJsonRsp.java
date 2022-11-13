package com.github.zshine.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageJsonRsp<T> extends BaseJsonRsp {

    public Long total;

    private Long pages;

    private List<T> data;

    public static <T> PageJsonRsp<T> ok(List<T> data, Long pages, Long total) {
        PageJsonRsp<T> pageJsonRsp = new PageJsonRsp<>();
        pageJsonRsp.errMsg = "";
        pageJsonRsp.code = RspCode.ok.getCode();
        pageJsonRsp.success = true;
        pageJsonRsp.data = data;
        pageJsonRsp.total = total;
        pageJsonRsp.pages = pages;
        return pageJsonRsp;
    }

    public static <T> PageJsonRsp<T> fail(String errMsg) {
        PageJsonRsp<T> pageJsonRsp = new PageJsonRsp<>();
        pageJsonRsp.errMsg = errMsg;
        pageJsonRsp.code = RspCode.fail.getCode();
        pageJsonRsp.success = false;
        return pageJsonRsp;
    }

}

