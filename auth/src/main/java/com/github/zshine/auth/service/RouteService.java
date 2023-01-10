package com.github.zshine.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zshine.auth.domain.Route;

import java.util.List;

public interface RouteService {


    /**
     * 分页查询
     *
     * @param page   页码
     * @param limit  查询条数
     * @param status 状态（非必填）
     * @return page pojo
     */
    Page<Route> page(Integer page, Integer limit, Integer status);


    /**
     * 根据ID删除（并校验是否为空，空值抛出异常，刷新网关）
     *
     * @param id 主键
     */
    void delete(String id);

    /**
     * 新增（并刷新网关）
     *
     * @param route POJO
     */
    void add(Route route);

    /**
     * 修改（并校验是否为空，空值抛出异常，刷新网关）
     *
     * @param route POJO
     */
    void edit(Route route);


    /**
     * 获取网关路由配置
     *
     * @return list
     */
    List<String> listRoutesString();

    /**
     * 刷新网关
     */
    void refresh();
}
