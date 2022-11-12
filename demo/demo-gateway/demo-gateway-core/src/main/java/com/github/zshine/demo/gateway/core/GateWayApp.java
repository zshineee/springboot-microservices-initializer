package com.github.zshine.demo.gateway.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GateWayApp {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/server1/*")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1))
                        .uri("http://127.0.0.1:8081/*"))
                .route("path_route", r -> r.path("/server2/*")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1))
                        .uri("http://127.0.0.1:8082/*"))
                .build();

    }


}
