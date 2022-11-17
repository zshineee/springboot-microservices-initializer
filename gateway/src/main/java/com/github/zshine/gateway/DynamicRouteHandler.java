package com.github.zshine.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DynamicRouteHandler implements RouteDefinitionRepository, ApplicationEventPublisherAware {

    private List<RouteDefinition> routeDefinitions = new ArrayList<>();
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeDefinitions);
    }

    /**
     * 构建网关
     */
    public void buildRoutes(String str) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinitions = objectMapper.readValue(str, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("解析网关数据异常: {}", e.getMessage());
        }

        if (routeDefinitions.isEmpty()) {
            log.error("动态网关数据为空");
        } else {
            //通知刷新网关
            applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        }
    }


    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
