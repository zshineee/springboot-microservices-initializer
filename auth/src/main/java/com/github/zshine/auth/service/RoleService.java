package com.github.zshine.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {


    /**
     * 分页查询
     *
     * @param page   页码
     * @param limit  查询条数
     * @param status 状态（非必填）
     * @return page pojo
     */
    Page<Role> page(Integer page, Integer limit, Integer status);

    /**
     * 获取所有角色ID和角色名称的映射
     *
     * @return map
     */
    Map<Integer, String> getRoleIdRoleNameMap();


    /**
     * 删除角色及关联的资源
     *
     * @param id 角色ID
     */
    void delete(Integer id);


    /**
     * 新增角色（并关联资源）
     *
     * @param name        角色名称
     * @param resourceIds 资源ID
     * @param status      状态（是否启用）
     */
    void add(String name, List<String> resourceIds, Integer status);


    /**
     * 修改角色（与相关资源）
     *
     * @param roleId      角色ID
     * @param name        角色名称
     * @param resourceIds 资源ID
     * @param status      状态（是否启用）
     */
    void edit(Integer roleId, String name, List<String> resourceIds, Integer status);
}
