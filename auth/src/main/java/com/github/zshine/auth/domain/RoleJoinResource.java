package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "role_join_resource")
public class RoleJoinResource {

    @ApiModelProperty(notes = "角色ID")
    private Integer roleId;

    @ApiModelProperty(notes = "资源ID")
    private String resourceId;

}
