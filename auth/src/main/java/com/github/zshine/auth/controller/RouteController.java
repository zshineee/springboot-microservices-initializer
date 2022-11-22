package com.github.zshine.auth.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
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
import com.github.zshine.common.valid.CustomValidator;
import com.github.zshine.common.valid.PatternEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;


@Api(tags = "路由管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/route")
@Validated
public class RouteController {

    @Resource
    private RouteService routeService;

    @Resource
    private RouteDao routeDao;


    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public PageJsonRsp<RouteVO> page(@ApiParam("查询页数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询页数") Integer page,
                                     @ApiParam("查询条数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询条数") Integer limit,
                                     @ApiParam("状态") @RequestParam(required = false) @CustomValidator(enumClass = StatusEnum.class, name = "状态") Integer status) {
        Page<Route> pageData = routeDao.page(new Page<>(page, limit), Wrappers.<Route>lambdaQuery()
                .eq(!ObjectUtils.isEmpty(status), Route::getStatus, status));
        return PageJsonRsp.ok(pageData.getRecords()
                .stream()
                .map(Route::convert)
                .collect(Collectors.toList()), pageData.getPages(), pageData.getTotal());
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public BaseJsonRsp add(@Valid RouteDTO add) {
        routeService.add(add.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/edit")
    public BaseJsonRsp edit(@Valid RouteDTO edit) {
        routeService.edit(edit.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public BaseJsonRsp delete(@ApiParam("id") @RequestParam() @CustomValidator(name = "服务名", max = 10) String id) {
        routeService.delete(id);
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "生效")
    @PutMapping("/effect")
    public BaseJsonRsp effect(@ApiParam("id") @RequestParam() @CustomValidator(name = "服务名", max = 10) String id) {
        routeService.updateStatus(id, StatusEnum.EFFECT);
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "失效")
    @PutMapping("/fail")
    public BaseJsonRsp fail(@ApiParam("id") @RequestParam() @CustomValidator(name = "服务名", max = 10) String id) {
        routeService.updateStatus(id, StatusEnum.FAIL);
        return BaseJsonRsp.ok();
    }

}
