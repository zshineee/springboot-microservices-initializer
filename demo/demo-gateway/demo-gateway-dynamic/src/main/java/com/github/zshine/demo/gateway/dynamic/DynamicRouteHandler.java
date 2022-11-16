package com.github.zshine.demo.gateway.dynamic;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

@Service
public class DynamicRouteHandler implements RouteDefinitionRepository, ApplicationEventPublisherAware {

    Map<String, RouteDefinition> stringStringMap = new HashMap<>();
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        System.out.println("getRouteDefinitions:" + stringStringMap.keySet());
        return Flux.fromIterable(stringStringMap.values());
    }

    public void save() {
        RouteDefinition routeDefinition1 = this.create("http://127.0.0.1:8081/*", "/server1/*", "1");
        RouteDefinition routeDefinition2 = this.create("http://127.0.0.1:8082/*", "/server2/*", "1");
        stringStringMap.put(routeDefinition1.getId(), routeDefinition1);
        stringStringMap.put(routeDefinition2.getId(), routeDefinition2);
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    private RouteDefinition create(String url, String path, String filter) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(UUID.randomUUID().toString());
        routeDefinition.setUri(URI.create(url));

        List<FilterDefinition> filters = new ArrayList<>();
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("StripPrefix");
        Map<String, String> filterDefinitionMap = new HashMap<>();
        filterDefinitionMap.put("parts", filter);
        filterDefinition.setArgs(filterDefinitionMap);
        filters.add(filterDefinition);
        routeDefinition.setFilters(filters);

        List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName("Path");
        Map<String, String> predicateDefinitionMap = new HashMap<>();
        predicateDefinitionMap.put("pattern", path);
        predicateDefinition.setArgs(predicateDefinitionMap);
        predicateDefinitions.add(predicateDefinition);
        routeDefinition.setPredicates(predicateDefinitions);
        return routeDefinition;
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
