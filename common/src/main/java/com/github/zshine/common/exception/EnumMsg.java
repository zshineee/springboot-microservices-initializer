package com.github.zshine.common.exception;


public interface EnumMsg {

    /**
     * 枚举的值
     *
     * @return int
     */
    Integer getCode();

    /**
     * 枚举的描述
     *
     * @return string
     */
    String getDescription();
}
