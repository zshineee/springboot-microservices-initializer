package com.github.zshine.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.RouteDTO;
import com.github.zshine.auth.controller.vo.RouteVO;
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
import java.util.List;
import java.util.stream.Collectors;


@Api(tags = "路由管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/route")
@Validated
public class RouteController {

    @Resource
    private RouteService routeService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public PageJsonRsp<RouteVO> page(@ApiParam("查询页数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询页数") Integer page,
                                     @ApiParam("查询条数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询条数") Integer limit,
                                     @ApiParam("状态") @RequestParam(required = false) @CustomValidator(enumClass = StatusEnum.class, name = "状态") Integer status) {
        Page<Route> pageData = routeService.page(page, limit, status);
        return PageJsonRsp.ok(pageData.getRecords()
                .stream()
                .map(Route::convert)
                .collect(Collectors.toList()), pageData.getPages(), pageData.getTotal());
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public BaseJsonRsp add(@RequestBody @Valid RouteDTO.Post add) {
        routeService.add(add.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/edit")
    public BaseJsonRsp edit(@RequestBody @Valid RouteDTO.Post edit) {
        routeService.edit(edit.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public BaseJsonRsp delete(@RequestBody @Valid RouteDTO.Id id) {
        routeService.delete(id.getId());
        return BaseJsonRsp.ok();
    }


    @ApiOperation(value = "获取网关路由配置", hidden = true)
    @GetMapping("routes")
    public List<String> listRoutesString() {
        return routeService.listRoutesString();
    }
}
