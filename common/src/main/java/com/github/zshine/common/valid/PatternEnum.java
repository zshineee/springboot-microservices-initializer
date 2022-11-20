package com.github.zshine.common.valid;


public enum PatternEnum {

    /**
     * 正则校验
     */
    NULL("null", "null"),
    POSITIVE("^[1-9]\\d*$", "正整数"),
    NATURE("^0|[1-9]\\d*$", "自然数"),
    ID_NUM("^\\d+$", "数字编号"),
    ID_ENG_NUM("^[A-Za-z0-9]+$", "字母和数字编号"),
    UUID("^[a-z\\-]+$", "UUID"),
    NUMBER("^\\d+(\\.\\d+)?$", "非负浮点数"),
    SORT("^ASC|DESC|asc|desc$", "排序"),
    YYYY_MM_DD("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", "日期"),
    YYYYMMDD("^[0-9]{4}[0-9]{2}[0-9]{2}$", "日期");

    final String pattern;

    final String description;

    PatternEnum(String pattern, String description) {
        this.pattern = pattern;
        this.description = description;
    }
}
