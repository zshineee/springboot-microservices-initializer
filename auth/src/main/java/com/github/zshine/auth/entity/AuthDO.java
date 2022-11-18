package com.github.zshine.auth.entity;

import lombok.Data;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class AuthDO {

    private String id;

    private List<PredicateDefinition> predicates;

    private List<FilterDefinition> filters;

    private URI uri;

    private Integer order;


    @Data
    private static class PredicateDefinition {

        private String name;

        private Map<String, String> args;

    }

    @Data
    private static class FilterDefinition {

        private String name;

        private Map<String, String> args;

    }


    public static AuthBuilder builder() {
        return new AuthBuilder();
    }


    private static PredicateDefinition getPredicateDefinitionInstance(String name, Map<String, String> args) {
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName(name);
        predicateDefinition.setArgs(args);
        return predicateDefinition;
    }


    private static FilterDefinition getFilterDefinitionInstance(String name, Map<String, String> args) {
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName(name);
        filterDefinition.setArgs(args);
        return filterDefinition;
    }


    public static class AuthBuilder {

        private final Map<String, Map<String, String>> predicateMap = new LinkedHashMap<>();

        private final Map<String, Map<String, String>> filterMap = new LinkedHashMap<>();

        private final AuthDO authDO;

        private AuthBuilder() {
            authDO = new AuthDO();
        }

        public AuthDO build() {
            authDO.setId(UUID.randomUUID().toString());
            authDO.setPredicates(predicateMap.entrySet().stream()
                    .map(stringMapEntry -> AuthDO.getPredicateDefinitionInstance(stringMapEntry.getKey(),stringMapEntry.getValue()))
                    .collect(Collectors.toList()));
            authDO.setFilters(filterMap.entrySet().stream()
                    .map(stringMapEntry -> AuthDO.getFilterDefinitionInstance(stringMapEntry.getKey(),stringMapEntry.getValue()))
                    .collect(Collectors.toList()));
            return authDO;
        }

        public AuthBuilder url(String url) {
            authDO.setUri(URI.create(url));
            return this;
        }

        public AuthBuilder predicate(String name, String key, String value) {
            Map<String, String> map = predicateMap.getOrDefault(name, new LinkedHashMap<>(1));
            map.put(key, value);
            predicateMap.put(name, map);
            return this;
        }

        public AuthBuilder filter(String name, String key, String value) {
            Map<String, String> map = filterMap.getOrDefault(name, new LinkedHashMap<>(1));
            map.put(key, value);
            filterMap.put(name, map);
            return this;
        }

        public AuthBuilder order(Integer order) {
            authDO.setOrder(order);
            return this;
        }
    }
}
