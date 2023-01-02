package com.github.zshine.auth.controller.vo;

import com.github.zshine.auth.constant.enums.StatusEnum;
import com.github.zshine.auth.controller.dto.UserDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends UserDTO.Edit {

    @ApiModelProperty(notes = "是否为超户")
    private Integer supper;

    @ApiModelProperty(notes = "角色")
    private List<RoleVO> roles;


    public static UserVO getInstance(String username, StatusEnum supper, StatusEnum status, String fullname, String remark, List<Integer> roleIds, Map<Integer, String> roleIdRoleNameMap) {
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setSupper(Integer.valueOf(supper.getCode()));
        user.setStatus(Integer.valueOf(status.getCode()));
        user.setFullname(fullname);
        user.setRemark(remark);
        if (!ObjectUtils.isEmpty(roleIds)) {
            user.setRoles(roleIds.stream()
                    .map(roleId -> RoleVO.getInstance(roleId, roleIdRoleNameMap.getOrDefault(roleId, "")))
                    .collect(Collectors.toList()));
        }
        return user;
    }
}
