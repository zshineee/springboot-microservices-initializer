package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.vo.RouteVO;
import lombok.Data;

@Data
@TableName("gateway_route")
public class Route {

    @TableId
    private String id;

    private String predicates;

    private String filters;

    private String uri;

    private Integer order;

    private String description;

    private StatusEnum statusEnum;


    public RouteVO convert() {
        return null;
    }
}
