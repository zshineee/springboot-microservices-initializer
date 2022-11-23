package com.github.zshine.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "auth")
public interface RouteFeignClient {

    /**
     * 获取网关路由配置
     *
     * @return list
     */
    @GetMapping("auth/route/routes")
    List<String> listRoutesString();
}
