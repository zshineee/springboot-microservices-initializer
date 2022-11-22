package com.github.zshine.auth.controller.vo;

import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.RouteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RouteVO extends RouteDTO {



    public static RouteVO getInstance(String id, String predicates, String filters, String uri, Integer orders, String description, StatusEnum statusEnum) {
        RouteVO routeVO = new RouteVO();
        routeVO.setId(id);
        routeVO.setPredicates(predicates);
        routeVO.setFilters(filters);
        routeVO.setUri(uri);
        routeVO.setOrders(orders);
        routeVO.setDescription(description);
        routeVO.setStatus(Integer.valueOf(statusEnum.getCode()));
        return routeVO;
    }
}
