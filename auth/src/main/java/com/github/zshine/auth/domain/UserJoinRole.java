package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_join_role")
public class UserJoinRole {

    private String username;

    private Integer roleId;


    public static UserJoinRole getInstance(String username, Integer roleId) {
        UserJoinRole userJoinRole = new UserJoinRole();
        userJoinRole.setRoleId(roleId);
        userJoinRole.setUsername(username);
        return userJoinRole;
    }
}
