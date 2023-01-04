package com.github.zshine.auth.controller.dto;


import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.common.valid.CustomValidator;
import com.github.zshine.common.valid.PatternEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class RoleDTO {


    @Data
    public static class Add {

        @ApiModelProperty(notes = "名称")
        @CustomValidator(name = "名称", max = 32, notnull = true)
        private String name;

        @ApiModelProperty(notes = "资源ID")
        @CustomValidator(name = "资源ID", max = 32, notnull = true)
        protected List<String> resourceIds;

        @ApiModelProperty(notes = "是否启用")
        @CustomValidator(name = "是否启用", notnull = true, enumClass = StatusEnum.class)
        protected Integer status;


    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Edit extends Add {

        @ApiModelProperty(notes = "角色ID")
        @CustomValidator(name = "角色ID", pattern = PatternEnum.NATURE, max = 4, notnull = true)
        private Integer id;

    }


    @Data
    public static class Delete {

        @ApiModelProperty(notes = "角色ID")
        @CustomValidator(name = "角色ID", pattern = PatternEnum.NATURE, max = 4, notnull = true)
        private Integer id;


    }
}
