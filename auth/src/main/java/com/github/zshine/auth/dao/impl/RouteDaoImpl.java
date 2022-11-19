package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.RouteDao;
import com.github.zshine.auth.domain.Route;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDaoImpl extends ServiceImpl<RouteDaoImpl.RouteMapper, Route> implements RouteDao {




    public interface RouteMapper extends BaseMapper<Route> {

    }
}
