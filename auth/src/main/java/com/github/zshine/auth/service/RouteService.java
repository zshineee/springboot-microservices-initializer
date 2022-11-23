package com.github.zshine.auth.service;

import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.domain.Route;

import java.util.List;

public interface RouteService {


    /**
     * 更新状态
     *
     * @param id         主键
     * @param statusEnum 状态
     */
    void updateStatus(String id, StatusEnum statusEnum);


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
}
