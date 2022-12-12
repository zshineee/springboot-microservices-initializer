package com.github.zshine.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.domain.User;

public interface UserService {

    /**
     * 分页查询
     *
     * @param page   页码
     * @param limit  查询条数
     * @param status 状态（非必填）
     * @return page pojo
     */
    Page<User> page(Integer page, Integer limit, Integer status);



    /**
     * 根据ID删除（并校验是否为空，空值抛出异常，刷新网关）
     *
     * @param username 主键
     */
    void delete(String username);

    /**
     * 新增
     *
     * @param user POJO
     */
    void add(User user);

    /**
     * 修改
     *
     * @param user POJO
     */
    void edit(User user);

}
