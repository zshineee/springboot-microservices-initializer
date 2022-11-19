package com.github.zshine.auth.controller.dto;


import com.github.zshine.auth.domain.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class RouteDTO {

    @Data
    public static class Add {

        private String predicates;

        private String filters;

        private String uri;

        private Integer order;

        private String description;

        private Integer status;

        public Route convert() {
            return null;
        }

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Edit extends Add {

        private String id;

    }


}
