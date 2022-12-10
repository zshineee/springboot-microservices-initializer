package com.github.zshine.auth.controller.vo;

import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends UserDTO.Edit {

    @ApiModelProperty(notes = "是否为超户")
    private Integer supper;


    public static UserVO getInstance(String username, StatusEnum supper, StatusEnum status, String fullname, String remark) {
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setSupper(Integer.valueOf(supper.getCode()));
        user.setStatus(Integer.valueOf(status.getCode()));
        user.setFullname(fullname);
        user.setRemark(remark);
        return user;
    }
}
