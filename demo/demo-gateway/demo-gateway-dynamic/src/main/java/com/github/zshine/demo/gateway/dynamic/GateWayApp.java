package com.github.zshine.demo.gateway.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class GateWayApp {

    @Resource
    private DynamicRouteHandler dynamicRouteHandler;


    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class);
    }


    @GetMapping("/refresh")
    public Mono<String> refresh() {
        dynamicRouteHandler.save();
        return Mono.create(stringMonoSink -> stringMonoSink.success("true"));
    }



}
