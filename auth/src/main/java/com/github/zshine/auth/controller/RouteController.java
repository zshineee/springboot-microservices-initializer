package com.github.zshine.auth.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.RouteDTO;
import com.github.zshine.auth.controller.vo.RouteVO;
import com.github.zshine.auth.dao.RouteDao;
import com.github.zshine.auth.domain.Route;
import com.github.zshine.auth.service.RouteService;
import com.github.zshine.common.response.BaseJsonRsp;
import com.github.zshine.common.response.PageJsonRsp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;


@RestController
@RequestMapping("route")
public class RouteController {

    @Resource
    private RouteService routeService;

    @Resource
    private RouteDao routeDao;


    @GetMapping("/page")
    public PageJsonRsp<RouteVO> page(Integer page, Integer limit, int status) {
        Page<Route> pageData = routeDao.page(new Page<>(page, limit), Wrappers.<Route>lambdaQuery()
                .eq(Route::getStatusEnum, status));
        return PageJsonRsp.ok(pageData.getRecords()
                .stream()
                .map(Route::convert)
                .collect(Collectors.toList()), pageData.getPages(), pageData.getTotal());
    }

    @PostMapping("/add")
    public BaseJsonRsp add(RouteDTO.Add add) {
        routeService.add(add.convert());
        return BaseJsonRsp.ok();
    }

    @PutMapping("/edit")
    public BaseJsonRsp edit(RouteDTO.Edit edit) {
        routeService.edit(edit.convert());
        return BaseJsonRsp.ok();
    }

    @DeleteMapping("/delete")
    public BaseJsonRsp delete(String id) {
        routeService.delete(id);
        return BaseJsonRsp.ok();
    }

    @PutMapping("/effect")
    public BaseJsonRsp effect(String id) {
        routeService.updateStatus(id, StatusEnum.EFFECT);
        return BaseJsonRsp.ok();
    }

    @PutMapping("/fail")
    public BaseJsonRsp fail(String id) {
        routeService.updateStatus(id, StatusEnum.FAIL);
        return BaseJsonRsp.ok();
    }

}
