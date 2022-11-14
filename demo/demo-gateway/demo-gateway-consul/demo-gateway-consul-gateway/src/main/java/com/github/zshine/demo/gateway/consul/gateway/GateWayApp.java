package com.github.zshine.demo.gateway.consul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class GateWayApp {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/server1/*")
                        .uri("lb://server1/*"))
                .route("path_route", r -> r.path("/server2/*")
                        .uri("lb://server2/*"))
                .build();

    }

    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.create(stringMonoSink -> stringMonoSink.success("health check"));
    }


}
