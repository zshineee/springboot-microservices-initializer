package com.github.zshine.auth.handler;

import com.github.zshine.auth.service.RouteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 程序启动时刷新网关
 */
@Component
public class RouteStartRunner implements CommandLineRunner {

    @Resource
    private RouteService routeService;


    @Override
    public void run(String... args) {
        routeService.refresh();
    }
}
