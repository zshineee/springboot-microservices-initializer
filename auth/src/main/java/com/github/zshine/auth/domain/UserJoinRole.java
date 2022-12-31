package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_join_role")
public class UserJoinRole {

    private String username;

    private Integer roleId;
}
