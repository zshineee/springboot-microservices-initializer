package com.github.zshine.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.RoleDTO;
import com.github.zshine.auth.controller.vo.RoleVO;
import com.github.zshine.auth.domain.Role;
import com.github.zshine.auth.service.RoleService;
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


@Api(tags = "角色管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/role")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public PageJsonRsp<RoleVO> page(@ApiParam("查询页数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询页数") Integer page,
                                    @ApiParam("查询条数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询条数") Integer limit,
                                    @ApiParam("状态") @RequestParam(required = false) @CustomValidator(enumClass = StatusEnum.class, name = "状态") Integer status) {
        Page<Role> pageData = roleService.page(page, limit, status);
        return PageJsonRsp.ok(pageData.getRecords()
                .stream()
                .map(role -> RoleVO.getInstance(role.getId(), role.getName()))
                .collect(Collectors.toList()), pageData.getPages(), pageData.getTotal());
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public BaseJsonRsp add(@RequestBody @Valid RoleDTO.Add add) {
        roleService.add(add.getName(), add.getResourceIds(), add.getStatus());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/edit")
    public BaseJsonRsp edit(@RequestBody @Valid RoleDTO.Edit edit) {
        roleService.edit(edit.getId(), edit.getName(), edit.getResourceIds(), edit.getStatus());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public BaseJsonRsp delete(@RequestBody @Valid RoleDTO.Delete delete) {
        roleService.delete(delete.getId());
        return BaseJsonRsp.ok();
    }

}
