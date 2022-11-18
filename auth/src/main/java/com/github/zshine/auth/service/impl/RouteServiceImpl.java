package com.github.zshine.auth.service.impl;

import com.github.zshine.auth.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Resource
    private StreamBridge streamBridge;


    private void refresh(List<String> routes) {

        log.info("广播网关消息：{}", routes);
        boolean response = streamBridge.send("route-out-0", routes);
        if (!response) {
            log.error("广播网关消息失败");
        }
    }


}
