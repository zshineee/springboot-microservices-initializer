package com.github.zshine.gateway;

import com.github.zshine.gateway.feign.RouteFeignClient;
import com.github.zshine.gateway.handler.DynamicRouteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@Slf4j
@EnableFeignClients
public class GateWayApp implements CommandLineRunner {

    @Resource
    private RouteFeignClient routeFeignClient;

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

    @Override
    public void run(String... args) {
        log.info("加载路由...");
        try {
            dynamicRouteHandler.buildRoutes(routeFeignClient.listRoutesString());
        } catch (Exception e) {
            log.error("加载路由失败...");
            e.printStackTrace();
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
