package com.github.zshine.auth.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleVO {


    @ApiModelProperty(notes = "id")
    private Integer id;

    @ApiModelProperty(notes = "名称")
    private String name;


    public static RoleVO getInstance(Integer id, String name) {
        RoleVO role = new RoleVO();
        role.setId(id);
        role.setName(name);
        return role;
    }
}
