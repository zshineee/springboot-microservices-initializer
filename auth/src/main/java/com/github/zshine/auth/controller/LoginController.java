package com.github.zshine.auth.controller;

import com.github.zshine.auth.controller.dto.UserDTO;
import com.github.zshine.auth.service.UserService;
import com.github.zshine.common.response.BaseJsonRsp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "登录")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/login")
@Validated
public class LoginController {

    @Resource
    private UserService userService;


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public BaseJsonRsp login(@RequestBody @Valid UserDTO.Login login) {
        userService.login(login.getUsername(), login.getPassword());
        return BaseJsonRsp.ok();
    }

}
