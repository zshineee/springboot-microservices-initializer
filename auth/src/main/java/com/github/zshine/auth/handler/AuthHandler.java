package com.github.zshine.auth.handler;

import cn.dev33.satoken.stp.StpInterface;
import com.github.zshine.auth.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class AuthHandler implements StpInterface {

    @Resource
    private UserService userService;


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userService.getResourceIdByUsername(loginId.toString());
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }


}