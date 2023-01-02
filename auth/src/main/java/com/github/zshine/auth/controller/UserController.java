package com.github.zshine.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.UserDTO;
import com.github.zshine.auth.controller.vo.UserVO;
import com.github.zshine.auth.domain.User;
import com.github.zshine.auth.service.RoleService;
import com.github.zshine.auth.service.UserService;
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
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "用户管理")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user")
@Validated
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;


    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public PageJsonRsp<UserVO> page(@ApiParam("查询页数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询页数") Integer page,
                                    @ApiParam("查询条数") @RequestParam() @CustomValidator(pattern = PatternEnum.NATURE, name = "查询条数") Integer limit,
                                    @ApiParam("状态") @RequestParam(required = false) @CustomValidator(enumClass = StatusEnum.class, name = "状态") Integer status) {
        Page<User> pageData = userService.page(page, limit, status);
        Map<Integer, String> roleIdRoleNameMap = roleService.getRoleIdRoleNameMap();
        return PageJsonRsp.ok(pageData.getRecords()
                .stream()
                .map(user -> UserVO.getInstance(user.getUsername(), user.getSupper(), user.getStatus(), user.getFullname(), user.getRemark()
                        , user.getRoleIds(), roleIdRoleNameMap))
                .collect(Collectors.toList()), pageData.getPages(), pageData.getTotal());
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public BaseJsonRsp add(@RequestBody @Valid UserDTO.Add add) {
        userService.add(add.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/edit")
    public BaseJsonRsp edit(@RequestBody @Valid UserDTO.Edit edit) {
        userService.edit(edit.convert());
        return BaseJsonRsp.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public BaseJsonRsp delete(@RequestBody @Valid UserDTO.Delete delete) {
        userService.delete(delete.getUsername());
        return BaseJsonRsp.ok();
    }


}

