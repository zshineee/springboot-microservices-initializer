package com.github.zshine.auth.controller.dto;

import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.domain.User;
import com.github.zshine.common.valid.CustomValidator;
import com.github.zshine.common.valid.PatternEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class UserDTO {

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Add extends Edit {

        @ApiModelProperty(notes = "密码")
        @CustomValidator(name = "密码", notnull = true, max = 32)
        private String password;


    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Edit extends Delete {

        @ApiModelProperty(notes = "是否启用")
        @CustomValidator(name = "是否启用", notnull = true, enumClass = StatusEnum.class)
        protected Integer status;

        @ApiModelProperty(notes = "全称")
        @CustomValidator(name = "全称", notnull = true, max = 64)
        protected String fullname;

        @ApiModelProperty(notes = "说明")
        @CustomValidator(name = "说明", max = 512)
        protected String remark;

        @ApiModelProperty(notes = "角色ID")
        @CustomValidator(name = "角色ID", max = 4, pattern = PatternEnum.NATURE)
        protected List<Integer> roleIds;

        public User convert() {
            return User.getInstance(username, null, StatusEnum.getInstance(status), fullname, remark, roleIds);
        }

    }

    @Data
    public static class Delete {

        @ApiModelProperty(notes = "用户名")
        @CustomValidator(name = "用户名", max = 32, notnull = true)
        protected String username;


    }


    @Data
    public static class Login {

        @ApiModelProperty(notes = "用户名")
        @CustomValidator(name = "用户名", max = 32, notnull = true)
        protected String username;


        @ApiModelProperty(notes = "密码")
        @CustomValidator(name = "密码", max = 32, notnull = true)
        protected String password;

    }

}
