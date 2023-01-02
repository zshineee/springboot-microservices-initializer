package com.github.zshine.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.domain.User;

public interface UserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    void login(String username, String password);

    /**
     * 分页查询用户信息（同时查询用户对应的角色）
     *
     * @param page   页码
     * @param limit  查询条数
     * @param status 状态（非必填）
     * @return page pojo
     */
    Page<User> page(Integer page, Integer limit, Integer status);


    /**
     * 根据ID删除用户信息（同时删除相关角色，校验用户是否为空，空值抛出异常，不能删除超户）
     *
     * @param username 主键
     */
    void delete(String username);

    /**
     * 新增用户信息（同时新增相关角色，校验用户是否为空，不为空抛出异常）
     *
     * @param user POJO
     */
    void add(User user);

    /**
     * 修改用户信息（同时删除并新增相关角色，校验用户是否为空，空值抛出异常）
     *
     * @param user POJO
     */
    void edit(User user);

}
