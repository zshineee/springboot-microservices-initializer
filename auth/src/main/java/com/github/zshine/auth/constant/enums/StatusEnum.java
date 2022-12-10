package com.github.zshine.auth.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.zshine.common.exception.EnumMsg;

public enum StatusEnum implements EnumMsg {

    /**
     * 状态
     */
    EFFECT(1, "是"),
    FAIL(0, "否");

    @EnumValue
    private final int code;

    private final String description;

    StatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }



    @Override
    @JsonValue
    public String getCode() {
        return String.valueOf(code);
    }

    @Override
    public String getDescription() {
        return description;
    }


    public static StatusEnum getInstance(Integer status) {
        for (StatusEnum value : StatusEnum.values()) {
            if (value.code == status) {
                return value;
            }
        }

        throw new RuntimeException("找不到状态枚举");

    }
}
