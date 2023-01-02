package com.github.zshine.auth.service.impl;

import com.github.zshine.auth.dao.RoleDao;
import com.github.zshine.auth.domain.Role;
import com.github.zshine.auth.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;


    @Override
    public Map<Integer, String> getRoleIdRoleNameMap() {
        return roleDao.list()
                .stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));
    }
}
