package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.vo.RouteVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("gateway_route")
public class Route {

    @TableId
    @ApiModelProperty(notes = "服务名")
    private String id;

    @ApiModelProperty(notes = "判定器")
    private String predicates;

    @ApiModelProperty(notes = "过滤器")
    private String filters;

    @ApiModelProperty(notes = "路径")
    private String uri;

    @ApiModelProperty(notes = "优先级")
    private Integer order;

    @ApiModelProperty(notes = "说明")
    private String description;

    @ApiModelProperty(notes = "状态")
    private StatusEnum statusEnum;


    public static Route getInstance(String id, String predicates, String filters, String uri, Integer order, String description, StatusEnum statusEnum) {
        Route route = new Route();
        route.setId(id);
        route.setPredicates(predicates);
        route.setFilters(filters);
        route.setUri(uri);
        route.setOrder(order);
        route.setDescription(description);
        route.setStatusEnum(statusEnum);
        return route;
    }

    public RouteVO convert() {
        return RouteVO.getInstance(id, predicates, filters, uri, order, description, statusEnum);
    }
}
