package com.github.zshine.auth.controller.dto;


import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.domain.Route;
import com.github.zshine.common.valid.CustomValidator;
import com.github.zshine.common.valid.PatternEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class RouteDTO {

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Post extends Id {

        @ApiModelProperty(notes = "判定器")
        @CustomValidator(name = "判定器", max = 512, notnull = true)
        private String predicates;

        @ApiModelProperty(notes = "过滤器")
        @CustomValidator(name = "过滤器", max = 512, notnull = true)
        private String filters;

        @ApiModelProperty(notes = "路径")
        @CustomValidator(name = "路径", max = 512, notnull = true)
        private String uri;

        @ApiModelProperty(notes = "优先级")
        @CustomValidator(name = "优先级", pattern = PatternEnum.ID_NUM, notnull = true)
        private Integer orders;

        @ApiModelProperty(notes = "说明")
        @CustomValidator(name = "说明", max = 512, notnull = true)
        private String description;

        @ApiModelProperty(notes = "状态")
        @CustomValidator(name = "状态", enumClass = StatusEnum.class, notnull = true)
        private Integer status;

        public Route convert() {
            return Route.getInstance(id, predicates, filters, uri, orders, description, StatusEnum.getInstance(status));
        }

    }
    @Data
    public static class Id {

        @ApiModelProperty(notes = "服务名")
        @CustomValidator(name = "服务名", max = 10, notnull = true)
        protected String id;


    }

}
