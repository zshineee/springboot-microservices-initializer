package com.github.zshine.gateway;

import com.github.zshine.gateway.handler.DynamicRouteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@Slf4j
public class GateWayApp {


    @Resource
    private DynamicRouteHandler dynamicRouteHandler;

    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class);
    }


    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.create(stringMonoSink -> stringMonoSink.success("health check"));
    }


    @Bean
    public Consumer<List<String>> route() {
        return route -> {
            log.info("广播网关消息：{}", route);
            dynamicRouteHandler.buildRoutes(route);
        };
    }

}
