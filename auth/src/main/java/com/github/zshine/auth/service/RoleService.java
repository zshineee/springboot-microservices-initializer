package com.github.zshine.auth.service;

import java.util.Map;

public interface RoleService {

    /**
     * 获取所有角色ID和角色名称的映射
     *
     * @return map
     */
    Map<Integer, String> getRoleIdRoleNameMap();
}
