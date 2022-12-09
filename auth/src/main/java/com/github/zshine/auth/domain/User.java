package com.github.zshine.auth.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zshine.auth.constant.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.bouncycastle.crypto.Digest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

@Data
@TableName("user")
public class User {

    @TableId
    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "是否为超户")
    private Integer supper;

    @ApiModelProperty(notes = "是否启用")
    private StatusEnum status;

    @ApiModelProperty(notes = "全称")
    private String fullname;

    @ApiModelProperty(notes = "随机数")
    private String random;

    @ApiModelProperty(notes = "说明")
    private String remark;

}
