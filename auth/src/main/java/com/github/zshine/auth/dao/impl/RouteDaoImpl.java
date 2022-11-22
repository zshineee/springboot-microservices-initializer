package com.github.zshine.auth.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zshine.auth.dao.RouteDao;
import com.github.zshine.auth.mapper.RouteMapper;
import com.github.zshine.auth.domain.Route;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDaoImpl extends ServiceImpl<RouteMapper, Route> implements RouteDao {

}
