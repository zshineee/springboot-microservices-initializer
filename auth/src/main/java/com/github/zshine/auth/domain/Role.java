package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zshine.auth.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "role")
public class Role {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "名称")
    private String name;

    @ApiModelProperty(notes = "是否启用")
    private StatusEnum status;


    public static Role getInstance(String name, Integer status) {
        Role role = new Role();
        role.setName(name);
        role.setStatus(StatusEnum.getInstance(status));
        return role;
    }
}
