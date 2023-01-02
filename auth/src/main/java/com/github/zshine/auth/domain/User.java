package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@TableName("user")
public class User {

    @TableId
    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "密码")
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String password;

    @ApiModelProperty(notes = "是否为超户")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private StatusEnum supper;

    @ApiModelProperty(notes = "是否启用")
    private StatusEnum status;

    @ApiModelProperty(notes = "全称")
    private String fullname;

    @ApiModelProperty(notes = "随机数")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String random;

    @ApiModelProperty(notes = "说明")
    private String remark;

    @ApiModelProperty(notes = "角色")
    @TableField(exist = false)
    private List<Integer> roleIds;


    public static User getInstance(String username, String password, StatusEnum status, String fullname, String remark, List<Integer> roleIds) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(status);
        user.setFullname(fullname);
        user.setRemark(remark);
        user.setSupper(StatusEnum.FAIL);
        user.setRandom(UUID.randomUUID().toString());
        user.setRoleIds(roleIds);
        return user;
    }

    public UserVO convert(Map<Integer, String> roleIdRoleNameMap) {
        return UserVO.getInstance(username, supper, status, fullname, remark, roleIds, roleIdRoleNameMap);
    }

}
